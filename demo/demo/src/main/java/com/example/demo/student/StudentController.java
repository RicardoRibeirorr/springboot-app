package com.example.demo.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    private  final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PostMapping("")
    public Student registerNewStudent(@RequestBody Student student){
        return studentService.addNewStudent(student);
    }

    @PutMapping("{studentId}")
    public Student updateStudent(@PathVariable("studentId") Long id,@RequestBody Student student){
        return studentService.updateStudent(id,student.getName(),student.getEmail());
    }

    //@RequestParam(required = false) String name,
    //@RequestParam(required = false) String email
    @DeleteMapping("{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }
}
