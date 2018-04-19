package com.ctgu.npc.business.news.mapper;

import com.ctgu.npc.business.news.entity.News;
import com.ctgu.npc.business.news.entity.NewsExtend;
import com.ctgu.npc.business.news.entity.NewsType;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/4/16.
 */
public interface NewsMapper {


    /**
     * 根据公众号对应的新闻分类
     * @param level
     * @return
     */
    public List<NewsType> getNewsType(@Param("level") String level );
    /**
     * 根据当前公众号对应type的所有新闻(分页)
     * @return
     */
    public List<News> getNewsByTypeAndLevel(Map<String, Object> map);
    /**
     * 根据id查找新闻明细
     * @param id
     * @return
     */
    public NewsExtend getNewsById(@Param("id") String id);

    /**
     * 根据当前公众号对应type的所有新闻的总条数
     * @param
     */
    public int getRowTotal(@Param("typeId") String typeId,@Param("level") String level);
}
