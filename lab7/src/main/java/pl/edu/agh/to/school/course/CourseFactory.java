package pl.edu.agh.to.school.course;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import pl.edu.agh.to.school.student.Student;

@Configuration
public class CourseFactory {

    @Bean
    public Course computerNetworksCourse() {
        var student = new Student("Piotr", "Budynek", LocalDate.of(1990, 11, 7), "22334455", "budynek@student.agh.edu.pl");
        var course = new Course("Sieci komputerowe");
        course.enrollStudent(student);
        return course;
    }
    
    @Bean
    public Course objectProgrammingCourse() {
        var student = new Student("Anna", "Nowak", LocalDate.of(1999, 5, 23), "99887766", "anowak@gmail.com");
        var course = new Course("Programowanie obiektowe");
        course.enrollStudent(student);
        return course;
    }

}
