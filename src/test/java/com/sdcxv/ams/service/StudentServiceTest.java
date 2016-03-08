package com.sdcxv.ams.service;

import com.sdcxv.ams.model.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by Sdcxv on 2016/3/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:springConfig/applicationContext.xml")
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    public void addStudent() {
        Student student = new Student();
        student.setName("隔壁老王");
        student.setStudentId(1024);
        student.setUpdateTime(new Date());
        studentService.addStudent(student);
    }

    @Test
    public void setStudent() {
        Student student = new Student();
        student.setName("大头儿子");
        student.setStudentId(1024);
        student.setUpdateTime(new Date());
        studentService.setStudent(student);
    }

    @Test
    public void getStudent() {
        Integer studentId = 1024;
        studentService.getStudent(studentId);
    }

    @Test
    public void deleteStudent() {
        Integer studentId = 1024;
        studentService.deleteStudent(studentId);
    }
}