package com.cpf.boottest.controller;


import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cpf on 2017/8/13.
 */

/*@Controller*/
@RestController
public class HelloWorldController {



    @Autowired
    private UserService userService;


    @RequestMapping("/hello")
    public String hello() {
        System.out.println("ssssss" +
                "ssssssssssss");
        return "hello,Spring boot!";
    }

    //访问jsp
   /* @RequestMapping("/{type}/word")
    public ModelAndView  word(@PathVariable String type) {
        ModelAndView  model = new ModelAndView();
       *//* if("jsp".equals(type)){*//*
//加入类型路径，从而可以通过viewNames来判断选择视图对应的解析器
            model.setViewName("helloA");
    *//*    }*//*
        System.out.println("jspjsptestaa");

        return model;
    }*/
    //访问freemarker
    @RequestMapping(value="/gettest/{uid}" ,method = RequestMethod.GET)
    public ModelAndView getuser(@PathVariable int uid, HttpServletRequest request) {
       /* UserEntity user =userService.getById(uid);
       *//* System.out.println(user.getRolelist().size());*//*
        System.out.println(user);
       System.out.println(user.getUid()+"--"+user.getUsername()+"--"+user.getUid()+"--"+user.getPassword());
        request.setAttribute("user",user);
       ModelAndView an = new ModelAndView();

        an.setViewName("/hw");
                an.addObject("user", user);
*/

        return null;
    }
    // 访问thymeleaf
    @RequestMapping(value="/userthy/{uid}" ,method = RequestMethod.GET ,produces="text/plain;charset=UTF-8")
    public ModelAndView getuserthy(@PathVariable int uid, HttpServletRequest request) {
        ModelAndView  an = new ModelAndView();
        an.setViewName("/them");
        System.out.println(uid);
        System.out.println("访问thymeleaf-----");

        an.addObject("name","thymeleaf传值");
        return an;
    }
    // 访问thymeleaf
    @RequestMapping(value="test" ,method = RequestMethod.GET ,produces="text/plain;charset=UTF-8")
    public ModelAndView test() {
        ModelAndView  an = new ModelAndView();
        an.setViewName("/manager/index/index");



        return an;
    }
    @RequestMapping(value="defalult" ,method = RequestMethod.GET ,produces="text/plain;charset=UTF-8")
    public ModelAndView defalult() {
        ModelAndView  an = new ModelAndView();
        an.setViewName("/manager/index/main");



        return an;
    }
    @RequestMapping(value="tab" ,method = RequestMethod.GET ,produces="text/plain;charset=UTF-8")
    public ModelAndView totab() {
        ModelAndView  an = new ModelAndView();
        an.setViewName("/manager/index/tab");



        return an;
    }
}
