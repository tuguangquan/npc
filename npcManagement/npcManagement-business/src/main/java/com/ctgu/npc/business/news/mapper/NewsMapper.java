package com.ctgu.npc.business.news.mapper;

import com.ctgu.npc.business.news.entity.News;

import java.util.List;

/**
 * Created by user on 2018/4/16.
 */
public interface NewsMapper {


    /**
     * 根据id查找明细
     * @param id
     * @return
     */
    public News getNewsById(String id);
    /**
     * 根据id查找明细
     * @param level_code
     * @return
     */
    public List<News> getNewsByCode(String level_code);


    public int getRowTotal(String level_code);
}
