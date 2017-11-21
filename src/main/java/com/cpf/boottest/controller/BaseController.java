package com.cpf.boottest.controller;


import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by cpf on 2017/9/24.
 */
@Controller
public class BaseController {
   public  Integer  page   ;
    public  Integer size;
    protected Logger logger = Logger.getLogger(this.getClass());
    /**得到ModelAndView
     * @return
     */
    public ModelAndView getModelAndView(){
        return new ModelAndView();
    }
    /**得到request对象
     * @return
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    public static void logBefore(Logger logger, String interfaceName){
        logger.info("");
        logger.info("start");
        logger.info(interfaceName);
    }

    public static void logAfter(Logger logger){
        logger.info("end");
        logger.info("");
    }
    public void pageutil(org.apache.catalina.servlet4preview.http.HttpServletRequest request){

        if (StringUtils.isNotBlank(request.getParameter("page")) && StringUtils.isNotBlank(request.getParameter("size"))){
            page = Integer.parseInt(request.getParameter("page"));
            size = Integer.parseInt(request.getParameter("size"));
        }else{
            page =  0 ;
            size= 10 ;
        }
    }
    public void responseText(String json, HttpServletResponse response)
            throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
