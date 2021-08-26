package controllers;


import models.Classes;
import models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import services.IClassesService;
import services.IStudentService;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

@Controller
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @Autowired
    private IClassesService classesService;

    @ModelAttribute("classes")
    public Iterable<Classes> classes() {
        return classesService.findAll();
    }
    @GetMapping("/home")
    public ModelAndView home(@RequestParam(defaultValue = "0") int pageNumber) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("students", studentService.findAll(PageRequest.of(pageNumber,3,Sort.by("name"))));
        return modelAndView;
    }

    @GetMapping("/findByName")
    public ModelAndView findByName(@RequestParam String findName) {
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("students", studentService.findAllByName(findName));
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreate() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("student", new Student());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView create(@RequestParam MultipartFile uppImg, @Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("create");
        }
        String nameImg = uppImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppImg.getBytes(), new File("D:\\JavaProject\\Spring_BT_QLSV\\src\\main\\webapp\\img/" + nameImg));
            String urlImg = "/i/img/" + nameImg;
            student.setImg(urlImg);
        } catch (IOException e) {
            System.err.println("chưa uppload file");
        }

            studentService.save(student);
            ModelAndView modelAndView = new ModelAndView("redirect:/home");
            return modelAndView;

    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEdit(@PathVariable int id) {
        Student student = studentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public ModelAndView edit(@RequestParam MultipartFile uppImg, @Valid @ModelAttribute Student student, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return new ModelAndView("edit");
        }
        String nameImg = uppImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(uppImg.getBytes(), new File("D:\\JavaProject\\Spring_BT_QLSV\\src\\main\\webapp\\img/" + nameImg));
            String urlImg = "/i/img/" + nameImg;
            student.setImg(urlImg);
        } catch (IOException e) {
            System.err.println("chưa uppload file");
        }

            studentService.save(student);
            ModelAndView modelAndView = new ModelAndView("redirect:/home");
            return modelAndView;

    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable Integer id){
        studentService.delete(studentService.findById(id).get());
        return new ModelAndView("redirect:/home");
    }


    @GetMapping("/view/{id}")
    public ModelAndView view(@PathVariable int id){
        Student student = studentService.findById(id).get();
        ModelAndView modelAndView = new ModelAndView("detail");
        modelAndView.addObject("student",student);
        return modelAndView;
    }
}
