package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private  final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Student addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw  new IllegalArgumentException("Email taken");
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean exists = studentRepository.existsById(id);
        if(!exists) throw new IllegalArgumentException("Student with id " + id + " does not exists");
        studentRepository.deleteById(id);
    }

    @Transactional
    public Student updateStudent(Long id, String name, String email) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Student with id " + id + " not found"));
        if(name != null && name != "" && student.getName() != name) student.setName(name);
        if(email != null && email != "" && student.getEmail() != email){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw  new IllegalArgumentException("Email taken");
            }
            student.setEmail(email);
        }
        return student;
    }
}
