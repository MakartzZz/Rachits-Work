# Rachits' Work

**Rachits' Work** es un juego de rompecabezas desarrollado en JavaFX e inspirado en los videojuegos de Game Boy Advance y en el universo de Pokémon.

Rachits es un Psayduck con problemas de autocontrol y estrés. Su entrenadora está a punto de participar en un torneo Pokémon, pero ha perdido sus Poké Balls en diferentes lugares de la casa. La misión del jugador es ayudar a Rachits a encontrarlas y colocarlas en los sitios correctos antes de que sea demasiado tarde.

## Cómo se juega

El juego utiliza una mecánica de rompecabezas similar a *Sokoban*. Debes mover a Psayduck por el escenario y empujar las Poké Balls hasta las casillas indicadas.

- Mueve a Psayduck con las flechas del teclado o con los botones de dirección.
- Empuja todas las Poké Balls hasta sus posiciones correctas.
- Evita dejar una Poké Ball atrapada contra paredes u obstáculos.
- Utiliza el botón para deshacer el movimiento anterior cuando sea posible.
- Reinicia el nivel si el rompecabezas queda bloqueado.
- Completa el escenario para desbloquear el paso al nivel siguiente.

## Características

- Cinco niveles con mapas y dificultades diferentes.
- Contador de pasos realizados.
- Opción para deshacer el último movimiento.
- Reinicio de nivel.
- Registro de entrenadores mediante un apodo.
- Guardado y recuperación del progreso de cada jugador.
- Música propia para los menús y niveles.
- Voces, efectos de sonido y animaciones.
- Interfaz visual inspirada en Pokémon y en juegos portátiles clásicos.

## Guardado del progreso

Los perfiles y el nivel alcanzado se almacenan localmente en:

`data/progress.txt`

Para guardar correctamente el progreso, regresa al menú principal usando el botón correspondiente dentro del juego. Cerrar directamente la ventana o finalizar el programa desde el sistema puede impedir que se guarde la partida actual.

## Tecnologías utilizadas

- Java 17.
- JavaFX 17.0.7.
- FXML.
- Apache Maven.
- NetBeans.

## Requisitos

Para compilar y ejecutar el proyecto se recomienda tener instalado:

- JDK 17 o una versión compatible.
- Apache Maven.
- Apache NetBeans o cualquier IDE con soporte para proyectos Maven.

JavaFX se descarga automáticamente mediante las dependencias declaradas en `pom.xml`.

## Ejecutar en NetBeans

1. Abre NetBeans.
2. Selecciona **File > Open Project**.
3. Elige la carpeta `Rachits' Work`.
4. Espera a que Maven descargue las dependencias.
5. Ejecuta el proyecto con **Run Project**.

La clase principal es `com.mycompany.journeytounemployment.App`.

## Ejecutar desde una terminal

Abre una terminal en la carpeta raíz del proyecto y ejecuta:

```bash
mvn clean javafx:run
```

Es importante ejecutar el comando desde la raíz del proyecto para que el juego pueda localizar correctamente sus recursos y el archivo de progreso.

## Estructura principal

```text
.
├── data/                 # Progreso local de los jugadores
├── src/main/java/        # Código fuente del juego
├── src/main/resources/   # FXML, imágenes, música, voces y efectos
├── nbactions.xml         # Acciones de ejecución para NetBeans
└── pom.xml               # Configuración y dependencias de Maven
```

## Créditos

- Creación: Makin Artavia Zúñiga.
- Diseño y animación: Makin Artavia.
- Voces y diálogos: Austin Salas.
- Edición de voces: Reyner Rojas.
- Proyecto realizado para el curso de Programación II.

## Aviso

Este es un proyecto educativo y de aficionados. Pokémon, Psayduck y los demás elementos relacionados pertenecen a sus respectivos propietarios.
