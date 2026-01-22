package pl.edu.agh.to.school.student;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.grade.GradeService;

@Service
public class StudentService {

    private final GradeService gradeService;

    public StudentService(GradeService gradeService) {
        this.gradeService = gradeService;
        System.out.println("StudentService Created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("StudentService started");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("StudentService destroyed");
    }

    public void assignGrade(Student student, Course course, double gradeValue) {
        gradeService.assignGrade(student, course, gradeValue);
    }
}
