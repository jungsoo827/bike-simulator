# Bike Application

A Java application that simulates a bike moving on a 7x7 grid with support for various commands.

## Objective

The goal of this application is to implement a simulation engine that enforces grid boundaries and command integrity, demonstrating clean object-oriented design principles in Java.

## Why I Selected the Bike-Simulation Challenge
I selected the Bike-Simulation challenge because it allows me to demonstrate fundamental Object-Oriented programming principles. 
Unlike mathematical puzzles, this challenge closely reflects real-world software engineering such as handling unpredictable input and enforcing strict business rules at system boundaries. 
My primary strength lies in software architecture and organizing code structures, making this challenge the best fit to showcase my expertise.


## Design and Approach
The application is structured into clearly separated layers to ensure maintainability and testability:

* **Entry Point (App.java)**: Handles the application lifecycle, including command-line arguments, input stream selection (file or STDIN), and application exit codes.
* **Controller Layer (BikeSimulator.java)**: Acts as the central orchestrator. It consumes input from file or STDIN, processes commands by delegating to the domain objects, and manages the execution loop.
* **Domain Model Layer (Bike.java, Grid.java)**: Encapsulates the core business logic.
    * **Grid.java**: Manages the coordinate system and boundary validation.
    * **Bike.java**: Maintains the current state (position and orientation) and handles state-changing operations like movement and rotation.
* **Support/Utility Layer**:
    * **Enums (Command.java, Direction.java)**: Defines valid system operations and cardinal directions, ensuring type safety.
    * **Util.java**: Contains helper methods for input parsing and validation, decoupling string processing from the domain objects.

This architecture enforces the Single Responsibility Principle, ensuring that command parsing, movement logic, and I/O handling are strictly isolated.

### Class Diagram
![Bike Simulation UML](bike-simulator.puml)
*(If the UML diagram above does not render in your environment, please refer to [bike-simulator-uml.png](bike-simulator-uml.png))*

## Features

- **Grid Movement**: Bike operates on a 7x7 grid where (0,0) is the south-west corner
- **Command Support**: 
  - `PLACE x,y,direction` - Position bike at specified coordinates and direction
  - `FORWARD` - Move bike one unit forward in current direction
  - `TURN_LEFT` - Rotate bike 90 degrees to the left
  - `TURN_RIGHT` - Rotate bike 90 degrees to the right
  - `GPS_REPORT` - Output current position and direction
  - `EXIT` - End bike simulation
  - `USAGE` - Usage description
  - `help` - Usage description
- **Boundary Enforcement**: Bike cannot exit the grid boundaries
- **Invalid Command**: Invalid commands will be ignored ( invalid positions, directions, commands )
- **Input Handling**: Reads commands from STDIN or Reads file

## Building

The project uses Gradle for build management.

To build the application:
```bash
./gradlew clean build
```

## Running the Application

### 1. Generate Files
```bash
./gradlew installDist
```

Generated files are stored in the build/install/bike folder.

### 2. Interactive Mode
To run the application and provide input via STDIN:

Execute the following bash file for the Linux environment:
```bash
build/install/bike/bin/bike
```

Execute the following command file for the Windows environment:
```
build\install\bike\bin\bike.bat
```


### 3. File Input Mode
To run the application using a file:

Execute the following bash file for the Linux environment:
```bash
build/install/bike/bin/bike path/to/your/input.txt
```

Execute the following command file for the Windows environment:
```
build\install\bike\bin\bike.bat path\to\your\input.txt
```


## Logging
The application automatically logs events to a file for debugging purpose:
`logs/app.log`

