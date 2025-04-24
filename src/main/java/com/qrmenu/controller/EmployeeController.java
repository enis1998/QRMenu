package com.qrmenu.controller;

import com.qrmenu.entity.ChangeRequest;
import com.qrmenu.entity.Product;
import com.qrmenu.service.ChangeRequestService;
import com.qrmenu.service.ProductService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/employee")
public class EmployeeController {

    private final ProductService productService;
    private final ChangeRequestService changeRequestService;

    public EmployeeController(ProductService productService,
                              ChangeRequestService changeRequestService) {
        this.productService = productService;
        this.changeRequestService = changeRequestService;
    }

    // Çalışan ürün değişikliği isteğini oluşturmak için formu gösterir
    @GetMapping("/products/edit/{id}")
    public String editProductRequest(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/menu";
        }
        model.addAttribute("product", product);
        return "employee/product-change-request-form";
    }

    // Çalışan değişiklik isteğini gönderir
    @PostMapping("/products/request-update/{id}")
    public String submitProductChangeRequest(@PathVariable Long id,
                                             @RequestParam("newName") String newName,
                                             @RequestParam("newDescription") String newDescription,
                                             @RequestParam("newPrice") Double newPrice,
                                             Authentication authentication) {
        Product product = productService.findById(id);
        if (product == null) {
            return "redirect:/menu";
        }
        ChangeRequest request = new ChangeRequest();
        request.setProduct(product);
        request.setNewName(newName);
        request.setNewDescription(newDescription);
        request.setNewPrice(newPrice);
        request.setInitiatedBy(authentication.getName());
        changeRequestService.createChangeRequest(request);
        return "redirect:/menu";
    }
}
