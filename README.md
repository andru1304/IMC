# IMC Learning Test Automation Framework
IMC-Learning
## Table of Contents
- [Setup Instructions](#setup-instructions)
- [How to Run Tests](#how-to-run-tests)
- [Assumptions and Notes](#assumptions-and-notes)
## Setup Instructions
1. **Prerequisites:**
    - Java Development Kit (JDK) 24 or higher
    - Apache Maven 3.6 or higher
    - An IDE (e.g., IntelliJ IDEA, Eclipse)
    - Ensure that the WebDriver for the specified browser is available

2. **Clone the Repository:**
   ```using bash
   git clone https://github.com/andru1304/IMC.git
   cd IMC
3. **Install Dependencies:**
    - mvn install
## How to Run Tests
1. **Via IDE**
    - Open the project in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
    - Locate the test classes: FirstTestLearner, SecondTestLearner, ThirdTestLearner in the src/test/java directory.
    - Right-click on the test class or method you want to run and select "Run" or "Run as JUnit Test."
2. **Via Command Line**
    - Open a terminal and navigate to the project directory.
    - Use the following command to run all tests:
    - mvn test
3. **Running a specific test class, use:**
    - mvn -Dtest=FirstTestLearner test
## Assumptions and Notes
- The tests are designed to be repeatable and should run independently of each other.
- Screenshots will be captured on test failures and saved in the screenshot's directory.
    
