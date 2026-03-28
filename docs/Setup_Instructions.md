# Setup Instructions

## Requirements
- Java Development Kit (JDK) installed on your system.
- **JDK Version Used in this Project:** Java 25 LTS (build 25+37-LTS-3491)

## Hello World Execution Explanation
Before building the full application, we verified the environment by running a simple "Hello World" program.
1. **Writing the code:** Created a file `HelloWorld.java` with a standard `public static void main` method.
2. **Compilation:** Running `javac HelloWorld.java` generates a `HelloWorld.class` file containing the platform-independent bytecode.
3. **Execution:** Running `java HelloWorld` starts the JVM, loads the compiled class, reads the bytecode, and simply prints "Hello World" to the console.

## How to Verify JDK Installation
1. Open your terminal or command prompt.
2. Run the following command:
   ```bash
   java -version
   ```
   You should see your installed Java version.
3. Run this command to check the compiler:
   ```bash
   javac -version
   ```

## Compiling and Running LearnTrack
1. Navigate to the project directory in your terminal:
   ```bash
   cd LearnTrack
   ```
2. Compile the source code:
   ```bash
   javac -d bin src/com/airtribe/learntrack/**/*.java src/com/airtribe/learntrack/*.java
   ```
3. Run the application:
   ```bash
   java -cp bin com.airtribe.learntrack.Main
   ```
