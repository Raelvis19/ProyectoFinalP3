# 👾 Space Invaders JavaFX

<p align="center">
  <img src="https://img.shields.io/badge/Java-17+-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/JavaFX-UI-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge">
</p>

<p align="center">
  🎮 Juego arcade inspirado en <b>Space Invaders</b> desarrollado con JavaFX  
</p>

---

## ✨ Características principales

- 🚀 Movimiento fluido de la nave  
- 🔫 Sistema de disparo con sonido  
- 👾 Enemigos animados con comportamiento clásico  
- 💥 Explosiones al destruir enemigos  
- ❤️ Sistema de vidas dinámico  
- 🏆 Sistema de puntuación en tiempo real  
- 💾 High Score persistente  
- 🔊 Sistema de audio (música + efectos)  
- 🏁 Pantalla de Game Over  
- 🎉 Pantalla de Victoria con efectos  

---

## 🎮 Gameplay

<p align="center">
  <img src="assets/Animation.gif" width="600">
</p>



---

## 🛠 Tecnologías utilizadas

| Tecnología | Uso |
|------------|-----|
| ☕ Java | Lógica del juego |
| 🎨 JavaFX | Interfaz gráfica |
| 📄 FXML | Estructura UI |
| 🎭 CSS | Estilos |
| 🔊 MediaPlayer | Música |
| 🔉 AudioClip | Efectos de sonido |

---

## 📂 Estructura del proyecto

```text
src/
 └── main/
     ├── java/
     │   └── main/java/app/
     │       ├── App.java
     │       ├── GameController.java
     │       ├── MainController.java
     │       ├── WinController.java
     │       ├── GameOverController.java
     │       ├── audioManager.java
     │       ├── GameData.java
     │       └── HighScoreManager.java
     │
     └── resources/
         ├── view/
         │   ├── main.fxml
         │   ├── game.fxml
         │   ├── gameover.fxml
         │   └── victoria.fxml
         │
         ├── img/
         ├── audio/
         ├── fonts/
         └── style/
## ▶️ Cómo ejecutar el proyecto

```bash
git clone https://github.com/tu-usuario/tu-repo.git
```

1. Abre el proyecto en **VS Code** 
2. Asegúrate de tener **Java 17 o superior**
3. Ejecuta la clase principal:

```bash
App.java
```

---

## 🎯 Controles

| Tecla | Acción          |
| ----- | --------------- |
| A     | Mover izquierda |
| D     | Mover derecha   |
| SPACE | Disparar        |

---

## 🧠 Arquitectura

El proyecto está organizado usando controladores:

* `GameController` → lógica principal del juego
* `MainController` → menú principal
* `WinController` → pantalla de victoria
* `GameOverController` → pantalla de derrota
* `audioManager` → manejo global de audio
* `GameData` → estado del juego (score, vidas)

---

## 📊 Sistema de puntuación

* +100 por enemigo destruido
* High Score guardado automáticamente
* Animaciones tipo arcade (**+100 flotante**)

---

## 🔊 Sistema de audio

* Música de fondo en loop
* Sonido de disparo
* Sonido de explosión
* Música de victoria
* Control de mute global

---

## 📸 Screenshots

<p align="center">
  <img src="assets/screenshot1.png" width="400">
  <img src="assets/screenshot2.png" width="400">
</p>

---

## 👨‍💻 Autor

**Raelvis Paulino**
**Universidad Catolica Nordestana UCNE**
**Proyecto FINAL PROGRAMACION 3**
**Maestro Anthony Barreras**
**ABRIL 2026**


---

