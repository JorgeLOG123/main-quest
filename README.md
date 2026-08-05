
# 🎮 Main Quest

Una aplicación de consola en Java que convierte la búsqueda del primer empleo en un juego de progresión. El usuario registra acciones reales de su desarrollo profesional, gana experiencia (XP), sube de nivel y avanza en su carrera.

> Proyecto desarrollado como parte del Programa Intensivo de programación en Java.

---

## 📖 Sobre el proyecto

Buscar el primer empleo es un proceso largo, frustrante y lleno de rechazos. La mayoría de las personas sabe *qué* tiene que hacer (personalizar el CV, practicar entrevistas, hacer seguimiento de postulaciones), pero no logra sostener la constancia.

**Main Quest** ataca ese problema aplicando mecánicas de videojuego a la empleabilidad: transforma acciones que se sienten como una carga en algo que da satisfacción inmediata (XP, niveles, progreso). Es la misma idea detrás de apps como Duolingo, pero aplicada al desarrollo profesional.

---

## ✨ Funcionalidades

- **Registro de usuarios** con validación (no permite nombres duplicados)
- **Inicio de sesión** con usuario y contraseña
- **Sistema de experiencia (XP)** que se acumula al completar misiones
- **Cálculo de nivel** automático a partir de la XP acumulada
- **Catálogo de misiones** con distinta XP según su dificultad

### Misiones disponibles

| Misión | XP |
|--------|-----|
| Personalizar CV para una oferta | 50 |
| Practicar una respuesta con método STAR | 30 |
| Hacer seguimiento de una postulación | 20 |
| Actualizar perfil de LinkedIn | 40 |
| Pedir feedback tras una entrevista | 60 |

---

## 🛠️ Tecnologías

- **Java** — lenguaje principal
- **Maven** — gestión del proyecto y construcción
- Programación Orientada a Objetos (clases, encapsulamiento, constructores)
- Estructuras dinámicas (`List`, `Map`)

---

## 📂 Estructura del proyecto

```
main-quest/
├── pom.xml
└── src/
    └── main/
        └── java/
            └── arg/jorge/mainquest/
                ├── Principal.java    (punto de entrada y menús)
                ├── Jugador.java      (modelo del jugador y su XP)
                └── Mision.java       (modelo de las misiones)
```

### Clases principales

- **`Principal`** — contiene el `main` y gestiona el flujo del programa (menú de acceso, registro, login).
- **`Jugador`** — representa a un usuario: nombre, contraseña, XP. Incluye el método `sumarXP()` para ganar experiencia y `calcularNivel()` para obtener el nivel actual.
- **`Mision`** — representa una misión con su nombre y la XP que otorga.

---

## 🚀 Cómo ejecutarlo

### Requisitos

- Java (JDK 17 o superior)
- Maven

### Pasos

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/JorgeLOG123/main-quest.git
   ```

2. Entrá a la carpeta del proyecto:
   ```bash
   cd main-quest
   ```

3. Compilá con Maven:
   ```bash
   mvn compile
   ```

4. Ejecutá la clase `Principal` desde tu IDE (botón Run) o mediante Maven.

---

## 🎯 Cómo se usa

1. Al iniciar, aparece el menú principal:
   ```
   -------- BIENVENIDO A MAIN QUEST ---------
   1) Registrarse
   2) Iniciar Sesion
   3) Salir
   ```

2. **Registrate** (opción 1) creando un usuario y contraseña.
3. **Iniciá sesión** (opción 2) con esas credenciales.
4. Completá misiones para ganar XP y subir de nivel.

---

## 🔮 Próximas mejoras

- [ ] Menú del jugador logueado (ver perfil, completar misiones, ranking)
- [ ] Persistencia de datos (guardar el progreso en archivos)
- [ ] Sistema de rangos profesionales (Junior, Semi-Senior, Senior)
- [ ] Logros desbloqueables y rachas de días activos

---

## 👤 Autor

**Jorge** — [JorgeLOG123](https://github.com/JorgeLOG123)

Proyecto de aprendizaje desarrollado durante el Programa Intensivo de Java.
