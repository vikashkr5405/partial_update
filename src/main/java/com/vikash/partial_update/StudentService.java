package com.vikash.partial_update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

@Service
public class StudentService {
    @Autowired
    StudentRepository srepo;

    public Student saveDetails(Student student) {
        return srepo.save(student);
    }

    public List<Student> fetchDetails() {
        return srepo.findAll();
    }

    public Student updateAllFields(int id, Student student) {
        Student s = srepo.findById(id).get();
        s.setName(student.getName());
        s.setAge(student.getAge());
        s.setAddress(student.getAddress());
        s.setMobile(student.getMobile());
        return srepo.save(s);
    }

    public Student updateField(int id, Map<String, Object> fields) {
        Optional<Student> existingDetail = srepo.findById(id);

        if (existingDetail.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Student.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingDetail.get(), value);
            });
            return srepo.save(existingDetail.get());
        } else {
            return null;
        }
    }
}
