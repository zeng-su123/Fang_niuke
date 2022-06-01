package com.nowcoder.community.controller;


import com.nowcoder.community.service.AlphaService;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.print.DocFlavor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @Autowired
    private AlphaService alphaService;


    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot.";
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);

        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("<h1>牛客网</h1>");
    }

    //GET请求
    //查询所有学生

    //students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStuddents(
            @RequestParam(name = "current",required = false,defaultValue = "1") int current ,
            @RequestParam(name = "limit",required = false,defaultValue = "10")  int limit)
    {
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    @RequestMapping(path = "/student/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){
        System.out.println(id);
        return "a student";
    }

    //Post请求

    @RequestMapping(path = "/student",method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age );
        return "success";
    }


    //响应HTML数据
    @RequestMapping(path = "/teacher",method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("name","张三");
        modelAndView.addObject("age", "30");
        modelAndView.setViewName("/demo/view");
        return modelAndView;

    }

    @RequestMapping(path = "/school",method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","大学");
        model.addAttribute("age","60");
        return "/demo/view";
    }
    //响应Json数据（异步请求）
    //java对象 -> Json ->JS数据
    @RequestMapping(path = "/emp",method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        Map<String, Object> emp = new HashMap<String, Object>();
        emp.put("name","zhangsan");
        emp.put("age",23);
        emp.put("salary",8000);
        ArrayList<Map<String, Object>> maps = new ArrayList<>();
        maps.add(emp);

        emp = new HashMap<String, Object>();
        emp.put("name","Lisi");
        emp.put("age",25);
        emp.put("salary",7000);
        maps.add(emp);

        emp = new HashMap<String, Object>();
        emp.put("name","wangwu");
        emp.put("age",27);
        emp.put("salary",9000);
        maps.add(emp);
        return maps;
    }


}


