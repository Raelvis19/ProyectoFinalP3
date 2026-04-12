# 👾 Space Invaders JavaFX

<p align="center">
  <img src="https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=java">
  <img src="https://img.shields.io/badge/JavaFX-UI-blue?style=for-the-badge">
  <img src="https://img.shields.io/badge/Status-Completed-brightgreen?style=for-the-badge">
</p>

<p align="center">
  🎮 Juego arcade inspirado en <b>Space Invaders</b> desarrollado con JavaFX  
</p>

---

## ✨ Características principales

🚀 Movimiento fluido de la nave  
🔫 Sistema de disparo con sonido  
👾 Enemigos animados con comportamiento clásico  
💥 Explosiones al destruir enemigos  
❤️ Sistema de vidas dinámico  
🏆 Sistema de puntuación en tiempo real  
💾 High Score persistente  
🔊 Sistema de audio (música + efectos)  
🏁 Pantalla de Game Over  
🎉 Pantalla de Victoria con efectos  

---

## 🎮 Gameplay

<p align="center">
  <img src="assets/gameplay.gif" width="600">
</p>

> 💡 *Puedes reemplazar esto con un GIF real de tu juego*

---

## 🛠 Tecnologías utilizadas

| Tecnología | Uso |
|----------|-----|
| ☕ Java | Lógica del juego |
| 🎨 JavaFX | Interfaz gráfica |
| 📄 FXML | Estructura UI |
| 🎭 CSS | Estilos |
| 🔊 MediaPlayer | Música |
| 🔉 AudioClip | Efectos de sonido |

---

## 📂 Estructura del proyecto
src/
└── main/
├── java/
│ └── main/java/app/
│ ├── App.java
│ ├── GameController.java
│ ├── MainController.java
│ ├── WinController.java
│ ├── GameOverController.java
│ ├── audioManager.java
│ ├── GameData.java
│ └── HighScoreManager.java
│
└── resources/
├── view/
│ ├── main.fxml
│ ├── game.fxml
│ ├── gameover.fxml
│ └── victoria.fxml
│
├── img/
├── audio/
├── fonts/
└── style/

## ▶️ Cómo ejecutar el proyecto

git clone https://github.com/tu-usuario/tu-repo.git

1. Abre el proyecto en VS Code o IntelliJ
2. Ejecuta la clase principal: App.java

##🧠 Arquitectura

El juego sigue una estructura basada en controladores:

GameController → lógica principal del juego
MainController → menú principal
WinController → pantalla de victoria
GameOverController → pantalla de derrota
audioManager → manejo global de audio
GameData → estado del juego (score, vidas)
