package com.ctgu.npc.business.inform.service;

import com.ctgu.npc.business.common.utils.DateUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.inform.entity.Opinion;
import com.ctgu.npc.business.inform.entity.Report;
import com.ctgu.npc.business.inform.mapper.OpinionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public class OpinionService {

	@Autowired
	private OpinionMapper opinionMapper;

	/**
	 * 根据系统级别分页查询意见列表
	 */
	public List<Opinion> getListOpinion(String level_code, int curPage) {
		// TODO Auto-generated method stub
		List<Opinion> lists = new ArrayList<Opinion>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelOpinion(level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

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
		// map.put("pub_col", pub_col);

		return opinionMapper.getListOpinion(map);
	}

	/**
	 * 根据系统级别查询Opinion总记录数
	 * 
	 * @param level_code
	 * @return
	 */
	private int getRowsByLevelOpinion(String level_code) {

		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		return opinionMapper.getRowsByLevelOpinion(map);
	}

	/**
	 * 根据opinion的id查询详细
	 */
	public Opinion getDetailByIdOpinion(String opin_id) {
		// TODO Auto-generated method stub
		return opinionMapper.getDetailByIdOpinion(opin_id);
	}

	/**
	 * 报告列表 根据系统级别分页查询代表报告列表
	 */
	public List<Report> getListReport(String level_code, int curPage) {
		// TODO Auto-generated method stub
		List<Report> lists = new ArrayList<Report>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelReport(level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

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
		// map.put("pub_col", pub_col);

		return opinionMapper.getListReport(map);
	}

	/**
	 * 根据系统级别查询Opinion总记录数
	 * 
	 * @param level_code
	 * @return
	 */
	private int getRowsByLevelReport(String level_code) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("level_code", level_code);
		return opinionMapper.getRowsByLevelReport(map);
	}

	/**
	 * 根据Report的id查询详细
	 */
	public Report getDetailByIdReport(String repot_id) {
		// TODO Auto-generated method stub
		return opinionMapper.getDetailByIdReport(repot_id);
	}

	/**
	 * 根据系统级别分页查询代表报告列表包含分页信息
	 * 
	 * @param level_code
	 * @param curPage
	 * @return
	 */
	public PagesUtil<Report> getListReportPage(String level_code, int curPage) {
		// TODO Auto-generated method stub
		List<Report> lists = new ArrayList<Report>();

		/* 以下是按分页进行查找 */
		// 获取总记录数
		int rowCount = this.getRowsByLevelReport(level_code);

		// System.out.println("myHeadSugList->row = " + rowCount);

		PagesUtil<Report> pagesUtil = new PagesUtil<Report>();
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
		// map.put("pub_col", pub_col);
		lists = opinionMapper.getListReport(map);
		pagesUtil.setLists(lists);
		return pagesUtil;

	}

	/**
	 * 保存填写意见
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2017-8-1 上午9:16:02
	 * @param opinion
	 * @param report
	 * @param userId
	 * @param level
	 */
	@Transactional(readOnly = false)
	public void addsaveOpinion(Opinion opinion, Report report, String userId, String level) {
		// TODO Auto-generated method stub
		opinion.setReportId(report.getId());
		opinion.setRaiseTime(DateUtils.getDateTime());
		opinion.setRaiseId(userId);
		opinion.setLevel(level);
		opinionMapper.insertOpinion(opinion);
	}

}
