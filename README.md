# Java OOPS Interview Questions and Answers

## What is OOP?

OOP stands for Object Oriented Programming. It is a programming paradigm based on objects and classes.

OOP helps developers organize code in a modular and reusable way.

The four main principles of OOP are:

* Encapsulation
* Inheritance
* Polymorphism
* Abstraction

### Advantages of OOP

* Code reusability
* Better security
* Easy maintenance
* Real-world modeling
* Scalability
* Easier debugging

---

# What is a Class?

A class is a blueprint or template used to create objects.

It defines variables and methods that objects will have.

## Example

```java
class Car {
    String color;

    void drive() {
        System.out.println("Car is driving");
    }
}
```

---

# What is an Object?

An object is an instance of a class.

It occupies memory and can access the methods and variables of the class.

## Example

```java
Car c = new Car();
```

Here `c` is an object.

---

# What is Inheritance?

Inheritance is a mechanism where one class acquires the properties and methods of another class using the `extends` keyword.

## Purpose

* Code reusability
* Reduces duplicate code
* Establishes IS-A relationship

## Example

```java
class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
}
```

Here `Dog` inherits properties from `Animal`.

---

# Why is Inheritance Important?

Without inheritance, developers must write the same code repeatedly in multiple classes.

Inheritance allows child classes to reuse common functionality from parent classes.

## Real-life Example

```text
Vehicle -> Car, Bike, Bus
```

All vehicles can:

* Start
* Stop
* Brake

So those methods are written once in `Vehicle` class.

---

# What is Encapsulation?

Encapsulation means binding data and methods together into a single unit and hiding data using private access modifiers.

Data is accessed using public getter/setter methods.

## Advantages

* Security
* Controlled access
* Data hiding

## Real-life Example

Bank account balance cannot be accessed directly.

---

# What is Polymorphism?

Polymorphism means one method can perform different tasks based on context.

## Types

1. Compile-time polymorphism (Method Overloading)
2. Runtime polymorphism (Method Overriding)

### Meaning

```text
One name with many forms
```

---

# What is Method Overloading?

Method overloading means creating multiple methods with same name but different parameters in the same class.

## Example

```java
class MathOperation {

    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}
```

## Advantages

* Improves readability
* Reduces method naming complexity

---

# What is Method Overriding?

Method overriding occurs when child class provides its own implementation of parent class method.

## Example

```java
class Animal {
    void sound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    void sound() {
        System.out.println("Dog barks");
    }
}
```

Used for runtime polymorphism.

---

# Difference Between Overloading and Overriding

| Overloading               | Overriding              |
| ------------------------- | ----------------------- |
| Same class                | Parent-child classes    |
| Different parameters      | Same parameters         |
| Compile-time polymorphism | Runtime polymorphism    |
| Increases readability     | Runtime behavior change |

---

# What is Abstraction?

Abstraction means hiding internal implementation details and showing only essential functionality to the user.

## Achieved Using

* Abstract classes
* Interfaces

## Real-life Example

ATM machine

Users only see:

* Buttons
* Screen

They do not see internal banking operations.

---

# What is an Abstract Class?

An abstract class is declared using `abstract` keyword and can contain:

* Abstract methods
* Normal methods

Objects cannot be created directly for abstract classes.

## Purpose

* Provide common functionality
* Force child classes to implement specific methods

## Example

```java
abstract class Vehicle {

    abstract void start();

    void stop() {
        System.out.println("Vehicle stopped");
    }
}
```

---

# What is an Interface?

An interface is used to define rules that classes must implement.

Interfaces achieve complete abstraction.

## Example

```java
interface Payment {
    void pay();
}

class PhonePe implements Payment {
    public void pay() {
        System.out.println("Payment using PhonePe");
    }
}
```

Classes like:

* PhonePe
* GPay
* Paytm

can implement `pay()` differently.

---

# Difference Between Interface and Abstract Class

| Interface                     | Abstract Class                        |
| ----------------------------- | ------------------------------------- |
| Complete abstraction          | Partial abstraction                   |
| Supports multiple inheritance | Does not support multiple inheritance |
| Uses `implements`             | Uses `extends`                        |
| Mostly method declarations    | Can contain implemented methods       |

## Usage

* Use interface for defining rules
* Use abstract class for sharing common code

---

# Why Java Does Not Support Multiple Inheritance with Classes?

Java avoids multiple inheritance with classes because of ambiguity problems called the Diamond Problem.

If two parent classes contain same method, Java cannot determine which method child should inherit.

## Solution

Java supports multiple inheritance using interfaces.

---

# What is Constructor?

A constructor is a special method automatically called when object is created.

## Purpose

* Initialize object values

## Types

* Default constructor
* Parameterized constructor

## Example

```java
class Student {

    Student() {
        System.out.println("Constructor called");
    }
}
```

---

# What is `this` Keyword?

`this` keyword refers to current object of the class.

## Uses

* Differentiate instance and local variables
* Call current class methods
* Constructor chaining

## Example

```java
class Student {

    String name;

    Student(String name) {
        this.name = name;
    }
}
```

---

# What is `super` Keyword?

`super` keyword refers to parent class object.

## Uses

* Access parent methods
* Access parent variables
* Call parent constructor

## Example

```java
class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {

    void show() {
        super.eat();
    }
}
```

---

# What is Static Keyword?

Static members belong to the class instead of objects.

## Characteristics

* Memory shared among objects
* Can access without creating object

## Example

```java
class Student {

    static int count = 0;
}
```

---

# What is Final Keyword?

`final` keyword is used to:

* Create constant variables
* Prevent inheritance
* Prevent method overriding

## Example

```java
final int x = 10;
```

---

# What are Access Modifiers?

Access modifiers control visibility of variables and methods.

| Modifier  | Access                                   |
| --------- | ---------------------------------------- |
| public    | Accessible everywhere                    |
| private   | Accessible only within class             |
| protected | Accessible within package and subclasses |
| default   | Accessible within package                |

---

# What is Runtime Polymorphism?

Runtime polymorphism occurs when overridden method is decided during program execution.

## Achieved Using

* Method overriding
* Inheritance

---

# What is Compile-time Polymorphism?

Compile-time polymorphism occurs when method call is resolved during compilation.

## Achieved Using

* Method overloading

---

# What is IS-A Relationship?

IS-A relationship represents inheritance.

## Example

```text
Dog IS-A Animal
```

Used to show parent-child relationship.

---

# What is HAS-A Relationship?

HAS-A relationship represents composition.

## Example

```text
Car HAS-A Engine
```

One class contains object of another class.

---

# Advantages of OOP

* Code reusability
* Better maintainability
* Security using encapsulation
* Real-world modeling
* Scalability
* Easier debugging
