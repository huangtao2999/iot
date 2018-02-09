package com.dsw.iot.controller.rpc;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dsw.iot.model.AttachDo;
import com.dsw.iot.service.FileUploadService;


/**
 * 文件上传rpc
 *
 * @author huangt
 * @create 2018-02-03 9:43
 **/
@RestController
@RequestMapping("/FileUpload")
public class FileUploadRpc {
    @Autowired
    private FileUploadService fileUploadService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public AttachDo upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        return fileUploadService.upload(file);
    }

	@RequestMapping("updateAttach")
	public AttachDo updateAttach(String ids, String taskId, String taskBelong) {
		fileUploadService.updateAttach(ids, taskId, taskBelong);
		return null;
	}

	@RequestMapping("showImg")
	public void showImg(HttpServletRequest request, HttpServletResponse response,String path)
			throws IOException {
		fileUploadService.showImg(response, path);
	}

	@RequestMapping("deleteAttachFalse")
	public void deleteAttachFalse(String ids) {
		fileUploadService.deleteAttachFalse(ids);
	}
}
