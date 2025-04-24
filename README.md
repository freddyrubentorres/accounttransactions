# Account Transactions System

Este proyecto es un sistema completo para la gestión de transacciones de cuentas bancarias. Está compuesto por un **Backend** desarrollado en **Java (Spring Boot)** y un **Frontend** desarrollado en **Angular**.

## Estructura del Proyecto

El proyecto está dividido en dos partes principales: el **Backend** y el **Frontend**.

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/1.png?raw=true)


### Backend (Java Spring Boot)

#### Descripción

El backend está construido con **Spring Boot** y **JPA**, utilizando una arquitectura limpia (Clean Architecture) que sigue los principios SOLID para asegurar un código modular, extensible y fácil de mantener.

#### Características principales

- **Servicios RESTful** para la gestión de clientes, cuentas y transacciones.
- **Transacciones** aseguradas con `@Transactional` para mantener la integridad de la base de datos.
- **Validación de datos** con anotaciones como `@NotNull`, `@DecimalMin`, y `@DecimalMax` para garantizar la validez de los datos de entrada.
- **Manejo de errores** con excepciones personalizadas y mensajes detallados para facilitar la depuración y mejorar la experiencia del usuario.
- **Conexión con MySQL** para la persistencia de datos.
- **Mapeo de objetos** con **MapStruct** para facilitar la conversión de entidades entre el modelo y la capa de datos.

#### Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3.3.10**
- **JPA (Hibernate)**
- **MySQL**
- **MapStruct**
- **Lombok**

### Frontend (Angular)

#### Descripción

El frontend está construido con **Angular**. Proporciona una interfaz de usuario limpia y moderna para interactuar con el backend a través de API RESTful.

#### Características principales

- **Componentes modulares**: El frontend se organiza en varios componentes reutilizables como `Header`, `Sidebar`, `Footer`, `Main`, etc.
- **Formulario dinámico** para la creación de cuentas, clientes y transacciones, con validación en tiempo real.
- **Conexión con el backend** utilizando servicios HTTP (`HttpClient`).
- **Manejo de errores y validación** de campos con mensajes claros para los usuarios.

#### Tecnologías utilizadas

- **Angular 15**
- **TypeScript**
- **Angular Material** para una interfaz atractiva y fácil de usar.
- **Reactive Forms** y **Two-Way Data Binding** para manejar formularios.

### Variables de Entorno

En el **Frontend (Angular)**, las URL de las API del backend están configuradas en el archivo `environment.ts`:

```typescript
export const environment = {
  production: false,
  baseUrlgetClients: 'http://localhost:8080/accounttransactions_back/api/clients/identification/',
  baseUrlpostClients: 'http://localhost:8080/accounttransactions_back/api/clients',
  baseUrlgetReports: 'http://localhost:8080/accounttransactions_back/api/reports',
  baseUrlgetAccount: 'http://localhost:8080/accounttransactions_back/api/accounts/accountNumber/',
  baseUrlpostTransaction: 'http://localhost:8080/accounttransactions_back/api/transactions',
  baseUrlpostAccount: 'http://localhost:8080/accounttransactions_back/api/accounts'
};
