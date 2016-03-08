package com.sdcxv.ams.mapper;

import com.sdcxv.ams.model.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface StudentMapper {
    @Delete("delete from student where student_id = #{studentId,jdbcType=INTEGER}")
    int deleteByStudentId(Integer studentId);

    @Insert("insert into student (student_id, name, update_time) " +
            "values (#{studentId,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, now())")
    int insert(Student student);

    @Select("select student_id, name, update_time from student where student_id = #{studentId,jdbcType=INTEGER}")
    Student selectByStudentId(Integer studentId);

    @Update("update student " +
            "set name = #{name,jdbcType=VARCHAR}, " +
            "update_time = now() " +
            "where student_id = #{studentId,jdbcType=INTEGER}")
    int updateByStudentId(Student student);
}