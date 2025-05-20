# Sistema de Registro Universitario - Práctica N° 2

---

## Datos del estudiante

- **Nombre:** Laura Ríos Lizbeth Fabiola  
- **CI:** 9104384  
- **Docente:** Lic. Rosalía López Montalvo  
- **Fecha:** 19/05/2025  

---

## Descripción del Proyecto

Este proyecto es un Sistema de Registro Universitario desarrollado con Spring Boot que incluye funcionalidades backend robustas, aplicando buenas prácticas como validaciones con anotaciones, manejo de excepciones personalizado, seguridad con JWT, y conexión a PostgreSQL y Redis para cache. Se han implementado módulos funcionales completos:

- Login, registro, manejo de JWT y roles de usuario.
- CRUD completo para estudiantes con validaciones.
- CRUD de materias con asignación a docentes y validaciones.
- CRUD de registros de inscripciones por estudiante con validaciones.
- Opción de cierre de sesión.
- Documentación de la API con Swagger.

---

## Estructura del proyecto

El código fuente está organizado en los siguientes paquetes:


---

## Funcionalidades principales

### 1. Login y Registro

- Registro de nuevos usuarios con validaciones de campos.
- Inicio de sesión con generación y validación de tokens JWT.
- Manejo de roles para autorización de acceso a endpoints.

### 2. CRUD completo

- **Estudiantes**: creación, lectura, actualización y eliminación lógica (baja).
- **Materias**: gestión con asignación a docentes.
- **Inscripciones**: registro de inscripciones por estudiante.

### 3. Validaciones

- Uso de anotaciones como `@NotNull`, `@NotBlank`, `@Email`, `@Size` para validar datos de entrada.
- Validaciones declaradas en DTOs (`AuthDTO`) para login y registro.

### 4. Manejo de excepciones personalizado

- Implementado con `@RestControllerAdvice` para respuesta uniforme de errores.

### 5. Seguridad y Autenticación

- Uso de JWT para proteger endpoints.
- Roles de usuario para control de acceso.

### 6. Base de datos y caché

- PostgreSQL como base de datos principal, configurada en `application.properties`.
- Redis configurado para cache y mejorar rendimiento.

---

## Pruebas y documentación

- Se realizaron pruebas funcionales usando Swagger UI para validar todos los endpoints.
- Se capturaron respuestas exitosas y errores, documentadas con capturas de pantalla.
- Base de datos poblada con datos de ejemplo para usuarios, roles y materias.
- Queries para tablas `usuarios_roles` y `roles` disponibles en la carpeta `/docs`.

---

## Manual de usuario (Interfaz gráfica)

- Acceso mediante navegador web a `http://localhost:8080` (o URL de despliegue).
- Inicio de sesión con usuario y contraseña.
- Navegación principal con opciones para gestionar usuarios, perfil y cierre de sesión.
- Funcionalidades CRUD accesibles vía interfaz con formularios claros.
- Mensajes de éxito, error y confirmación para facilitar la experiencia del usuario.

---

## Configuración del proyecto

- Archivo `application.properties` contiene configuración para PostgreSQL, Redis y seguridad.
- Swagger UI habilitado para documentación interactiva.
- Roles configurados en seguridad para control granular de accesos.

---


