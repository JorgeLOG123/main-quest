# 🎮 Main Quest

Una aplicación en Java que convierte la búsqueda del primer empleo en un juego de progresión. El usuario registra acciones reales de su desarrollo profesional, gana experiencia (XP), sube de nivel y avanza en su carrera.

> Proyecto desarrollado como parte del Programa Intensivo de programación en Java.

---

## 🌿 Evolución del proyecto

Este repositorio está organizado en ramas que documentan la evolución del proyecto, semana a semana. Cada una parte de la anterior e incorpora los conceptos vistos en esa etapa del programa, de modo que el historial funciona como registro del proceso de aprendizaje.

| Rama | Etapa |
|---|---|
| `master` | Versión estable |
| `aplicando-solid` | Refactor aplicando principios SOLID |
| `aplicando-lo-visto-semana-5` | Migración a Spring Boot y persistencia con JPA |

Para ver el proyecto en un punto específico de su desarrollo, basta con cambiar de rama:

```bash
git checkout aplicando-solid
```

---

## 📖 Sobre el proyecto

Buscar el primer empleo es un proceso largo, frustrante y lleno de rechazos. La mayoría de las personas sabe *qué* tiene que hacer (personalizar el CV, practicar entrevistas, hacer seguimiento de postulaciones), pero no logra sostener la constancia cuando los resultados no llegan de inmediato.

Main Quest ataca ese problema con mecánicas de videojuego: cada acción concreta se convierte en una misión que otorga XP. El progreso se vuelve visible y medible, incluso cuando todavía no hay una oferta de trabajo del otro lado.

### Tipos de misión

| Tipo | Descripción | XP |
|---|---|---|
| **General** | Acciones puntuales del día a día | XP base |
| **Semanal** | Objetivos de mayor esfuerzo | XP base × 2 |

Cada 100 XP acumulados, el jugador sube un nivel.

---

## 🛠️ Stack

- **Java 21**
- **Spring Boot 3.5.16**
- **Spring Data JPA** (Hibernate 6.6)
- **MySQL 8.0**
- **Maven**

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

### 2. Crear la base de datos

Conectate a MySQL como un usuario con permisos administrativos y creá la base:

```sql
CREATE DATABASE main_quest;
```

Luego ejecutá el script de creación de tablas, que está en `src/main/resources/database/create_db.sql`. Desde MySQL Workbench: **File → Open SQL Script**, seleccionar el archivo y ejecutar con el botón ⚡.

Opcionalmente, cargá los datos iniciales con `init_db.sql`.

> Para volver a empezar de cero, `drop_db.sql` elimina las tablas en el orden correcto según sus dependencias.

### 3. Crear el usuario de la aplicación

Por seguridad, la aplicación no se conecta con `root`, sino con un usuario acotado a esta base y con permisos mínimos:

```sql
CREATE USER 'main_quest_user'@'localhost' IDENTIFIED BY 'tu_contraseña';
GRANT SELECT, INSERT, UPDATE, DELETE ON main_quest.* TO 'main_quest_user'@'localhost';
FLUSH PRIVILEGES;
```

Ese usuario puede leer y escribir datos, pero no modificar el esquema — las tareas administrativas quedan reservadas para `root`.

### 4. Configurar la conexión

Copiá la plantilla de configuración y completala con tus credenciales:

```bash
cp src/main/resources/application.properties.example src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/main_quest
spring.datasource.username=main_quest_user
spring.datasource.password=tu_contraseña
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

> ⚠️ `application.properties` está excluido del control de versiones mediante `.gitignore`. **Nunca subas este archivo al repositorio** — contiene credenciales.

### 5. Compilar y ejecutar

```bash
mvn clean install
mvn spring-boot:run
```

O ejecutando la clase `Principal` directamente desde el IDE.

Si todo está bien configurado, en consola vas a ver `HikariPool-1 - Start completed` seguido del menú de la aplicación.

---

## 🗄️ Modelo de datos

```
JUGADORES                MISIONES                 progreso_mision
─────────────            ─────────────            ─────────────────
ID          PK           ID          PK           id            PK
nombre                   nombre                   id_jugador    FK → JUGADORES
contrasena               XP                       id_mision     FK → MISIONES
email                    TIPO                     estado
XP
```

**Sobre la columna `TIPO`:** actúa como discriminador de la jerarquía de misiones. `MisionGeneral` y `MisionSemanal` comparten la tabla `MISIONES` y se distinguen por el valor almacenado ahí (`GENERAL` o `SEMANAL`), siguiendo la estrategia `SINGLE_TABLE` de JPA.

**Sobre `estado`:** enumeración con los valores `EN_CURSO` y `COMPLETADA`, mapeada con `@Enumerated(EnumType.STRING)`.

---

## 📁 Estructura del proyecto

```
src/main/
├── java/arg/jorge/mainquest/
│   ├── Principal.java           # Punto de entrada (@SpringBootApplication)
│   ├── MenuRunner.java          # Menú interactivo (CommandLineRunner)
│   ├── Persistible.java         # Clase base con el @Id (@MappedSuperclass)
│   ├── Jugador.java             # @Entity
│   ├── Mision.java              # @Entity abstracta
│   ├── MisionGeneral.java       # @Entity
│   ├── MisionSemanal.java       # @Entity
│   ├── ProgresoDeMision.java    # @Entity
│   ├── AuthServices.java        # Registro y login
│   └── Menu.java                # Presentación del menú
└── resources/
    ├── database/
    │   ├── create_db.sql        # Creación de tablas
    │   ├── init_db.sql          # Datos iniciales
    │   └── drop_db.sql          # Limpieza
    └── application.properties.example
```

### Decisiones de diseño

**`Persistible`** centraliza el `@Id` y su estrategia de generación. Al anotarla con `@MappedSuperclass`, todas las entidades heredan el identificador sin repetir el código en cada clase.

**`MenuRunner`** separa la lógica del juego del arranque de la aplicación. `Principal` solo levanta el contexto de Spring; la interacción con el usuario ocurre en un `CommandLineRunner`, que se ejecuta una vez que el contexto está completamente inicializado y todos los componentes disponibles para inyección.

**Herencia en las misiones** — `Mision` es abstracta y define `calcularXpFinal()`. Cada subclase implementa su propia regla de cálculo, lo que permite agregar nuevos tipos de misión sin modificar el código existente.

---

## 🔧 Problemas frecuentes

**`Access denied for user`** — Las credenciales de `application.properties` no coinciden con las de MySQL. Verificá que el usuario y la contraseña sean correctos probándolos primero en Workbench.

**`Failed to configure a DataSource`** — Spring no encuentra el archivo de configuración. Confirmá que se llame exactamente `application.properties` (con dos `p`) y esté en `src/main/resources/`.

**`Communications link failure`** — El servidor MySQL no está corriendo. Iniciá el servicio.

**`Table doesn't exist`** — Faltó ejecutar `create_db.sql`, o la URL apunta a otra base.

---

## 📌 Estado actual

**Implementado**
- Registro y autenticación de jugadores
- Sistema de misiones con herencia y cálculo diferenciado de XP
- Cálculo de niveles según XP acumulada
- Persistencia en MySQL mediante JPA/Hibernate

**En desarrollo**
- Repositorios de Spring Data JPA
- Migración del almacenamiento en memoria a persistencia real
- Hash de contraseñas
- Historial de progreso por jugador

---

## 👤 Autor

[@JorgeLOG123](https://github.com/JorgeLOG123)
