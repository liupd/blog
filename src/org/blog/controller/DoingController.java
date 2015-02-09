package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.blog.domain.Doing;
import org.blog.domain.Pagination;
import org.blog.service.DoingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class DoingController {
	@Resource
	DoingService doingService;

	@RequestMapping(value = "/doingList.htm")
	public String doingManage(HttpServletRequest request,
			Pagination pagination, Doing doing) throws Exception {
		List<Doing> doingList = doingService.getDoings(doing, pagination);
		request.setAttribute("doingList", doingList);
		request.setAttribute("pagination", pagination);
		return "blog/doing/doingList";
	}
	
	@RequestMapping(value = "/doingListForPortal.htm")
	public String doingList(HttpServletRequest request,
			Pagination pagination) throws Exception {
		List<Doing> doingList = doingService.getDoings(new Doing(), pagination);
		request.setAttribute("doingList", doingList);
		request.setAttribute("pagination", pagination);
		return "blog/portal/doing";
	}

	@RequestMapping(value = "/modDoing.htm")
	public String modDoing(HttpServletRequest request, String _id)
			throws Exception {
		Doing doing = doingService.selectOne(_id);
		request.setAttribute("doing", doing);
		return "blog/doing/modDoing";
	}

	@RequestMapping(value = "/updateDoing.do")
	@ResponseBody
	public Object updateDoing(HttpServletRequest request, Doing doing)
			throws Exception {
		try {
			doing.set_id(doing.getCreateTimeStr());
			doingService.updateDoing(doing);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "碎言碎语修改成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("碎言碎语修改失败！\n\r" + ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/deleteDoing.do")
	@ResponseBody
	public Object deleteCdeleteDoinglassify(HttpServletRequest request,
			String _ids) throws Exception {
		try {
			String[] idsArr = _ids.split(",");
			for (String _id : idsArr) {
				doingService.deleteDoing(_id);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "删除成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("删除失败！\n\r" + ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/addDoing.htm")
	public String addDoing() throws Exception {
		return "blog/doing/addDoing";
	}

	@RequestMapping(value = "/createDoing.do")
	@ResponseBody
	public Object createDoing(HttpServletRequest request, Doing doing)
			throws Exception {
		try {
			doingService.insertDoing(doing);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "创建成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("创建失败！\n\r" + ex.getMessage(), ex);
		}
	}
}
