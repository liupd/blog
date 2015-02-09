package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.blog.domain.Blog;
import org.blog.domain.Classify;
import org.blog.domain.Comment;
import org.blog.domain.Pagination;
import org.blog.service.BlogService;
import org.blog.service.ClassifyService;
import org.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
	@Resource
	private BlogService blogService;
	
	@Resource
	private ClassifyService classifyService;
	
	@Resource
	private CommentService commentService;
	@RequestMapping(value = "/blogList.htm")
	public String blogManage(HttpServletRequest request,
			Pagination pagination , Blog blog) throws Exception {
		List<Blog> blogList = blogService.getBlogs(pagination,blog,"createTime");
		request.setAttribute("blogList", blogList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("queryBean", blog);
		return "blog/blog/blogList";
	}
	
	@RequestMapping(value = "/blogListForPortal.htm")
	public String blogListForPortal(HttpServletRequest request,
			Pagination pagination , Blog blog) throws Exception {
		List<Blog> blogList = blogService.getBlogs(pagination,blog,"createTime");
		List<Classify> classifyList = classifyService.getClassifys(null,"0");
		Map<String ,String> classifyMap = new HashMap<String,String>();
		for(Classify classify : classifyList){
			classifyMap.put(classify.get_id(), classify.getClassifyName());
		}
		request.setAttribute("classifyList", classifyList);
		request.setAttribute("classifyMap", classifyMap);
		request.setAttribute("blogList", blogList);
		request.setAttribute("pagination", pagination);
		request.setAttribute("queryBean", blog);
		return "blog/portal/blogList";
	}
	
	
	@RequestMapping(value = "/blogList.do")
	@ResponseBody
	public Object blogList(HttpServletRequest request,
			Pagination pagination , Blog blog) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("success", new Boolean(true));
		result.put("blogList", blogService.getBlogs(pagination,blog,"createTime"));
		return result;
	}
	
	@RequestMapping(value = "/addBlog.htm")
	public String addBlog() throws Exception{
		return "blog/blog/addBlog";
	}
	
	@RequestMapping(value = "/news.htm")
	public String newsBlog(HttpServletRequest request) throws Exception{
		Pagination pagination = new Pagination();
		pagination.setNumPerPage(8);
		request.setAttribute("newsblogList", blogService.getBlogs(pagination,new Blog(),"createTime"));
		pagination.setNumPerPage(5);
		request.setAttribute("hotblogList", blogService.getBlogs(pagination,new Blog(),"viewCount"));
		return "blog/portal/news";
	}
	
	@RequestMapping(value = "/index.htm")
	public String indexBlog(HttpServletRequest request) throws Exception{
		Pagination pagination = new Pagination();
		pagination.setNumPerPage(6);
		Blog blog = new Blog();
		blog.setIsIndex("1");
		List<Classify> classifyList = classifyService.getClassifys(null,"0");
		Map<String ,String> classifyMap = new HashMap<String,String>();
		for(Classify classify : classifyList){
			classifyMap.put(classify.get_id(), classify.getClassifyName());
		}
		request.setAttribute("classifyList", classifyList);
		request.setAttribute("classifyMap", classifyMap);
		request.setAttribute("blogList", blogService.getBlogs(pagination,blog,"createTime"));
		return "blog/portal/index";

//		if(_id != null && !_id.isEmpty()){
//			blog.set_id(_id);
//		}
//		request.setAttribute("blogList", blogService.getBlogs(pagination,blog,"createTime"));
//		request.setAttribute("classifyList", classifyList);
//		request.setAttribute("classifyMap", classifyMap);
//		request.setAttribute("pagination", pagination);
//		request.setAttribute("queryBean", blog);
//		if(pageType != null && !pageType.isEmpty()){
//			return "blog/portal/index";
//		}
//		if(_id != null && !_id.isEmpty()){
//			blogService.updateViewCount(_id);
//			Comment comment = new Comment();
//			comment.setBlogId(_id);
//			request.setAttribute("commentList", commentService.getComments(null, comment));
//			return "blog/portal/blogShow";
//		}
//		return "blog/portal/blog";
	}
	
	@RequestMapping(value = "/addBlog.do")
	@ResponseBody
	public Object addBlog(HttpServletRequest request , Blog blog) throws Exception{
		try {
			blog.setIsIndex("0");
			blogService.insertBlog(blog);
			classifyService.updateBlogCount(blog.getClassifyId(),"add");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "博客发表成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("博客发表失败！\n\r" + ex.getMessage(), ex);
		}
	}
	
	@RequestMapping(value="/deleteBlog.do")
	@ResponseBody
	public Object deleteBlog(HttpServletRequest request, String _ids) throws Exception{
		try {
			String[] idsArr = _ids.split(",");
			for(String _id : idsArr){
				Blog blog = blogService.selectBlog(_id);
				blogService.deleteBlogPhoto(blog.getFileId());
				blogService.deleteBlog(_id);
				classifyService.updateBlogCount(blog.getClassifyId(),"delete");
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "博客删除成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("博客删除失败！\n\r" + ex.getMessage(), ex);
		}
	}
	
	@RequestMapping(value = "/modBlog.htm")
	public String modBlog(HttpServletRequest request , String _id) throws Exception {
		Blog blog = blogService.selectBlog(_id);
		request.setAttribute("blog", blog);
		return "blog/blog/modBlog";
	}
	
	@RequestMapping(value = "/about.htm")
	public String about() throws Exception {
		return "blog/portal/about";
	}
	
	@RequestMapping(value = "/show.htm")
	public String showBlog(HttpServletRequest request , String _id) throws Exception {
		Blog blog = blogService.selectBlog(_id);
		blogService.updateViewCount(_id);
		List<Classify> classifyList = classifyService.getClassifys(null,"0");
		Map<String ,String> classifyMap = new HashMap<String,String>();
		for(Classify classify : classifyList){
			classifyMap.put(classify.get_id(), classify.getClassifyName());
		}
		Pagination pagination = new Pagination();
		pagination.setNumPerPage(6);
		Blog queryBlog = new Blog();
		queryBlog.setClassifyId(blog.getClassifyId());
		request.setAttribute("nextBlog", blogService.getNextOrPreviousBlog(blog, "next"));
		request.setAttribute("previous", blogService.getNextOrPreviousBlog(blog, "previous"));
		request.setAttribute("relateBlog", blogService.getBlogs(pagination, queryBlog, "createTime"));
		request.setAttribute("classifyList", classifyList);
		request.setAttribute("classifyMap", classifyMap);
		request.setAttribute("blog", blog);
		return "blog/portal/blogShow";
	}
	
	@RequestMapping(value = "/modBlog.do")
	@ResponseBody
	public Object updateBlog(HttpServletRequest request , Blog blog,String _id) throws Exception{
		try {
			blog.set_id(_id);
			blogService.updateBlog(blog);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "博客修改成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("博客修改失败！\n\r" + ex.getMessage(), ex);
		}
	}
	
	@RequestMapping(value = "/updateBlogStatus.do")
	@ResponseBody
	public Object updateBlogStatus(HttpServletRequest request , String _id , String status) throws Exception{
		try {
			blogService.updateBlog(_id,status);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "修改成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("修改失败！\n\r" + ex.getMessage(), ex);
		}
	}
	@RequestMapping(value = "/404.htm")
	public String return404(HttpServletRequest request , String _id) throws Exception {
		return "blog/common/404";
	}
}
