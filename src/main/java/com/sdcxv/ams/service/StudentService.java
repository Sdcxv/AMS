package com.sdcxv.ams.service;

import com.sdcxv.ams.model.Student;

/**
 * Created by Sdcxv on 2016/3/7.
 */
public interface StudentService {
    void addStudent(Student student);

    void deleteStudent(Integer studentId);

    void setStudent(Student studentId);

    void getStudent(Integer studentId);
}
