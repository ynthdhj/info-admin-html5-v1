package com.info.util.upload.json;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.info.util.upload.UploadHelper;

/*
 *  @作者 段红杰
 *  QQ: 740836
 *  说明：可以任意修改、可以商用，但是需要保留此信息
 */
public class JsonUploadHelper extends UploadHelper {

	public JsonUploadHelper(MultipartFile file, String modelName) {
		super(file, modelName);
	}

	public JsonUploadHelper(MultipartFile file, String modelName, String type) {
		super(file, modelName, type);
	}

	public Map<String, Object>  executeJson() {
		Map<String, Object> map = new HashMap<>();
		if (isAllow(super.getFile().getOriginalFilename())) {
			String path = super.execute();
			if (path == null) {
				map.put("uploaded", 0);
				map.put("fileName", "");
				map.put("url", "");
			} else {
				map.put("uploaded", 1);
				map.put("fileName", super.getFile().getOriginalFilename());
				map.put("url", path);
			}
		} else {
			map.put("uploaded", 0);
			map.put("fileName", "");
			map.put("url", "");
		}
		return map;
	}

	private boolean isAllow(String fileName) {
		String[] allowFiles = { ".gif", ".png", ".jpg", ".jpeg", ".bpm", ".svg" };
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		List<String> suffixList = Arrays.stream(allowFiles).collect(Collectors.toList());
		return suffixList.contains(suffix);
	}

}
