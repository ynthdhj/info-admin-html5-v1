package com.info.util.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */

@Setter
@Getter
public class UploadHelper {

	private MultipartFile file; // 从页面上提交的文件
	private String modelName; // 模块名，用于上传文件的目录分类
	private String type = "images"; // 文件类型 为 images 或 files

	public UploadHelper(MultipartFile file, String modelName) {
		this.file = file;
		this.modelName = modelName;
	}

	public UploadHelper(MultipartFile file, String modelName, String type) {
		this.file = file;
		this.modelName = modelName;
		this.type = type;
	}

	public String execute() {
		// 上传文件，上传的资源文件路径，路径在部署jar包同级目录
		Date currentDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatter.format(currentDate);

		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String path = "/static/upload/" + modelName + "/" + type + "/" + formattedDate + "/" + uuid + "/"
				+ file.getOriginalFilename();
		try {
			String destFilename = System.getProperty("user.dir") + path;
			File destFile = new File(destFilename);
			destFile.getParentFile().mkdirs();
			// System.out.println(destFile);
			file.transferTo(destFile);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return path;

		//
	}
}
