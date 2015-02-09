package org.blog.controller;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.blog.dao.MongoDao;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.gridfs.GridFSDBFile;

@Controller
public class UploadController {
	@Resource
	private MongoDao mongoDao;
	
	@RequestMapping(value = "/upload.htm")
	public String upload() throws Exception{
		return "blog/common/upload";
	}
	
	@RequestMapping(value = "/upload.do")
	@ResponseBody
	public Object doUpload(HttpServletRequest request) throws Exception{
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<?> files = null;
		try {
			files = upload.parseRequest(request);
			Iterator<?> iter = files.iterator();
			while (iter.hasNext()) {
				FileItem item = (FileItem)iter.next();
				if(!item.isFormField()){
					String lastpath = item.getName();//获取上传文件的名称
					lastpath = lastpath.substring(lastpath.lastIndexOf("."));
					String filename = UUID.randomUUID().toString().replace("-", "") ;
					mongoDao.saveFile(filename, item.get(), lastpath);
					return filename;
				}
			}
		} catch (FileUploadException e) {// 处理文件尺寸过大异常
			return "上传失败";
		}
		return "上传失败";
	}
	
	@RequestMapping(value="/download.do")
	@ResponseBody
	public Object resourceDownload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileId = request.getParameter("fileId");
		if (fileId == null) {
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("result", "100201");
			result.put("resultDesc", "fileId is null");
			return result;
		}
		GridFSDBFile gridFile = mongoDao.getFile(fileId);
		
		if(gridFile == null){
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("result", "100201");
			result.put("resultDesc", "file is not exists");
			return result;
		}
		OutputStream out = response.getOutputStream();
		gridFile.writeTo(out);
		out.flush();
		out.close();
		return null;
	}
}
