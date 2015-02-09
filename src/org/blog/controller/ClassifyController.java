package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.blog.domain.Classify;
import org.blog.domain.Pagination;
import org.blog.service.ClassifyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ClassifyController {

	@Resource
	private ClassifyService classifyService;

	@RequestMapping(value = "/classifList.htm")
	public String classifManage(HttpServletRequest request,
			Pagination pagination) throws Exception {
		List<Classify> classifyList = classifyService.getClassifys(pagination,null);
		request.setAttribute("classifyList", classifyList);
		request.setAttribute("pagination", pagination);
		return "blog/classify/classifyList";
	}
	
	@RequestMapping(value = "/modClassify.htm")
	public String modClassify(HttpServletRequest request , String _id) throws Exception {
		Classify classify = classifyService.selectOne(_id);
		request.setAttribute("classify", classify);
		return "blog/classify/modClassify";
	}
	
	@RequestMapping(value="/updateClassify.do")
	@ResponseBody
	public Object updateClassify(HttpServletRequest request, Classify classify) throws Exception{
		try {
			classify.set_id(classify.getCreateTimeStr());
			classifyService.updateClassify(classify);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "分类修改成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("分类修改失败！\n\r" + ex.getMessage(), ex);
		}
	}
	
	@RequestMapping(value="/deleteClassify.do")
	@ResponseBody
	public Object deleteClassify(HttpServletRequest request, String _ids) throws Exception{
		try {
			String[] idsArr = _ids.split(",");
			for(String _id : idsArr){
				classifyService.deleteClassify(_id);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "删除成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("删除失败！\n\r" + ex.getMessage(), ex);
		}
	}
	
	@RequestMapping(value = "/addClassify.htm")
	public String addClassify() throws Exception {
		return "blog/classify/addClassify";
	}
	
	@RequestMapping(value="/createClassify.do")
	@ResponseBody
	public Object createClassify(HttpServletRequest request, Classify classify)
			throws Exception {
		try {
			classifyService.insertClassify(classify);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "分类创建成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("分类创建失败！\n\r" + ex.getMessage(), ex);
		}
	}
	@RequestMapping(value="/classifList.do")
	@ResponseBody
	public Object getClassify(String isDisplay) throws Exception{
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", new Boolean(true));
		result.put("classifyList", classifyService.getClassifys(null,isDisplay));
		return result;
	}
	
}
