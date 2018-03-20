package com.ctgu.npc.business.sys.web;

import com.ctgu.npc.business.sys.service.UserService;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.sys.entity.Office;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018/3/19.
 */
@Component
@Path("/user")
public class UserServiceWeb {

    @Autowired
    private UserService userService;


    /**===代表团分页列表
     * 根据系统级别编码查询部门信息(如代表团)并包含分页信息
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="getOfficeNameByLevelCodePages")
    @ResponseBody
    public PagesUtil<Office> getOfficeNameByLevelCodePages(HttpServletRequest request,
                                                           HttpServletResponse response, Model model)
    {
        PagesUtil<Office> pagesUtil = new PagesUtil<Office>();
        List<Office> lists = new ArrayList<Office>();

        String curPageStr = request.getParameter("curPage");
        int curPage = 1;
        if (curPageStr != null) {
            try {
                curPage = Integer.parseInt(curPageStr);

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        String level_code = request.getParameter("level_code");
        String office_type = request.getParameter("office_type");
        pagesUtil= userService.getOfficeNameByLevelCodePages(level_code,office_type,curPage);

        return pagesUtil;
    }//------getOfficeNameByLevelCode END --------------

    /**
     * 根据系统级别编码查询部门信息列表(如代表团)
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="getOfficeNameByLevelCode")
    @ResponseBody
    public List<Office> getOfficeNameByLevelCode(HttpServletRequest request,
                                                 HttpServletResponse response, Model model)
    {
        List<Office> lists = new ArrayList<Office>();

        String curPageStr = request.getParameter("curPage");
        int curPage = 1;
        if (curPageStr != null) {
            try {
                curPage = Integer.parseInt(curPageStr);

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        String level_code = request.getParameter("level_code");
        String office_type = request.getParameter("office_type");
        lists = userService.getOfficeNameByLevelCode(level_code,office_type,curPage);

        return lists;
    }//------getOfficeNameByLevelCode END --------------

}
