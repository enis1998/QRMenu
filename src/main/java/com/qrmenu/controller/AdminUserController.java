package com.qrmenu.controller;

import com.qrmenu.controller.contract.AdminUserContract;
import com.qrmenu.dto.AdminUserDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    private final AdminUserContract userContract;


    public AdminUserController(AdminUserContract userContract) {
        this.userContract = userContract;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgotPassword")
    public String handleForgot(@RequestParam String email, Model model) {
        // 1) userService.sendResetLink(email);
        model.addAttribute("message", "E‑posta sıfırlama bağlantısı gönderildi.");
        return "forgot-password";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userContract.getAllUsers());
        model.addAttribute("adminUser", new AdminUserDto());
        return "admin/user-list";
    }

    @PostMapping("/users")
    public String createUser(@Valid @ModelAttribute("adminUser") AdminUserDto dto,
                             BindingResult br,
                             Model model) {
        if (br.hasErrors()) {
            // Hatalı durumda yine listeyi ve boş formu göster
            model.addAttribute("openDrawer", true);
            model.addAttribute("users", userContract.getAllUsers());
            return "admin/user-list";
        }
        userContract.createUser(dto);
        return "redirect:/admin/users";
    }

    @GetMapping("/users/edit/{id}")
    public String editUserForm(@PathVariable Long id, Model model) {
        AdminUserDto dto = userContract.getUserById(id);
        if (dto == null) {
            return "redirect:/admin/users";
        }
        dto.setPassword(""); // kullanıcıya hash gösterme
        model.addAttribute("adminUser", dto);
        return "admin/user-form";
    }

    @PostMapping("/users/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("adminUser") AdminUserDto dto,
                             BindingResult br) {
        if (br.hasErrors()) {
            return "admin/user-form";
        }
        userContract.updateUser(id, dto);
        return "redirect:/admin/users";
    }

    @PostMapping("/users/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userContract.deleteUser(id);
        return "redirect:/admin/users";
    }
}
