Java OOPS Interview Questions and Detailed Answers
What is OOP?
OOP stands for Object Oriented Programming. It is a programming paradigm based on objects and classes. 
OOP helps developers organize code in a modular and reusable way. 
The main principles of OOP are Encapsulation, Inheritance, Polymorphism, and Abstraction.

Advantages:
• Code reusability
• Better security
• Easy maintenance
• Real-world modeling
What is a Class?
A class is a blueprint or template used to create objects. 
It defines variables and methods that objects will have.

Example:
class Car {
    String color;
    void drive() {}
}
What is an Object?
An object is an instance of a class. 
It occupies memory and can access the methods and variables of the class.

Example:
Car c = new Car();

Here c is an object.
What is Inheritance?
Inheritance is a mechanism where one class acquires the properties and methods of another class using extends keyword.

Purpose:
• Code reusability
• Reduces duplicate code
• Establishes IS-A relationship

Example:
class Animal {}
class Dog extends Animal {}

Here Dog inherits Animal properties.
Why is Inheritance important?
Without inheritance, developers must write the same code repeatedly in multiple classes. 
Inheritance allows child classes to reuse common functionality from parent classes.

Real-life example:
Vehicle -> Car, Bike, Bus

All vehicles can start and stop, so those methods are written once in Vehicle class.
What is Encapsulation?
Encapsulation means binding data and methods together into a single unit and hiding data using private access modifiers.

Data is accessed using public getter/setter methods.

Advantages:
• Security
• Controlled access
• Data hiding

Real-life example:
Bank account balance cannot be accessed directly.
What is Polymorphism?
Polymorphism means one method can perform different tasks based on context.

Types:
1. Compile-time polymorphism (Method Overloading)
2. Runtime polymorphism (Method Overriding)

Meaning:
One name with many forms.
What is Method Overloading?
Method overloading means creating multiple methods with same name but different parameters in the same class.

Example:
add(int a, int b)
add(int a, int b, int c)

Advantages:
• Improves readability
• Reduces method naming complexity
What is Method Overriding?
Method overriding occurs when child class provides its own implementation of parent class method.

Example:
class Animal {
   void sound(){}
}

class Dog extends Animal {
   void sound(){}
}

Used for runtime polymorphism.
Difference between Overloading and Overriding
Overloading:
• Same class
• Different parameters
• Compile-time polymorphism

Overriding:
• Parent-child classes
• Same parameters
• Runtime polymorphism
What is Abstraction?
Abstraction means hiding internal implementation details and showing only essential functionality to the user.

Achieved using:
• Abstract classes
• Interfaces

Real-life example:
ATM machine

Users only see buttons and screen, not internal banking operations.
What is an Abstract Class?
An abstract class is declared using abstract keyword and can contain:
• Abstract methods
• Normal methods

Cannot create objects directly.

Purpose:
• Provide common functionality
• Force child classes to implement specific methods

What is an Interface?
An interface is used to define rules that classes must implement.

Interfaces achieve complete abstraction.

Example:
interface Payment {
   void pay();
}

Classes like PhonePe, GPay implement pay() differently.
Difference between Interface and Abstract Class
Interface:
• Complete abstraction
• Supports multiple inheritance
• Uses implements

Abstract Class:
• Partial abstraction
• Can contain implemented methods
• Uses extends

Use interface for defining rules.
Use abstract class for sharing common code.
Why Java does not support Multiple Inheritance with classes?
Java avoids multiple inheritance with classes because of ambiguity problems called Diamond Problem.

If two parent classes contain same method, Java cannot determine which method child should inherit.

Solution:
Java supports multiple inheritance using interfaces.

What is Constructor?
A constructor is a special method automatically called when object is created.

Purpose:
• Initialize object values

Types:
• Default constructor
• Parameterized constructor
What is this keyword?
this keyword refers to current object of the class.

Uses:
• Differentiate instance and local variables
• Call current class methods
• Constructor chaining

What is super keyword?
super keyword refers to parent class object.

Uses:
• Access parent methods
• Access parent variables
• Call parent constructor
What is Static Keyword?
Static members belong to the class instead of objects.

Characteristics:
• Memory shared among objects
• Can access without creating object

Example:
static int count;
What is Final Keyword?
final keyword is used to:
• Create constant variables
• Prevent inheritance
• Prevent method overriding

Example:
final int x = 10;

What are Access Modifiers?
Access modifiers control visibility of variables and methods.

Types:
• public -> accessible everywhere
• private -> accessible only within class
• protected -> accessible within package and subclasses
• default -> accessible within package

What is Runtime Polymorphism?
Runtime polymorphism occurs when overridden method is decided during program execution.

Achieved using method overriding and inheritance.
What is Compile-time Polymorphism?
Compile-time polymorphism occurs when method call is resolved during compilation.
Achieved using method overloading.

What is IS-A Relationship?
IS-A relationship represents inheritance.
Example:
Dog IS-A Animal
Used to show parent-child relationship.

What is HAS-A Relationship?
HAS-A relationship represents composition.
Example:
Car HAS-A Engine
One class contains object of another class.

What are advantages of OOP?
Advantages:
• Code reusability
• Better maintainability
• Security using encapsulation
• Real-world modeling
• Scalability
• Easier debugging
