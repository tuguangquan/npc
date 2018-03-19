package com.ctgu.npc.business.contact.mapper;

import com.ctgu.npc.business.contact.entity.AnswerSqmy;
import com.ctgu.npc.business.contact.entity.LeaveMessage;
import com.ctgu.npc.business.contact.entity.Sqmy;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 社情民意接口
 * Title:SqmyDao 
 * Description: 
 * Company: ctgu 
 * @author : youngmien
 * @date  2017-7-26 上午8:44:13
 */
public interface SqmyMapper {

	/**
	 * 获取我的社情民意列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-26 上午9:33:26
	 * @param theObj
	 * @return
	 */
	List<Sqmy> mySqmyList(Sqmy theObj);

	/**
	 * 社情民意详细
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-26 上午10:29:57
	 * @param id
	 * @return
	 */
	Sqmy get(@Param("id") String id);

	/**
	 * 获取该社情民意的留言
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-26 上午10:39:40
	 * @param id
	 * @return
	 */
	LeaveMessage getLeaveMessage(@Param("id") String id);
	/**
	 * 获取社情民意的重复列表
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-7-26 上午10:45:25
	 * @param sqmyID
	 * @return
	 */
	List<AnswerSqmy> getAnswerSqmy(@Param("sqmyID") String sqmyID);

}
