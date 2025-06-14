# Zavala Fleet Management

## Descripción
Este proyecto es una plataforma digital para la optimización de la visualización y gestión técnica de la flota vehicular de la empresa de transporte de carga Zavala Cargo S.A.C. La solución permite la gestión centralizada y eficiente de vehículos, consultas de servicios y reservas, así como el control de mantenimiento programado y el estado de la flota.

---

## Funcionalidades principales

1. **Login Administrador**  
   - Acceso exclusivo para administradores con credenciales predefinidas.

2. **Consulta de Envío para Clientes**  
   - Los clientes pueden consultar la disponibilidad del servicio.  
   - Visualización de reservas por día o por equipaje.  
   - Visualización de los vehículos disponibles en la flota.

3. **Gestión de Flota para Administración**  
   - Mantenimiento programado de vehículos (CRUD).  
   - Control de estado de vehículos: disponibles, en ruta o fuera de servicio (CRUD).

---

## Tecnologías usadas

- Backend: APIs RESTful  
- Base de datos: MySQL  
- Arquitectura: SOA con ESB para orquestación de servicios  
- Frontend: Diseño web orientado a UX (cliente y administrador)

---

## Estructura del proyecto

- `/backend`  - Lógica de negocio y APIs  
- `/frontend` - Interfaz de usuario  
- `/database` - Scripts de base de datos  
- `/docs`     - Documentación y diagramas

---

## Cómo ejecutar

1. Clonar el repositorio  
2. Configurar la base de datos MySQL  
3. Ejecutar backend (API REST)  
4. Iniciar frontend en el navegador  
5. Usar credenciales de administrador para login

