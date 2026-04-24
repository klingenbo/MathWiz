# MathWiz
Fun Android app for practicing multiplication tables with game mechanics.

MathWiz – Learn Multiplication Through Play

A simple Android game designed to help children practice multiplication tables in a fun and engaging way.

The player answers multiplication questions, earns points, and feeds a turtle by selecting the correct answers.

  Features
Practice specific multiplication tables (1–10) or all combined
Multiple-choice answers
Score tracking
Visual feedback for correct/incorrect answers
Animated progress system (feeding a turtle)
Smooth UI built with Jetpack Compose

  How it works
The player selects a multiplication table from the start screen
A question is generated with 4 possible answers
The player selects an answer:
- Correct → score increases
- Incorrect → feedback is shown
The game continues with new questions
Progress is visualized through a feeding mechanic (turtle eating a leaf)


  Tech Stack
Kotlin
Jetpack Compose
ViewModel
StateFlow
Coroutines

  Architecture

The app follows a simple modern Android architecture:

ViewModel
Handles game logic
Generates questions
Manages score and state
Uses viewModelScope for asynchronous operations
UI (Compose)
Displays state from ViewModel
Reacts automatically to state changes
Handles user interactions
State Management
StateFlow for observable data
collectAsState() in UI
Local UI state using remember

  What I learned
Building UI using Jetpack Compose
Managing state in a reactive way
Difference between UI state and ViewModel state
Using StateFlow and collectAsState
Handling user interaction and recomposition
Working with coroutines and lifecycle-aware scopes
Structuring a scalable Android app

  Future Improvements
Animations for the turtle (eating, reacting)
Sound effects for feedback
Difficulty levels / progression system
Timer-based challenges
Better visual design and theming per table
Saving progress locally (DataStore)

  Screenshots

-----

  Motivation

This project was built to explore modern Android development with Jetpack Compose and to create something interactive and meaningful.

The goal is to make learning math feel more like playing a game.
