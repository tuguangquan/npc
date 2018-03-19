package com.ctgu.npc.business.contact.mapper;

import com.ctgu.npc.business.contact.entity.LeaveWord;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


public interface ContactMapper {

	/**
	 *  ===我的留言分页列表
	 * @param map
	 * @return
	 */
	List<LeaveWord> getListmyLeaveWord(Map<String, Object> map);

	/**
	 *  ===我的留言分页列表记录数
	 * @param map
	 * @return
	 */
	int getRowsMyLeaveWord(Map<String, Object> map);

	/**
	 * 留言详细
	 * @param id
	 * @return
	 */
	LeaveWord getInfoLeaveWord(@Param("id") String id);

}
