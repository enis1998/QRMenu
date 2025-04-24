package com.qrmenu.controller;


import com.qrmenu.entity.Category;
import com.qrmenu.entity.Product;
import com.qrmenu.service.AdminUserService;
import com.qrmenu.service.CategoryService;
import com.qrmenu.service.ChangeRequestService;
import com.qrmenu.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AdminUserService adminUserService;
    private final ChangeRequestService changeRequestService;

    public AdminController(ProductService productService,
                           CategoryService categoryService, AdminUserService adminUserService, ChangeRequestService changeRequestService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.adminUserService = adminUserService;
        this.changeRequestService = changeRequestService;
    }
    @GetMapping("/dashboard")
    public String panel(Model model, Authentication auth) {
        model.addAttribute("users", adminUserService.findAll());
        model.addAttribute("products", productService.findAll());

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) {
            model.addAttribute("pendingUpdates", changeRequestService.findAllPending());
        }
        return "admin/dashboard";
    }

    @GetMapping("/products")
    public String listProducts(Model model) {
        List<Category> categories = categoryService.findAll();
        List<Product>  allProductsList  = productService.findAll();
        Map<Long,List<Product>> productsByCategoryMap = categories.stream()
                .collect(Collectors.toMap(
                        Category::getId,
                        productService::findActiveByCategory
                ));
        List<Product> unassignedProducts = allProductsList.stream()
                .filter(p -> p.getCategory() == null)
                .collect(Collectors.toList());
        model.addAttribute("categories", categories);
        model.addAttribute("productsByCategory", productsByCategoryMap);
        model.addAttribute("allProducts", allProductsList);
        model.addAttribute("unassignedProducts", unassignedProducts);
        model.addAttribute("product", new Product());
        model.addAttribute("category", new Category());
        return "admin/product-list";
    }

    @PostMapping("/products")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @ModelAttribute("product") Product updatedProduct,
                                @RequestParam("imageFile") MultipartFile imageFile) {
        Product existing = productService.findById(id);
        if (existing == null) {
            return "redirect:/admin/products";
        }
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setActive(updatedProduct.isActive());
        existing.setCategory(updatedProduct.getCategory());
        // Eğer yeni resim gönderildiyse, güncelle
        try {
            if (imageFile != null && !imageFile.isEmpty()) {
                existing.setImage(imageFile.getBytes());
                existing.setImageContentType(imageFile.getContentType());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        productService.save(existing);
        return "redirect:/admin/products";
    }
    // Ürün silme

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }

    @PostMapping("/categories")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/products";
    }

    @PostMapping("/products/assign/{prodId}/{catId}")
    @ResponseBody
    public void assignCategory(
            @PathVariable Long prodId,
            @PathVariable Long catId
    ) {
        Product p = productService.findById(prodId);
        Category c = categoryService.findById(catId);
        // ürünün eski kategorisini temizlemek isterseniz:
        if(p.getCategory()!=null){ p.getCategory().getProducts().remove(p); }
        p.setCategory(c);
        productService.save(p);
    }
}