package com.gym.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.gym.model.UserModel;
import com.gym.service.UserShiroService;

public class UserRealm extends AuthorizingRealm{
	@Autowired
	UserShiroService userShiroService;

	//执行授权逻辑
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");
		
		//给资源进行授权
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		
		//到数据库中去查询当前登录用户授权字符串
		//获取当前登录用户
		Subject subject=SecurityUtils.getSubject();
		UserModel userModel=(UserModel)subject.getPrincipal();
		
		UserModel user=userShiroService.getUserModelByName(userModel.getName());
		
		//获取权限字符串
		info.addStringPermission(user.getPerms());
				
		return info;
	}

	//执行认证逻辑
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("执行认证逻辑");
		
		UsernamePasswordToken token_1=(UsernamePasswordToken)token;
		
		UserModel userModel=userShiroService.getUserModelByName(token_1.getUsername());
		
		//编写shiro判断逻辑，判断用户名和密码
		//1. 判断用户名
		if(userModel==null) {
			System.out.println(token_1.getUsername());
			//用户名不存在
			return null;	//shiro底层会抛出“用户名不正确”
		}
		//2. 判断密码
		return new SimpleAuthenticationInfo(userModel,userModel.getPassword(),"");
	}
}
