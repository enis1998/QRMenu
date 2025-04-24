# 📦 QRMenu Admin Panel

A Spring Boot + Thymeleaf application for managing Categories & Products with both Table View and Drag-&-Drop assignment.

---

## 🎯 Purpose

The QRMenu Admin Panel streamlines restaurant or retail menu management by providing a single interface to create, organize, and assign products to categories. It allows staff to quickly modify menu items, categorize them, and visually assign products via drag-and-drop, reducing manual database updates and improving operational efficiency.

---

## ✨ Features

- **Table View**  
  • List, create, edit & delete categories  
  • List, create, edit & delete products
- **Drag & Drop View**  
  • Unassigned products appear on the right  
  • Drag a product into any category to assign it  
  • Real-time update via AJAX (no full page reload)
- **Thymeleaf Templates** with Tailwind CSS for a responsive, modern UI
- **Spring Data JPA** entities for `Category` & `Product`
- CSRF protection & basic validation

---

## 🏗️ Tech Stack

- **Backend**: Java 17, Spring Boot, Spring MVC, Spring Data JPA, Hibernate
- **Frontend**: Thymeleaf, Tailwind CSS, Sortable.js, jQuery
- **Database**: H2 (in-memory) or any relational (configure in `application.yml`)
- **Build**: Maven 3.x
- **Security**: Spring Security (basic admin authentication)

---

## 🚀 Prerequisites

- Java 17+
- Maven 3.6+
- (Optional) Docker & Docker Compose

---

## 🔧 Installation

1. **Clone the repo**
   ```bash
   git clone https://github.com/your-org/qrmenu-admin-panel.git
   cd qrmenu-admin-panel
