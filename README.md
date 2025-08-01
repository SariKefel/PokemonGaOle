# 🎮 Pokémon Ga-Olé — Java Console Game

A Java-based console simulation of the arcade game Pokémon Ga-Olé, featuring object-oriented principles, persistent player profiles, catch mechanics, turn-based battle system, Pokéball system, and Ga-Olé Disk collection.

## 🚀 How to Run

1. **Compile the game:**
   ```bash
   javac -d bin src/*.java
   ```

2. **Run the game:**
   ```bash
   java -cp bin PokemonGaOle
   ```

## 📁 Project Structure

```
Pokemon/
├── src/         # All .java source files
│   ├── PokemonGaOle.java
│   ├── GameManager.java
│   ├── Battle.java
│   ├── Stage.java
│   ├── CatchMechanism.java
│   ├── Utils.java
│   ├── Player.java
│   ├── GaOleDisk.java
│   ├── Pokemon.java
│   ├── TypeChart.java
│   ├── Move.java
│   ├── Pokeball.java
│   └── PokemonType.java
├── bin/         # All .class compiled files
│   ├── PokemonGaOle.class
│   ├── GameManager.class
│   ├── Battle.class
│   ├── Stage.class
│   ├── CatchMechanism.class
│   ├── Utils.class
│   ├── Player.class
│   ├── GaOleDisk.class
│   ├── Pokemon.class
│   ├── TypeChart.class
│   ├── Move.class
│   ├── Pokeball.class
│   └── PokemonType.class
├── README.md
```

## 🎯 Game Features

### Core Mechanics
- **Persistent Player Profiles**: Save and load your progress automatically
- **Ga-Olé Disk Collection**: Collect Pokémon on special disks
- **Turn-based Battle System**: Strategic battles with type effectiveness
- **Catch Mechanics**: Realistic catch formula with Pokéball animations
- **Multiple Stages**: Forest, Volcano, Ocean, Cave, and Legendary locations

### Battle System
- **Type Effectiveness**: Complete type chart with super effective/not very effective moves
- **Health Bars**: Visual representation of Pokémon health
- **Damage Calculation**: Realistic damage based on attack, move power, and type effectiveness
- **Auto-switching**: Automatic Pokémon switching when one faints

### Catch System
- **Catch Formula**: `((3 * maxHP - 2 * currentHP) * baseRate * ballModifier) / (3 * maxHP)`
- **Pokéball Types**: Poke Ball, Great Ball, Ultra Ball, Master Ball
- **Catch Animations**: Realistic "wiggle" and "click" animations
- **Random Ball Selection**: Weighted distribution of different ball types

## 🎮 Game Modes

### 1. Battle Mode
- Select from 5 different stages
- Choose 2 Pokémon from your collection
- Battle 2 wild Pokémon per stage
- Catch surviving wild Pokémon after battle
- Earn medals for successful battles

### 2. Catch Mode
- Quick catching mode for new players
- 3 random wild Pokémon appear
- Choose one to attempt catching
- Uses only regular Pokéballs

### 3. Disk Collection
- View all your collected Ga-Olé Disks
- See Pokémon stats and rental status
- Track your total collection and medals

## 🏗️ Class Architecture

### Core Classes
- **`PokemonGaOle`**: Main entry point
- **`GameManager`**: Handles game flow and menu system
- **`Player`**: Manages player data and disk collection
- **`Pokemon`**: Represents individual Pokémon with stats
- **`GaOleDisk`**: Collectible disks containing Pokémon
- **`Battle`**: Handles turn-based battles
- **`Stage`**: Different locations with wild Pokémon pools

### Supporting Classes
- **`Move`**: Pokémon moves with power and type
- **`TypeChart`**: Complete type effectiveness system
- **`CatchMechanism`**: Catch calculations and animations
- **`Pokeball`**: Different ball types with catch modifiers
- **`Utils`**: Console utilities and input validation

### Enums
- **`PokemonType`**: All 19 Pokémon types including Legendary

## 🎯 OOP Principles Implemented

- **High Cohesion**: Each class has focused responsibility
- **Low Coupling**: GameManager delegates work to modular classes
- **Encapsulation**: Private fields with getters/setters
- **Polymorphism**: Easy to extend with new battle types
- **Reusability**: Pokémon, Moves, and TypeChart reusable across modes

## 🎲 Game Flow

1. **Login Phase**: Enter username, load existing profile or create new
2. **Main Menu**: Choose between Battle, Catch, View Disks, Save, or Exit
3. **Battle Mode**: Select stage → Choose team → Battle → Catch phase
4. **Catch Mode**: Select from 3 wild Pokémon → Attempt catch
5. **Persistence**: Automatic saving to `.ser` files

## 🏆 Tips for Success

- **Type Matchups**: Learn type effectiveness for better battles
- **Team Building**: Choose Pokémon with diverse types
- **Catch Strategy**: Weaken Pokémon before attempting to catch
- **Stage Selection**: Different stages have different Pokémon types
- **Save Regularly**: Your progress is automatically saved

## 🎨 Console Features

- **Visual Health Bars**: ASCII art health representation
- **Animated Spinners**: Loading and catch animations
- **Formatted Headers**: Professional-looking menu borders
- **Input Validation**: Robust error handling for user input
- **Clear Console**: Clean screen transitions between phases

## 🔧 Technical Details

- **Serialization**: Player data saved as `.ser` files
- **Exception Handling**: Comprehensive error handling throughout
- **Input Validation**: Range checking and type validation
- **Thread Safety**: Safe sleep operations for animations
- **Memory Management**: Efficient object creation and cleanup

Enjoy your Pokémon Ga-Olé adventure! 🎮✨ 