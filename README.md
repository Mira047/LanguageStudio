# Language Studio

Language Studio is a tool that allows you to create and configure your own programming languages. 

It provides an easy-to-use API that enables you to build and test your language, as well as an interpreter to run it. With Language Studio, you can create your own language with a simple and intuitive API, register instructions, and load default instructions from a path.

## Features

- Create your own language using a simple and intuitive API
- Register instructions using string identifier or predicates
- Load default instructions from a path
- Create and configure a LanguageInfo object that contains all the information and settings of the language
- Interpret and run the code written in your language

## Getting Started

### Prerequisites

- Java 16 or later

### Usage

Here's an example of how you can use the Language Studio to create a new language:

```java
// Create a new LanguageBuilder
LanguageBuilder builder = LanguageBuilder.create();

// Register some instructions
builder.register("print", args -> System.out.println(args.get(1)));
builder.register("add", args -> {
    int result = 0;
    for (int i = 1; i < args.size(); i++) {
        result += Integer.parseInt(args.get(i));
    }
    System.out.println(result);
});

// Configure the language
LanguageInfo languageInfo = builder.withName("My Language")
       .withIdentifier("mylang")
       .withVersion("1.0.0")
       .withAuthor("John Doe")
       .loadDefaults()
       .build()
       
InterpreterImpl interpreter = new InterpreterImpl();
interpreter.load(languageInfo);
interpreter.run("print 'Hello, World!'");
```
