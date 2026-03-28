# Setup Instructions

## Requirements
- Java Development Kit (JDK) 11 or higher installed on your system.

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
