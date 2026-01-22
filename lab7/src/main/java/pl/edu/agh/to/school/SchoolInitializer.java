package pl.edu.agh.to.school;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import pl.edu.agh.to.school.course.Course;
import pl.edu.agh.to.school.student.Student;
import pl.edu.agh.to.school.student.StudentService;

@Service
public class SchoolInitializer {

    private final Student student;
    private final Course course;
    private final Course computerNetworksCourse;
    private final Course objectProgrammingCourse;
    private final StudentService studentService;
    private List<Course> courses = new ArrayList<>();

    public SchoolInitializer(StudentService studentService, @Qualifier("computerNetworksCourse") Course computerNetworksCourse, @Qualifier("objectProgrammingCourse") Course objectProgrammingCourse, List<Course> courses) {
        Student student = new Student("Jan", "Kowalski", java.time.LocalDate.of(2000, 1, 15), "12", "jkowalski@gmail.com");
        this.course = new Course("Wprowadzenie do informatyki");
        this.objectProgrammingCourse = objectProgrammingCourse;
        this.courses = courses;
        this.student = student;
        this.computerNetworksCourse = computerNetworksCourse;
        this.studentService = studentService;
    }

    @PostConstruct
    public void initComputerNetworksCourse() {
        computerNetworksCourse.getStudents()
                .forEach(student -> studentService.assignGrade(student, computerNetworksCourse, 4.5));
    }

    @PostConstruct
    public void initializeSchool() {
        course.enrollStudent(student);
        studentService.assignGrade(student, course, 5.0);
    }

    @PostConstruct
    public void initObjectProgrammingCourse() {
        objectProgrammingCourse.getStudents()
                .forEach(student -> studentService.assignGrade(student, objectProgrammingCourse, 4.0));
    }

    @PostConstruct
    public void assignGradeAllCourses() {
        for (Course course : courses) {
            for (Student student : course.getStudents()) {
                studentService.assignGrade(student, course, 3.5);
            }
        }
    }

}
