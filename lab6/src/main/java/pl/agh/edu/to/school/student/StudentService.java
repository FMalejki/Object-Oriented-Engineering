package pl.agh.edu.to.school.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {
    public List<Student> getStudents() {
        return List.of(new Student("Jan", "Kowalski", LocalDate.now(), "123456"));
    }
}
