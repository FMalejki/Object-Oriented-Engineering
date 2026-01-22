package pl.edu.agh.to.school.notification;

import java.io.Serial;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import pl.edu.agh.to.school.grade.Grade;
import pl.edu.agh.to.school.student.Student;

@Service
public class ConsoleNotificationService implements NotificationService {

    public ConsoleNotificationService() {
        System.out.println("ConsoleNotificationService Created");
    }

    public void notify(Student student, Grade grade) {
        System.out.println(student.getFullName() + " Otrzymal " + grade.value() + " Z " + grade.course());
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("ConsoleNotificationService Started");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("ConsoleNotificationService Destroyed");
    }
}
