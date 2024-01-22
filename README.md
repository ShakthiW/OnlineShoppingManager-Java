# Westminster Shopping Center

This is our 1st coursework for the 2d year under the module of Object Oriented programming.<br>
Object-Oriented Programming (OOP) is a programming paradigm that uses objects, which are instances of classes, for organizing and structuring code. Here are the core concepts of OOP and how they are used in your Westminster Shopping Center project:

1. **Classes and Objects:**
   - **Usage in the Project:** Classes are used to model entities like `Product`, `User`, `Electronics`, `Clothing`, etc. Objects are instances of these classes. For example, the `Product` class is used to create objects representing different products in the shopping center.

2. **Encapsulation:**
   - **Usage in the Project:** Encapsulation is achieved by bundling the data (attributes) and the methods that operate on the data within a class. For instance, the `Product` class encapsulates information like product ID, name, category, price, etc., and provides methods to access and manipulate this information.

3. **Inheritance:**
   - **Usage in the Project:** Inheritance is used to create a relationship between classes. For example, the `Electronics` and `Clothing` classes inherit from the `Product` class. This allows them to inherit common attributes and methods from the `Product` class while having specialized attributes and methods of their own.

4. **Polymorphism:**
   - **Usage in the Project:** Polymorphism is demonstrated through method overriding. For example, the `generateProductDetails` method is overridden in the `Electronics` and `Clothing` classes to provide product details specific to those subclasses. The `ShoppingCentre` class can then use polymorphism to display detailed information about any type of product.

5. **Abstraction:**
   - **Usage in the Project:** Abstraction involves simplifying complex systems by modeling classes based on the essential properties and behaviors. In your project, each class represents a real-world entity with specific attributes and behaviors, providing a simplified and abstracted view of the shopping center.

6. **Association:**
   - **Usage in the Project:** Associations represent relationships between classes. For example, the `ShoppingCart` class is associated with the `Product` class, allowing users to add products to their shopping cart. The `User` class is associated with the `ShoppingCart` class, representing the relationship between a user and their shopping cart.

These OOP principles help in organizing code, improving code readability, and creating a modular and maintainable software structure. They also contribute to the flexibility and extensibility of the system, making it easier to add new features or modify existing ones.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Acknowledgements](#acknowledgements)

## Introduction

Westminster Shopping Center is a Java-based desktop application that simulates an interactive shopping experience. 
Users can log in, browse a list of available products, 
add items to their shopping cart, and proceed to checkout. The system also provides features such as user registration and product categorization.

## Features

Highlight the key features of your project. Use bullet points or a numbered list for clarity.

- Feature 1
- Feature 2
- ...

## Getting Started

Explain how to set up and run your project. Provide clear steps for users to follow.

### Prerequisites

List any software, libraries, or tools that users need to have installed before they can use your project.

### Installation

Step-by-step instructions on how to install and configure your project.

```bash
# Example command
npm install your-package-name
