# Task-Complete: Accessible Gamified Routine Manager

## 📖 Overview
**Task-Complete** is a Full-Stack web application (React.js + Spring Boot) designed to gamify daily routine management. Originally developed as a Bachelor's Thesis (TCC) at the University of São Paulo (USP), this project serves a critical social and technical purpose: to assist individuals with intellectual and cognitive challenges in organizing their daily activities.

By transforming mundane real-world tasks into an engaging interactive experience, the platform bridges the gap between productivity tools and accessible entertainment. Transform daily tasks into motivational elements, engaging users through a virtual reward system. The project combines technology, behavioral psychology (Gamification), and accessibility guidelines to create an inclusive virtual environment.

---

## ✨ Features

* **Routine Management:** Control of Tasks, Dailies, Habits, and Bills.
* **Reward System:** Completing tasks generates virtual coins for the user.
* **Virtual Store:** Use of rewards to purchase new items.
* **Avatar Customization:** The user can equip and customize their character with purchased items (clothes, hair, etc.).
* **Integrated Accessibility:** Interface rigorously designed and tested for high contrast and ease of use.

---

## 🔬 Academic Research & Accessibility (UX/UI)
Unlike standard CRUD applications, Task-Complete was built upon rigorous academic research in Human-Computer Interaction (HCI) and digital accessibility. 

The interface and game loops were designed following the **Web Content Accessibility Guidelines (WCAG 2.1)**, alongside the **GameFlow** and **PENS (Player Experience of Need Satisfaction)** models, to comprehensively assess and guarantee player satisfaction for users with cognitive impairments.

**Key Accessibility Implementations:**
* **Cognitive Load Reduction:** Deep menu trees were eliminated. The application utilizes a flat, component-based routing system allowing users to switch contexts (Tasks, Habits, Bills) with a single click without page reloads.
* **Visual Clarity & Contrast Evaluation:** Strict adherence to WCAG contrast ratios, continuously validated using the **Color Contrast Analyzer** tool, to assist users with visual and cognitive processing difficulties.
* **W3C Validation:** The source code was rigorously verified against World Wide Web Consortium (W3C) standards to ensure structural integrity and broad compatibility.
* **Immediate Feedback Loops:** Clear, sensory feedback upon task completion to reinforce positive habits and maintain engagement without causing frustration.

---

## 🚀 Gamification Engine & Core Modules

The application is logically partitioned into four main state-driven modules, each handling a specific aspect of a user's routine:

1. **Tarefas (One-off Tasks):** Standard to-do list for non-recurring activities.
2. **Diárias (Daily Tasks):** Recurring tasks that reset every 24 hours, building a streak system.
3. **Hábitos (Habit Tracking):** Long-term tracking for positive and negative behaviors, directly impacting the user's in-game status.
4. **Contas (Bill Management):** A simplified financial module to help users track monthly obligations responsibly.

**The Reward Loop:**
Completing tasks across any of the four modules dispatches state updates that reward the user with virtual currency and experience points. These resources feed into an "Inventory" and "Store" system, converting real-world responsibilities into tangible digital rewards.

---

## 🛠️ Tech Stack & Architecture

### Frontend (Client-Side)
* **Framework:** React.js
* **State Management:** React Hooks (`useState`, `useEffect`) for localized state mutation and prop-drilling for global game events.
* **Routing:** React Router DOM (Single Page Application architecture).
* **Styling:** Custom CSS tailored for high-contrast accessibility.

### Backend (Server-Side)
* **Framework:** Java / Spring Boot
* **Architecture:** RESTful API
* **Data Handling:** JSON serialization and dedicated REST endpoints.
* **Persistence:** Relational database integration to store user profiles, task states, and inventory progress.

### Database (Data Persistence)
* **Engine:** PostgreSQL
* **Architecture:** Relational Database Management System (RDBMS).
* **Data Modeling:** Structured schemas to securely manage user profiles, daily task states, and virtual inventory progression.

### Design & Prototyping (UI/UX)
* **Design Tool:** Figma
* **Methodology:** Persona-based design to map the user journey for individuals with intellectual challenges.
* **Wireframing:** High-fidelity prototyping and contrast validation prior to frontend implementation.

---

## 📁 Project Structure (Monorepo)

```text
📦 Task-Complete
 ┣ 📂 Backend/                # Spring Boot REST API
 ┃ ┣ 📂 src/main/java         # Controllers, Services, Repositories, and Entities
 ┃ ┗ 📜 pom.xml               # Maven dependencies
 ┣ 📂 react-task-complete/    # React.js Frontend SPA
 ┃ ┣ 📂 public/
 ┃ ┣ 📂 src/
 ┃ ┃ ┣ 📂 Contas              # Bill management UI and logic
 ┃ ┃ ┣ 📂 Diarias             # Daily tasks UI and logic
 ┃ ┃ ┣ 📂 Habitos             # Habit tracking UI and logic
 ┃ ┃ ┣ 📂 Tarefas             # General task UI and logic
 ┃ ┃ ┣ 📂 Inventario          # Gamification inventory system
 ┃ ┃ ┣ 📂 Loja                # Virtual store component
 ┃ ┃ ┣ 📜 Rotina.js           # Core state routing controller
 ┃ ┃ ┗ 📜 App.js              # Application entry point
 ┃ ┗ 📜 package.json          # Node.js dependencies
 ┗ 📜 README.md
```

 ## 🚀 How to Run the Project

### Prerequisites
* Node.js and npm (or yarn)
* Java JDK 11+
* PostgreSQL running locally

### Backend Setup (Spring Boot)
1. Navigate to the backend folder.
2. Configure your PostgreSQL database credentials in the `application.properties` file.
3. Run the application using Maven or your preferred IDE.

### Frontend Setup (React)
1. Navigate to the frontend folder.
2. Install the dependencies:
   ```bash
   npm install

## 🎓 Authorship

* **Author:** Pedro Afonso Fazio Michalichem.
* **Advisor:** Prof. Dr. Kamila Rios da Hora Rodrigues.
* **Institution:** Institute of Mathematics and Computer Sciences (ICMC) - University of São Paulo (USP), São Carlos - SP.
