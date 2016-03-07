package com.sdcxv.ams.controller;

import com.sdcxv.ams.model.Course2;
import com.sdcxv.ams.service.CourseService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
        log.info("In viewCourse, courseId={}", courseId);
        Course2 course = courseService.getCoursebyId(courseId);
        model.addAttribute(course);
        return "course_overview";
    }

    //RUSTful
    @RequestMapping(value = "/view2/{courseId}", method = RequestMethod.GET)
    public String viewCourse2(@PathVariable("courseId") Integer courseId, Map<String, Course2> model) {
        log.info("In viewCourse, courseId={}", courseId);
        Course2 course = courseService.getCoursebyId(courseId);
        model.put("course", course);
        return "course_overview";
    }

    //Servlet
    @RequestMapping(value = "/view3", method = RequestMethod.GET)
    public String viewCourse3(HttpServletRequest request) {
        Integer courseId = Integer.valueOf(request.getParameter("courseId"));
        log.info("In viewCourse, courseId={}", courseId);
        Course2 course = courseService.getCoursebyId(courseId);
        request.setAttribute("course", course);
        return "course_overview";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET, params = "add")
    public String createCourse() {
        return "course_admin/edit";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String doSave(Course2 course) {
        log.info("In viewCourse:");
        log.info(ReflectionToStringBuilder.toString(course));
        course.setCourseId(1024);
        return "redirect:view2/" + course.getCourseId();
    }

    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadPage() {
        return "course_admin/file";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            log.info("Processing file:{}", file.getOriginalFilename());
            try {
                FileUtils.copyInputStreamToFile(file.getInputStream(), new File("C:\\temp\\", System.currentTimeMillis() + file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }

    //JSON
    @RequestMapping(value = "/{courseId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Course2 getCourseInJson(@PathVariable("courseId") Integer courseId) {
        log.info("In viewCourse, courseId={}", courseId);
        return courseService.getCoursebyId(courseId);
    }

    //JSON2
    @RequestMapping(value = "/jsontype/{courseId}", method = RequestMethod.GET)
    public ResponseEntity<Course2> getCourseInJson2(@PathVariable("courseId") Integer courseId) {
        log.info("In viewCourse, courseId={}", courseId);
        Course2 course = courseService.getCoursebyId(courseId);
        return new ResponseEntity<Course2>(course, HttpStatus.OK);
    }
}
