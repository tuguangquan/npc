package com.ctgu.npc.business.inqury_meeting.mapper;


import com.ctgu.npc.business.inqury_meeting.entity.Inqury;
import com.ctgu.npc.business.inqury_meeting.entity.Meet;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface InquryMapper {

	/**
	 * 根据user_id查询询问列表总记录数-我的
	 * @param user_id
	 * @param level_code 
	 * @return
	 */
	int getRowsByLevelUserIdInqury(Map<String, Object> map);

	/*根据user_id查询询问列表-我的*/
	List<Inqury> getListMyInqury(Map<String, Object> map);

	/**
	 * 根据inqury的id查询详细信息
	 * @param inq_id
	 * @return
	 */
	Inqury getDetailByIdInqury(@Param("id") String id);

	
	/**
	 * 询问处理记录数
	 * @param inqury
	 * @return
	 */
	int getRowsByLevelInqury(Inqury inqury);

	/**
	 * 询问处理列表
	 * @param map
	 * @return
	 */
	List<Inqury> getListInquryHandle(Map<String, Object> map);

	/**
	 * === 答复询问记录数
	 * @param inqury
	 * @return
	 */
	int getRowsByLevelUnitInqury(Inqury inqury);

	/**
	 * 答复询问列表
	 * @param map
	 * @return
	 */
	List<Inqury> getListInquryReply(Map<String, Object> map);

	/**
	 * === 我的约见列表
	 * @param map
	 * @return
	 */
	List<Meet> getListMyMeet(Map<String, Object> map);

	/**
	 * === 我的约见记录数
	 * @param theObj
	 * @return
	 */
	int getRowsMyMeet(Meet theObj);

	/**
	 * 约见详细信息
	 * @param id
	 * @return
	 */
	Meet getDetailByIdMeet(@Param("id") String id);

	/**
	 * === 约见处理列表
	 * @param map
	 * @return
	 */
	List<Meet> getListMeetHandle(Map<String, Object> map);

	/**
	 * 约见处理记录数
	 * @param theObj
	 * @return
	 */
	int getRowsMeetHandle(Meet theObj);

	

}
