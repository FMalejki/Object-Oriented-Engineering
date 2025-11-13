package pl.edu.agh.iisg.to.service;

import pl.edu.agh.iisg.to.dao.CourseDao;
import pl.edu.agh.iisg.to.dao.GradeDao;
import pl.edu.agh.iisg.to.dao.StudentDao;
import pl.edu.agh.iisg.to.model.Course;
import pl.edu.agh.iisg.to.model.Grade;
import pl.edu.agh.iisg.to.model.Student;
import pl.edu.agh.iisg.to.session.TransactionService;
import java.util.Optional;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

public class SchoolService {

    private final TransactionService transactionService;

    private final StudentDao studentDao;

    private final CourseDao courseDao;

    private final GradeDao gradeDao;

    public SchoolService(TransactionService transactionService, StudentDao studentDao, CourseDao courseDao, GradeDao gradeDao) {
        this.transactionService = transactionService;
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.gradeDao = gradeDao;
    }

    public boolean enrollStudent(final Course course, final Student student) {
        return transactionService.doAsTransaction(() -> {
            if(course.studentSet().contains(student)){
                return false;
            }
            course.studentSet().add(student);
            student.courseSet().add(course);
            return true;
        }).orElse(false);
    }

    public boolean removeStudent(int indexNumber) {
        return transactionService.doAsTransaction(() -> {
            Optional<Student> studentOpt = studentDao.findByIndexNumber(indexNumber);
            if ( studentOpt.isEmpty()) {
                return false;
            }
            Student student = studentOpt.get();
            for( Course course : student.courseSet()) {
                course.studentSet().remove(student);
            }
            studentDao.remove(student);
            return true;
        }).orElse(false);
    }

    public boolean gradeStudent(final Student student, final Course course, final float gradeValue) {
        return transactionService.doAsTransaction(() -> {
            Grade grade = new Grade(student, course, gradeValue);

            student.gradeSet().add(grade);
            course.gradeSet().add(grade);

            gradeDao.save(grade);
            return true;
        }).orElse(false);
    }

    public Map<String, List<Float>> getStudentGrades(String courseName) {
        Optional<Course> courseOpt = courseDao.findByName(courseName);
        if (courseOpt.isEmpty()) {
            return Collections.emptyMap();
        }
        Course course = courseOpt.get();
        Map<String, List<Float>> studentMap = new HashMap<>();
        for(Student student : course.studentSet()) {
            List<Float> grades = student.gradeSet().stream()
                    .filter(grade -> grade.course().equals(course))
                    .map(Grade::grade)
                    .sorted()
                    .toList();
            if (!grades.isEmpty()) {
                studentMap.put(student.fullName(), grades);
            }
            else {
                studentMap.put(student.fullName(), Collections.emptyList());
            }
        }
        return studentMap;
    }
}
