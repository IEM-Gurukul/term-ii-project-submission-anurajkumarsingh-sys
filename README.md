# HireSync: University Placement Management System

HireSync is a core Java console-based application designed to manage the university placement process efficiently. It implements several design patterns to ensure a robust and scalable architecture.

##  Features

- **User Management**: Support for Students and Companies.
- **Job Management**: Companies can post job profiles with specific eligibility criteria.
- **Eligibility Engine**: Automatic filtering of students based on CGPA and Backlogs using the **Filter Design Pattern**.
- **Application Lifecycle**: Tracking of student applications from Applied to Interview and Offer using the **State Design Pattern**.
- **Singleton Service**: Centralized coordination of all placement activities.
- **Robust Error Handling**: Custom exceptions for business rule violations and edge cases.

##  Architecture & Design Patterns

The project follows a modular package structure:

- **`hiresync.models`**: Data classes (User, Student, Company, JobProfile).
- **`hiresync.state`**: **State Design Pattern** implementation for application lifecycle.
- **`hiresync.filters`**: **Filter Design Pattern** for student eligibility criteria.
- **`hiresync.services`**: **Singleton Design Pattern** (PlacementCoordinator) for business logic.
- **`hiresync.exceptions`**: Custom exceptions for business rule enforcement.
- **`hiresync.ui`**: Console-based presentation layer.
- **`hiresync.tests`**: Unit tests for core system verification.

##  Installation & Running

### Prerequisites
- Java Development Kit (JDK) 17 or higher.

### Compilation
From the project root directory, run:
```powershell
javac -d bin -sourcepath src src/hiresync/ui/ConsoleApp.java
```

### Execution
To start the main application:
```powershell
java -cp bin hiresync.ui.ConsoleApp
```

To run the unit tests:
```powershell
java -cp bin hiresync.tests.CoreUnitTests
```

## 📝 Usage Guide

1. **Register Student**: Enter details like CGPA and backlogs.
2. **Register Company**: Provide company information.
3. **Post Job Profile**: Create job openings with eligibility requirements.
4. **Apply for Job**: Students can apply for eligible profiles.
5. **Manage Application**: Move applications through Interview and Offer stages.
6. **View Eligibility**: Instantly see which students qualify for a specific job.

##  Business Rules & Validation

- Students cannot apply for jobs they are ineligible for (CGPA/Backlogs).
- Application states must follow the sequence: `APPLIED` -> `INTERVIEW_SCHEDULED` -> `OFFERED`.
- A student can only hold ONE active offer across the entire system.
- Duplicate applications for the same job are prevented.
