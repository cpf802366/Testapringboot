package com.cpf.boottest.config.shiroconfig;


import com.cpf.boottest.pojo.ModuleEntity;
import com.cpf.boottest.pojo.RoleEntity;
import com.cpf.boottest.pojo.UserEntity;
import com.cpf.boottest.service.UserReService;
import com.cpf.boottest.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 验证用户登录
 * 
 * @author Administrator
 */
@Component("userRealm")
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserReService userreService;

	public UserRealm() {
		setName("UserRealm");
		// 采用MD5加密
		/*setCredentialsMatcher(new HashedCredentialsMatcher("md5"));*/
	}

	//权限资源角色
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        List<RoleEntity> rolelist = userreService.findRolelist(username);
        Set<String> rolename = new HashSet<String>();
        for (RoleEntity role:rolelist) {
			rolename.add(role.getRname());
		}
        List<ModuleEntity> permissionlist = userreService.findPermissionlist(username);
        Set<String> perurls = new HashSet<String>();
        for (ModuleEntity module:permissionlist) {
            perurls.add(module.getMurl());
        }


		// 将角色名称提供给info  add Roles String[Set<String> roles]
		info.setRoles(rolename);
		//add Permission Resources
		info.setStringPermissions(perurls);





		return info;
	}
	
	//登录验证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken upt = (UsernamePasswordToken) token;
		String userName = upt.getUsername(); //得到用户名
		String pd= new String((char[])upt.getCredentials()); //得到密码
		UserEntity user =userreService.findByUsernameAndPassword(userName,pd);
		System.out.println("user==="+user.getUsername());
		//处理session
		DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
		DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
		Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
		for(Session session:sessions){
			//清除该用户以前登录时保存的session
			if(userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
				sessionManager.getSessionDAO().delete(session);
			}
		}
		if (user == null ) {
			throw new UnknownAccountException();
		}
		if(!pd.equals(user.getPassword())) {
			throw new IncorrectCredentialsException(); //如果密码错误
		}
      /*第三个参数不了解  */
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userName, pd, getName());
		return info;
	}
}