#  🖥️🧑🏻 API de Gestión de Clientes (Prueba Técnica NTT Data) 📄

---

### Contenido
1. [Descripción](#-descripción)
2. [Tecnologías Utilizadas](#%EF%B8%8F-tecnologías-utilizadas)
3. [Configuración del Proyecto](#-configuración-del-proyecto)
4. [Documentación de la API con Swagger](#-documentación-de-la-api-con-swagger)
5. [Endpoints](#-endpoints)
6. [Ejemplos de Uso](#-ejemplos-de-uso)
7. [Base de Datos H2 (en Memoria)](#-base-de-datos-h2-en-memoria)
8. [Datos Iniciales (data.sql)](#-datos-iniciales-datasql)
9. [Configuración de la Aplicación (application.properties)](#%EF%B8%8F-configuración-de-la-aplicación-applicationproperties)
10. [Licencia](#-licencia)

---

## 📌 Descripción
API REST para gestionar información de clientes, desarrollada con **Spring Boot**. Se desarrolla como parte de una prueba técnica para **NTT Data**. Permite:
- Consultar datos de clientes por tipo/número de documento.
- Actualizar información de clientes.
- Validación de parámetros y manejo de errores HTTP.

---

## 🛠️ Tecnologías Utilizadas

| Tecnología         | Versión | Uso Principal                          |
|--------------------|---------|----------------------------------------|
| Java               | 17      | Lenguaje base                          |
| Spring Boot        | 3.3.8   | Framework principal                    |
| Spring Data JPA    | 3.3.8   | Persistencia de datos                  |
| Spring Web         | 3.3.8   | Controladores REST                     |
| Spring Validation  | 3.3.8   | Validación de datos                    |
| Spring Test        | 3.3.8   | Pruebas de integración                 |
| H2 Database        | 2.2.220 | Base de datos en memoria               |
| Lombok             | 1.18.36 | Reducción de código boilerplate        |
| Spring Doc OpenAPI | 2.3.0   | Documentación API (Swagger)            |
| Maven              | 3.9.6   | Gestión de dependencias                |
| Mockito            | 5.3.1   | Pruebas unitarias                      |

---

## 🔧 Configuración del Proyecto

### Requisitos Previos
- JDK 17 instalado
- Maven 3.8+
- Puerto 8090 disponible

### Instalación
1. Clonar repositorio:
```bash
git clone https://github.com/Bardolog1/java-spring-nttdata-prueba-tecnica.git
cd java-spring-nttdata-prueba-tecnica
```
2. Compilar:
```bash
mvn clean package
```
3. Ejecutar:
```bash
java -jar target/clientes-app-0.0.1-SNAPSHOT.jar
```

---

## 📚 Documentación de la API con Swagger

La documentación de la API se encuentra disponible en:
- Swagger UI: [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html)
- OpenAPI JSON: [http://localhost:8090/v3/api-docs](http://localhost:8090/v3/api-docs)

---

## 🔌 Endpoints

### GET `/api/clientes/{tipoDocumento}/{numeroDocumento}`
Consulta información de un cliente.

**Parámetros:**

| Parámetro         | Tipo     | Descripción                  | Valores Permitidos      |
|-------------------|----------|------------------------------|-------------------------|
| `tipoDocumento`   | Path     | Tipo de documento            | `C` (Cédula), `P` (Pasaporte) |
| `numeroDocumento` | Path     | Número de documento          | String                  |
| `withAddress`     | Query    | Incluir dirección en respuesta | `true`/`false`         |

**Respuestas:**
- `200 OK`: Datos del cliente
- `400 Bad Request`: Parámetros inválidos
- `404 Not Found`: Cliente no existe
- `500 Internal Server Error`: Error interno

---

### PUT `/api/clientes/{tipoDocumento}/{numeroDocumento}`
Actualiza información de un cliente.

**Body Request (JSON):**

```json
{
    "primerNombre": "NuevoNombre",
    "telefono": "3101234567",
    "email": "nuevo@email.com"
}
```

### Respuestas

- `200 OK` : Datos actualizados
- `400 Bad Request`: Datos inválidos
- `404 Not Found`: Cliente no existe

---

## 📖 Ejemplos de Uso

### Consultar cliente (cURL)
```bash
curl -X GET "http://localhost:8090/api/clientes/C/1023949685?withAddress=true"
```

**Respuesta Exitosa:**
```json
{
    "primerNombre": "Juan",
    "segundoNombre": "Carlos",
    "primerApellido": "Pérez",
    "segundoApellido": "Gómez",
    "telefono": "3001234567",
    "direccion": "Calle 123 # 7 - 54",
    "ciudadResidencia": "Bogotá",
    "email": "juacarpergom@ntt-data.com"
}
```

---

### Actualizar cliente (cURL)
```bash
curl -X PUT -H "Content-Type: application/json" -d '{
    "primerNombre": "Juanito",
    "email": "nuevo@email.com"
}' "http://localhost:8090/api/clientes/C/1023949685"
```

**Respuesta Exitosa:**
```json
{
    "primerNombre": "Juanito",
    "segundoNombre": "Carlos",
    "primerApellido": "Pérez",
    "segundoApellido": "Gómez",
    "telefono": "3001234567",
    "direccion": "Calle 123 # 7 - 54",
    "ciudadResidencia": "Bogotá",
    "email": "nuevo@email.com"
}
```

---

## 💾 Base de Datos H2 (en Memoria)

- **Consola H2**: [http://localhost:8090/h2-console](http://localhost:8090/h2-console)  
- **JDBC URL**: `jdbc:h2:mem:pruebaTecnica`  
- **Usuario**: `sa`  
- **Contraseña**: (vacío)

---

## 📁 Datos Iniciales (data.sql)

El archivo **`src/main/resources/data.sql`** contiene datos de prueba que se cargan automáticamente al iniciar la aplicación:

```sql
  INSERT INTO cliente (primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, numero_documento, 
  tipo_documento, telefono, direccion, ciudad_residencia, email) VALUES
  ('Juan', 'Carlos', 'Pérez', 'Gómez', '1023949685', 'C', '3001234567',
  'Calle 123 # 7 - 54', 'Bogotá', 'juacarpergom@ntt-data.com'),
  ('María', 'Fernanda', 'López', 'Gonzalez', '1033750628', 'C', '3107654321', 
  'Carrera 45 # 38 - 24 sur', 'Bogotá', 'marferlopgon@ntt-data.com'),      
  ('Pedro', NULL, 'Ramírez', 'Sánchez', 'AM47689265', 'P', '3201112233', 
  'Avenida 2 Norte #10 - 70 Santiago de Cali', 'Cali', 'pedramsan@ntt-data.com');
```

---

## ⚙️ Configuración de la Aplicación (application.properties)

### Configuración General
```properties
# Nombre de la aplicación
spring.application.name=clientes-app

# Puerto de ejecución
server.port=8090
```
### Configuración de JPA y Base de Datos
```properties
# inicialización de datos 
spring.jpa.defer-datasource-initialization=true

# Configuración de Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configuración de H2 (base de datos en memoria)
spring.datasource.url=jdbc:h2:mem:pruebaTecnica
spring.datasource.driverClassName=org.h2.Driver
spring.h2.console.enabled=true
```

### Configuración de Logging
```properties
# Nivel de logging
logging.level.root=INFO
logging.level.com.pruebatecnica.nttdata=DEBUG

# se define el archivo y formato de logs
logging.file.name=logs/app.log
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```

---

## 📝 Licencia
    
- Desarrollado por: Libardo Lozano Gambasica [Bardolog1](https://github.com/Bardolog1)
- Licencia: [MIT](https://choosealicense.com/licenses/mit/)


----




