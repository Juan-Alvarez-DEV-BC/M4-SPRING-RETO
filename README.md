# M4-SPRING-RETO

---
# Documentación de la API para la Gestión de Cuentas Bancarias

Esta API proporciona endpoints para realizar operaciones básicas en cuentas bancarias, como la creación de cuentas, consultas de saldo, depósitos y retiros.

---
#### 1. Consulta de Saldo
- **Endpoint:** `GET /cuenta/saldo`
- **Descripción:** Consulta el saldo de una cuenta bancaria.
- **Cuerpo de la solicitud:**
  ```json
  {
    "nroCuenta": "1111"
  }
  ```
- **Encabezados:**
	- `Content-Type: application/json`
	- `User-Agent: insomnia/10.1.0`
- **Respuesta:** Retorna el saldo actual de la cuenta especificada.

---

#### 2. Creación de Cuenta
- **Endpoint:** `POST /cuenta/crear-cuenta`
- **Descripción:** Crea una nueva cuenta bancaria.
- **Tipos de cuenta:**
	- `BASICA`
	- `PREMIUM`
- **Cuerpo de la solicitud (ejemplo - Cuenta básica):**
  ```json
  {
    "nroCuenta": "33332",
    "titular": "Patricio Estrella",
    "monto": "1000.30",
    "tipoCuenta": "BASICA"
  }
  ```
  
  ```json
  {
    "nroCuenta":"77773",
    "titular":"Calamardo Tentaculos",
    "monto":"2500.30",
    "tipoCuenta":"PREMIUM"
  }
  ```

- **Encabezados:**
	- `Content-Type: application/json`
	- `User-Agent: insomnia/10.1.0`
- **Respuesta:** Confirmación de la cuenta creada.

---

#### 3. Depósitos
- **Endpoint:** `POST /cuenta/deposito`
- **Descripción:** Realiza un depósito en la cuenta especificada.
- **Origen de la transacción:**
	- `CAJERO`
	- `SUCURSAL`
	- `OTRA_CUENTA`
- **Cuerpo de la solicitud (ejemplo - depósito desde cajero):**
  ```json
  {
    "nroCuenta": "2222",
    "monto": "5000",
    "origenTRX": "CAJERO"
  }
  ```
- **Encabezados:**
	- `Content-Type: application/json`
	- `User-Agent: insomnia/10.1.0`
- **Respuesta:** Confirmación del depósito.

---

#### 4. Retiro
- **Endpoint:** `POST /cuenta/retiro`
- **Descripción:** Realiza un retiro de la cuenta especificada.
- **Origen de la transacción:**
	- `FISICO`
	- `OTRA_CUENTA`
	- `CAJERO`
- **Cuerpo de la solicitud (ejemplo - retiro físico):**
  ```json
  {
    "nroCuenta": "2222",
    "monto": "500",
    "origenTRX": "FISICO"
  }
  ```
- **Encabezados:**
	- `Content-Type: application/json`
	- `User-Agent: insomnia/10.1.0`
- **Respuesta:** Confirmación del retiro.

---

#### 5. Listar Transacciones
- **Endpoint:** `POST /cuenta/listar-transacciones`
- **Descripción:** Lista las últimas transacciones de una cuenta.
- **Cuerpo de la solicitud:**
  ```json
  {
    "nroCuenta": "1111"
  }
  ```
- **Opcional:** Se puede limitar la cantidad de transacciones añadiendo el parámetro `cantidad` en la URL; 
 	-  Si no se especifica la cantidad, por defecto entrega 5 transacciones
	   URL con Cantidad Específica
	   ```bash 
	   POST {{ _.local }}/cuenta/listar-transacciones?cantidad=7
       ```
	   Lista las últimas 7 transacciones de una cuenta bancaria.
- **Encabezados:**
	- `Content-Type: application/json`
	- `User-Agent: insomnia/10.1.0`
- **Respuesta:** Lista de transacciones recientes de la cuenta.

---

Esta API sigue un formato JSON en los cuerpos de las solicitudes y requiere el encabezado `Content-Type: application/json`.

---
---
# Reto: Aplicación de Gestión de Cuenta Bancaria con SpringBoot y PostgreSQL
> **Larry M. Ramírez - Coach Técnico**

## Objetivo.
Crear una aplicación Spring Boot que permita la gestión de cuentas bancarias, aplicando los conceptos vistos durante el módulo: Spring Boot, Inyección de Dependencias, Spring MVC, manejo de excepciones, y JPA con PostgreSQL. El sistema debe permitir realizar operaciones bancarias sobre diferentes tipos de cuentas, gestionando el saldo, registrando las transacciones, y almacenando la información en una base de datos PostgreSQL.

## Funcionalidades Principales
1. Cuentas Bancarias:
   Debes crear una clase abstracta y dos clases hijas:
	- `Cuenta`: representa la cuenta bancaria básica, con los siguientes atributos:
		- `id`: Identificador único (generado automáticamente).
		- `saldo`: Saldo actual de la cuenta.
		- `numeroCuenta`: Número único de cuenta.
		- `listaTransacciones`: Historial de transacciones de la cuenta.
	- Utiliza herencia para definir dos tipos de cuentas:
		- `CuentaBásica`: cuenta estándar con comisiones por algunas operaciones.
		- `CuentaPremium`: cuenta sin comisiones adicionales.
2. Operaciones sobre las cuentas: Implementa las siguientes operaciones que afectarán el saldo de las cuentas. Utiliza polimorfismo para que las reglas cambien según el tipo de cuenta:
	- **Depósito desde sucursal**: sin costo.
	- **Depósito desde cajero automático**: costo de 2 USD (solo en `CuentaBásica`).
	- **Depósito desde otra cuenta**: costo de 1.5 USD.
	- **Compra en establecimiento físico**: sin costo.
	- **Compra en página web**: costo de 5 USD por seguro contra robos.
	- **Retiro en cajero**: costo de 1 USD (solo en `CuentaBásica`).
3. Historial de Transacciones: Al realizar cualquier operación, se debe registrar la transacción en una tabla de historial en PostgreSQL. Cada transacción debe tener:
	- Tipo de transacción (depósito, retiro, compra).
	- Monto de la operación.
	- Fecha y hora de la transacción (autogenerados por el sistema).
	- Código único de transacción.
4. Consultas:
	- Permitir al usuario consultar el saldo actual de su cuenta.
	- Consultar el historial de las últimas 5 transacciones realizadas (ordenadas por fecha).
## Requerimientos Técnicos
1. Base de Datos:
	- Utiliza PostgreSQL para almacenar la información de cuentas y transacciones.
	- Configura el acceso a PostgreSQL en el archivo `application.properties`.
2. SpringBoot y JPA:
	- Utiliza SpringBoot con JPA para el acceso a la base de datos y las operaciones CRUD sobre cuentas y transacciones.
	- Implementa controladores REST para cada funcionalidad: operaciones, consultas, y historial.
3. Manejo de Excepciones:
	- Implementa manejo de excepciones para errores comunes como `CuentaNoEncontrada`, `SaldoInsuficiente`, y `TransacciónNoPermitida`.

**Nota:** Al igual que los talleres, el reto debe entregarse por medio de la estrategia establecida para la formación, por medio de un Pull Request desde el repositorio Fork hacia la rama main del repositorio del reto.
