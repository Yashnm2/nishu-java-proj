# Student CCA Management System Explanation

This project is a plain Java console application for managing a student's CCA
events. It does not use Spring, JavaFX, Swing, Maven, or Gradle. The program is
run from `Main.java`, reads input from the terminal, and stores all data in
memory while the program is running.

## Project Structure

```text
nishu_java_proj/
|-- Main.java
|-- backend/
|   |-- Activity.java
|   |-- Authenticatable.java
|   |-- CCA_Event.java
|   `-- Student.java
|-- controller/
|   |-- AuthController.java
|   |-- CCA_EventController.java
|   `-- CRUDController.java
`-- view/
    |-- BaseView.java
    |-- CCA_EventView.java
    `-- LoginView.java
```

### `Main.java`

`Main.java` is the entry point of the application. It contains the `main`
method, creates the shared `Scanner`, creates the controllers, creates the
views, and starts the login loop.

The main flow is:

1. Create `AuthController` for login/logout logic.
2. Create `CCA_EventController` for CCA event operations.
3. Create `LoginView` and `CCA_EventView`.
4. Ask the student to log in.
5. Show the CCA event menu for the logged-in student.
6. After logout, return to the login screen.

### `backend/`

The `backend` folder contains the model or domain classes. These classes
represent the important data used by the application.

- `Activity.java` defines an interface for activity-like objects. It requires
  methods such as `getId()`, `getName()`, `getDate()`, `getRole()`,
  `getDescription()`, `getHours()`, and `getSealPoints()`.
- `Authenticatable.java` defines an interface for objects that can be
  authenticated. It requires `getId()` and `getPassword()`.
- `CCA_Event.java` represents one CCA event. It implements `Activity`, stores
  fields such as name, date, role, description, hours, and SEAL points, and
  provides getters, setters, and a `toString()` method.
- `Student.java` represents a student account. It implements
  `Authenticatable`, stores the student's name, student ID, password, and list
  of CCA events, and provides methods to add, remove, list, and find events.

### `controller/`

The `controller` folder contains the business logic. Controllers sit between
the view layer and the backend models.

- `AuthController.java` handles login, logout, and the current logged-in
  student. It also creates two demo accounts: `S001 / pass123` and
  `S002 / pass456`.
- `CCA_EventController.java` handles CCA event operations. It can list all
  events, add a new event, edit an existing event, and delete an event.
- `CRUDController.java` is a generic interface for common CRUD-style
  operations. In this project, it defines `getAll(Student student)` and
  `delete(Student student, int id)`.

### `view/`

The `view` folder contains the console user interface. These classes print
menus, read input, and call controller methods.

- `BaseView.java` is an abstract base class shared by other views. It stores
  the `Scanner`, provides `readLine()` for reading trimmed user input, and
  provides `parseInt()` for safely converting text to an integer.
- `LoginView.java` shows the login prompt and keeps asking until the student
  enters valid credentials.
- `CCA_EventView.java` shows the CCA event menu. It lets the logged-in student
  view, add, edit, and delete CCA events, or log out.

## Architecture and Framework Style

This project follows a simple MVC-style structure:

- Model: classes in `backend/`, such as `Student` and `CCA_Event`.
- View: classes in `view/`, such as `LoginView` and `CCA_EventView`.
- Controller: classes in `controller/`, such as `AuthController` and
  `CCA_EventController`.

MVC means the application is split into separate responsibilities:

- The model stores and represents data.
- The view handles input and output.
- The controller contains the logic that connects the view to the model.

This is not a full external framework. It is a lightweight structure written
directly in Java to keep the code organized.

## Application Flow

When the application starts, `Main.java` creates the controllers and views.
It then displays the system title and demo login accounts.

The login process works like this:

1. `LoginView.promptLogin()` asks for a student ID and password.
2. `LoginView` passes those values to `AuthController.login()`.
3. `AuthController` checks its `HashMap` of demo students.
4. If the credentials match, the matching `Student` is returned.
5. If the credentials are wrong, the login view asks again.

After login, the CCA event menu works like this:

1. `CCA_EventView.show(student)` displays the menu.
2. The student chooses an option.
3. `CCA_EventView` reads any needed input.
4. `CCA_EventView` calls `CCA_EventController`.
5. `CCA_EventController` updates the logged-in `Student` object's event list.
6. The result is printed back to the console.

## Java Concepts Used

### Packages and Imports

Files inside folders use package declarations such as `package backend;`,
`package controller;`, and `package view;`. Other files import them using
statements such as `import backend.Student;`.

Packages organize classes into groups. Imports let one class use another class
from a different package.

### Classes and Objects

Classes define the structure and behavior of something. For example,
`Student` defines what information a student has, and `CCA_Event` defines what
information a CCA event has.

Objects are actual instances of classes. For example, `new Student("Alice",
"S001", "pass123")` creates a real student object.

### Interfaces

Interfaces define methods that a class must provide.

- `Activity` says that an activity must provide methods such as `getName()`,
  `getHours()`, and `getSealPoints()`.
- `Authenticatable` says that an object must provide `getId()` and
  `getPassword()`.
- `CRUDController<T>` says that a controller must provide methods for getting
  all items and deleting an item.

`CCA_Event` implements `Activity`, and `Student` implements `Authenticatable`.

### Encapsulation

Encapsulation means keeping fields private and accessing them through methods.
For example, `CCA_Event` stores fields like `name`, `date`, and `hours` as
private fields. Other classes use getter and setter methods to read or update
those values.

This helps control how data is accessed and changed.

### Constructors

Constructors create new objects with starting values. For example,
`CCA_Event` has a constructor that receives the event name, date, role,
description, hours, and SEAL points.

### Getters and Setters

Getters return field values, such as `getName()` or `getHours()`. Setters
update field values, such as `setName()` or `setHours()`.

### Inheritance and Abstraction

`BaseView` is an abstract class. It contains shared view behavior used by both
`LoginView` and `CCA_EventView`.

This avoids repeating the same input helper methods in multiple view classes.

### Generics

`CRUDController<T>` uses a generic type `T`. This means the interface can work
with different types of objects. In this project,
`CCA_EventController implements CRUDController<CCA_Event>`, so `T` becomes
`CCA_Event`.

### Collections

The project uses Java collection classes:

- `ArrayList` stores each student's CCA events in order.
- `List` is the general interface used when returning a group of events.
- `HashMap` stores demo student accounts by student ID.
- `Map` is the general interface used for key-value storage.

### Console Input with `Scanner`

The application uses `Scanner` to read text typed by the user in the terminal.
The same `Scanner` is shared between the login view and CCA event view.

`BaseView.readLine()` wraps the scanner logic so the view classes can ask for
input with a prompt.

### CRUD Operations

CRUD means Create, Read, Update, and Delete.

In this project:

- Create: add a new CCA event.
- Read: view all CCA events.
- Update: edit an existing CCA event.
- Delete: remove a CCA event by ID.

These operations are mainly handled by `CCA_EventController` and stored inside
the logged-in `Student` object's event list.

## Data Storage

The project stores data in memory only. This means data exists while the
program is running, but it is not saved to a file or database.

`AuthController` creates two demo accounts when the program starts:

- Student ID: `S001`, password: `pass123`
- Student ID: `S002`, password: `pass456`

Alice also starts with one sample CCA event. Any events added while the program
runs are stored in that student's `ArrayList`.

## Important Limitations

- Data is not saved after the program exits.
- There is no database.
- There is no graphical interface.
- There is no web server.
- There is no external framework.
- Input validation is simple. For example, invalid numbers fall back to a
  default value using `parseInt()`.

## How to Compile and Run

From the project root, compile the Java files:

```powershell
javac Main.java backend/*.java controller/*.java view/*.java
```

Then run the application:

```powershell
java Main
```

Use one of the demo accounts shown by the program to log in.
