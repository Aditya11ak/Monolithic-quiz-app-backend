# Quiz Application Backend 


A **Spring Boot monolithic backend** for a quiz application.  
It provides REST APIs to manage questions, create quizzes based on category, and evaluate quiz submissions.  

This project demonstrates clean architecture with **Spring Boot, Spring Data JPA, MySQL, REST APIs, and Lombok**.



## 🏗️ Monolithic vs Microservices

This app is **monolithic** because all features (questions, quizzes, evaluation) run in a single Spring Boot service.  
It can be converted to **microservices** by splitting into independent services (Question Service, Quiz Service, Evaluation Service) communicating via REST.

---

## 🚀 Features

- **Question Management**
  - Add new questions  
  - Update existing questions  
  - Delete questions by ID  
  - Fetch all questions or filter by category  

- **Quiz Management**
  - Create a quiz by selecting random questions from a category  
  - Retrieve quiz questions (without exposing correct answers)  
  - Submit answers and calculate the score  

- **Database Integration**
  - Uses **Spring Data JPA** with **MySQL** for persistence  
  - Custom query to fetch random questions by category  

- **Error Handling**
  - Proper response codes (`200 OK`, `201 Created`, `404 Not Found`, etc.)  
  - Entity validation for updates and deletes  

---

## 🛠️ Tech Stack

- **Java 17+**  
- **Spring Boot** (REST API development)  
- **Spring Data JPA** (ORM)  
- **MySQL** (database)  
- **Lombok** (boilerplate reduction)  
- **Maven** (build tool)  
## 🔑 API Endpoints

### Question APIs 
| Method | Endpoint                        | Description                     |
|--------|---------------------------------|---------------------------------|
| GET    | `/question/allQuestions`        | Get all questions               |
| GET    | `/question/category/{category}` | Get questions by category       |
| POST   | `/question/add`                 | Add a new question              |
| PUT    | `/question/update`              | Update an existing question     |
| DELETE | `/question/deleteQuestion/{id}` | Delete question by ID           |

### Quiz APIs
| Method | Endpoint             | Description                                |
|--------|----------------------|--------------------------------------------|
| POST   | `/quiz/createQuiz`   | Create a quiz by category & question count |
| GET    | `/quiz/get/{id}`     | Fetch quiz questions (without answers)     |
| POST   | `/quiz/submit/{id}`  | Submit answers and get score               |
