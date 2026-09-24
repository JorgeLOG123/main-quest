# 🎮 Main Quest

Una aplicación web en Java que convierte la búsqueda del primer empleo en un juego de progresión. El usuario registra acciones reales de su desarrollo profesional, gana experiencia (XP), sube de nivel y avanza en su carrera.

Proyecto desarrollado como parte del Programa Intensivo de programación en Java.

---

## 🌿 Evolución del proyecto

Este repositorio está organizado en ramas que documentan la evolución del proyecto, semana a semana. Cada una parte de la anterior e incorpora los conceptos vistos en esa etapa del programa, de modo que el historial funciona como registro del proceso de aprendizaje.

| Rama | Etapa |
|---|---|
| `master` | Versión estable |
| `aplicando-solid` | Refactor aplicando principios SOLID |
| `aplicando-lo-visto-semana-5` | Migración a Spring Boot y persistencia con JPA |
| `semana-7-excepciones-testing` | Manejo de excepciones y tests unitarios y de integración |
| `semana-8-FrontEnd` | Interfaz web con Spring MVC, Thymeleaf y validaciones del servidor |

Para ver el proyecto en un punto específico de su desarrollo, basta con cambiar de rama:

```bash
git checkout aplicando-solid
```

---

## 📖 Sobre el proyecto

Buscar el primer empleo es un proceso largo, frustrante y lleno de rechazos. La mayoría de las personas sabe qué tiene que hacer (personalizar el CV, practicar entrevistas, hacer seguimiento de postulaciones), pero no logra sostener la constancia cuando los resultados no llegan de inmediato.

Main Quest ataca ese problema con mecánicas de videojuego: cada acción concreta se convierte en una misión que otorga XP. El progreso se vuelve visible y medible, incluso cuando todavía no hay una oferta de trabajo del otro lado.

### Tipos de misión

| Tipo | Descripción | XP |
|---|---|---|
| General | Acciones puntuales del día a día | XP base |
| Semanal | Objetivos de mayor esfuerzo | XP base × 2 |

Cada 100 XP acumulados, el jugador sube un nivel.

---

## 🛠️ Stack

- **Java 21**
- **Spring Boot 3.5.x** — Spring MVC y Spring Data JPA
- **Thymeleaf** + Thymeleaf Layout Dialect
- **Hibernate 6.6**
- **Bootstrap 5** (estilos de la interfaz)
- **MySQL 8.0**
- **Maven**
- **JUnit 5** para los tests

---

## 📋 Requisitos previos

- JDK 21 o superior
- Maven 3.6+
- MySQL Server 8.0+
- Un cliente para administrar la base: MySQL Workbench, el MySQL Command Line Client, o las Database Tools de IntelliJ IDEA Ultimate

---

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/JorgeLOG123/main-quest.git
cd main-quest
```

### 2. Crear las bases de datos

La aplicación usa dos bases con el mismo esquema: una para el uso normal y otra exclusiva para los tests automáticos. Separarlas evita que los datos de prueba se mezclen con los reales.

```sql
CREATE DATABASE main_quest;
CREATE DATABASE main_quest_test;
```

Luego ejecutá el script `src/main/resources/database/create_db.sql` **sobre cada una** de las dos bases. Desde MySQL Workbench: `File → Open SQL Script`, seleccionar el archivo y ejecutar con ⚡.

> ⚠️ Antes de ejecutar el script, verificá qué base está activa (aparece en negrita en el panel de schemas). Es el error más común: correr el script sobre la base equivocada.

Opcionalmente, cargá los datos iniciales con `init_db.sql`. Para volver a empezar de cero, `drop_db.sql` elimina las tablas en el orden correcto según sus dependencias.

### 3. Crear el usuario de la aplicación

Por seguridad, la aplicación no se conecta con `root`, sino con un usuario acotado a estas bases y con permisos mínimos:

```sql
CREATE USER 'main_quest_user'@'localhost' IDENTIFIED BY 'tu_contraseña';
GRANT SELECT, INSERT, UPDATE, DELETE ON main_quest.* TO 'main_quest_user'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON main_quest_test.* TO 'main_quest_user'@'localhost';
FLUSH PRIVILEGES;
```

Ese usuario puede leer y escribir datos, pero no modificar el esquema: las tareas administrativas quedan reservadas para `root`.

### 4. Configurar la conexión

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/main_quest
spring.datasource.username=main_quest_user
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
```

Y el archivo equivalente para los tests, en `src/test/resources/application-test.properties`, idéntico salvo por la base:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/main_quest_test
```

> **`ddl-auto=none`** le indica a Hibernate que no toque el esquema. Las tablas se crean con los scripts SQL, que son la única fuente de verdad de la estructura.

> ⚠️ Ambos archivos están excluidos del control de versiones mediante `.gitignore`. Nunca los subas al repositorio: contienen credenciales.

### 5. Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

Con la aplicación levantada, abrí **http://localhost:8080** en el navegador.

---

## 🌐 La aplicación web

La interfaz está construida con Spring MVC y Thymeleaf, siguiendo el recorrido **Controller → Service → Repository** para cada funcionalidad.

### Rutas disponibles

| Ruta | Método | Descripción |
|---|---|---|
| `/` | GET | Redirige al home |
| `/home` | GET | Página principal |
| `/registro` | GET | Formulario de registro |
| `/registro` | POST | Procesa el alta del jugador |
| `/login` | GET | Formulario de ingreso |
| `/login` | POST | Procesa el ingreso |

Los `POST` responden siempre con una redirección (patrón **Post/Redirect/Get**), de modo que refrescar la página no reenvía el formulario. La excepción son los casos con errores de validación, que devuelven la vista para conservar los mensajes y los datos ya escritos.

### Validaciones del servidor

Cada formulario tiene su propia clase validadora, que implementa la interfaz `Validator` de Spring. El controlador no contiene reglas: solo las dispara y reacciona al resultado.

```
Formulario  →  transporta los datos de la vista
Validator   →  aplica las reglas y anota los errores
Controller  →  consulta si hubo errores y decide a dónde ir
Service     →  lógica de negocio y acceso a datos
```

El flujo completo usa `@InitBinder` para asociar el validador al controlador, `@Validated` para disparar la validación y `BindingResult` para recibir los errores. En la vista, `th:errorclass` marca el campo y `th:errors` muestra el mensaje.

**Reglas implementadas**

| Campo | Registro | Login |
|---|---|---|
| Nombre | Obligatorio, máximo 50 caracteres, no repetido | — |
| Email | Obligatorio, máximo 255 caracteres, no registrado previamente | Obligatorio, debe existir |
| Contraseña | Obligatoria, mínimo 5 caracteres | Obligatoria |

Los mensajes de error viven en `messages.properties`, con soporte para parámetros (`{0}`), de modo que los límites de longitud se definen una sola vez como constantes en la entidad y se reutilizan tanto en la comparación como en el texto que ve el usuario.

---

## 🗄️ Modelo de datos

```
JUGADORES                MISIONES                 progreso_mision
─────────────            ─────────────            ─────────────────
ID          PK           ID          PK           id            PK
nombre      UNIQUE       nombre                   id_jugador    FK → JUGADORES
contrasena               XP                       id_mision     FK → MISIONES
email       UNIQUE       TIPO                     estado
XP (default 0)
```

**Sobre las restricciones de unicidad:** `nombre` y `email` identifican a cada jugador, así que son únicos. La contraseña **no** lo es, y es deliberado: dos jugadores pueden elegir la misma sin ningún problema. Si fuera única, el registro fallaría cada vez que alguien repitiera una contraseña ya usada, y ese error le estaría confirmando a un desconocido que esa contraseña existe en el sistema.

**Sobre la columna `TIPO`:** actúa como discriminador de la jerarquía de misiones. `MisionGeneral` y `MisionSemanal` comparten la tabla `MISIONES` y se distinguen por el valor almacenado ahí (`GENERAL` o `SEMANAL`), siguiendo la estrategia `SINGLE_TABLE` de JPA.

**Sobre `estado`:** enumeración con los valores `EN_CURSO` y `COMPLETADA`, mapeada con `@Enumerated(EnumType.STRING)`.

---

## 📁 Estructura del proyecto

```
src/main/
├── java/arg/jorge/mainquest/
│   ├── Principal.java                    # Punto de entrada (@SpringBootApplication)
│   ├── controllers/
│   │   ├── BaseController.java           # Constantes y redirect() comunes
│   │   ├── AppController.java            # Ruta raíz
│   │   ├── HomeController.java
│   │   ├── RegistracionController.java
│   │   └── LoginController.java
│   ├── domain/                           # Entidades JPA
│   │   ├── Persistible.java              # @MappedSuperclass con el @Id
│   │   ├── Jugador.java
│   │   ├── Mision.java                   # @Entity abstracta
│   │   ├── MisionGeneral.java
│   │   ├── MisionSemanal.java
│   │   ├── ProgresoDeMision.java
│   │   └── AuthServices.java
│   ├── forms/                            # Objetos de transporte vista ↔ controlador
│   │   ├── RegistracionForm.java
│   │   └── LoginForm.java
│   ├── Validators/
│   │   ├── RegistracionFormValidator.java
│   │   └── LoginFormValidator.java
│   ├── exceptions/
│   │   └── MultipleJugadoresFoundException.java
│   ├── services/                         # Interfaces + implementaciones (@Service)
│   ├── repositories/                     # Spring Data JPA
│   └── Enums/
└── resources/
    ├── database/
    │   ├── create_db.sql
    │   ├── init_db.sql
    │   └── drop_db.sql
    ├── html/
    │   ├── layouts/
    │   │   ├── default.html              # Layout base
    │   │   └── fragments/                # navbar, header, footer, scripts
    │   ├── home.html
    │   ├── login.html
    │   └── signup.html
    ├── static/                           # css, js e imágenes
    ├── messages.properties               # Textos e idioma de la interfaz
    └── application.properties.example
```

### Decisiones de diseño

**`BaseController`** centraliza lo que todos los controladores necesitan: la constante `FORM_ATTRIBUTE` con la que se publica cada formulario en el modelo, y el método `redirect(path)`. Así, cada controlador nuevo nace con lo común ya resuelto y todas las vistas usan el mismo `th:object="${form}"`.

**Los validadores viven aparte.** El controlador maneja el flujo (qué vista o redirección devolver), no las reglas. Esa separación mantiene los controladores cortos y permite reutilizar un validador desde cualquier punto de entrada.

**Interfaz + implementación en los servicios.** Los controladores dependen de la interfaz (`JugadorServices`), no de la clase concreta. La implementación puede cambiar sin tocar nada del resto.

**`Persistible`** centraliza el `@Id` y su estrategia de generación. Al anotarla con `@MappedSuperclass`, todas las entidades heredan el identificador sin repetir el código.

**Herencia en las misiones.** `Mision` es abstracta y define `calcularXpFinal()`. Cada subclase implementa su propia regla de cálculo, lo que permite agregar nuevos tipos de misión sin modificar el código existente.

**Textos fuera del código.** Ningún texto de la interfaz está escrito en Java ni en el HTML: todos salen de `messages.properties` mediante `th:text="#{clave}"`. Traducir la aplicación es agregar un archivo.

---

## 🧪 Tests

```bash
mvn test
```

El proyecto combina dos tipos de prueba:

- **Unitarios** (`JugadorEntityTest`, `MisionEntityTest`): verifican el comportamiento de una clase aislada, creada con `new`. Se ejecutan en milisegundos.
- **De integración** (`JugadorServicesTest`): levantan el contexto de Spring con `@SpringBootTest` para probar servicio, repositorio y base de datos trabajando juntos.

Los tests de integración usan `@ActiveProfiles("test")` para apuntar a `main_quest_test`, y `@Transactional` para que cada test haga *rollback* al terminar. Así la base queda igual que antes y los tests se pueden repetir infinitas veces con el mismo resultado.

---

## 🔧 Problemas frecuentes

**Access denied for user** — Las credenciales de `application.properties` no coinciden con las de MySQL. Verificá usuario y contraseña probándolos primero en Workbench.

**Failed to configure a DataSource** — Spring no encuentra el archivo de configuración. Confirmá que se llame exactamente `application.properties` y esté en `src/main/resources/`.

**Communications link failure** — El servidor MySQL no está corriendo. Iniciá el servicio.

**Table doesn't exist** — Faltó ejecutar `create_db.sql`, o la URL apunta a otra base. Verificá el final de `spring.datasource.url`.

**Los datos no aparecen en Workbench** — Casi siempre son dos cosas: la consulta se está haciendo sobre la otra base (`main_quest` vs `main_quest_test`), o la grilla no se refrescó. Workbench no se actualiza solo.

**TemplateInputException: error during template parsing** — HTML mal formado en la vista: una etiqueta sin cerrar o unas comillas abiertas. Thymeleaf es estricto con esto, a diferencia del navegador.

**`??clave_es??` en pantalla** — Falta esa clave en `messages.properties`, o está escrita distinto.

---

## 📌 Estado actual

**Implementado**

- Interfaz web con Spring MVC y Thymeleaf, con layout reutilizable
- Registro de jugadores con persistencia real en MySQL
- Validaciones del lado del servidor en registro y login, con mensajes por campo
- Internacionalización de todos los textos mediante `messages.properties`
- Sistema de misiones con herencia y cálculo diferenciado de XP
- Cálculo de niveles según XP acumulada
- Persistencia mediante Spring Data JPA
- Tests unitarios y de integración con ambiente separado

**En desarrollo**

- Verificación de contraseña en el login
- Hash de contraseñas (la columna ya está dimensionada para BCrypt)
- Manejo de sesión del jugador autenticado
- Historial de progreso por jugador
- Vistas de misiones y ranking

---

## 👤 Autor

[@JorgeLOG123](https://github.com/JorgeLOG123)
