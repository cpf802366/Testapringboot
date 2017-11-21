package com.cpf.boottest.controller.system;

import com.cpf.boottest.controller.BaseController;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.BaseService;
import com.cpf.boottest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by cpf on 2017/9/24.
 */
@Controller
@RequestMapping("/manager/user")
public class UserController  extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private BaseService baseService;
    @RequestMapping("/userlist")
    public List<UserEntity>  getUserEntitylist(){
        baseService.getlistBy("UserEntity","user",null);
        ModelAndView ma =this.getModelAndView();
        return  null;
    }

}
