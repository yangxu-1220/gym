package com.gym.config;

import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gym.config.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiRoConfig {
	//创建ShiroFilterFactoryBean
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		
		//设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//添加ShiRo内置过滤器
		//ShiRo内置过滤器，可以实现权限相关的拦截器
		//常用的过滤器：
		//	anon：无需认证（登录）可以访问	
		//	authc：必须认证才可以访问
		//	user: 如果使用rememberMe的功能可以直接访问
		//	perms：该资源必须得到资源权限才可以访问
		//	role：该资源必须得到角色权限才可以访问
		
		Map<String,String> filterMap=new LinkedHashMap<String,String>();
		
		//授权过滤器
		//注意：当前授权拦截后，shiro会自动跳转到未授权页面
		
		//设置拦截放行
		filterMap.put("/background/**","anon");			//放行静态资源
		filterMap.put("/layui/**","anon");				//放行静态资源
		filterMap.put("/MemberImage/**","anon");	    //放行静态资源
		filterMap.put("/styles/**","anon");				//放行静态资源
		filterMap.put("/index/**","anon");				//放行静态资源
		filterMap.put("/image/**","anon");				//放行静态资源
		filterMap.put("/js/**","anon");					//放行静态资源
		filterMap.put("/images/**","anon");				//放行静态资源
		
		//通用地址
		filterMap.put("/defaultKaptcha","anon");
		filterMap.put("/imgvrifyControllerDefaultKaptcha","anon");
		filterMap.put("/login","anon");
		filterMap.put("/login_check","anon");
		filterMap.put("/index","anon");
		filterMap.put("/unAuth","anon");
		filterMap.put("/online_count","anon");
		
		//登录成功放行地址
		filterMap.put("/goods/getGoodsBtween","authc");
		filterMap.put("/goodsType/getTypeList","authc");
		filterMap.put("/toIndex","authc");
		filterMap.put("/pay","authc");
		filterMap.put("/pay_do","authc");
		filterMap.put("/pay_over","authc");
		filterMap.put("/pay_private_course","authc");
		filterMap.put("/pay_over_private_course","authc");
		
		//会员放行地址
		filterMap.put("/shop/**","perms[user:member]");
		filterMap.put("/pay_private_course","perms[user:member]");
		filterMap.put("/pay_over_private_course","perms[user:member]");
		filterMap.put("/user","perms[user:member]");
		filterMap.put("/user/**","perms[user:member]");
		filterMap.put("/cart/**","perms[user:member]");
		filterMap.put("/order/**","perms[user:member]");
		filterMap.put("/orderCourse/**","perms[user:member]");
		filterMap.put("/orderDetail/**","perms[user:member]");
		filterMap.put("/myOrder/**","perms[user:member]");
		filterMap.put("/private_course_management","perms[user:member]");
		filterMap.put("/private_course_management/user_type","perms[user:member]");
		filterMap.put("/private_course_management/member/**","perms[user:member]");
		filterMap.put("/consume/attend_course/get_trainer_name","perms[user:member]");
		
		//教练放行地址
//		filterMap.put("/private_course_management","perms[user:member,user:trainer]");
//		filterMap.put("/private_course_management/user_type","perms[user:member,user:trainer]");
//		filterMap.put("/private_course_management/**","perms[user:member,user:trainer]");
		
		//前台放行地址
		filterMap.put("/attendCourse/**","perms[user:member,user:trainer,user:stuff]");
		filterMap.put("/trainer_management","perms[user:member,user:trainer,user:stuff]");
		filterMap.put("/trainer_management/**","perms[user:member,user:trainer,user:stuff]");
		filterMap.put("/member/**","perms[user:member,user:trainer,user:stuff]");
		filterMap.put("/card/GetCardType","perms[user:member,user:trainer,user:stuff]");
		
		//管理员放行地址
		filterMap.put("/change_5","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/attend_course","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/attend_course/get_pie_1","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/attend_course/get_pie_2","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/change_4","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/attend_private_course","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/attend_private_course/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/change_2","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/business_consume","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/business_consume/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/index","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/getCardInfo","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/turnCardAdd","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/CardAdd","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/update","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/doUpdate","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/doDelete","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/card/getCardBtween","perms[user:member,user:trainer,user:stuff,user:management]");
		
		filterMap.put("/course/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/setting/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/index","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/toAdd","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/deleteGoodsById","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/selectById","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/doUpdate","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/doInsert","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/detail","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/setStateToYes","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goods/setStateToNo","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsSale/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/stock/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/toInsert","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/index","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/selectTypeById","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/doInsert","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/update","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/deleteTypeById","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType/getGoodsTypeBtween","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/goodsType","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/change_1","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/member_consume","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/member_consume/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/change_7","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/member_come","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/member_come/get_barline","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/Mstuff/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/Mtrainer/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/change_6","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/order_course","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/order_course/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/change_3","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/private_course_sale","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/consume/private_course_sale/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/searchOrder/**","perms[user:member,user:trainer,user:stuff,user:management]");
		filterMap.put("/operate/**","perms[user:member,user:trainer,user:stuff,user:management]");
		
//		//测试权限管理
//		filterMap.put("/change_1","perms[user:member]");
//		filterMap.put("/change_2","perms[user:member,user:trainer]");
//		filterMap.put("/change_3","perms[user:member,user:trainer,user:stuff]");
//		filterMap.put("/change_7","perms[user:member,user:trainer,user:stuff,user:management]");
		
		//设置拦截全部
		filterMap.put("/**","authc");
		
		//修改拦截后默认跳转页面
		shiroFilterFactoryBean.setLoginUrl("/login");
		
		//设置未授权提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/unAuth");
		
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
		
		return shiroFilterFactoryBean;
	}
	
	//创建DefaultWebSecurityManager
	@Bean(name="securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm) {
		DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
		//关联realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}
	
	
	//创建Realm
	@Bean(name="userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}
	
	//配置ShiroDialect，用于thymeleaf和Shiro标签配合使用
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}
}
