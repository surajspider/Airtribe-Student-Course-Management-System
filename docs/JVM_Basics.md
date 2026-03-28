# JVM Basics

## What is JDK, JRE, and JVM?
- **JVM (Java Virtual Machine)**: It is the engine that provides a runtime environment to drive the Java Code or applications. It converts Java bytecode into machine language.
- **JRE (Java Runtime Environment)**: It provides libraries, JVM, and other components to run applets and applications written in the Java programming language. It is a subset of the JDK.
- **JDK (Java Development Kit)**: It is a software development environment used for developing Java applications and applets. It includes the JRE, an interpreter/loader (java), a compiler (javac), an archiver (jar), a documentation generator (javadoc), and other tools needed in Java development.

## What is Bytecode?
Bytecode is a highly optimized set of instructions designed to be executed by the Java Virtual Machine. When you compile a Java source file (e.g., `javac MyProgram.java`), the Java compiler generates a `.class` file containing this bytecode, which is independent of the platform.

## What does "Write once, run anywhere" mean?
"Write once, run anywhere" (WORA) is a slogan created by Sun Microsystems to illustrate the cross-platform benefits of the Java language. Because Java programs are compiled into standardized bytecode, any device or operating system with a compatible Java Virtual Machine (JVM) can execute the program. You don't need to recompile the code for each specific platform.
