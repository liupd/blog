package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.blog.domain.Comment;
import org.blog.domain.Pagination;
import org.blog.service.BlogService;
import org.blog.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentController {
	@Resource
	private CommentService commentService;
	@Resource
	private BlogService blogService;
	
	@RequestMapping(value = "/addComment.do")
	@ResponseBody
	public Object addComment(HttpServletRequest request , Comment comment) throws Exception{
		try {
			commentService.insertComment(comment);
			blogService.updateCommentCount(comment.getBlogId(), "add");
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "评论发表成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("评论发表失败！\n\r" + ex.getMessage(), ex);
		}
	}
	@RequestMapping(value = "/commentList.htm")
	public String commentList(HttpServletRequest request,
			Pagination pagination) throws Exception {
		List<Comment> commentList = commentService.getComments(pagination,new Comment());
		request.setAttribute("commentList", commentList);
		request.setAttribute("pagination", pagination);
		return "blog/comment/commentList";
	}
	
	@RequestMapping(value="/deleteComment.do")
	@ResponseBody
	public Object deleteComment(HttpServletRequest request, String _ids) throws Exception{
		try {
			String[] idsArr = _ids.split(",");
			for(String _id : idsArr){
				Comment comment = commentService.selectComment(_id);
				commentService.deleteComment(_id);
				blogService.updateCommentCount(comment.getBlogId(), "delete");
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "评论删除成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("评论删除失败！\n\r" + ex.getMessage(), ex);
		}
	}
}
