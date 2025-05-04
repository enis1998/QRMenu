package com.qrmenu.controller.Menu;


import com.qrmenu.entity.Category;
import com.qrmenu.service.CategoryService;
import com.qrmenu.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/menu")
public class MenuController {

    private final CategoryService categoryService;
    private final ProductService productService;

    public MenuController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping
    public String showCategories(Model m) {
        m.addAttribute("categories", categoryService.findAllByStatus());
        return "menu/categories";
    }

    @GetMapping("/category/{id}")
    public String showProducts(@PathVariable UUID id, Model m) {
        Category cat = categoryService.findById(id);
        m.addAttribute("category", cat);
        m.addAttribute("products", productService.findActiveByCategory(cat));
        return "menu/products";
    }

}
