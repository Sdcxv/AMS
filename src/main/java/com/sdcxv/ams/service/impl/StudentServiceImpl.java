package com.sdcxv.ams.service.impl;

import com.sdcxv.ams.mapper.StudentMapper;
import com.sdcxv.ams.model.Student;
import com.sdcxv.ams.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sdcxv on 2016/3/7.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void addStudent(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentMapper.deleteByStudentId(studentId);
    }

    @Override
    public void setStudent(Student student) {
        studentMapper.updateByStudentId(student);
    }

    @Override
    public void getStudent(Integer studentId) {
        studentMapper.selectByStudentId(studentId);
    }
}
