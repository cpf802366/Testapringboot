package com.cpf.boottest.config.shiroconfig;


import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.UserReService;
import com.cpf.boottest.service.UserService;
import com.cpf.boottest.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class URLPermissionsFilter extends PermissionsAuthorizationFilter {

	@Autowired
	private UserReService userReService;

	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
		String curUrl = getRequestUrl(request);
		Subject subject = SecurityUtils.getSubject();
		if(subject.getPrincipal() == null 
				|| StringUtils.endsWithAny(curUrl, ".js",".css",".html")
				|| StringUtils.endsWithAny(curUrl, ".jpg",".png",".gif", ".jpeg")
				|| StringUtils.equals(curUrl, "/unauthor")) {
			return true;
		}

		Session session = subject.getSession();
		List<ModuleEntity> permissionlist = (List<ModuleEntity>) session.getAttribute(Const.SESSION_MODULE);
		String username = (String) subject.getPrincipals().getPrimaryPrincipal();
		if(permissionlist == null){
            permissionlist = userReService.findPermissionlist(username);
        }
           String servername =request.getServletContext().getContextPath();
		Set<String> perurls = new HashSet<String>();
		for (ModuleEntity module:permissionlist) {
			perurls.add( servername+"/"+module.getMurl());
		}
   for(String str: perurls){
	   System.out.println("url==="+str);
   }
            System.out.println("curUrl=="+curUrl);
		System.out.println(perurls.contains(curUrl));
		 return perurls.contains(curUrl);
//		return true;
	}
	
	/**
	 * 获取当前URL+Parameter
	 * @author lance
	 * @since  2014年12月18日 下午3:09:26
	 * @param request	拦截请求request
	 * @return			返回完整URL
	 */
	private String getRequestUrl(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest)request;
		String queryString = req.getQueryString();

		queryString = StringUtils.isBlank(queryString)?"": "?"+queryString;
		return req.getRequestURI()+queryString;
	}
}
