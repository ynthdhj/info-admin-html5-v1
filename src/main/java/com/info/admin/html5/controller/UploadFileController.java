package com.info.admin.html5.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.info.util.upload.json.JsonUploadHelper;

import jakarta.servlet.http.HttpServletResponse;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
@Controller
@RequestMapping("/admin")

public class UploadFileController {
	//返回 json
	@ResponseBody
	@PostMapping("/ckeditorUpload")
	public  Map<String, Object>  ckeditorUploadFile(@RequestParam("upload") MultipartFile upload,HttpServletResponse response) {
		JsonUploadHelper JsonUploadHelper = new JsonUploadHelper(upload,"public_ckeditor_image");
		Map<String, Object> map = JsonUploadHelper.executeJson();
		return map;
	}
	
	
	

}
