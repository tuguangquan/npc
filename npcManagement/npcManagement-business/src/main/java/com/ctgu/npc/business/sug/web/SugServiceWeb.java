package com.ctgu.npc.business.sug.web;


import com.ctgu.npc.business.common.utils.Global;
import com.ctgu.npc.business.common.utils.GsonUtils;
import com.ctgu.npc.business.common.utils.PagesUtil;
import com.ctgu.npc.business.common.utils.StringUtils;
import com.ctgu.npc.business.sug.entity.*;
import com.ctgu.npc.business.sug.service.SugService;
import com.ctgu.npc.business.sys.entity.Users;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class SugServiceWeb {

	@Autowired
	private SugService sugService;
	/**
	 * 沟通信息
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-9-27 下午5:22:14
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping("communicationInfo")
	@ResponseBody
	public CommunicationForm communicationInfo(HttpServletResponse response, HttpServletRequest request){
		
		//String loginName = request.getParameter("loginName");
		String id = request.getParameter("theObjId");

		//String level_code = request.getParameter("level_code");

		
		CommunicationForm communicationForm = sugService.getCommunicationForm(id);
		//request.setAttribute("form", communicationForm);
		return communicationForm;
	}
	
	
	/**
	 * 下载办理答复件
	 * @param fileName
	 * @param response
	 * @throws Exception
	 */
	//@RequiresPermissions("user")
	@RequestMapping("/answerFileDownLoad")
	public void answerFileDownLoad(String fileName , HttpServletResponse response) throws Exception{
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		String fileUploadPath = Global.getConfig("sug.answerFileUploadPath");
		if(StringUtils.isEmpty(fileName))
			return;
		FileInputStream fio = new FileInputStream(fileUploadPath+"/"+fileName);
		 byte[] b = new byte[1024];
	        int len = -1;
	        while ((len = fio.read(b, 0, 1024)) != -1) {
	            response.getOutputStream().write(b, 0, len);
	        }
	        fio.close();
	}
	
	
	
	/**
	 * 议案建议审核
	 * Description: 
	 * Company: ctgu  
	 * @author : youngmien
	 * @date  2016-8-29 下午3:59:24
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"saveCheckSug"})
	@ResponseBody
	public String saveCheckSug(HttpServletRequest request, HttpServletResponse response, Model model){
		String loginName = request.getParameter("loginName");
		String json_str = request.getParameter("json_str");

		String level_code = request.getParameter("level_code");

		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			Suggestion sug = (Suggestion) gson.fromJson(json_str, type);
			
			//System.out.println("json_str->"+json_str);
			
			sugService.saveCheckSug(sug, loginName, level_code);
			return "success!";
		} else {
			return "fail!";
		}
		
		
	}
	

	
	
	
	/**
	 * 建议议案评价
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"saveEvaluteSug"})
	@ResponseBody
	public String saveEvaluteSug(HttpServletRequest request, HttpServletResponse response, Model model){
		String loginName = request.getParameter("loginName");
		String json_str = request.getParameter("json_str");

		String level_code = request.getParameter("level_code");

		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			Suggestion sug = (Suggestion) gson.fromJson(json_str, type);
			
			//System.out.println("result->"+ sug.getResevaluation() + ","+sug.getEvaluate());
			sugService.saveEvaluteSug(sug, loginName, level_code);
			return "success!";
		} else {
			return "fail!";
		}
		
		
	}
	
	
	/**
	 * ===办理=待签收===交办直接办理
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"sugAssignDealList"})
	@ResponseBody
	public PagesUtil<AssignForm> sugAssignDealList(HttpServletRequest request, HttpServletResponse response, Model model){
		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");
		String type_value = request.getParameter("type_value");
		String status = request.getParameter("status");
		String loginName = request.getParameter("loginName");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		PagesUtil<AssignForm> pagesUtil = sugService.sugAssignDealList(
				curPage, level_code,status,type_value,loginName);

		return pagesUtil;
		
	}
	
	
	
	
	
	/**
	 * ===交办任务列表===
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"sugAssignList"})
	@ResponseBody
	public PagesUtil<AssignForm> sugAssignList(HttpServletRequest request, HttpServletResponse response, Model model){
		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");
		String type_value = request.getParameter("type_value");
		String status = request.getParameter("status");
		String loginName = request.getParameter("loginName");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		PagesUtil<AssignForm> pagesUtil = sugService.sugAssignList(
				curPage, level_code,status,type_value,loginName);

		return pagesUtil;
		
	}
	
	
	
	/**
	 * ===转交列表===议案建议
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"sugTransferList"})
	@ResponseBody
	public PagesUtil<TransferForm> sugTransferList(HttpServletRequest request, HttpServletResponse response, Model model){
		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");

		String status = request.getParameter("status");
		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		PagesUtil<TransferForm> pagesUtil = sugService.sugTransferList(
				curPage, level_code,status,type_value);

		return pagesUtil;
		
	}
	
	
	
	/**
	 * 立案列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"sugPutonList"})
	@ResponseBody
	public PagesUtil<Suggestion> sugPutonList(HttpServletRequest request, HttpServletResponse response, Model model){
		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");

		//String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		PagesUtil<Suggestion> pagesUtil = sugService.sugPutonList(
				curPage, level_code);

		return pagesUtil;
		
	}
	
	
	
	
	/**
	 * === 审核列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"checkList"})
	@ResponseBody
	public PagesUtil<Suggestion> sugCheckList(HttpServletRequest request, HttpServletResponse response, Model model){
		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		PagesUtil<Suggestion> pagesUtil = sugService.sugCheckList(
				curPage, level_code, type_value);

		return pagesUtil;
		
	}
	
	

	/**
	 * ===优秀的 
	 * 查看本级议案建议列表包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListExcellentPage" })
	@ResponseBody
	public PagesUtil<Suggestion> sugListExcellentPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		/*
		 * List<Suggestion> sugList = sugService.sugListExcellent(curPage,
		 * level_code, request, response);
		 */
		PagesUtil<Suggestion> pagesUtil = sugService.sugListExcellentPage(
				curPage, level_code, type_value);

		return pagesUtil;
	}

	/**
	 * ===重点的 
	 * 查看本级议案建议列表包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListEmphasisPage" })
	@ResponseBody
	public PagesUtil<Suggestion> sugListEmphasisPage(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<Suggestion> pagesUtil = sugService.sugListEmphasisPage(
				curPage, level_code, type_value);
		/*
		 * List<Suggestion> sugList = sugService.sugListEmphasis(curPage,
		 * level_code, request, response);
		 */

		return pagesUtil;
	}

	/**
	 * ===代表团的 
	 * 根据代表团id查询本代表团所有的suggestion包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListOfficePage" })
	@ResponseBody
	public PagesUtil<Suggestion> sugListOfficePage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String type_value = request.getParameter("type_value");

		String level_code = request.getParameter("level_code");
		String team_id = request.getParameter("team_id");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		/*
		 * List<Suggestion> sugList = sugService.sugListOffice(curPage,
		 * level_code,team_id, request, response);
		 */
		PagesUtil<Suggestion> pagesUtil = sugService.sugListOfficePage(curPage,
				level_code, team_id, type_value);

		return pagesUtil;
	}

	/**
	 * ===代表们的 
	 * 查看本级代表们议案建议列表 包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListAllPage" })
	@ResponseBody
	public PagesUtil<Suggestion> sugListAllPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		/*
		 * List<Suggestion> sugList = sugService.sugListAll(curPage, level_code,
		 * request, response);
		 */
		PagesUtil<Suggestion> pagesUtil = sugService.sugListAllPage(curPage,
				level_code, type_value);

		return pagesUtil;
	}// -----sugListAllPage END ------------------

	/**
	 * ===我领衔的
	 *  根据登录名和系统等级分页查询suggestion 的List包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "mySugListHeadPage" })
	@ResponseBody
	public PagesUtil<Suggestion> mySugListHeadPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");
		/*
		 * if(type_value == null || "".equals(type_value) ){ type_value = "1"; }
		 */

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		/*
		 * List<Suggestion> sugList = sugService.mySugListHead(loginName,
		 * curPage, level_code, request, response);
		 */
		PagesUtil<Suggestion> pagesUtil = sugService.mySugListHeadPage(
				loginName, curPage, level_code, type_value);

		return pagesUtil;
	}

	/**
	 * ===我联名的 
	 * 根据登录名和系统等级分页查询我联名的suggestion 的List包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "mySugListJoinPage" })
	@ResponseBody
	public PagesUtil<Suggestion> mySugListJoinPage(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");
		/*
		 * if(type_value == null || "".equals(type_value) ){ type_value = "1"; }
		 */

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		PagesUtil<Suggestion> pagesUtil = sugService.mySugListJoinPage(
				loginName, curPage, level_code, type_value);
		return pagesUtil;
	}

	/**===新增
	 * 新增议案建议
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "mySugHeadAdd" })
	@ResponseBody
	public String mySugHeadAdd(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String json_str = request.getParameter("json_str");

		String level_code = request.getParameter("level_code");

		Suggestion sug = new Suggestion();
		if (json_str != null) {
			Gson gson = new Gson();
			java.lang.reflect.Type type = new TypeToken<Suggestion>() {
			}.getType();
			sug = (Suggestion) gson.fromJson(json_str, type);

			sugService.mySugHeadAdd(sug, loginName, level_code);
			return "save success!";
		} else {
			return "save fail!";
		}
	}
	
	/*
	 * ===议案详细 
	 * 根据suggestion的id和suggestion的type查找Suggestion议案的详细
	 */
	@RequestMapping(value = "billsDetail")
	@ResponseBody
	public BillsEntity billsDetail(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		
		String sug_type = request.getParameter("type_value");
		
		String sugID = request.getParameter("sugId");
		BillsEntity theObj = sugService.billsDetail(sugID);
		
		return theObj;
	}



	/*
	 * ===建议详细 
	 * 根据suggestion的id和suggestion的type查找Suggestion的详细
	 */
	@RequestMapping(value = "sugInfoHead")
	@ResponseBody
	public Suggestion sugInfoHead(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		String sug_type = request.getParameter("type_value");
		String id = request.getParameter("sugId");
		// System.out.println("sugId->" + id);
		Suggestion sug = sugService.getSuggestionByIdType(id, sug_type);

		if (sug != null) {

			// 获取联名人
			if (StringUtils.isNotEmpty(sug.getSecondWriterIDS())) {
				List<Users> list = sugService.getSecondWriter(sug
						.getSecondWriterIDS());
				String str = "";
				for (Users u : list) {
					str += u.getName() + ",";
				}

				/*
				 * System.out.println("2nd writer->" + str.substring(0,
				 * str.length() - 1));
				 */

				sug.setSecondWriter(str.substring(0, str.length() - 1));
			}

			
		}
		return sug;
	}
	
	
	
	
	

	/**
	 * 议案建议的转交信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getListTransfer")
	@ResponseBody
	public List<TransferForm> getListTransfer(HttpServletRequest request,
			HttpServletResponse response, Model model)
	{
		//String sug_type = request.getParameter("type_value");
		String sugID = request.getParameter("sugId");
		List<TransferForm> lists = sugService.getListTransfer(sugID);
		
		return lists;
	}
	
	/**
	 * 交办信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getListAssign")
	@ResponseBody
	public List<AssignForm> getListAssign(HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		String sugID = request.getParameter("sugId");
		List<AssignForm> lists = sugService.getListAssign(sugID);
		
		return lists;
		
	}
	
	/**
	 * 承办信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getListUnitAnswer")
	@ResponseBody
	public List<UnitAnswerForm> getListUnitAnswer(HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		String sugID = request.getParameter("sugId");
		List<UnitAnswerForm> lists = sugService.getListUnitAnswer(sugID);
		
		return lists;
		
	}
	
	/**
	 * 议案建议处理信息
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "getSugProcessInfo")
	@ResponseBody
	public SugProcessInfo getSugProcessInfo(HttpServletRequest request,
			HttpServletResponse response, Model model){
		
		String sugID = request.getParameter("sugId");
		SugProcessInfo theObj = sugService.getSugProcessInfo(sugID);
		
		return theObj;
		
	}
	
	
	
	
	
	
	
	
	
	
	// ===== 以下是用PrintWriter将结果输出至客户端
	// ============================================
	@RequestMapping(value = { "myHeadSugList" })
	public void myHeadSugList(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		response.setContentType("text/html;charset=UTF-8.");
		response.setCharacterEncoding("utf-8");
		String jsonStr = null;
		PrintWriter out = null;

		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.myHeadSugList(loginName, curPage,
				request, response);

		if (sugList != null && sugList.size() > 0) {
			// System.out.println("size->" + sugList.size());
			jsonStr = GsonUtils.getJsonString(sugList);
		} else {
			jsonStr = "fail";
		}
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(jsonStr);
	}

	/*
	 * 根据id查找Suggestion(我的领衔), method = RequestMethod.GET
	 */
	@RequestMapping(value = "headsuginfo")
	public void headSugInfo(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		response.setContentType("text/html;charset=UTF-8.");
		response.setCharacterEncoding("utf-8");
		String jsonStr = null;
		PrintWriter out = null;

		String id = request.getParameter("sugId");
		// System.out.println("sugId->" + id);
		Suggestion sug = sugService.getSuggestionById(id);

		if (sug != null) {

			// 获取联名人
			if (StringUtils.isNotEmpty(sug.getSecondWriterIDS())) {
				List<Users> list = sugService.getSecondWriter(sug
						.getSecondWriterIDS());
				String str = "";
				for (Users u : list) {
					str += u.getName() + ",";
				}
				/*
				 * model.addAttribute("secondWriter", str.substring(0,
				 * str.length() - 1));
				 */
				System.out.println("2nd writer->"
						+ str.substring(0, str.length() - 1));
				sug.setSecondWriter(str.substring(0, str.length() - 1));
			}

			if (StringUtils.isNotEmpty(sug.getStatus())) {
				String states = sugService.getStatus(sug.getStatus(),
						"sug_status");
				System.out.println("states->" + states);
				sug.setState(states);
			}

			/*
			 * if(StringUtils.isNotEmpty(sug.getContent())){ String content =
			 * (StringUtils.replaceHtml( StringUtils.toHtml(sug.getContent())));
			 * sug.setContent(content); System.out.println("content->" +
			 * content); }
			 */
			// model.addAttribute("sug", sug);
			// attr.addAttribute("sug", sug);

			jsonStr = GsonUtils.getJsonString(sug);
			System.out.println("1->" + jsonStr);

		} else {
			jsonStr = "fail";
		}

		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
			jsonStr = "suggestion error!";
		}
		System.out.println("2->" + jsonStr);
		out.print(jsonStr);

		// return "redirect:/myHeadSugList";
	}

	// ==============修改前的==================
	/**
	 * 查看本级优秀议案建议列表
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListExcellent" })
	@ResponseBody
	public List<Suggestion> sugListExcellent(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.sugListExcellent(curPage,
				level_code, request, response);

		return sugList;
	}

	/**
	 * 优秀的 查看本级议案建议列表包含分页信息
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *//*
	@RequestMapping(value = { "sugListExcellentPage" })
	@ResponseBody
	public PagesUtil<Suggestion> sugListExcellentPageType(
			HttpServletRequest request, HttpServletResponse response,
			Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");
		// System.out.println("curPage->" + curPageStr);

		String level_code = request.getParameter("level_code");

		String type_value = request.getParameter("type_value");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		
		 * List<Suggestion> sugList = sugService.sugListExcellent(curPage,
		 * level_code, request, response);
		 
		PagesUtil<Suggestion> pagesUtil = sugService.sugListExcellentPage(
				curPage, level_code, type_value);

		return pagesUtil;
	}*/

	/**
	 * 重点的 查看本级议案建议
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListEmphasis" })
	@ResponseBody
	public List<Suggestion> sugListEmphasis(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.sugListEmphasis(curPage,
				level_code, request, response);

		return sugList;
	}

	/**
	 * 根据代表团id查询本代表团所有的suggestion
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListOffice" })
	@ResponseBody
	public List<Suggestion> sugListOffice(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		String team_id = request.getParameter("team_id");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.sugListOffice(curPage,
				level_code, team_id, request, response);

		return sugList;
	}

	/**
	 * 所有代表 查看本级代表们议案建议
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "sugListAll" })
	@ResponseBody
	public List<Suggestion> sugListAll(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		// String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.sugListAll(curPage, level_code,
				request, response);

		return sugList;
	}

	/**
	 * 根据登录名和系统等级分页查询我领衔的suggestion 的List
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "mySugListHead" })
	@ResponseBody
	public List<Suggestion> mySugListHead(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.mySugListHead(loginName, curPage,
				level_code, request, response);

		return sugList;
	}

	/**
	 * 根据登录名和系统等级分页查询我联名的suggestion 的List
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "mySugListJoin" })
	@ResponseBody
	public List<Suggestion> mySugListJoin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String loginName = request.getParameter("loginName");

		String curPageStr = request.getParameter("curPage");

		String level_code = request.getParameter("level_code");

		int curPage = 1;
		if (curPageStr != null) {
			try {
				curPage = Integer.parseInt(curPageStr);

			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		List<Suggestion> sugList = sugService.mySugListJoin(loginName, curPage,
				level_code, request, response);

		return sugList;
	}

}
