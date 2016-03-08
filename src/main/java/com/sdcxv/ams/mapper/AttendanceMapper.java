package com.sdcxv.ams.mapper;

import com.sdcxv.ams.model.Attendance;
import org.apache.ibatis.annotations.*;

public interface AttendanceMapper {

    @Delete("delete from attendance where attendance_id=#{attendanceId}")
    int deleteByAttendanceId(Integer attendanceId);

    @Insert("insert into attendance(attendance_id, student_id, teacher_id, " +
            "course_id, attendance_time, status, create_time) " +
            "values (#{attendanceId,jdbcType=INTEGER}, #{studentId,jdbcType=INTEGER}, " +
            "#{teacherId,jdbcType=INTEGER}, #{courseId,jdbcType=INTEGER}, " +
            "#{attendanceTime,jdbcType=TIMESTAMP}, #{status,jdbcType=BIT}, " +
            "#{createTime,jdbcType=TIMESTAMP})")
    int insert(Attendance record);

    int insertSelective(Attendance record);

    @Select("select  attendance_id, student_id, teacher_id, course_id, attendance_time, status, create_time " +
            "from attendance" +
            "where attendance_id=#{attendanceId}")
    Attendance selectByAttendanceId(Integer attendanceId);

    @Update("update attendance " +
            "set student_id = #{studentId,jdbcType=INTEGER}," +
            "teacher_id = #{teacherId,jdbcType=INTEGER}," +
            "course_id = #{courseId,jdbcType=INTEGER}," +
            "attendance_time = #{attendanceTime,jdbcType=TIMESTAMP}," +
            "status = #{status,jdbcType=BIT}," +
            "create_time = #{createTime,jdbcType=TIMESTAMP}" +
            "where attendance_id=#{attendanceId}")
    int updateByAttendanceId(Attendance record);

    int updateByAttendanceIdSelective(Attendance record);


}