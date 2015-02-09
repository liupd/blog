package org.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.blog.domain.Classify;
import org.blog.domain.Pagination;
import org.blog.domain.Photo;
import org.blog.service.ClassifyService;
import org.blog.service.PhotoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PhotoController {
	@Resource
	PhotoService photoService;
	@Resource
	private ClassifyService classifyService;

	@RequestMapping(value = "/photoList.htm")
	public String photoManage(HttpServletRequest request,
			Pagination pagination, String classifyId) throws Exception {
		Photo photo = new Photo();
		if (classifyId != null && !classifyId.isEmpty())
			photo.setClassifyId(classifyId);
		List<Classify> classifyList = classifyService.getClassifys(null, null);
		Map<String, String> classifyMap = new HashMap<String, String>();
		for (Classify classify : classifyList) {
			classifyMap.put(classify.get_id(), classify.getClassifyName());
		}
		request.setAttribute("classifyMap", classifyMap);
		List<Photo> photoList = photoService.getPhotos(photo, pagination);
		request.setAttribute("photoList", photoList);
		request.setAttribute("classifyId", classifyId);
		request.setAttribute("pagination", pagination);
		return "blog/photo/photoList";
	}

	@RequestMapping(value = "/photoListForCheck.htm")
	public String photoListForCheck(HttpServletRequest request,
			Pagination pagination, String classifyId) throws Exception {
		Photo photo = new Photo();
		if (classifyId != null && !classifyId.isEmpty())
			photo.setClassifyId(classifyId);
		List<Classify> classifyList = classifyService.getClassifys(null, null);
		Map<String, String> classifyMap = new HashMap<String, String>();
		for (Classify classify : classifyList) {
			classifyMap.put(classify.get_id(), classify.getClassifyName());
		}
		request.setAttribute("classifyMap", classifyMap);
		List<Photo> photoList = photoService.getPhotos(photo, pagination);
		request.setAttribute("photoList", photoList);
		request.setAttribute("classifyId", classifyId);
		request.setAttribute("pagination", pagination);
		return "blog/photo/photoListForCheck";
	}
	
	@RequestMapping(value = "/updatePhoto.do")
	@ResponseBody
	public Object updatePhoto(HttpServletRequest request, Photo photo)
			throws Exception {
		try {
			photoService.updatePhotos(photo);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "修改成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("修改失败！\n\r" + ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/modPhoto.htm")
	public String modPhoto(HttpServletRequest request, String _id)
			throws Exception {
		Photo photo = photoService.selectOne(_id);
		request.setAttribute("photo", photo);
		return "blog/photo/modPhoto";
	}

	@RequestMapping(value = "/deletePhoto.do")
	@ResponseBody
	public Object deletePhoto(HttpServletRequest request, String _ids)
			throws Exception {
		try {
			String[] idsArr = _ids.split(",");
			for (String _id : idsArr) {
				photoService.deletePhoto(_id);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "删除成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("删除失败！\n\r" + ex.getMessage(), ex);
		}
	}

	@RequestMapping(value = "/addPhoto.htm")
	public String addPhoto() throws Exception {
		return "blog/photo/addPhoto";
	}

	@RequestMapping(value = "/createPhoto.do")
	@ResponseBody
	public Object createPhoto(HttpServletRequest request, Photo photo)
			throws Exception {
		try {
			String fileIds = photo.getFileId();
			String[] fileIdArr = fileIds.split(",");
			for (String fileId : fileIdArr) {
				if (fileId.isEmpty()) {
					continue;
				}
				Photo photoForInsert = new Photo();
				photoForInsert.setFileId(fileId);
				photoForInsert.setClassifyId(photo.getClassifyId());
				photoService.insertPhoto(photoForInsert);
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("success", new Boolean(true));
			result.put("message", "创建成功！");
			return result;
		} catch (Exception ex) {
			throw new Exception("创建失败！\n\r" + ex.getMessage(), ex);
		}
	}
}
