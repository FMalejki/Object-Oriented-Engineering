package pl.edu.agh.to.school.grade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.student.Student;

@Service
public class GradeBook {

    private final Map<String, List<Grade>> studentGrades = new HashMap<>();

    public GradeBook() {
        System.out.println("GradeBook Created");
    }

    @PostConstruct
    public void onServiceStarted() {
        System.out.println("GradeBook Started");
    }

    @PreDestroy
    public void Destroyed() {
        System.out.println("GradeBook Destroyed");
    }

    public Grade assignGrade(Student student, Course course, double gradeValue) {
        Grade grade = new Grade(course, gradeValue);
        this.studentGrades.computeIfAbsent(student.getFullName(), k -> new java.util.ArrayList<>()).add(grade);
        return grade;
    }

    
}
