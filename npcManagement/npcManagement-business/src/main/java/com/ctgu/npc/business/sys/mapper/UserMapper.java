package com.ctgu.npc.business.sys.mapper;

import com.ctgu.npc.business.sys.entity.Office;
import com.ctgu.npc.business.sys.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

	/**
	 * 获取记录总数
	 * @return 记录数
	 */
	int getRowCount();

	/**
	 * 
	 * @param map 传入offset size
	 * @return 分页的List
	 */
	List<Users> getListByMap(Map<String, Object> map);

	/**
	 * 根据用户名查找用户
	 * @param auser:登录时用户
	 * @return 查找到的用户
	 */
	Users getUserByLoginName(Users auser);
	
	/**
	 * 根据用户手机号码查找用户
	 * @param auser
	 * @return
	 */
	Users getUserByMobile(Users auser);

	/**
	 * 根据userId查找该user的所有系统级别
	 * @param userId
	 * @return
	 */
	List<String> getLevelList(@Param("userId") String userId);

	/**
	 * 根据level_code查找name
	 * @param code
	 * @return
	 */
	String getOfficeNameByCode(@Param("code") String code);

	/**
	 * 根据用户id查询角色名称
	 * @param userId
	 * @return
	 */
	List<String> getRoleName(@Param("userId") String userId);

	/**
	 * 根据用户id查询角色id
	 * @param userId
	 * @return
	 */
	List<String> getRoleId(@Param("userId") String userId);
	
	
	/**=== 代表团列表
	 * 根据office的id及type查询部门信息列表(如代表团)
	 * @param
	 * @return
	 */
	List<Office> getOfficeNameByLevelCode(Map<String, Object> map);

	/**
	 * 根据系统级别编码查询Office的id
	 * @param level_code
	 * @return
	 */
	String getOfficeIdByCode(@Param("level_code") String level_code);

	/**
	 * 根据office的id及type查询总的记录数
	 * @param map
	 * @return
	 */
	int getRowsByIdTypeOffice(Map<String, Object> map);

	/**
	 * ===修改我的个人资料
	 * @param user
	 * @return
	 */
	int updateUser(Users user);

	/**
	 * 更新 密码
	 * @param user
	 */
	void updatePasswordById(Users user);

	/**
	 * 发送短信根据单位id查找工作人员
	 * @param permission
	 * @param officeID
	 * @param level
	 * @return
	 */
	List<Users> findUserByPermissionAndOfficeID(@Param("permission") String permission, @Param("officeID") String officeID, @Param("level") String level);

	/**
	 * 根据权限查找相应工作人员
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:45:49
	 * @param permission
	 * @param sysLevel
	 * @return
	 */
	List<Users> findUserByPermission(@Param("permission") String permission, @Param("level") String level);

	

	

}
