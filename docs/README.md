# Eltry User Guide

Welcome to **Eltry**, your personal task management chatbot!
Eltry helps you keep track of **todos, deadlines, and events**, all from a simple command-line or GUI interface.

---

## 🚀 Getting Started

### Launch Eltry
- Run the application using the provided launcher or via your IDE.
- On startup, Eltry will greet you and load your previous tasks.

### Interacting with Eltry
- Type commands into the input field (GUI) or console (CLI).
- Eltry will respond and update your task list accordingly.

---

## ✨ Features

### 1. Add a Todo
**Format:**
```
todo TASK_NAME
```
**Example:**
```
todo read book
```
**Outcome:**
Adds a todo task named *"read book"*.

---

### 2. Add a Deadline
**Format:**
```
deadline TASK_NAME /by DATE_TIME
```
- `DATE_TIME` must be in `yyyy-MM-dd HH:mm` format (e.g., `2025-09-01 18:00`).

**Example:**
```
deadline return book /by 2025-09-01 18:00
```
**Outcome:**
Adds a deadline task with the specified due date.

---

### 3. Add an Event
**Format:**
```
event TASK_NAME /from START_DATE_TIME /to END_DATE_TIME
```
- Dates must be in `yyyy-MM-dd HH:mm` format.

**Example:**
```
event homework /from 2025-09-01 18:00 /to 2025-09-02 18:00
```
**Outcome:**
Adds an event spanning the specified time range.

---

### 4. List Tasks
**Format:**
```
list
```
**Outcome:**
Displays all tasks in your list.

---

### 5. Mark a Task as Done
**Format:**
```
mark INDEX
```
- `INDEX` is the **1-based position** of the task in the list.

**Example:**
```
mark 2
```
**Outcome:**
Marks the second task as completed.

---

### 6. Unmark a Task
**Format:**
```
unmark INDEX
```
**Example:**
```
unmark 2
```
**Outcome:**
Marks the second task as not completed.

---

### 7. Delete a Task
**Format:**
```
delete INDEX
```
**Example:**
```
delete 1
```
**Outcome:**
Removes the first task from your list.

---

### 8. Find Tasks
**Format:**
```
find KEYWORD
```
**Example:**
```
find book
```
**Outcome:**
Lists all tasks containing *"book"* in their name.

---

### 9. Exit Eltry
**Format:**
```
bye
```
**Outcome:**
Saves your tasks and exits the application.

---

## ⚠️ Error Handling

- Eltry will display helpful error messages if your command is invalid or if date formats are incorrect.
- For date/time, always use the format:
  ```
  yyyy-MM-dd HH:mm
  ```
  Example: `2025-09-01 18:00`

---

## 💡 Example Session
```
todo read book
event homework /from 2025-09-01 18:00 /to 2025-09-02 18:00
deadline return book /by 2025-09-01 18:00
list
mark 1
unmark 1
delete 2
find book
bye
```
