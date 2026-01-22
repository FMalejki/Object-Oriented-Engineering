package pl.edu.agh.to.school.grade;

import javax.management.Notification;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.notification.ConsoleNotificationService;
import pl.edu.agh.to.school.student.Student;

@Service
public class GradeService {

    private final GradeBook gradeBook;
    private final ConsoleNotificationService notificationService;

    public GradeService(GradeBook gradeBook, ConsoleNotificationService notificationService) {
        this.gradeBook = gradeBook;
        this.notificationService = notificationService;
        System.out.println("GradleService Created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("GradeService Started");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("GradeService Destroyed");
    }

    public void assignGrade(Student student, Course course, double gradeValue) {
        Grade grade = gradeBook.assignGrade(student, course, gradeValue);
        notificationService.notify(student, grade);
    }
}
