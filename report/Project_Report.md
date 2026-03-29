# Project Report: HireSync - University Placement Management System

## 1. Introduction
**HireSync** is a comprehensive, console-based Java application designed to streamline and automate the university placement process. The system manages the interactions between students, companies, and the placement cell, ensuring that eligibility criteria are met and application lifecycles are tracked accurately.

## 2. Problem Statement
Managing university placements manually is error-prone and inefficient. Key challenges include:
- Verifying student eligibility (CGPA, backlogs) for various job roles.
- Tracking the status of multiple applications per student.
- Enforcing placement rules (e.g., one offer per student).
- Managing communication between companies and eligible candidates.

## 3. Proposed Solution
HireSync provides a centralized platform with:
- Automated eligibility filtering.
- State-based application tracking.
- Singleton-based centralized coordination.
- Custom exception handling for business rule enforcement.

## 4. Software Architecture
The project is built using **Pure Java** with a modular package structure:
- **`hiresync.models`**: Core data entities.
- **`hiresync.state`**: Application lifecycle management.
- **`hiresync.filters`**: Eligibility criteria implementation.
- **`hiresync.services`**: Centralized business logic.
- **`hiresync.exceptions`**: Error handling.
- **`hiresync.ui`**: User interaction layer.

## 5. Design Patterns Implemented
1. **State Design Pattern**: Used to manage the lifecycle of a job application (`AppliedState`, `InterviewState`, `OfferedState`). It ensures that transitions occur in the correct order.
2. **Filter Design Pattern**: Implemented to check student eligibility against job requirements (`CgpaCriteria`, `BacklogCriteria`, `AndCriteria`).
3. **Singleton Design Pattern**: The `PlacementCoordinator` is implemented as a Singleton to ensure a single point of control for all placement activities.

## 6. Implementation Details (Tasks 1-10)
- **Phase 1**: Defined core models (Student, Company, JobProfile).
- **Phase 2**: Implemented State Pattern for application tracking.
- **Phase 3**: Developed Filter Pattern for eligibility.
- **Phase 4**: Created custom exceptions for robust error handling.
- **Phase 5**: Established the Singleton `PlacementCoordinator` service.
- **Phase 6**: Developed the interactive Console UI.
- **Phase 7-9**: Integrated all components, refined logic, and added edge-case validation (e.g., preventing duplicate offers).
- **Phase 10**: Finalized documentation and testing.

## 7. Business Rules Enforced
- **Eligibility**: Students must meet minimum CGPA and maximum backlog requirements.
- **Lifecycle**: Sequence must be Apply -> Interview -> Offer.
- **Uniqueness**: A student cannot hold more than one job offer at a time.
- **Validation**: Prevents duplicate applications for the same profile.

## 8. Conclusion
HireSync successfully demonstrates the application of Object-Oriented Programming principles and Design Patterns to solve a real-world management problem. The system is robust, extensible, and provides a clear interface for all stakeholders in the placement process.
