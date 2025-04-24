package com.qrmenu;

import com.qrmenu.entity.AdminUser;
import com.qrmenu.enums.Role;
import com.qrmenu.service.AdminUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class QrMenuApplication {

    public static void main(String[] args) {
        SpringApplication.run(QrMenuApplication.class, args);
    }

    @Bean
    public CommandLineRunner initAdmin(AdminUserService userService,
                                       PasswordEncoder encoder) {
        return args -> {
            String adminUsername = "admin@cafemenu.com";
            if (userService.findByUsername(adminUsername) == null) {
                AdminUser admin = new AdminUser();
                admin.setEmail(adminUsername);
                admin.setPassword(encoder.encode("Admin123!"));
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);
                userService.save(admin);
                System.out.println("Default admin user created: " + adminUsername);
            }
        };
    }
}

// Loglama sistemi ekle ve try catch
// Jwt key ve cookie ayarlarını koy. Kullanıcı sürekli oturumda kalsın.
// Tüm id'leri UUID
// Employee ve admin çalışmalarını doğru ayarla
// Annotation ekle hem frontend hem backend. Sadece dto eklesen yeterli
// Mobile uygun yap
// dark mode ve dil seçeneği ekle
// Olmayan bir sayfaya istek attığında default bir sayfa tasarla