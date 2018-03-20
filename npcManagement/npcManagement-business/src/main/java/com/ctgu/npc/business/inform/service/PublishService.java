package com.ctgu.npc.business.inform.service;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.Publish;
import com.ctgu.npc.business.inform.mapper.PublishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublishService {
	
	@Autowired
	private PublishMapper publishDao;

	/**NPC
	 * 根据系统级别获取publishList-人大
	 * @param level_code
	 * @param pub_col 
	 * @return
	 */
	public List<Publish> getPublishListNpc(int curPage,String level_code, String pub_col	) {
		// TODO Auto-generated method stub
		List<Publish> pubList = new ArrayList<Publish>();
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelNpc(level_code,pub_col);

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
		map.put("level_code", level_code);
		map.put("pub_col", pub_col);
		
		return publishDao.getPublishListNpc(map);
	}

	/**NPC
	 * 根据系统级别获取NPC发布的总记录数
	 * @param level_code
	 * @param pub_col 
	 * @return
	 */
	private int getRowsByLevelNpc(String level_code, String pub_col) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		map.put("pub_col", pub_col);
		return publishDao.getRowsByLevelNpc(map);
	}

	/**
	 * 根据publishId查询publish详细信息
	 * @param pub_id
	 * @return
	 */
	public Publish getPublishDetailNpc(String pub_id) {
		// TODO Auto-generated method stub
		return publishDao.getPublishDetailNpc(pub_id);
	}

	/**
	 * 根据系统级别获取publishList-人大,政府,法院,检察院 包含分页信息
	 * @param curPage
	 * @param level_code
	 * @param pub_col
	 * @return
	 */
	public PagesUtil<Publish> getPublishListNpcPage(int curPage,
			String level_code, String pub_col) {
		// TODO Auto-generated method stub
		List<Publish> pubList = new ArrayList<Publish>();
		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelNpc(level_code,pub_col);

		//System.out.println("myHeadSugList->row = " + rowCount);
		
		PagesUtil<Publish> pagesUtil = new PagesUtil<Publish>();
		pagesUtil.setRowCount(rowCount);

		if (pagesUtil.getTotalPages() < curPage) {
			curPage = pagesUtil.getTotalPages();
		}

		int offset = (curPage - 1) * pagesUtil.getSizePage();
		int size = pagesUtil.getSizePage();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("offset", offset);
		map.put("size", size);
		map.put("level_code", level_code);
		map.put("pub_col", pub_col);
		
		pubList = publishDao.getPublishListNpc(map);
		pagesUtil.setLists(pubList);
		return pagesUtil;

	}

}
