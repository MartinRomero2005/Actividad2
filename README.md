# API REST de Recetas - Actividad 2

## Descripción

Esta aplicación es una API REST desarrollada con Spring Boot para gestionar recetas de cocina.

En esta segunda actividad se incorporó persistencia de datos mediante Spring Data JPA, Hibernate y una base de datos H2. La API permite realizar operaciones CRUD sobre las recetas y cuenta con una consulta personalizada para buscar recetas por nombre.

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- H2 Database
- Maven

## Estructura principal

```text
src/
└── main/
    ├── java/
    │   └── martinromero/
    │       └── actividad2/
    │           ├── Actividad2Application.java
    │           ├── controller/
    │           │   └── RecetaController.java
    │           ├── model/
    │           │   └── Receta.java
    │           └── Repository/
    │               └── RecetaRepository.java
    │
    └── resources/
        └── application.properties
```

## Requisitos

Para ejecutar el proyecto se necesita:

- Java JDK 17 o superior.
- Un programa para realizar peticiones HTTP, como Postman.
- Maven no es obligatorio, ya que el proyecto incluye Maven Wrapper.

## Ejecución del proyecto

1. Clonar o descargar el repositorio.
2. Abrir una terminal en la carpeta principal del proyecto.
3. Ejecutar:

```bash
.\mvnw.cmd spring-boot:run
```

La aplicación se ejecutará en:

```text
http://localhost:8080
```

## Endpoints

### Obtener todas las recetas

```http
GET /api/recetas
```

Devuelve todas las recetas almacenadas.

**Respuesta:** `200 OK`

### Obtener una receta por ID

```http
GET /api/recetas/{id}
```

Ejemplo:

```http
GET /api/recetas/1
```

**Respuestas:**

- `200 OK` si la receta existe.
- `404 Not Found` si no existe.

### Crear una receta

```http
POST /api/recetas
```

Ejemplo de cuerpo JSON:

```json
{
    "nombre": "Arroz con pollo",
    "descripcion": "Arroz preparado con pollo y verduras",
    "categoria": "Almuerzo",
    "tiempoPreparacion": 45
}
```

**Respuesta:** `201 Created`

### Buscar recetas por nombre

```http
GET /api/recetas/buscar?nombre=pollo
```

Permite buscar recetas cuyo nombre contenga el texto indicado, sin distinguir entre mayúsculas y minúsculas.

**Respuesta:** `200 OK`

### Actualizar una receta

```http
PUT /api/recetas/{id}
```

Ejemplo:

```http
PUT /api/recetas/1
```

Cuerpo JSON:

```json
{
    "nombre": "Arroz con pollo especial",
    "descripcion": "Arroz con pollo y verduras",
    "categoria": "Almuerzo",
    "tiempoPreparacion": 50
}
```

**Respuestas:**

- `200 OK` si la receta existe y fue actualizada.
- `404 Not Found` si no existe.

### Eliminar una receta

```http
DELETE /api/recetas/{id}
```

Ejemplo:

```http
DELETE /api/recetas/1
```

**Respuestas:**

- `204 No Content` si la receta fue eliminada.
- `404 Not Found` si no existe.

## Persistencia

La aplicación utiliza una base de datos H2 configurada en modo archivo.

La información se almacena de manera persistente, por lo que los registros creados pueden ser consultados posteriormente, incluso después de reiniciar la aplicación.

La configuración de la base de datos se encuentra en:

```text
src/main/resources/application.properties
```

## Prueba de funcionamiento

Las operaciones de la API pueden probarse utilizando Postman.

Se recomienda realizar las pruebas en el siguiente orden:

1. Crear una receta mediante `POST`.
2. Consultar las recetas mediante `GET`.
3. Consultar la receta creada utilizando su ID.
4. Buscar recetas mediante la consulta personalizada.
5. Actualizar una receta mediante `PUT`.
6. Eliminar una receta mediante `DELETE`.
7. Volver a consultar los registros para comprobar los cambios y la persistencia.

## Autor

Martín Elías Romero Arrieta

Actividad colaborativa #2 - API REST con persistencia y operaciones CRUD.
