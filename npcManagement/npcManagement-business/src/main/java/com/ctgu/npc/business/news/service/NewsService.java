package com.ctgu.npc.business.news.service;

import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.news.entity.News;
import com.ctgu.npc.business.news.entity.NewsExtend;
import com.ctgu.npc.business.news.entity.NewsType;
import com.ctgu.npc.business.news.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/4/16.
 */
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;


    /**
     * 根据公众号对应的新闻分类
     * @param level
     * @return
     */
    public List<NewsType> getNewsType(String level){
        return newsMapper.getNewsType(level);
    }
    /**
     * 根据当前公众号对应type的所有新闻(分页)
     * @param typeId
     * @param level
     * @return
     */
    public PagesUtil<News> getNewsByTypeAndLevel(int curPage,String typeId,String level){
		/* 以下是按分页进行查找 */
        // 获取总记录数
        int rowCount = newsMapper.getRowTotal(typeId,level);
        PagesUtil<News> pagesUtil = new PagesUtil<News>();
        pagesUtil.setRowCount(rowCount);
        if (pagesUtil.getTotalPages() < curPage) {
            curPage = pagesUtil.getTotalPages();
        }
        int offset = (curPage - 1) * pagesUtil.getSizePage();
        int size = pagesUtil.getSizePage();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("offset", offset);
        map.put("size", size);
        map.put("typeId", typeId);
        map.put("level", level);
        pagesUtil.setLists(newsMapper.getNewsByTypeAndLevel(map));
        return pagesUtil;
    }
    /**
     * 根据id查找新闻明细
     * @param id
     * @return
     */
    public NewsExtend getNewsById(String id){
       return newsMapper.getNewsById(id);
    }
}
