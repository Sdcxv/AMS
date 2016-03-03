package com.sdcxv.ams.controller;

import com.sdcxv.ams.model.Course;
import com.sdcxv.ams.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Sdcxv on 2016/3/3.
 */
@Controller
@RequestMapping("/courses")//处理/courses路径
public class CourseController {
    private static Logger log = LoggerFactory.getLogger(CourseController.class);

    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    //处理/courses/view
    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewCourse(@RequestParam("courseId") Integer courseId, Model model) {
        log.warn("In viewCourse, courseId={}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        model.addAttribute(course);
        return "course_overview";
    }

    //RUSTful
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Course> model) {
        log.warn("In viewCourse, courseId={}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        model.put("course", course);
        return "course_overview";
    }

    //Servlet
    @RequestMapping(value = "/view3", method = RequestMethod.GET)
    public String viewCourse3(HttpServletRequest request) {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        log.warn("In viewCourse, courseId={}", courseId);
        Course course = courseService.getCoursebyId(courseId);
        request.setAttribute("course", course);
        return "course_overview";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse() {
        return "course_admin/edit";
    }
}
