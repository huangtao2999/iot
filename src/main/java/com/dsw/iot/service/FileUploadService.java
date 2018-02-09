package com.dsw.iot.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.dsw.iot.model.AttachDo;

/**
 * 文件上传服务类
 *
 * @author huangt
 * @create 2018-02-03 9:54
 **/
public interface FileUploadService {

	// 上传文件
    public AttachDo upload(MultipartFile file);

	// 根据id跟新taskID和taskBelong
	public void updateAttach(String ids, String taskId, String taskBelong);

	// 显示图片
	public void showImg(HttpServletResponse response, String path)
			throws IOException;

	// 删除（改变is_deleted状态）
	public void deleteAttachFalse(String ids);

	// 删除（真实删除）
	public void deleteAttachTrue(String ids);
}
