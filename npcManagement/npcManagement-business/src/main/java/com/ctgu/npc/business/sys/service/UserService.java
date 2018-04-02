package com.ctgu.npc.business.sys.service;

import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.sys.dto.OfficeInfo;
import com.ctgu.npc.business.sys.entity.Office;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import com.ctgu.npc.business.sys.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private NpcMapper npcMapper;
	
	/**
	 * === 根据登录名查找用户
	 * @param loginName
	 * @return
	 */
	public Users getUser(String loginName) {
		// TODO Auto-generated method stub
		Users theUser = null;
		if (StringUtils.isMobile(loginName)){
			Users auser = new Users();
			theUser  = userMapper.getUserByMobile(auser);
		}else{
			Users auser = new Users(loginName);
			theUser = userMapper.getUserByLoginName(auser);
		}
		
		return theUser;
	}

	
	/**
	 * 根据用户名密码登录
	 * 
	 * @param username
	 * @param pswd
	 * @return
	 */
	public Users getUserByParams(String username, String pswd) {
		// TODO Auto-generated method stub

		//System.out.println("->" + username + ",->" + pswd);

		Users auser = new Users(username);
		Users usr = userMapper.getUserByLoginName(auser);
		if (usr != null) {

			String userId = usr.getId();
			
			//获取角色Id
			//String roleId = userDao.getRoleId(userId);	usr.setRoleId(roleId);
			List<String> roleList = userMapper.getRoleId(userId);
			if (roleList != null) {
				if (roleList.size() > 1) {
					usr.setRoleId(StringUtils.join(roleList, ","));
					//System.out.println("roleId->" + StringUtils.join(roleList, ","));
				} else {
					usr.setRoleId(roleList.get(0));
					//System.out.println("roleId->" + roleList.get(0));
				}
			}
		
			//获取角色名称
			/*String roleName = userDao.getRoleName(userId);
			usr.setRoleName(roleName);*/
			List<String> roleName = userMapper.getRoleName(userId);
			if (roleName != null) {
				if (roleName.size() > 1) {
					usr.setRoleName(StringUtils.join(roleName, ","));
					//System.out.println("roleName->" + StringUtils.join(roleName, ","));
				} else {
					usr.setRoleName(roleName.get(0));
					//System.out.println("roleName->" + roleName.get(0));
				}
			}

		
			// 获取系统级别编码
			List<String> list = userMapper.getLevelList(userId);
			if (list != null) {
				if (list.size() > 1) {
					usr.setLevel(StringUtils.join(list, ","));
					System.out.println("levels->" + StringUtils.join(list, ","));
				} else {
					usr.setLevel(list.get(0));
					System.out.println("level0->" + list.get(0));
				}
			}
			
			// 验证密码
			String pswdNew = usr.getPassword();
			if (SysService.validatePassword(pswd, pswdNew)) {
				return usr;
			}
			else{
				System.out.println("password error!");
				return null;
			}
		} else {
			System.out.println("user->null");
			return null;

		}
		
	}
	
	
	/**
	 * 根据电话号码和密码进行登录
	 * @param mobile
	 * @param pswd
	 * @return
	 */
	public Users getUserByMobile(String mobile, String pswd) {
		// TODO Auto-generated method stub
		Users auser = new Users();
		auser.setMobile(mobile);
		
		Users usr = userMapper.getUserByMobile(auser);
		if (usr != null) {

			String userId = usr.getId();
			
			//获取角色Id
			//String roleId = userDao.getRoleId(userId);	usr.setRoleId(roleId);
			List<String> roleList = userMapper.getRoleId(userId);
			if (roleList != null) {
				if (roleList.size() > 1) {
					usr.setRoleId(StringUtils.join(roleList, ","));
				} else {
					usr.setRoleId(roleList.get(0));
				}
			}
		
			//获取角色名称
			/*String roleName = userDao.getRoleName(userId);
			usr.setRoleName(roleName);*/
			List<String> roleName = userMapper.getRoleName(userId);
			if (roleName != null) {
				if (roleName.size() > 1) {
					usr.setRoleName(StringUtils.join(roleName, ","));
					
				} else {
					usr.setRoleName(roleName.get(0));
				}
			}

		
			// 获取系统级别编码
			List<String> list = userMapper.getLevelList(userId);
			if (list != null) {
				if (list.size() > 1) {
					usr.setLevel(StringUtils.join(list, ","));
					System.out.println("levels->" + StringUtils.join(list, ","));
				} else {
					usr.setLevel(list.get(0));
					System.out.println("level0->" + list.get(0));
				}
			}
			// 验证密码
			String pswdNew = usr.getPassword();
			if (SysService.validatePassword(pswd, pswdNew)) {
				return usr;
			}else{
				//System.out.println("password error!");
			}
		} else {
			//System.out.println("user->null");

		}
		return null;
	}


	/**
	 * 
	 * Description:根据userId获取该user的所有系统级别 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-1-17 上午9:14:35
	 * @param userId
	 * @return
	 */
	public String getLevelsByUserId(String userId) {
		// TODO Auto-generated method stub
		
		List<String> list = userMapper.getLevelList(userId);
		String levels = null;
		if (list != null) {
			if (list.size() > 1) {
				levels = StringUtils.join(list, ",");
				System.out.println("levels->" + StringUtils.join(list, ","));
			} else {
				levels = list.get(0);
				System.out.println("level0->" + list.get(0));
			}
		}
		return levels;
	}


	/**
	 * 根据系统级别编码查询系统级别名称
	 * @param code
	 * @return
	 */
	public String getOfficeNameByCode(String code) {
		// TODO Auto-generated method stub
		return userMapper.getOfficeNameByCode(code);
	}


	//=====代表团总数==== 根据office_id office_type查询记录数 ======================================
	private int getRowsByIdTypeOffice(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return userMapper.getRowsByIdTypeOffice(map);
	}//------- 查询记录数END ------------------------------------------------------------

	
	/**===代表团分页列表
	 * 根据系统级别编码查询部门信息(如代表团)包含分页信息
	 * @param level_code
	 * @param curPage 
	 * @return
	 */
	public PagesUtil<OfficeInfo> getOfficeNameByLevelCodePages(String level_code,
			String office_type, int curPage) {
		// TODO Auto-generated method stub
		
		String office_id = userMapper.getOfficeIdByCode(level_code);
		Map<String, Object> mapso = new HashMap<String, Object>();
		mapso.put("office_id", office_id);
		mapso.put("office_type", office_type);
		
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByIdTypeOffice(mapso);

		//System.out.println("myHeadSugList->row = " + rowCount);
		
		PagesUtil<OfficeInfo> pagesUtil = new PagesUtil<OfficeInfo>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("office_id", office_id);
		map.put("office_type", office_type);
		
		List<OfficeInfo> lists = userMapper.getOfficeNameByLevelCode(map);
		pagesUtil.setLists(lists);
		return pagesUtil;
	}

	/**
	 * 根据系统级别编码查询部门信息(如代表团)
	 * @param level_code
	 * @param curPage 
	 * @return
	 */
	public List<OfficeInfo> getOfficeNameByLevelCode(String level_code,String office_type, int curPage) {
		// TODO Auto-generated method stub
		
		String office_id = userMapper.getOfficeIdByCode(level_code);
		Map<String, Object> mapso = new HashMap<String, Object>();
		mapso.put("office_id", office_id);
		mapso.put("office_type", office_type);
		
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByIdTypeOffice(mapso);

		//System.out.println("myHeadSugList->row = " + rowCount);
		
		PagesUtil pagesUtil = new PagesUtil();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("office_id", office_id);
		map.put("office_type", office_type);
		
	
		
		return userMapper.getOfficeNameByLevelCode(map);
	}

	


	public int getRowCount() {
		// TODO Auto-generated method stub

		return userMapper.getRowCount();
	}

	public PagesUtil getPagesByNum(PagesUtil pagesUtil) {
		// limit offset , size
		int curPage = pagesUtil.getCurPage();
		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);

		List<Users> userList = userMapper.getListByMap(map);

		pagesUtil.setLists(userList);

		return pagesUtil;
	}






}
