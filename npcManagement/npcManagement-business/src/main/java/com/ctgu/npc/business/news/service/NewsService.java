package com.ctgu.npc.business.news.service;

import com.ctgu.npc.business.news.entity.News;
import com.ctgu.npc.business.news.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 2018/4/16.
 */
public class NewsService {

    @Autowired
    private NewsMapper newsMapper;
    /**
     * 查询集合记录总数
     * @param level_code
     * @return
     */
    public int getRowTotal(String level_code) {
        // TODO Auto-generated method stub
        return newsMapper.getRowTotal(level_code);
    }

    /**
     * 查询集合记录总数
     *
     * @param id
     * @return
     */
    public News getNewsById(String id) {
        // TODO Auto-generated method stub
        return newsMapper.getNewsById(id);
    }
}
