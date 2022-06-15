package ru.ulstu.is.sbapp.student.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.ulstu.is.sbapp.student.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class StudentService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Student addStudent(String firstName, String lastName) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Student name is null or empty");
        }
        final Student student = new Student(firstName, lastName);
        em.persist(student);
        return student;
    }

    @Transactional(readOnly = true)
    public Student findStudent(Long id) {
        final Student student = em.find(Student.class, id);
        if (student == null) {
            throw new EntityNotFoundException(String.format("Student with id [%s] is not found", id));
        }
        return student;
    }

    @Transactional(readOnly = true)
    public List<Student> findAllStudents() {
        return em.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Transactional
    public Student updateStudent(Long id, String firstName, String lastName) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName)) {
            throw new IllegalArgumentException("Student name is null or empty");
        }
        final Student currentStudent = findStudent(id);
        currentStudent.setFirstName(firstName);
        currentStudent.setLastName(lastName);
        return em.merge(currentStudent);
    }

    @Transactional
    public Student deleteStudent(Long id) {
        final Student currentStudent = findStudent(id);
        em.remove(currentStudent);
        return currentStudent;
    }

    @Transactional
    public void deleteAllStudents() {
        em.createQuery("delete from Student").executeUpdate();
    }
}
