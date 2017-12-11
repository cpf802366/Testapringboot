package com.cpf.boottest.config.shiroconfig;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.filter.DelegatingFilterProxy;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
	
	/**
	 * FilterRegistrationBean
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
        filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistration.setEnabled(true);
        filterRegistration.addUrlPatterns("/*"); 
        filterRegistration.setDispatcherTypes(DispatcherType.REQUEST);
        return filterRegistration;
	}
	/**
	 * @see org.apache.shiro.spring.web.ShiroFilterFactoryBean
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter(){
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(securityManager());
		/*登录路径*/
		bean.setLoginUrl("/login/login");
		/*权限不足时跳转路径*/
		bean.setUnauthorizedUrl("/unauthor");
		/*创建拦截器  拦截器名字 +具体实现*/
     Map<String,Filter> filters = new HashMap<String,Filter>();
		filters.put("perms", urlPermissionsFilter());
		/*不做任何处理*/
		filters.put("anon", new AnonymousFilter());
		bean.setFilters(filters);
        /*根据url不同选着相应的拦截器  authc shiro 内置的 用来验证是否登录*/
		Map<String,String> chains = new HashMap<String,String>();
            // 静态资源
		chains.put("/**/assets/**", "anon");
		chains.put("/**/bootstrap/**", "anon");
		chains.put("/*/css/**","anon");
		chains.put("/**/images/**", "anon");
		chains.put("http:/**", "anon");



		chains.put("/**/js/**","anon");
		chains.put("/loginht", "anon");
		//登录
		chains.put("/login/**", "anon");
	/*	chains.put("/logout", "logout");*/
		//后台管理
	 chains.put("/**/manager/**", "authc,perms");
		//前台管理

		bean.setFilterChainDefinitionMap(chains);
		return bean;
	}
	/**
	 * @see org.apache.shiro.mgt.SecurityManager
	 * @return
	 */
	@Bean(name="securityManager")
	public DefaultWebSecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(userRealm());
		manager.setCacheManager(redisCacheManager());
		manager.setSessionManager(defaultWebSessionManager());
		return manager;
	}

	/**
	 * @see DefaultWebSessionManager
	 * @return
	 */
	@Bean(name="sessionManager")
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(redisCacheManager());
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setDeleteInvalidSessions(true);
		sessionManager.setSessionValidationSchedulerEnabled(true);
		sessionManager.setDeleteInvalidSessions(true);
		return sessionManager;
	}
	/**
	 * @see UserRealm--->AuthorizingRealm
	 * @return
	 */
	@Bean
	@DependsOn(value={"lifecycleBeanPostProcessor", "shrioRedisCacheManager"})
	public UserRealm userRealm() {
		UserRealm userRealm = new UserRealm();
		/*缓存管理  redis */
		userRealm.setCacheManager(redisCacheManager());
		/*开启缓存*/
		userRealm.setCachingEnabled(true);
		/*启用身份验证缓存，即缓存AuthenticationInfo信息*/
		userRealm.setAuthenticationCachingEnabled(true);
		/*启用授权缓存，即缓存AuthorizationInfo信息*/
		userRealm.setAuthorizationCachingEnabled(true);
		return userRealm;
	}

	@Bean
	public URLPermissionsFilter urlPermissionsFilter() {
		return new URLPermissionsFilter();
	}

	@Bean(name="shrioRedisCacheManager")
	/*实例化shrioRedisCacheManager 之前实例化redisTemplate*/
	@DependsOn(value="redisTemplate")
	public ShrioRedisCacheManager redisCacheManager() {
		ShrioRedisCacheManager cacheManager = new ShrioRedisCacheManager(redisTemplate());
		return cacheManager;
	}

	@Bean(name="redisTemplate")
	public RedisTemplate<byte[], Object> redisTemplate() {
		RedisTemplate<byte[], Object> template = new RedisTemplate<>();
		template.setConnectionFactory(connectionFactory());
		return template;
	}

	@Bean
	public JedisConnectionFactory connectionFactory(){
		JedisConnectionFactory conn = new JedisConnectionFactory();
		conn.setDatabase(3);
		conn.setHostName("127.0.0.1");
	/*	conn.setPassword("123456");*/
		conn.setPort(6379);
		conn.setTimeout(3000);
		return conn;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

}