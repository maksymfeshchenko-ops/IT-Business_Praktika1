package com.example.demo.controller;

import com.example.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/students")
public class MainController {

    private List<Student> students = new ArrayList<>();
    private int studentId = 1;

    // список студентів
    @GetMapping
    public String showStudents(Model model) {
        model.addAttribute("students", students);
        return "students";
    }

    // форма додавання
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // додавання
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student) {
        student.setId(studentId++);
        students.add(student);
        return "redirect:/students";
    }

    // форма редагування
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {

        for (Student student : students) {
            if (student.getId() == id) {
                model.addAttribute("student", student);
                break;
            }
        }

        return "edit-student";
    }

    // збереження редагування
    @PostMapping("/update")
    public String updateStudent(@ModelAttribute Student student) {

        for (Student s : students) {
            if (s.getId() == student.getId()) {
                s.setName(student.getName());
                s.setAge(student.getAge());
                s.setEmail(student.getEmail());
                break;
            }
        }

        return "redirect:/students";
    }

    // видалення
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {

        students.removeIf(student -> student.getId() == id);

        return "redirect:/students";
    }
}
