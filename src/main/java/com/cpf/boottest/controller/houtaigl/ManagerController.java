package com.cpf.boottest.controller.houtaigl;

import com.cpf.boottest.controller.BaseController;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.RoleService;
import com.cpf.boottest.service.UserReService;
import org.apache.catalina.connector.Response;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpf on 2017/11/19.
 */
@Controller
@RequestMapping(value = "manager")
public class ManagerController extends BaseController {
    @Autowired
    private UserReService userReService;
    @Autowired
    private RoleService roleService;

    //user
    @RequestMapping(value = "/user/list", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView userlist(HttpServletRequest request, UserEntity user)
            throws Exception {
        super.pageutil(request);

        ModelAndView mv = new ModelAndView();
        Page<UserEntity> userCriteria = userReService.findUserCriteria(page, size, user);
        mv.addObject("userlist", userCriteria);
        mv.setViewName("/manager/user/list");
        return mv;
    }

    @RequestMapping(value = "/role/list", method = {RequestMethod.POST,
            RequestMethod.GET})

    public ModelAndView rolelist(HttpServletRequest request)
            throws Exception {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("/manager/role/list");
        return mv;
    }

    @RequestMapping(value = "/module/list", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView funlist(HttpServletRequest request)
            throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.addObject("zsg", "true");
        mv.setViewName("/manager/module/list");
        return mv;
    }

    @RequestMapping(value = "/sbbj/one", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView a(HttpServletRequest request)
            throws Exception {
        ModelAndView mv = new ModelAndView();

        mv.setViewName("sbbj/sbbj");
        return mv;
    }


}
