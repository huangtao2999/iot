package com.dsw.iot.service;

import java.io.IOException;
import java.util.List;

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

	/**
	 * 上传文件（单个）
	 *
	 * @param file
	 * @return
	 */
    public AttachDo upload(MultipartFile file);

	/**
	 * 根据id更新taskID和taskBelong，自动解绑原先的附件信息
	 *
	 * @param ids
	 *            附件记录id
	 * @param taskId
	 *            业务id
	 * @param taskBelong
	 *            所属业务
	 * @param remark
	 *            备注
	 */
	public void updateAttach(String ids, Long taskId, String taskBelong, String remark);

	/**
	 * 获得附件（显示图片或下载）
	 *
	 * @param response
	 * @param path
	 * @throws IOException
	 */
	public void getAttach(HttpServletResponse response, String id)
			throws IOException;

	/**
	 * 删除
	 *
	 * @param ids
	 */
	public void deleteAttach(String ids);

	/**
	 * 接收base64编码，保存附件同文件上传
	 *
	 * @param base64
	 * @param fileName
	 * @return
	 */
	public AttachDo uploadBase64(String base64, String fileName);

	/**
	 * 获得附件列表返回给前台，编辑和查看页面用
	 *
	 * @param taskId
	 * @param taskBelong
	 * @return
	 */
	public List<AttachDo> listAttach(Long taskId, String taskBelong);

	/**
	 * 获得该业务下所有附件的id，逗号拼接
	 *
	 * @param registerId
	 * @param string
	 * @return
	 */
	public String getAttachIds(Long taskId, String taskBelong);

	/**
	 * 上传
	 *
	 * @param attachDo
	 * @param bytes
	 * @param fileName
	 */
	public AttachDo uploadFaceImg(String path);
}
