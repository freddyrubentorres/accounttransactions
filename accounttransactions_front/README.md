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

## **Configuración del Entorno**

El archivo de configuración `environment.ts` se encuentra en el directorio `src/environments/` y contiene las URLs base para las solicitudes HTTP realizadas desde el frontend.

### **Variables de Entorno**

Las variables definidas en `environment.ts` son:

- **`production`**: Booleano que indica si el entorno está en modo producción. En este archivo de configuración, el valor es `false`, ya que está configurado para desarrollo local.
  
- **`baseUrlgetClients`**: URL base para obtener la información de los clientes. Ejemplo: `http://localhost:8080/accounttransactions_back/api/clients/identification/`.
  
- **`baseUrlpostClients`**: URL base para crear nuevos clientes. Ejemplo: `http://localhost:8080/accounttransactions_back/api/clients`.
  
- **`baseUrlgetReports`**: URL base para obtener reportes de transacciones. Ejemplo: `http://localhost:8080/accounttransactions_back/api/reports`.
  
- **`baseUrlgetAccount`**: URL base para obtener información sobre cuentas por su número. Ejemplo: `http://localhost:8080/accounttransactions_back/api/accounts/accountNumber/`.
  
- **`baseUrlpostTransaction`**: URL base para realizar una transacción. Ejemplo: `http://localhost:8080/accounttransactions_back/api/transactions`.
  
- **`baseUrlpostAccount`**: URL base para crear una nueva cuenta. Ejemplo: `http://localhost:8080/accounttransactions_back/api/accounts`.

### **Uso de las Variables de Entorno**

Estas variables de entorno se importan directamente en los servicios de la aplicación Angular. Por ejemplo, en el servicio `TransactionService`, se utilizan las siguientes URLs:

- **`getAccountNumber`** utiliza `baseUrlgetAccount` para realizar una solicitud GET para obtener los datos de una cuenta.
- **`createTransaction`** utiliza `baseUrlpostTransaction` para enviar los datos de una transacción al backend.

Estas URLs se encuentran configuradas en el archivo `environment.ts` para facilitar el desarrollo y la modificación de la URL del backend en un solo lugar.

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