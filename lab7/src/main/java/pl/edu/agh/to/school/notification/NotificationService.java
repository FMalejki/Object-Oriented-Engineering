package pl.edu.agh.to.school.notification;

import pl.edu.agh.to.school.student.Student;

import pl.edu.agh.to.school.grade.Grade;

public interface NotificationService {

    void notify(Student student, Grade grade);

}
