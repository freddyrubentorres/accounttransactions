# Account Transactions Backend

Este es un proyecto backend para gestionar transacciones de cuentas bancarias. Proporciona una API RESTful que permite crear cuentas, realizar transacciones y consultar información de las cuentas de los clientes.

## Arquitectura Hexagonal

El sistema está implementado utilizando **Arquitectura Hexagonal** (también conocida como **Arquitectura de Puertos y Adaptadores**). Este enfoque permite desacoplar la lógica de negocio del resto del sistema, como la base de datos, servicios externos y la interfaz de usuario. Los principales beneficios de este patrón son:

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/arquitecturaHexagonal.png?raw=true)


- **Desacoplamiento**: La lógica de negocio se encuentra aislada de las tecnologías específicas, como la base de datos o los frameworks, lo que facilita el mantenimiento y la evolución del sistema.
- **Escalabilidad**: Se pueden agregar nuevas funcionalidades o adaptadores sin modificar el núcleo del sistema, manteniendo la aplicación flexible y extensible.
- **Pruebas**: Gracias a la separación de responsabilidades, las pruebas unitarias y de integración son más fáciles de implementar y ejecutar.

### Capas del Proyecto:

- **Dominio**: Contiene las entidades de negocio y las reglas de negocio.
- **Aplicación**: Define la lógica del servicio y los casos de uso.
- **Adaptadores**:
    - **Entrada (Web)**: Controladores HTTP que interactúan con los usuarios finales.
    - **Salida (Persistence)**: Adaptadores que interactúan con bases de datos u otros sistemas externos.

## Estructura de Directorios

La estructura del proyecto sigue el patrón de la arquitectura hexagonal y está organizada de la siguiente manera:

```plaintext
com.ms.accounttransactions_back
├── adapter
│   ├── in
│   │   └── web
│   │       ├── controller     # Controladores REST que exponen la API al exterior.
│   │       ├── dto            # Objetos de transferencia de datos (DTOs) usados en la capa web.
│   │       ├── exception      # Manejo de errores específicos de las peticiones web.
│   │       └── util           # Funciones auxiliares para la capa de entrada.
│   └── out
│       └── persistence
│           ├── entity        # Entidades JPA mapeadas para la base de datos.
│           ├── mapper        # Mappers para transformar entidades en modelos de dominio.
│           ├── repository    # Repositorios que extienden interfaces de Spring Data.
│           └──               # Implementación de lógica de persistencia adicional.
├── application
│   ├── exception             # Excepciones propias de la lógica de negocio.
│   ├── port
│   │   ├── in                # Interfaces que definen los casos de uso del sistema.
│   │   └── out               # Interfaces para dependencias externas necesarias (persistencia, etc.).
│   └── service               # Implementaciones concretas de los casos de uso.
├── common
│   └── component             # Componentes reutilizables como servicios utilitarios, helpers, etc.
├── domain
│   ├── enums                 # Enumeraciones que representan conceptos del dominio (tipos, estados, etc.).
│   ├── validator             # Validaciones propias del negocio.
│   └──                       # Entidades centrales del dominio y su lógica principal.

```



## SOLID en el Proyecto


![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/SOLID.png?raw=true)


## 1. **Single Responsibility Principle (SRP)** - Principio de Responsabilidad Única
- Las clases tienen responsabilidades bien definidas, como `AccountPersistenceAdapter` (persistencia) y `AccountMapper` (mapeo).
- Las interfaces en **`port/in`** y **`port/out`** también siguen este principio, separando claramente los casos de uso y servicios.

## 2. **Open/Closed Principle (OCP)** - Principio de Abierto/Cerrado
- El sistema es fácilmente extensible mediante nuevas implementaciones de interfaces, sin modificar las clases existentes.
- La arquitectura hexagonal permite agregar adaptadores o servicios sin alterar el comportamiento de las clases base.

## 3. **Liskov Substitution Principle (LSP)** - Principio de Sustitución de Liskov
- No hay jerarquías complejas de herencia, pero las interfaces definidas permiten sustituir implementaciones sin alterar el comportamiento del sistema.

## 4. **Interface Segregation Principle (ISP)** - Principio de Segregación de Interfaces
- Las interfaces en **`application/port`** son específicas para cada caso de uso, evitando que las clases implementen métodos innecesarios.
- Esto permite una implementación más limpia y centrada en las necesidades del sistema.

## 5. **Dependency Inversion Principle (DIP)** - Principio de Inversión de Dependencias
- Las clases de alto nivel, como **`AccountService`**, dependen de interfaces (como `LoadAccountPort`), no de clases concretas.
- Esto desacopla las capas del sistema y facilita la prueba y el mantenimiento.

---

## 🧾 Información General
- **Spring Boot Version:** `3.3.11`
- **Java Version:** `17`
---

## Dependencias Principales

| Dependencia                          | Propósito                                    |
|--------------------------------------|----------------------------------------------|
| `spring-boot-starter-data-jpa`       | Persistencia con JPA/Hibernate               |
| `spring-boot-starter-web`            | Creación de APIs REST                        |
| `spring-boot-starter-validation`     | Validación de datos con anotaciones          |
| `mysql-connector-j` (scope: runtime) | Conector JDBC para base de datos MySQL       |
| `lombok` (opcional)                  | Reducción de código boilerplate con anotaciones |
| `spring-boot-starter-test` (test)    | Herramientas para pruebas (JUnit, Mockito)   |

---

## Plugins de Build

| Plugin                     | Función |
|----------------------------|--------|
| `maven-compiler-plugin`    | Compilación de código y soporte para procesadores de anotaciones (Lombok). |
| `spring-boot-maven-plugin`| Soporte para empaquetado y ejecución de aplicaciones Spring Boot. |
| `jacoco-maven-plugin`     | Generación de reportes de cobertura de pruebas unitarias. Excluye archivos específicos. |

---

## Buenas Prácticas Aplicadas

- Uso de `Lombok` para código limpio.
- Separación clara de dependencias por entorno (`runtime`, `test`, `optional`).
- Integración con `JaCoCo` para medir cobertura de pruebas.
- Preparado para análisis estático con herramientas como SonarQube.

---

# AccountTransactions Back API Configuration

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/2.png?raw=true)

## Configuración del Entorno

El entorno de esta API está configurado con las siguientes variables clave:

- **Scheme**: `http`
    - El esquema utilizado para las solicitudes es HTTP.

- **Host**: `localhost`
    - La API se ejecuta localmente en el host `localhost`.

- **Port**: `8080`
    - El puerto de la aplicación es el `8080`.

- **Base Path**: `/accounttransactions_back/api`
    - La base para todas las rutas de la API es `/accounttransactions_back/api`.

## Rutas de la API

A continuación, se detallan las rutas principales de la API:

1. **Clientes**
    - **Ruta**: `/clients`
    - Maneja las operaciones relacionadas con los clientes.

2. **Cuentas**
    - **Ruta**: `/accounts`
    - Administra las operaciones de cuentas bancarias.

3. **Transacciones**
    - **Ruta**: `/transactions`
    - Permite realizar operaciones de transacciones entre cuentas.

4. **Reportes**
    - **Ruta**: `/reports`
    - Genera y devuelve los reportes relacionados con las transacciones y balances.

## Rutas de Búsqueda

- **Por Identificación del Cliente**
    - **Ruta**: `/identification/{identification}`
    - Permite consultar la información de un cliente usando su número de identificación.

- **Por Número de Cuenta**
    - **Ruta**: `/accountNumber/{accountNumber}`
    - Permite consultar la información de una cuenta bancaria usando el número de cuenta.

## Ejemplo de Configuración

A continuación se presenta un ejemplo de las variables del entorno en formato JSON:

```json
{
  "scheme": "http",
  "host": "localhost",
  "port": "8080",
  "back": "/accounttransactions_back/api",
  "clients": "/clients",
  "accounts": "/accounts",
  "transactions": "/transactions",
  "reports": "/reports",
  "byIdentificacion": "/identification/",
  "byAccountNumber": "/accountNumber/"
}

```
# AccountTransactions API - Postman Collection

## Rutas de la API

### 1. **Clientes**

#### 1.1 Crear un Cliente
- **Método**: `POST`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{clients}}`
- **Cuerpo (JSON)**:
    ```json
    {
      "email": "joselema@gmail.com",
      "age": "32",
      "password": "Ag@68hese10",
      "name": "jose",
      "lastName": "lema",
      "gender": "M",
      "identification": "1717493571",
      "address": "Otavalo sn y principal",
      "phone": "0982547851"
    }
    ```
![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/1.png?raw=true)

#### 1.2 Obtener Cliente por Identificación
- **Método**: `GET`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{clients}}{{byIdentificacion}}1717493571`

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/3.png?raw=true)

#### 1.3 Actualizar Cliente
- **Método**: `PATCH`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{clients}}{{byIdentificacion}}1717493571`
- **Cuerpo (JSON)**:
    ```json
    {
      "email": "joselematest@hotmail.com"
    }
    ```
![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/4.png?raw=true)

### 2. **Cuentas**

#### 2.1 Crear una Cuenta
- **Método**: `POST`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{accounts}}`
- **Cuerpo (JSON)**:
    ```json
    {
      "accountType": "AHORRO",
      "initialBalance": "2000.00",
      "client": {
        "identification": 1717493571
      }
    }
    ```
![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/5.png?raw=true)

#### 2.2 Obtener Cuenta por Número de Cuenta
- **Método**: `GET`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{accounts}}{{byIdentificacion}}1717493571`

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/6.png?raw=true)

#### 2.3 Actualizar Estado de la Cuenta
- **Método**: `PATCH`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{accounts}}{{byAccountNumber}}593602`

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/7.png?raw=true)

### 3. **Transacciones**

#### 3.1 Crear una Transacción
- **Método**: `POST`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{transactions}}`
- **Cuerpo (JSON)**:
    ```json
    {
      "description": "SUELDO 04 2025",
      "amount": 2500,
      "account": {
        "accountNumber": 593602
      }
    }
    ```
![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/8.png?raw=true)


### 4. **Reportes**

#### 4.1 Obtener Reporte por Identificación y Fechas
- **Método**: `GET`
- **URL**: `{{scheme}}://{{host}}:{{port}}{{back}}{{reports}}?identification=1717493571&startDate=2025-04-06&endDate=2025-04-28`
- **Parámetros de Consulta**:
    - `identification`: 1717493571
    - `startDate`: 2025-04-06
    - `endDate`: 2025-04-28

---

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/9.png?raw=true)


### 5. **Validaciones**

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/11.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/12.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/13.png?raw=true)

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/postman/14.png?raw=true)

# Base de Datos: `accounttransactions_db`

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/bd/15.png?raw=true)

## Estructura de Tablas

### 1. `person`
Contiene información personal del cliente.
- Campos: `person_id`, `address`, `age`, `gender`, `identification`, `last_name`, `name`, `phone`, `status`
- Clave primaria: `person_id`
- Clave única: `identification`

### 2. `client`
Representa al cliente como usuario del sistema, ligado a una persona.
- Campos: `email`, `password`, `person_id`
- Clave primaria: `person_id`
- Clave única: `email`
- Relación: FK a `person.person_id`

### 3. `account`
Información de las cuentas bancarias.
- Campos: `account_id`, `account_number`, `account_type`, `initial_balance`, `status`, `client_id`
- Clave primaria: `account_id`
- Clave única: `account_number`
- Relación: FK a `client.person_id`

### 4. `transaction`
Registra las transacciones realizadas en cuentas.
- Campos: `transaction_id`, `amount`, `balance`, `date`, `description`, `transaction_type`, `account_id`
- Clave primaria: `transaction_id`
- Relación: FK a `account.account_id`

## Datos Iniciales

Se incluyen datos de ejemplo para cada tabla:
- **Persona**: José Lema, masculino, 32 años, identificación `1717493571`.
- **Cliente**: Usuario con email `joselematest@hotmail.com`.
- **Cuenta**: Cuenta de ahorro con número `593602` y saldo inicial de `$2000`.
- **Transacciones**:
    - Depósito de apertura: `$2000`
    - Depósito de sueldo: `$2500`

---

# Reporte de Calidad de Código - SonarQube

## 🔐 Seguridad
- **Calificación:** A
- **Issues abiertos:** 0
- No se han detectado vulnerabilidades con impacto en la seguridad del software.

## ⚙️ Fiabilidad (Reliability)
- **Calificación:** A
- **Issues abiertos:** 0
- No hay problemas que puedan afectar la estabilidad o el correcto funcionamiento del sistema.

## 🔧 Mantenibilidad (Maintainability)
- **Calificación:** A
- **Issues abiertos:** 0
- Nivel de deuda técnica bajo en relación con el tamaño del código base.

## ✅ Issues Aceptados
- **Cantidad:** 0
- No hay problemas marcados como aceptados sin corregir.

## 🧪 Cobertura de Pruebas
- **Cobertura total:** 88.8%
- **Líneas cubiertas:** 253
- Buen nivel de pruebas automatizadas, superando el estándar recomendado del 80%.

## 🔁 Duplicación de Código
- **Porcentaje:** 0.0%
- **Líneas analizadas:** 1,700
- No se ha detectado código duplicado, lo que indica un alto nivel de reutilización y calidad estructural.

## 🔥 Puntos Críticos de Seguridad (Security Hotspots)
- **Cantidad:** 0
- No hay fragmentos de código que requieran revisión manual por posibles riesgos de seguridad.

---

## 🟢 Conclusión

El análisis indica que el código fuente es **seguro, confiable, mantenible, bien probado y libre de duplicación**. Estos resultados reflejan un excelente nivel de calidad y buenas prácticas de desarrollo.

![image](https://github.com/freddyrubentorres/accounttransactions/blob/main/img/doc/img/back/sonar/10.png?raw=true)


# 🚀 Aplicación Spring Boot con Docker

Este proyecto despliega una aplicación Spring Boot utilizando Docker, en dos etapas: una de construcción y otra de ejecución. Se utiliza OpenJDK 17 y Maven para compilar la aplicación, y una imagen final liviana para ejecutarla.

---

## 🐳 Dockerfile

### Etapa de Construcción

```dockerfile
FROM openjdk:17-slim AS build
RUN apt-get update && apt-get install -y maven
WORKDIR /app
COPY pom.xml /app
RUN mvn dependency:go-offline
COPY src /app/src
RUN mvn clean package -DskipTests
