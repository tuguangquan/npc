package com.ctgu.npc.business.inform.mapper;


import com.ctgu.npc.business.inform.entity.Publish;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PublishMapper {
	
	/**
	 * 根据系统级别获取publishList-人大
	 * @param map
	 * @return
	 */
	List<Publish> getPublishListNpc(Map<String, Object> map);

	/**
	 * 根据系统级别获取publish总记录数-人大
	 * @param level_code
	 * @param pub_col 
	 * @return
	 */
	int getRowsByLevelNpc(Map<String, Object> map);

	/**
	 * 根据publishId查询详细
	 * @param pub_id
	 * @return
	 */
	Publish getPublishDetailNpc(@Param("pub_id") String pub_id);

}
