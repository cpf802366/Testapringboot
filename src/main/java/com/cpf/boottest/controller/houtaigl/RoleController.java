package com.cpf.boottest.controller.houtaigl;

import com.cpf.boottest.controller.BaseController;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.service.RoleService;
import com.cpf.boottest.util.commen.CreateNewKey;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpf on 2017/11/21.
 */
@Controller
@RequestMapping(value = "/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/treelist", method = {RequestMethod.POST,
            RequestMethod.GET})
    public
    @ResponseBody
    void roletreelist(HttpServletRequest request, HttpServletResponse response, RoleEntity roleEntity) {

        String tree = roleService.getTree(roleEntity);
        try {
            responseText(tree, response);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cuoeullllllll");
        }
    }

    /**
     * 保存基础数据信息
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/saveroel", method = {RequestMethod.POST, RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> saveroel(HttpServletRequest request, HttpServletResponse response, RoleEntity roleEntity) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(roleEntity.getRid())) {
            roleService.update(roleEntity);
        } else {
            roleEntity.setRid(CreateNewKey.createId());
            roleService.insert(roleEntity);
        }
        map.put("mesg", true);
        return map;
    }

    @RequestMapping(value = "/delrole", method = {RequestMethod.POST,
            RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> delrole(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String rid = request.getParameter("rid");
        if (StringUtils.isNotBlank(rid)) {
            roleService.delete(rid);
        }
        return map;
    }

    @RequestMapping(value = "/modulebyrid", method = {RequestMethod.POST,
            RequestMethod.GET})
    public ModelAndView modulebyrid(HttpServletRequest request)
            throws Exception {

        ModelAndView mv = new ModelAndView();
        String rid = request.getParameter("rid");
        RoleEntity byRid = roleService.findByRid(rid);
        if (StringUtils.isNotBlank(byRid.getFunctions())) {
            String[] split = byRid.getFunctions().split("/");
            List<String> strings = Arrays.asList(split);
            mv.addObject("mids", strings);
        }
        mv.addObject("rid", rid);
        mv.setViewName("/manager/module/list");
        return mv;
    }

    @RequestMapping(value = "/addmodule", method = {RequestMethod.POST,
            RequestMethod.GET})
    public
    @ResponseBody
    Map<String, Object> addmodule(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        String mid = request.getParameter("mid");
        String rid = request.getParameter("rid");
        RoleEntity byRid = roleService.findByRid(rid);
        int i = -1;
        if (StringUtils.isNotBlank(byRid.getFunctions())) {
            i = byRid.getFunctions().indexOf(mid);
        }

        if (i == -1) {
            byRid.setFunctions(byRid.getFunctions() + mid + "/");
            roleService.update(byRid);
        }
        return map;
    }

}
