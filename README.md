# MathWiz 🐢

MathWiz is a simple Android game designed to help children practice multiplication tables in a fun and engaging way.

Players answer multiplication questions, earn points, and help feed a turtle by selecting the correct answers.

---

## Features

- Practice individual multiplication tables (1–10)
- Play with all multiplication tables combined
- Multiple-choice answers
- Visual feedback for correct and incorrect answers
- Animated turtle feeding mechanic
- Progress indicator
- High score saved locally using DataStore
- Clean UI built with Jetpack Compose

---

## How It Works

1. Select a multiplication table from the start screen.
2. Answer multiplication questions by choosing one of four possible answers.
3. Earn points for correct answers.
4. Feed the turtle as you progress through the game.
5. Complete the challenge and view your score and high score.

---

## Screenshots

### Start Screen
<img width="1440" height="3120" alt="Home Screen" src="https://github.com/user-attachments/assets/17344e5e-948e-484a-b46f-0aa75438f0fe" />

### Game Screen
<img width="1440" height="3120" alt="Game Screen" src="https://github.com/user-attachments/assets/22f1c908-9add-4e90-8891-ee6298de8462" />

### Complete Screen
<img width="1440" height="3120" alt="Complete Screen" src="https://github.com/user-attachments/assets/b45adf22-0e97-4f04-b1f5-a0692aeea60f" />

---

## Tech Stack

- Kotlin
- Jetpack Compose
- Material 3
- ViewModel
- StateFlow
- Coroutines
- Navigation Compose
- DataStore

---

## Architecture

The app follows a simple modern Android architecture.

### ViewModel

Responsible for:

- Generating multiplication questions
- Managing game state
- Tracking score and progress
- Handling game completion
- Saving and loading high scores

### UI (Jetpack Compose)

Responsible for:

- Displaying state from the ViewModel
- Reacting automatically to state changes
- Handling user interactions
- Showing animations and visual feedback

### State Management

- StateFlow for observable state
- collectAsState() for Compose integration
- remember for local UI state

---

## What I Learned

Through this project I practiced:

- Building Android UIs with Jetpack Compose
- State management using StateFlow
- ViewModel architecture
- Coroutines and asynchronous operations
- Navigation with Navigation Compose
- Persisting data with DataStore
- Refactoring reusable UI components
- Managing recomposition and reactive UI updates

---

## Future Improvements

- More turtle animations and reactions
- Sound effects
- Difficulty levels
- Timed game modes
- Statistics and achievements
- Per-table high score tracking

---

## Motivation

This project was created to explore modern Android development with Jetpack Compose while building something interactive and educational.

The goal was to make learning multiplication feel more like playing a game.
