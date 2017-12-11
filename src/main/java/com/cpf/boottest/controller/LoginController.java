package com.cpf.boottest.controller;

import com.cpf.boottest.config.shiroconfig.ShrioRedisCache;
import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.service.RedisService;
import com.cpf.boottest.service.UserReService;
import com.cpf.boottest.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpf on 2017/9/12.
 */
/*@RestController*/

@Controller
@RequestMapping(value ="/login" )
public class LoginController {

    @Autowired
    private UserReService userReService;
    @Autowired
    private RedisService redisService;
    @Resource(name="sessionManager")
    private DefaultWebSessionManager sessionManager;
    @RequestMapping("/login")
    public ModelAndView tologin(HttpServletRequest request) {
        ModelAndView ma = new ModelAndView();
        ma.setViewName("/loginht");
        return ma;
    }

    @RequestMapping("/logintomenu")
    public ModelAndView login(HttpServletRequest request) {
        ModelAndView ma = new ModelAndView();
        //当前Subject
        Subject currentUser = SecurityUtils.getSubject();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isNotBlank(username)) {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                currentUser.login(token);
                // 权限路径  放入request 和 redis 缓存中
                List<ModuleEntity> permissionlist = userReService.findPermissionlist(username);
                 //放入session中
                Session session = currentUser.getSession();
                session.setAttribute(Const.SESSION_MODULE,permissionlist);
                /*List<ModuleEntity> perlists= (List<ModuleEntity>) session.getAttribute(Const.SESSION_MODULE);*/
                ma.addObject("perurl", permissionlist);
               /* redisService.put("perurl", permissionlist);
                List<ModuleEntity> perlist = (List<ModuleEntity>) redisService.get(List.class, "perurl");*/
                ma.setViewName("/manager/index/index");
                return ma;

            } catch (AuthenticationException e) {//登录失败
                request.setAttribute("msg", "用户名和密码错误");
                System.out.println("cuowu+" + e);
            }
            ma.setViewName("/loginht");
            return ma;

        } else {
            if (currentUser.getPrincipal() != null) {
                ma.setViewName("/manager/index/index");
            /* ma=new ModelAndView("redirect:/manager/index/index.html");*/
                return ma;
            }else{
                ma.setViewName("/loginht");
                return ma;
            }
        }

    }

    /**
     * 退出
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        ModelAndView ma = new ModelAndView();
        ma.setViewName("/loginht");
        return ma;
    }

    @RequestMapping(value = "/tab", method = {RequestMethod.POST,
                RequestMethod.GET})
        public ModelAndView tab(HttpServletRequest request)
                throws Exception {
            ModelAndView mv = new ModelAndView();

            mv.setViewName("/manager/index/tab");
            return mv;
        }

    @RequestMapping(value = "/manager/test", method = RequestMethod.GET)
    public ModelAndView test() {
        System.out.println("test----------");
        ModelAndView ma = new ModelAndView();
        ma.setViewName("/loginht");
        return ma;
    }
}
