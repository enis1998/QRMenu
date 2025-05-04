package com.qrmenu.controller.Admin;


import com.qrmenu.controller.Admin.Contract.AdminContract;
import com.qrmenu.dto.Category.responses.CategoryResponseDto;
import com.qrmenu.dto.Product.requests.ProductRequestDto;
import com.qrmenu.dto.Product.responses.ProductResponseDto;
import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.service.AdminUserService;
import com.qrmenu.service.CategoryService;
import com.qrmenu.service.ChangeRequestService;
import com.qrmenu.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AdminUserService adminUserService;
    private final ChangeRequestService changeRequestService;
    private final AdminContract adminContract;

    public AdminController(ProductService productService,
                           CategoryService categoryService, AdminUserService adminUserService, ChangeRequestService changeRequestService, AdminContract adminContract) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.adminUserService = adminUserService;
        this.changeRequestService = changeRequestService;
        this.adminContract = adminContract;
    }
    @GetMapping("/dashboard")
    public String panel(Model model, Authentication auth) {
        model.addAttribute("users", adminUserService.countAll());
        model.addAttribute("products", productService.countAll());

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            model.addAttribute("pendingUpdates", changeRequestService.countByPending());
        }
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product>  allProductsList  = productService.findAll();
        Map<UUID,List<ProductResponseDto>> productsByCategoryMap = adminContract.findByCategory();

        List<Product> unassignedProducts = allProductsList.stream()
                .filter(p -> p.getCategory() == null)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        model.addAttribute("productsByCategory", productsByCategoryMap);
        model.addAttribute("allProducts", allProductsList);
        model.addAttribute("unassignedProducts", unassignedProducts);
        model.addAttribute("product", new ProductResponseDto());
        model.addAttribute("category", new CategoryResponseDto());
        return "admin/product-list";
    }

    @PostMapping("/products")
    public String saveProduct(
            @ModelAttribute("product") ProductRequestDto productRequestDto,
            @RequestParam("imageFile") MultipartFile imageFile
    ) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            productRequestDto.setImage(imageFile.getBytes());
            productRequestDto.setImageContentType(imageFile.getContentType());
        }
        adminContract.saveProduct(productRequestDto);

        return "redirect:/admin/products";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable UUID id,
                                @ModelAttribute("product") ProductRequestDto productRequestDto,
                                @RequestParam("imageFile") MultipartFile imageFile,
                                BindingResult br) {
        if (br.hasErrors()) {
            return "admin/product-list";
        }

        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                productRequestDto.setImage(imageFile.getBytes());
                productRequestDto.setImageContentType(imageFile.getContentType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        adminContract.updateProduct(id, productRequestDto);

        return "redirect:/admin/products";
    }

    @GetMapping("/products/{id}/image")
    @ResponseBody
    public ResponseEntity<byte[]> productImage(@PathVariable UUID id) {
        Product p = productService.findById(id);
        if (p == null || p.getImage() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(p.getImageContentType()))
                .body(p.getImage());
    }

    @GetMapping("/products/edit/{id}")
    @ResponseBody
    public ProductResponseDto loadProduct(@PathVariable UUID id) {
        return adminContract.findByProduct(id);
    }

    // Ürün silme

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable UUID id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/categories")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/assign/{prodId}/{catId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void assignCategory(
            @PathVariable UUID prodId,
            @PathVariable UUID catId
    ) {
        Product p = productService.findById(prodId);
        Category c = categoryService.findById(catId);
        if (p == null || c == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product or Category not found");
        }
        if (p.getCategory() != null) {
            p.getCategory().getProducts().remove(p);
        }
        p.setCategory(c);
        productService.save(p);
    }

    @PostMapping("/products/unassigned/{prodId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void unassignedCategory(@PathVariable UUID prodId) {
        Product p = productService.findById(prodId);
        p.setCategory(null);
        productService.save(p);
    }
}