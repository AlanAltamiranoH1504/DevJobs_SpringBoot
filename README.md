# Plataforma DevJobs

**DevJobs** es una plataforma en línea que conecta a los reclutadores con desarrolladores. Permite a los reclutadores publicar ofertas de trabajo, y a los desarrolladores de empleo crear perfiles y postularse a empleos. La plataforma está desarrollada utilizando **Spring Boot**, **Spring Security**, **MySQL** y otras tecnologías modernas para ofrecer una experiencia de usuario segura, eficiente e intuitiva.

---

## ✨ Características

- **Publicación de Ofertas de Trabajo:** Los reclutadores pueden crear ofertas con título, descripción, requisitos, salario, etc.
- **Gestión de Perfiles:** Los usuarios pueden registrarse como empleadores o candidatos y administrar su información personal y profesional.
- **Postulación a Vacantes:** Los candidatos pueden aplicar a los trabajos, y los reclutadores pueden revisar las postulaciones recibidas.
- **Panel de Administración:** Acceso exclusivo para administradores para gestionar usuarios, ofertas y postulaciones.

---

## ⚙️ Tecnologías Utilizadas

- **Spring Boot:** Framework principal para construir la aplicación backend en Java.
- **Spring Security:** Proporciona autenticación y autorización para proteger rutas y recursos.
- **Spring Data JPA:** Abstracción sobre JPA para operaciones con la base de datos.
- **MySQL:** Sistema de gestión de base de datos relacional utilizado para almacenar usuarios, ofertas y postulaciones.
- **Thymeleaf:** Motor de plantillas utilizado para renderizar las vistas HTML en el servidor.
- **AJAX:** Utilizado para enviar y recibir datos de manera asíncrona, mejorando la experiencia del usuario.
- **CSRF Protection:** Implementada mediante Spring Security para proteger los formularios y peticiones contra ataques Cross-Site Request Forgery.

---

## 🚀 Requisitos para ejecutar el proyecto

- Java 21 o superior
- Maven 3.8+
- MySQL 8.x
- IDE como IntelliJ IDEA o Spring Tool Suite

---

## ⚡ Configuración Básica

```properties
# application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/DevJobs_SpringBoot_2025
spring.datasource.username=root
spring.datasource.password=tu_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

#Servicio de Envio de E-Mail con Mailtrap
spring.mail.host=tu_host
spring.mail.port=tu_puerto
spring.mail.username=tu_nombre_de_usuario
spring.mail.password=tu_password
spring.mail.test-connection=true

#Ruta base backend
backend.ruta.base=http://localhost:8080