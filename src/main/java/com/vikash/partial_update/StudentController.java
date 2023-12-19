package com.vikash.partial_update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class StudentController
{
    @Autowired
    StudentService serv;

    @PostMapping()
    public ResponseEntity<Student> save(@RequestBody Student student)
    {
        Student s= serv.saveDetails(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Student>> fetch()
    {
        return ResponseEntity.ok(serv.fetchDetails());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateAll(@PathVariable int id,@RequestBody Student s)
    {
        Student student=serv.updateAllFields(id,s);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Student> updateFields(@PathVariable int id,@RequestBody Map<String, Object> fields)
    {
        Student s=serv.updateField(id,fields);
        return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
    }
}
