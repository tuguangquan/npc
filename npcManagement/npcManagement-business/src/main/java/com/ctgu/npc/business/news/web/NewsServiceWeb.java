package com.ctgu.npc.business.news.web;

import com.ctgu.npc.business.common.utils.MD5Util;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.news.entity.News;
import com.ctgu.npc.business.news.entity.NewsExtend;
import com.ctgu.npc.business.news.entity.NewsType;
import com.ctgu.npc.business.news.service.NewsService;
import com.ctgu.npc.fundamental.config.FundamentalConfigProvider;
import com.ctgu.npc.fundamental.logger.PlatformLogger;
import com.ctgu.npc.fundamental.util.json.JsonResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by user on 2018/4/16.
 */
@Component
@Path("/news")
public class NewsServiceWeb {
    PlatformLogger logger = PlatformLogger.getLogger(NewsServiceWeb.class);

    private static String secretKey = FundamentalConfigProvider.get("npc.key");

    @Autowired
    private NewsService newsService;

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getNewsType")
    @POST
    public String getNewsType(@FormParam("level") String level, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(level + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        List<NewsType> newsTypes = newsService.getNewsType(level);
        return JsonResultUtils.getObjectResultByStringAsDefault(newsTypes, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getNewsByTypeAndLevel")
    @POST
    public String getNewsByTypeAndLevel(@FormParam("typeId") String typeId,@FormParam("level") String level,@FormParam("pageNum") String pageNum, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(typeId+level+pageNum + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        int curPage = 1;
        if (pageNum != null) {
            try {
                curPage = Integer.parseInt(pageNum);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        PagesUtil<News> pagesUtil = newsService.getNewsByTypeAndLevel(curPage,typeId,level);
        return JsonResultUtils.getObjectResultByStringAsDefault(pagesUtil, JsonResultUtils.Code.SUCCESS);
    }

    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Path("/getNewsById")
    @POST
    public String getNewsById(@FormParam("id") String id, @FormParam("key") String key) {
        String keyWord = MD5Util.md5Encode(id + MD5Util.getDateStr() + secretKey);
        if (!keyWord.equals(key)) {
            return JsonResultUtils.getCodeAndMesByString(JsonResultUtils.Code.ERROR.getCode(), "请求参数有误!");
        }
        NewsExtend newsExtend = newsService.getNewsById(id);
        return JsonResultUtils.getObjectResultByStringAsDefault(newsExtend, JsonResultUtils.Code.SUCCESS);
    }
}
