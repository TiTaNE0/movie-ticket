# 🎥 Movie Ticket Booking System - Backend Development

## 📝 Project Overview
This assignment aims to develop a RESTful API for a movie ticket booking system using Spring Boot. The system will manage movies, showtimes, users, and ticket bookings. This tests the candidate's ability to design a robust backend system.

---

## 🔧 Functional Requirements

### 🎬 1. Movie Management
**Features:**
- ➕ Add new movies with details:
    - 🎞️ Title
    - 🎭 Genre
    - ⏳ Duration
    - ⭐ Rating
    - 📅 Release year
- 🔄 Update movie information.
- ❌ Delete a movie.
- 📜 Fetch a list of movies or specific movie details.

### ⏰ 2. Showtime Management
**Features:**
- ➕ Add showtimes for movies with details:
    - 🎥 Movie
    - 🎭 Theater
    - 🕒 Start time
    - 🕒 End time
- 🔄 Update showtime details.
- ❌ Delete a showtime.
- 📜 Fetch all showtimes for a specific movie or theater.

**Constraints:**
- 🚫 No overlapping showtimes for the same theater.

### 👥 3. User Management
**Features:**
- 📝 Register users with details such as:
    - 👤 Name
    - 📧 Email
    - 🔑 Password
    - 🎫 Role (Admin/Customer)
- 🔐 Authenticate users via login.
- 🛠️ Admin users can manage movies and showtimes.
- 🎟️ Customers can book tickets.

### 🎟️ 4. Ticket Booking System
**Features:**
- 🛒 Allow customers to book tickets for available showtimes.
- 🗂️ Track booking details:
    - 👤 User
    - 🎥 Movie
    - ⏰ Showtime
    - 💺 Seat number
    - 💵 Price
- 🚫 Ensure no seat is booked twice for the same showtime.

**Constraints:**
- 🪑 Maximum seats per showtime must be configurable.

---

## 🛠️ Technical Requirements

### 1. Frameworks and Tools
- **Backend Framework:** 🖥️ Spring Boot
- **Database:** 🗄️ H2, PostgreSQL, or MySQL (candidate's choice)
- **Authentication & Authorization:** 🔐 Spring Security
- **Testing:** 🧪 JUnit and Mockito, Test Containers
- **API Documentation:** 📘 Swagger or detailed README, Postman collection

---

## 📩 Submission Guidelines

### Deliverables
- 📂 Deliver the source code via a GitHub repository.

### 📖 Setup Instructions (Include in README.md):
1. **Prerequisites:**
    - ☕ Java (version 17 or higher)
    - 🛠️ Maven or Gradle (depending on your setup)
2. **Steps to Run the Application:**
    - 📥 Clone the repository.
    - ⚙️ Configure the database connection in `application.properties` or `application.yml`.
    - ▶️ Run the application using:
      ```bash
      mvn spring-boot:run
      ```
   or
     ```bash
     ./gradlew bootRun
     ```
3. **Database Configuration:**
    - 🗄️ Default settings use H2 in-memory database.
    - 🔄 To switch to PostgreSQL or MySQL, update the database configuration in `application.properties`.

4. **API Documentation:**
    - 🌐 Access the Swagger UI at `http://localhost:8080/swagger-ui.html` after running the application.
    - 📄 Alternatively, refer to the provided Postman collection.

---

### 💡 Additional Notes
- ✅ Ensure all endpoints are well-documented and test cases are included.
- 🛡️ Follow best practices for code structure and security.

---

🙏 Thank you for reviewing this project!
