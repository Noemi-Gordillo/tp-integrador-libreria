# 📚 API Biblioteca — Sistema de Gestión de Librería


Alumna: **Gordillo Noemi Cristina** 

Enunciado elegido: Biblioteca

---

##  Cómo correr el proyecto

### Requisitos

- Java 21 instalado
- Maven **no es necesario** — el proyecto incluye el wrapper `mvnw.cmd`

### Levantar el backend

```powershell
# Desde la carpeta del proyecto
.\mvnw.cmd spring-boot:run
```
Al ejecutar este comando se levanta el servidor y queda habilitada toda la API.
La base de datos H2 funciona en memoria, por lo que al reiniciar el backend se eliminan los registros cargados. 
Para probar los préstamos primero deben existir un socio y un libro en la base.

El servidor queda en disponible en **`http://localhost:8085`**.

### Consola H2 (Base de datos en memoria)
Con el backend corriendo, ingresá en el navegador:
**http://localhost:8085/h2‑console**
JDBC URL: `jdbc:h2:mem:libreria`

> Para detenerlo: `Ctrl + C` en la terminal donde está corriendo.

### Consola H2 (base de datos en memoria)

Con el backend corriendo, abrí en el navegador:

```
http://localhost:8085/h2-console
```

| Campo    | Valor                  |
|----------|------------------------|
| JDBC URL | `jdbc:h2:mem:libreria` |
| User     | `sa`                   |
| Password | (vacío)                |

Al completar estos campos y presionar Conectar, se pueden ver las tablas de la librería y ejecutar consultas SQL para revisar los registros guardados en la base de datos en memoria.

---

##  Rutas de la API

Base URL: `http://localhost:8085`

### Libros

| Método | Ruta                       | Descripción                      |
|--------|----------------------------|----------------------------------|
| GET    | `/api/libros`              | Listar todos los libros          |
| GET    | `/api/libros?disponible=true` | Listar solo libros disponibles |
| GET    | `/api/libros/{id}`         | Buscar libro por ID              |
| POST   | `/api/libros`              | Crear un libro                   |
| PUT    | `/api/libros/{id}`         | Modificar un libro               |
| DELETE | `/api/libros/{id}`         | Borrar un libro                  |

### Socios

| Método | Ruta                        | Descripción               |
|--------|-----------------------------|---------------------------|
| GET    | `/api/socios`               | Listar todos los socios   |
| GET    | `/api/socios/{id}`          | Buscar socio por ID       |
| GET    | `/api/socios/{id}/prestamos`| Préstamos de un socio     |
| POST   | `/api/socios`               | Crear un socio            |
| PUT    | `/api/socios/{id}`          | Modificar un socio        |
| DELETE | `/api/socios/{id}`          | Borrar un socio           |

### Préstamos

| Método | Ruta                             | Descripción                 |
|--------|----------------------------------|-----------------------------|
| GET    | `/api/prestamos`                 | Listar todos los préstamos  |
| GET    | `/api/prestamos/{id}`            | Buscar préstamo por ID      |
| POST   | `/api/prestamos`                 | Crear un préstamo           |
| PATCH  | `/api/prestamos/{id}/renovar`    | Renovar préstamo (+7 días)  |
| PATCH  | `/api/prestamos/{id}/devolver`   | Devolver un préstamo        |

### Turnos

| Método | Ruta             | Descripción             |
|--------|------------------|-------------------------|
| GET    | `/api/turnos`    | Listar todos los turnos |
| GET    | `/api/turnos/{id}` | Buscar turno por ID   |
| POST   | `/api/turnos`    | Crear un turno          |

---

##  Ejemplos de uso con curl

### Libros

```bash
# Listar todos
curl http://localhost:8085/api/libros

# Solo disponibles
curl "http://localhost:8085/api/libros?disponible=true"

# Buscar por ID
curl http://localhost:8085/api/libros/1

# Crear
curl -X POST http://localhost:8085/api/libros \
  -H "Content-Type: application/json" \
  -d '{"id":2,"isbn":"9789500765220","titulo":"El amor en tiempos de colera","edicion":"1ra","editorial":"Sudamericana","autores":"Gabriel Garcia Marquez"}'

# Modificar
curl -X PUT http://localhost:8085/api/libros/2 \
  -H "Content-Type: application/json" \
  -d '{"id":2,"isbn":"9789500765220","titulo":"Titulo nuevo","edicion":"2da","editorial":"Sudamericana","autores":"Gabriel Garcia Marquez"}'

# Borrar
curl -X DELETE http://localhost:8085/api/libros/2
```

### Socios

```bash
# Listar todos
curl http://localhost:8085/api/socios

# Buscar por ID
curl http://localhost:8085/api/socios/1

# Ver préstamos del socio
curl http://localhost:8085/api/socios/1/prestamos

# Crear
curl -X POST http://localhost:8085/api/socios \
  -H "Content-Type: application/json" \
  -d '{"id":1,"nombre":"Juan Perez","email":"juan@test.com"}'

# Modificar
curl -X PUT http://localhost:8085/api/socios/1 \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Juan Carlos Perez","email":"juancarlos@test.com"}'

# Borrar
curl -X DELETE http://localhost:8085/api/socios/1
```

### Préstamos

```bash
# Listar todos
curl http://localhost:8085/api/prestamos

# Buscar por ID
curl http://localhost:8085/api/prestamos/1

# Crear  (el socio y el libro deben existir primero)
curl -X POST http://localhost:8085/api/prestamos \
  -H "Content-Type: application/json" \
  -d '{"id":1,"libroId":1,"socioId":1,"fechaInicio":"2026-11-15","fechaDevolucion":"2026-11-22"}'

# Renovar (+7 días)
curl -X PATCH http://localhost:8085/api/prestamos/1/renovar

# Devolver
curl -X PATCH http://localhost:8085/api/prestamos/1/devolver
```

### Turnos

```bash
# Listar todos  (IDs 1 y 2 vienen precargados)
curl http://localhost:8085/api/turnos

# Buscar por ID
curl http://localhost:8085/api/turnos/1

# Crear
curl -X POST http://localhost:8085/api/turnos \
  -H "Content-Type: application/json" \
  -d '{"id":3,"nombre":"Carlos Lopez","horario":"14:00 - 15:00"}'
```

---

##  Reglas de negocio — Préstamos

Al crear un préstamo se valida:

1. El socio y el libro deben existir en el sistema.
2. Un socio no puede tener más de 3 préstamos activos simultáneos.
3. Un libro no puede estar prestado a más de una persona a la vez.

Si alguna regla falla, el sistema retorna el codigo `409 Conflict` con el mensaje de error correspondiente en el body.

---

##  Interfaz web de rutas

Abrí el archivo **`API_Rutas.html`** (en la raíz del proyecto) en el navegador para probar cada endpoint con un clic, con el backend corriendo.

---

##  Pruebas y estado actual del proyecto
 Se implementaron las rutas API para Libros, Socios, Préstamos y Turnos, junto con ejemplos de prueba mediante curl.
 Las reglas de negocio para la creación de préstamos validan la existencia del socio y el libro, el límite de tres préstamos activos por socio y la disponibilidad del libro; en caso de incumplir alguna regla se retorna el código `409 Conflict` con el mensaje de error correspondiente.

---

 ##  Conclusión
 El proyecto integrador de biblioteca está desarrollado bajo una arquitectura por capas, contando con operaciones CRUD para las entidades principales, reglas de negocio definidas para la gestión de préstamos, base de datos H2 en memoria, ejemplos curl para testear cada endpoint y una interfaz web `API_Rutas.html` para facilitar las pruebas. 
 
 El sistema cumple con los requisitos solicitados en el enunciado del trabajo práctico, realizando las validaciones necesarias al momento de registrar un préstamo y retornando los códigos de error correspondientes cuando se infringe una regla de negocio. Para la entrega final se recomienda levantar el backend y comprobar el funcionamiento completo de todas las rutas.
