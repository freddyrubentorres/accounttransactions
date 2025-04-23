# Account Transactions Backend

Este es un proyecto backend para gestionar transacciones de cuentas bancarias. Proporciona una API RESTful que permite crear cuentas, realizar transacciones y consultar información de las cuentas de los clientes.

## Estructura del Proyecto

La estructura del proyecto sigue una arquitectura limpia y modular, basada en los principios de SOLID. A continuación se muestra un esquema detallado de la estructura de directorios:

## Estructura de Directorios

```plaintext
com.ms.accounttransactions_back
├── adapter
│   ├── config           # Configuraciòn necesarias para el funcionamiento.
│   ├── in.web
│   │   ├── constants    # Constantes
│   │   ├── controller   # Controladores que gestionan las solicitudes HTTP entrantes y las redirigen a los servicios adecuados.
│   │   ├── dto          # Objetos de transferencia de datos (DTO) para facilitar el intercambio de información entre capas.
│   │   └── exception    # Clases relacionadas con el manejo de excepciones y errores en la capa de entrada.
│   ├── out.persistence
│   │   ├── entity       # Clases de entidad que representan los objetos persistidos en la base de datos.
│   │   ├── repository   # Repositorios para interactuar con la base de datos y realizar operaciones CRUD.
│   │   └── mapper       # Clases de mapeo para convertir entre entidades y DTOs.
│   └── out.messages     # Comunicación con sistemas externos mediante mensajería (por ejemplo, Kafka, RabbitMQ, etc.).
├── application
│   ├── port.in          # Interfaz que define las operaciones que la capa de entrada (controllers) puede invocar.
│   ├── port.out         # Interfaz que define las operaciones que la capa de salida (repositories, servicios externos) ofrece.
│   └── service          # Implementación de la lógica de negocio central del sistema.
├── common
│   └── component        # Componentes y utilidades comunes que se utilizan en varias partes de la aplicación (por ejemplo, configuraciones, validaciones, etc.).
└── domain
    └── TipoNota.java    # Clase de dominio que representa un objeto de negocio o entidad central en el sistema.

```



## SOLID en el Proyecto

Este proyecto sigue los principios SOLID para asegurar que el código sea fácil de mantener, escalable y testeable. A continuación, se detalla cómo se implementan estos principios en el código:

## 1. **Single Responsibility Principle (SRP)** - Principio de Responsabilidad Única

Cada clase y componente del proyecto tiene una única responsabilidad, lo que facilita su comprensión, mantenimiento y pruebas. Ejemplos:

- **Controladores** (`AccountController`): Gestionan las solicitudes HTTP y delegan la lógica de negocio a los servicios.
- **Servicios** (`AccountServiceImpl`): Contienen la lógica de negocio relacionada con las cuentas y las transacciones.
- **Repositorios** (`AccountRepository`): Realizan la interacción con la base de datos.
- **DTOs y Entidades** (`Account`, `AccountDto`): Contienen los datos y validaciones necesarias para la transferencia de información.
- **Mapper** (`AccountMapperImpl`): Realiza la conversión entre las entidades y los DTOs.

## 2. **Open/Closed Principle (OCP)** - Principio de Abierto/Cerrado

El código está diseñado de forma que se puede **extender** sin necesidad de **modificar** las clases existentes:

- Puedes agregar nuevas funcionalidades (por ejemplo, nuevos tipos de transacciones) creando nuevas clases o métodos sin modificar el código base.
- Los servicios y mappers están diseñados para ser fácilmente extendibles sin que se vea afectada la funcionalidad existente.

Ejemplo:
- La clase `AccountServiceImpl` es extensible y permite agregar nuevos comportamientos como la creación de transacciones adicionales sin modificar la implementación existente.

## 3. **Liskov Substitution Principle (LSP)** - Principio de Sustitución de Liskov

Las clases que implementan interfaces o extienden otras clases pueden ser sustituidas sin afectar la funcionalidad del sistema. Ejemplos:

- **`AccountMapperImpl`** implementa la interfaz `AccountMapper`, y puede ser reemplazada por otras implementaciones sin afectar el funcionamiento del sistema.
- **`MessageService`** es una interfaz que permite diferentes implementaciones, como `PropertiesMessageService`, las cuales pueden ser intercambiadas sin problemas.

## 4. **Interface Segregation Principle (ISP)** - Principio de Segregación de Interfaces

Las interfaces están diseñadas de manera que contienen solo los métodos necesarios para las clases que las implementan. Esto asegura que las clases no estén obligadas a implementar métodos que no usen. Ejemplos:

- **`MessageService`** tiene un solo método `get` que es el necesario para recuperar mensajes, manteniendo la interfaz simple y enfocada.
- Otros servicios o interfaces siguen el mismo principio, asegurando que las implementaciones sean específicas y no tengan métodos innecesarios.

## 5. **Dependency Inversion Principle (DIP)** - Principio de Inversión de Dependencias

El código depende de **abstracciones** (interfaces), no de implementaciones concretas. Esto permite un desacoplamiento entre las clases de alto nivel y las de bajo nivel, y facilita la inyección de dependencias y el testeo. Ejemplos:

- La clase **`AccountServiceImpl`** depende de interfaces como `AccountRepository` y `TransactionRepository`, y no de las implementaciones concretas de estas clases.
- Las depend

---

## Características del Proyecto

## Arquitectura Limpia

El código sigue una arquitectura típica basada en la separación de responsabilidades, utilizando patrones como el de **DTO (Data Transfer Object)**, **Mapper**, y **Service Layer**, lo cual mejora la mantenibilidad y la escalabilidad.

- **DTO (Data Transfer Object)**: Los DTOs se usan para transferir datos entre capas, especialmente para la entrada y salida de información en las solicitudes HTTP.
- **Mapper**: Se utiliza para transformar los objetos de dominio a DTOs y viceversa, lo que simplifica el proceso de conversión entre diferentes representaciones de los datos.
- **Service Layer**: Los servicios contienen la lógica de negocio y se encargan de interactuar con los repositorios para persistir los datos. Esta capa también maneja las operaciones críticas como la creación de cuentas y las transacciones.

La implementación de interfaces como **MessageService** y la inyección de dependencias con `@RequiredArgsConstructor` ayuda a mantener el código desacoplado, lo cual es ideal para pruebas y cambios futuros.

---

## Uso de Lombok

El uso de **Lombok** (@Data, @Getter, @Setter, etc.) hace el código más conciso y legible, eliminando la necesidad de escribir getters y setters manualmente. Esto mejora la claridad del código y reduce la repetición, lo que facilita su mantenimiento.

---

## Manejo de Errores

El manejo de errores se realiza utilizando **excepciones personalizadas** como `NotFoundException`, lo cual permite capturar y manejar errores específicos de la lógica del dominio. Además, se gestiona los errores de forma detallada a través de la clase **ErrorsMessage**, lo que mejora la robustez y claridad de la aplicación.

---

## Transacciones

El uso de la anotación `@Transactional` en los servicios asegura que las operaciones de base de datos sean consistentes y que los cambios se gestionen de manera adecuada. Esto es particularmente importante en operaciones críticas como:

- La creación de cuentas.
- La realización de transacciones entre cuentas.
  
Esto garantiza que las modificaciones en la base de datos sean atómicas, y que cualquier error o interrupción en el proceso no deje el sistema en un estado inconsistente.

---

## Validación

La validación de datos con anotaciones como `@NotNull`, `@DecimalMin`, y `@DecimalMax` en los DTOs ayuda a garantizar que los datos recibidos sean válidos antes de procesarlos. Esto protege el sistema contra datos corruptos o mal formateados, y asegura que las reglas del negocio se cumplan correctamente.

---



## Dependencias y Plugins

Este proyecto está basado en Maven, y las principales dependencias y plugins utilizados son:

### Dependencias Principales:

- **Spring Boot Starter Data JPA**  
   - Integración con JPA para la persistencia de datos.
- **Spring Boot Starter Web**  
   - Framework para crear aplicaciones web RESTful.
- **MySQL Connector/J**  
   - Conexión con bases de datos MySQL.
- **Lombok**  
   - Reducción de código repetitivo (Getters, Setters, etc.).
- **Spring Boot Starter Test**  
   - Herramientas para realizar pruebas.
- **Validation API**  
   - API para validación de datos.
- **MapStruct**  
   - Herramienta para mapeo de DTOs y entidades.

   ### Plugins Utilizados:

- **Maven Compiler Plugin**  
   - Configuración para usar Lombok y procesadores de anotaciones.
- **Spring Boot Maven Plugin**  
   - Empaquetado de la aplicación como un JAR ejecutable.
- **Jacoco Maven Plugin**  
   - Herramienta de cobertura de pruebas y generación de informes.

   ## Tecnologías Utilizadas

- **Java 17**: Lenguaje de programación utilizado para el desarrollo del proyecto.
- **Spring Boot**: Framework para crear aplicaciones empresariales y servicios web RESTful.
- **MySQL**: Sistema de gestión de bases de datos utilizado para almacenar la información.
- **MapStruct**: Biblioteca para simplificar el mapeo entre objetos.


---

# accounttransactions_back - API Collection

Este archivo describe las configuraciones y rutas de la API `accounttransactions_back`. Está basado en una colección exportada desde Postman.

## Configuración de la API

### Variables de Entorno:
- **scheme**: `http`  
  Esquema utilizado para la conexión a la API.
- **host**: `localhost`  
  Host del servidor de la API.
- **port**: `8080`  
  Puerto donde la API está disponible.
- **back**: `/accounttransactions_back/api`  
  Ruta base de la API.

### Rutas de la API:

1. **Clientes**:
   - **Ruta base**: `/clients`  
   - **Descripción**: Permite acceder a la información de los clientes.

2. **Clientes por Identificación**:
   - **Ruta**: `/identification/`  
   - **Descripción**: Permite acceder a la información de un cliente usando su identificación.

3. **Cuentas**:
   - **Ruta base**: `/accounts`  
   - **Descripción**: Permite acceder a la información de las cuentas.

4. **Cuenta por Número de Cuenta**:
   - **Ruta**: `/accountNumber/`  
   - **Descripción**: Permite acceder a la información de una cuenta específica mediante su número de cuenta.

5. **Transacciones**:
   - **Ruta base**: `/transactions`  
   - **Descripción**: Permite acceder a las transacciones realizadas en las cuentas.

6. **Informes**:
   - **Ruta base**: `/reports`  
   - **Descripción**: Permite generar y acceder a informes relacionados con las cuentas y transacciones.


![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/1.png?raw=true)

---

# API Collection: accounttransactions

Esta colección de Postman contiene las rutas y operaciones de la API `accounttransactions`. A continuación se describen las principales rutas y métodos disponibles.

## Rutas y Operaciones

### **1. Clientes**
   - **POST /clients** - Crear un nuevo cliente.
     - **Cuerpo de la solicitud**:
       ```json
       {
         "email": "joselema@gmail.com",
         "age": "32",
         "password": "Ag@68hese10",
         "name": "jose",
         "last_name": "lema",
         "gender": "M",
         "identification": "1717493571",
         "address": "Otavalo sn y principal",
         "phone": "0982547851"
       }
       ```
   
   - **GET /clients/{identification}** - Obtener los detalles de un cliente por su número de identificación.
     - **Ejemplo de URL**: `/clients/identification/1717493571`
   
   - **PATCH /clients/{id}** - Actualizar los datos de un cliente.
     - **Cuerpo de la solicitud**:
       ```json
       {
         "email": "joselema@hotmail.com",
         "status": "TRUE",
         "password": "Ag@68hese11"
       }
       ```
     - **Ejemplo de URL**: `/clients/1`

### **2. Cuentas**
   - **POST /accounts** - Crear una nueva cuenta.
     - **Cuerpo de la solicitud**:
       ```json
       {
         "accountType": "AHORRO",
         "initialBalance": "2000.00",
         "client": {
           "clientId": 1
         }
       }
       ```
   
   - **GET /accounts/{accountNumber}** - Obtener detalles de una cuenta por su número de cuenta.
     - **Ejemplo de URL**: `/accounts/accountNumber/908727`
   
   - **PUT /accounts/{accountNumber}** - Actualizar el estado de una cuenta.
     - **Ejemplo de URL**: `/accounts/142360`

### **3. Transacciones**
   - **POST /transactions** - Realizar una nueva transacción.
     - **Cuerpo de la solicitud**:
       ```json
       {
         "description": "SUELDO 03 2025",
         "amount": 2500,
         "account": {
           "accountNumber": 908727
         }
       }
       ```

### **4. Informes**
   - **GET /reports** - Obtener un informe basado en la identificación del cliente y un rango de fechas.
     - **Parámetros de la consulta**:
       - `identification`: Identificación del cliente.
       - `startDate`: Fecha de inicio del reporte.
       - `endDate`: Fecha final del reporte.
     - **Ejemplo de URL**:
       `/reports?identification=1717493571&startDate=2025-04-06&endDate=2025-04-08`
---
### **5. Ejemplos**
---
![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/2.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/3.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/4.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/5.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/6.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/7.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/8.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/postman/9.png?raw=true)
---

## Sonar

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/accounttransactions_back/src/doc/img/sonar/2.png?raw=true)
---