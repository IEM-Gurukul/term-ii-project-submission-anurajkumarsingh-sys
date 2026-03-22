package hiresync.tests;

import hiresync.models.JobProfile;
import hiresync.models.Student;
import hiresync.services.PlacementCoordinator;
import hiresync.exceptions.IneligibleStudentException;
import hiresync.state.OfferedState;

/**
 * Basic Unit Tests for HireSync Core Components.
 */
public class CoreUnitTests {

    public static void main(String[] args) {
        System.out.println("=== Running HireSync Unit Tests ===\n");
        
        testStudentEligibility();
        testStateTransitions();
        testSingletonProperty();
        
        System.out.println("\n=== All Tests Completed ===");
    }

    private static void testStudentEligibility() {
        System.out.println("Test 1: Student Eligibility Filtering");
        PlacementCoordinator coordinator = PlacementCoordinator.getInstance();
        
        Student s1 = new Student("S1", "Eligible Student", "s1@test.com", "pass", 8.5, 0, "CS");
        Student s2 = new Student("S2", "Ineligible Student", "s2@test.com", "pass", 6.0, 3, "CS");
        
        coordinator.registerStudent(s1);
        coordinator.registerStudent(s2);
        
        JobProfile profile = new JobProfile("J1", "Software Engineer", "Desc", 7.5, 0, 10.0);
        
        var eligible = coordinator.getEligibleStudents(profile);
        
        if (eligible.contains(s1) && !eligible.contains(s2)) {
            System.out.println("[PASS] Eligibility filtering works correctly.");
        } else {
            System.out.println("[FAIL] Eligibility filtering failed.");
        }
    }

    private static void testStateTransitions() {
        System.out.println("Test 2: Application State Transitions");
        PlacementCoordinator coordinator = PlacementCoordinator.getInstance();
        
        Student s1 = new Student("S3", "State Test Student", "s3@test.com", "pass", 9.0, 0, "IT");
        coordinator.registerStudent(s1);
        JobProfile profile = new JobProfile("J2", "DevOps Engineer", "Desc", 7.0, 0, 12.0);
        
        try {
            coordinator.applyStudentToJob(s1, profile);
            System.out.println(" - Applied: " + s1.getApplicationState(profile.getProfileId()).getStatus());
            
            coordinator.scheduleInterview(s1, profile);
            System.out.println(" - Interview: " + s1.getApplicationState(profile.getProfileId()).getStatus());
            
            coordinator.makeOffer(s1, profile);
            System.out.println(" - Offer: " + s1.getApplicationState(profile.getProfileId()).getStatus());
            
            if (s1.getApplicationState(profile.getProfileId()) instanceof OfferedState) {
                System.out.println("[PASS] State transitions verified.");
            } else {
                System.out.println("[FAIL] Final state mismatch.");
            }
        } catch (IneligibleStudentException e) {
            System.out.println("[FAIL] Unexpected IneligibleStudentException: " + e.getMessage());
        }
    }

    private static void testSingletonProperty() {
        System.out.println("Test 3: Singleton PlacementCoordinator");
        PlacementCoordinator instance1 = PlacementCoordinator.getInstance();
        PlacementCoordinator instance2 = PlacementCoordinator.getInstance();
        
        if (instance1 == instance2) {
            System.out.println("[PASS] Singleton property verified.");
        } else {
            System.out.println("[FAIL] Multiple instances found.");
        }
    }
}
