package com.dsw.iot.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.AttachDoMapperExt;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.util.DateUtil;
import com.dsw.iot.util.DomainUtil;
import com.dsw.iot.util.FileUtils;
import com.dsw.iot.util.MD5Util;

/**
 * 文件上传服务类
 *
 * @author huangt
 * @create 2018-02-03 10:12
 **/
@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Value("${file.upload.path}")
    private String uploadPath;
	@Autowired
	private AttachDoMapperExt attachDoMapperExt;
	@Autowired
	private CurrentUserService currentUserService;

	// 上传文件
    @Override
	public AttachDo upload(MultipartFile file) {
        String contentType = file.getContentType();
		String fileName = file.getOriginalFilename();// 文件名（带后缀名）
		String today = DateUtil.getDate();
		String filePath = uploadPath + "/" + today + "/";// 文件路径
		AttachDo attachDo = new AttachDo();
        try {
			String saveName = MD5Util.md5ForFile(file.getBytes()) + "."
					+ FileUtils.getExtend(fileName);// 保存后文件名（带后缀）
			FileUtils.uploadFile(file.getBytes(), filePath, saveName);
			attachDo.setFileName(fileName);
			attachDo.setPath(today + "/" + saveName);
			attachDo.setSize(file.getSize() / 1024); // 单位为KB
			attachDo.setStatus("0");
			attachDo.setIsDeleted(CommConfig.DELETED.NO.getName());
			DomainUtil.setCommonValueForCreate(attachDo,
					currentUserService.getPvgInfo());
			attachDoMapperExt.insertSelective(attachDo);
        } catch (Exception e) {
            // TODO: handle exception
        }
        //返回json
		return attachDo;
    }

	// 根据id跟新taskId和taskBelong
	@Override
	public void updateAttach(String ids, String taskId, String taskBelong) {
		AttachDo record = new AttachDo();
		record.setTaskId((long) Integer.parseInt(taskId));
		record.setTaskBelong(taskBelong);
		if (!StringUtils.isEmpty(ids)) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					record.setId((long) Integer.parseInt(id));
					DomainUtil.setCommonValueForUpdate(record,
							currentUserService.getPvgInfo());
					attachDoMapperExt.updateByPrimaryKeySelective(record);
				}
			}
		}
	}

	// 查看图片
	@Override
	public void showImg(HttpServletResponse response, String path)
			throws IOException {
		String filePath = uploadPath + "/" + path;
		byte[] bytes = FileUtils.getBytes(filePath);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		OutputStream os = response.getOutputStream();
		os.write(bytes);
		os.flush();
		os.close();
	}

	// 删除（改变isDeleted状态为“Y”）
	@Override
	public void deleteAttachFalse(String ids) {
		AttachDo record = new AttachDo();
		if (!StringUtils.isEmpty(ids)) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					record.setId((long) Integer.parseInt(id));
					record.setIsDeleted(CommConfig.DELETED.YES.getName());
					DomainUtil.setCommonValueForUpdate(record,
							currentUserService.getPvgInfo());
					attachDoMapperExt.updateByPrimaryKeySelective(record);
				}
			}
		}
	}

	// 删除（真实删除）
	@Override
	public void deleteAttachTrue(String ids) {
		if (!StringUtils.isEmpty(ids)) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!StringUtils.isEmpty(id)) {
					AttachDo attachDo = attachDoMapperExt
							.selectByPrimaryKey((long) Integer.parseInt(id));
					if (null != attachDo) {
						attachDoMapperExt.deleteByPrimaryKey(attachDo.getId()); // 删除记录
						FileUtils.deleteSpecificFile(uploadPath + "/"
								+ attachDo.getPath()); // 删除文件
					}
				}
			}
		}
	}
}
