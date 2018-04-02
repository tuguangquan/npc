package com.ctgu.npc.business.basicInfo.service;

import com.ctgu.npc.business.basicInfo.dto.NpcInfo;
import com.ctgu.npc.business.basicInfo.entity.Npc;
import com.ctgu.npc.business.basicInfo.entity.PersonInfo;
import com.ctgu.npc.business.basicInfo.mapper.NpcMapper;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NpcService {

	@Autowired
	private NpcMapper npcMapper;


	@Autowired
	private UserMapper userMapper;
	
	/**
	 * === 修改我的个人资料
	 * @param pn
	 * @param loginName
	 * @param level_code
	 */
	@Transactional(readOnly = false)
	public void updatePersonalInfo(PersonInfo pn, String loginName,
			String level_code) {
		// TODO Auto-generated method stub
		
		Users auser = new Users(loginName);
		String usrId = userMapper.getUserByLoginName(auser).getId();
		
		Npc npc = pn.getNpc();
		npc.setUserId(StringUtils.toInteger(usrId));
		npcMapper.updatePersonalInfo(npc);
		
		Users user = pn.getUser();
		user.setId(usrId);
		userMapper.updateUser(user);
		
	}


	/**
	 * 根据loginName和系统级别查询npc的详细信息
	 * @param loginName
	 * @param level_code
	 * @return
	 */
	public Npc getBasicInfo(String loginName, String level_code) {
		Users auser = new Users(loginName);
		auser = userMapper.getUserByLoginName(auser);
		String usrId;
		if (auser!=null){
			 usrId = auser.getId();
		}else{
			return null;
		}

		Npc theObj = null;
		
		List<Npc> list = npcMapper.getBasicInfo(usrId);
		if(list != null){
			if(list.size() > 1){//有多条记录
				for(Npc item:list){
					if( !("终止".equals(item.getStatus())) && level_code.equals(item.getUserLevel())||
						!("暂停".equals(item.getStatus())) && level_code.equals(item.getUserLevel())||
						"0".equals(item.getChangeFlag()) && level_code.equals(item.getUserLevel())	)
					{
						theObj = item;
						break;
					}
				}
			
			}else if(list.size() ==1){
				theObj = list.get(0);
			}
			
		}
		if(theObj == null)
		{
			theObj = new Npc();
			theObj.setNpcData(auser);
		}
		return theObj;
	}


	/**
	 * 
	 * Description:根据登录名查询代表信息 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-1-5 上午9:46:53
	 * @param loginName
	 * @return
	 */
	public Npc getInfoNPCByLoginName(String loginName) {
		Users auser = new Users(loginName);
		String usrId = userMapper.getUserByLoginName(auser).getId();
		return npcMapper.getInfoNPCByUserId(usrId);
	}
	
	/**
	 * 根据代表团的id查询该代表团的代表信息
	 * @param mission_id
	 * @param level_code
	 * @return
	 */
	public List<NpcInfo> getNpcMembsByMissionId(String mission_id, String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mission_id", mission_id);
		map.put("level_code", level_code);
		
		return npcMapper.getNpcMembsByMissionId(map);
	}

	/**
	 * 根据系统级别与代表id查询代表详细信息
	 * @param npc_id
	 * @param level_code
	 * @return
	 */
	public Npc getNpcMembDetailById(String npc_id, String level_code) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("npc_id", npc_id);
		map.put("level_code", level_code);
		
		Npc theObj = null;
		
		List<Npc> list = npcMapper.getNpcMembDetailById(map);
		if(list != null){
			if(list.size() > 1){//有多条记录
				for(Npc item:list){
					if( !("终止".equals(item.getStatus())) && level_code.equals(item.getUserLevel())||
							!("暂停".equals(item.getStatus())) && level_code.equals(item.getUserLevel())||
							"0".equals(item.getChangeFlag()) && level_code.equals(item.getUserLevel())	)
					{
						theObj = item;
						break;
					}
				}
				
			}
			if(theObj == null){
				theObj = list.get(0);
			}
		}
		
		return theObj;	
	}

	/**
	 * 根据代表团的id查询该代表团的代表信息列表包含分页信息
	 * @param mission_id
	 * @param level_code
	 * @param curPage 
	 * @return
	 */
	public PagesUtil<Npc> getNpcMembsByMissionIdPage(String mission_id,
			String level_code, int curPage) {
		// TODO Auto-generated method stub
		
	/*	Map<String, Object> mapso = new HashMap<String, Object>();
		mapso.put("mission_id", mission_id);
		mapso.put("level_code", level_code);*/
		
		Npc aObject = new Npc();
		if("348".equals(mission_id)){
			aObject.setMissionId(mission_id);
			aObject.setUserLevel("0");
		}else if("349".equals(mission_id)){
			aObject.setMissionId(mission_id);
			aObject.setUserLevel("1");
		}else{
			aObject.setMissionId(mission_id);
			aObject.setUserLevel(level_code);
		}
		/* 以下是按分页进行查找 */
		// 获取总记录数
		//int rowCount = this.getRowsByOfficeId(mapso);
		int rowCount = this.findListRows(aObject);

		//System.out.println("myHeadSugList->row = " + rowCount);
		
		PagesUtil<Npc> pagesUtil = new PagesUtil<Npc>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();
		
		/*Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("mission_id", mission_id);
		map.put("level_code", level_code);*/
		
		//List<Npc> lists = npcDao.getNpcMembsByMissionIdPage(map);
		List<Npc> lists = npcMapper.findList(aObject);
		pagesUtil.setLists(lists);
		return pagesUtil;
	}

	private int findListRows(Npc aObject) {
		// TODO Auto-generated method stub
		return npcMapper.findListRows(aObject);
	}


	/**
	 * 根据代表团id获取代表的总记录数
	 * @param map
	 * @return
	 */
	private int getRowsByOfficeId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return npcMapper.getRowsByOfficeId(map);
	}

	public int test() {
		// TODO Auto-generated method stub
		return npcMapper.test();
	}

	
}
