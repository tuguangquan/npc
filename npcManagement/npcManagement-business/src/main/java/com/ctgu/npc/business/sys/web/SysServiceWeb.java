package com.ctgu.npc.business.sys.web;

import com.ctgu.npc.business.sys.service.UserService;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.sys.entity.Users;
import com.ctgu.npc.business.sys.service.SysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2018/3/19.
 */
@Component
@Path("/sys")
public class SysServiceWeb {
    @Autowired
    private UserService userService;

    @Autowired
    private SysService systemService;

    /**
     * 修改个人用户密码
     * @param oldPassword
     * @param newPassword
     * @param model
     * @return
     */

    @RequestMapping(value = "updatePwd")
    @ResponseBody
    public String updatePwd(HttpServletRequest request,
                            HttpServletResponse response, Model model) {

        String result = null;

        String loginName = request.getParameter("loginName");
        String oldPassword = request.getParameter("pswd_old");
        String newPassword = request.getParameter("pswd_new");


        Users theUser = null;
        theUser = userService.getUser(loginName);

        if(theUser != null){

            if (StringUtils.isNotBlank(oldPassword) && StringUtils.isNotBlank(newPassword)){

                if (SysService.validatePassword(oldPassword, theUser.getPassword())){
                    systemService.updatePasswordById(theUser.getId(),  newPassword);
                    result = "1";
                }else{
                    result = "0";
                }
            }else{
                result = "-1";
            }
        }else{
            result = "-1";
        }
        return result;
    }


    /**
     * 根据用户名密码进行登录判断
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="login")
    @ResponseBody
    public Users login(HttpServletRequest request,
                       HttpServletResponse response, Model model){

        Users usrs = null;
        String uname = request.getParameter("username");
        String pswd = request.getParameter("password");

        //System.out.println("->" +uname +",->" +pswd);

		/*try {*/
        if (StringUtils.isMobile(uname))
        {
            usrs = userService.getUserByMobile(uname,pswd);
        }else{
            usrs = userService.getUserByParams(uname,pswd);
        }

		/*} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}*/

        //out.print(jsonStr);
        //System.out.println("user roleName->" + usrs.getRoleName() + ",roleId->" + usrs.getRoleId());
        return usrs;

    }


    /**
     * 根据用户的级别编码查询用户级别名称
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="getLevelMaps")
    @ResponseBody
    public List<Map<String,Object>> getLevelByList(HttpServletRequest request,
                                                   HttpServletResponse response, Model model){
        List<Map<String,Object>> levelMaps = new ArrayList<Map<String,Object>>();

        String levels = request.getParameter("levels");
        if(levels != null){
            String[] str = levels.split(",");
            for(int i = 0;i < str.length; i++){
                Map<String, Object> map = new HashMap<String, Object>();
                String value = userService.getOfficeNameByCode(str[i]);
                map.put("level_code", str[i]);
                map.put("level_title", value);
                levelMaps.add(map);
            }
        }

        return levelMaps;

    }

    /**
     *
     * Description:根据userId获取该user的所有系统级别
     * Company: ctgu
     * @author : youngmien
     * @date  2017-1-17 上午9:13:02
     * @param request
     * @param response
     * @param model
     * @return String 如100,10010
     */
    @RequestMapping(value="getLevelsByUserId")
    @ResponseBody
    public String getLevelsByUserId(HttpServletRequest request,
                                    HttpServletResponse response, Model model){
        String levels = null;

        String userId = request.getParameter("user_Id");
        levels = userService.getLevelsByUserId(userId);
        System.out.println("levels->" + levels);

        return levels;

    }



    /**
     * 用户分页
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value="page")
    public String getPagesByNum(HttpServletRequest request, Model model){

        String curPageStr = request.getParameter("numpages");
        int curPageI = 1;
        if(curPageStr != null){
            try {
                curPageI = Integer.parseInt(curPageStr);

            } catch (Exception e) {
                // TODO: handle exception
            }
        }
        //获取总记录数
        int rowCount = userService.getRowCount();

        PagesUtil pagesUtil = new PagesUtil();
        pagesUtil.setRowCount(rowCount);

        if(pagesUtil.getTotalPages() < curPageI){
            curPageI = pagesUtil.getTotalPages();
        }
        pagesUtil = userService.getPagesByNum(pagesUtil);

        System.out.println("list->"+pagesUtil.getLists().size());

        model.addAttribute("pagelist", pagesUtil);
        //request.setAttribute("pagelist", pagesUtil);

        return "index";
    }

    @RequestMapping(value="index")
    public String helloworld(){
        return "index";
    }

}
