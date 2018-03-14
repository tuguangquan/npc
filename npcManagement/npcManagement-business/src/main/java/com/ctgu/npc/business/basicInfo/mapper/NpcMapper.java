package com.ctgu.npc.business.basicInfo.mapper;

import com.ctgu.npc.business.basicInfo.entity.Npc;
import com.ctgu.npc.business.basicInfo.entity.Period;
import com.ctgu.npc.fundamental.orm.mapper.AbstractMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface NpcMapper extends AbstractMapper<Npc> {

	/**
	 * 修改我的个人资料
	 * @param
	 */
	public int updatePersonalInfo(Npc npc);
	
	//根据userId及系统级别查询代表信息
	public	List<Npc> getInfoListUseridLevel(@Param("id") Integer id, @Param("specialLevel") String level);
	/**
	 * 根据users的id和系统级别查询该users的NpcMember信息
	 * @param usrId
	 * @return
	 */
	public	List<Npc> getBasicInfo(@Param("usrId") String usrId);

	/**
	 * 根据系统级别与代表团id 查询代表列表
	 * @param map
	 * @return
	 */
	public	List<Npc> getNpcMembsByMissionId(Map<String, Object> map);

	/**
	 * 根据系统级别与代表id查询代表详细信息
	 * @param map
	 * @return
	 */
	public	List<Npc> getNpcMembDetailById(Map<String, Object> map);

	//根据代表团的id分页查询该代表团的代表信息列表
	List<Npc> getNpcMembsByMissionIdPage(Map<String, Object> map);

	//根据代表团id获取代表的总记录数
	public	int getRowsByOfficeId(Map<String, Object> map);

	//根据系统级别查询届次
	public	Period findPeriod(@Param("level") String level);

	//根据userId及系统级别查询代表信息
	public	Npc getInfo(@Param("id") Integer id, @Param("specialLevel") String level);


	/**
	 * 
	 * Description:根据用户id查询该用户的人大代表信息 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-1-5 上午9:48:24
	 * @param usrId
	 * @return
	 */
	public	Npc getInfoNPCByUserId(@Param("id") String usrId);

	public	int findListRows(Npc npc);

	public	List<Npc> findList(Npc npc);

	public int test();
	
	

}
