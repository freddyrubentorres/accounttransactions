# **Account Transactions - Frontend**

## **Descripción**
Este es el frontend para la gestión de transacciones bancarias, desarrollado con **Angular**. La aplicación permite a los usuarios realizar y visualizar transacciones, gestionar cuentas y clientes, y generar reportes.

---

## **Características**

### **Componentes Principales**
- **HeaderComponent**: Barra de navegación superior.
- **SidebarComponent**: Menú lateral de navegación para acceder a diferentes secciones.
- **MainComponent**: Componente principal que muestra el contenido central.
- **FooterComponent**: Pie de página con información adicional.
- **ClientComponent**: Sección para visualizar y gestionar los clientes.
- **AccountComponent**: Permite gestionar y consultar información de cuentas.
- **TransactionComponent**: Permite realizar y visualizar transacciones.
- **ReportComponent**: Generación de reportes de transacciones.

### **Funcionalidades Principales**
- **Gestión de transacciones**: Los usuarios pueden consultar una cuenta y agregar transacciones, asegurando que todos los campos sean validados correctamente antes de enviar.
- **Validaciones**: Se incluyen validaciones en los formularios, como la validación de número de cuenta y montos de transacciones.
- **Comunicaciones HTTP**: Se comunica con el backend utilizando **HTTPClient** de Angular, realizando solicitudes GET y POST para manejar la información de cuentas, clientes y transacciones.

---

# Configuración del Entorno (Desarrollo)

Este archivo define las URLs base para el entorno de desarrollo (`production: false`) de una aplicación que se comunica con un backend en `http://localhost:8080/accounttransactions_back`.

## Clientes
- **Crear Cliente**  
  `POST /api/clients`  
  URL: `http://localhost:8080/accounttransactions_back/api/clients`

- **Obtener Cliente por Identificación**  
  `GET /api/clients/identification/{identificación}`  
  URL: `http://localhost:8080/accounttransactions_back/api/clients/identification/{identificación}`

- **Actualizar Cliente por Identificación**  
  `PATCH /api/clients/identification/{identificación}`  
  URL: `http://localhost:8080/accounttransactions_back/api/clients/identification/{identificación}`

## Cuentas
- **Crear Cuenta**  
  `POST /api/accounts`  
  URL: `http://localhost:8080/accounttransactions_back/api/accounts`

- **Obtener Cuenta por Identificación**  
  `GET /api/accounts/identification/{identificación}`  
  URL: `http://localhost:8080/accounttransactions_back/api/accounts/identification/{identificación}`

- **Actualizar Cuenta por Número de Cuenta**  
  `PATCH /api/accounts/accountNumber/{número}`  
  URL: `http://localhost:8080/accounttransactions_back/api/accounts/accountNumber/{número}`

## Transacciones
- **Crear Transacción**  
  `POST /api/transactions`  
  URL: `http://localhost:8080/accounttransactions_back/api/transactions`

## Reportes
- **Obtener Reportes**  
  `GET /api/reports`  
  URL: `http://localhost:8080/accounttransactions_back/api/reports`


---

## **Estilos CSS**

En este proyecto, todos los estilos fueron creados desde cero, sin el uso de templates o frameworks preexistentes. El diseño y los estilos fueron desarrollados de manera personalizada para garantizar que la interfaz de usuario sea única, responsive y acorde con las necesidades específicas del sistema de gestión de cuentas y transacciones.

### **Características del CSS Personalizado**

- **Uso de Clases y Selectores**: Se hicieron clases y selectores personalizados para mantener un control total sobre el diseño y facilitar la futura escalabilidad del proyecto.

- **Sin Dependencias de Frameworks CSS**: No se utilizó ningún framework como Bootstrap o Materialize, lo que permitió una mayor personalización y control sobre los detalles visuales y la estructura de la aplicación.

### **Estructura del CSS**

El archivo de estilos fue estructurado en módulos y componentes, de modo que cada parte del proyecto tiene su propio archivo de estilo, lo que mejora la mantenibilidad y modularidad del código.

---

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/1.png?raw=true)



![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/2.png?raw=true)



![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/3.png?raw=true)



![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/4.png?raw=true)



![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/5.png?raw=true)



![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/6.png?raw=true)



![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_front/public/assets/capturas/7.png?raw=true)

---

### **Reporte Jasmine**

---

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/jasmine.png?raw=true)