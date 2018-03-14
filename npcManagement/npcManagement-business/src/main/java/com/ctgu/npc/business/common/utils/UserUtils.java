package com.ctgu.npc.business.common.utils;


import com.ctgu.npc.business.sys.entity.Users;

import java.io.Serializable;

/**
 * 用户工具类
 * @author 旺旺
 *
 */
public class UserUtils {

	public static String getSysLevel() {
		// TODO Auto-generated method stub
		return null;
	}

	public static Users getUser() {
		// TODO Auto-generated method stub
		Principal principal = getPrincipal();
		if (principal!=null){
			Users user = get(principal.getId());
			if (user != null){
				return user;
			}
			return new Users();
		}
		// 如果没有登录，则返回实例化空的User对象。
		return new Users();
	}
	
	
	private static Users get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Principal getPrincipal() {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 授权用户信息
	 */
	public static class Principal implements Serializable {

		private static final long serialVersionUID = 1L;
		
		private String id; // 编号
		private String loginName; // 登录名
		private String name; // 姓名
		private boolean mobileLogin; // 是否手机登录
		
//		private Map<String, Object> cacheMap;

		public Principal(Users user, boolean mobileLogin) {
			this.id = user.getId();
			this.loginName = user.getLoginName();
			this.name = user.getName();
			this.mobileLogin = mobileLogin;
		}

		public String getId() {
			return id;
		}

		public String getLoginName() {
			return loginName;
		}

		public String getName() {
			return name;
		}

		public boolean isMobileLogin() {
			return mobileLogin;
		}

//		@JsonIgnore
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

		/**
		 * 获取SESSIONID
		 */
		public String getSessionid() {
			try{
				return (String) UserUtils.getSession().getId();
			}catch (Exception e) {
				return "";
			}
		}
		
		@Override
		public String toString() {
			return id;
		}

	}


	public static Users getSession() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
