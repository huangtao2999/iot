package com.dsw.iot.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.dsw.iot.constant.CommConfig;
import com.dsw.iot.dal.AttachDoMapperExt;
import com.dsw.iot.model.AttachDo;
import com.dsw.iot.model.AttachDoExample;
import com.dsw.iot.service.CurrentUserService;
import com.dsw.iot.service.FileUploadService;
import com.dsw.iot.util.BASE64Util;
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
    protected static final Logger logger = Logger
            .getLogger(FileUploadServiceImpl.class);
    @Value("${file.upload.path}")
    private String uploadPath;
    @Autowired
    private AttachDoMapperExt attachDoMapperExt;
    @Autowired
    private CurrentUserService currentUserService;

    /**
     * 上传文件
     */
    @Override
    @Transactional
    public AttachDo upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();// 文件名（带后缀名）
        AttachDo attachDo = new AttachDo();
        try {
            saveFile(attachDo, file.getBytes(), fileName);
        } catch (IOException e) {
            logger.error("上传出错", e);
        }
        return attachDo;
    }

    /**
     * 根据id更新taskId和taskBelong
     */
    @Override
	@Transactional
    public void updateAttach(String ids, Long taskId, String taskBelong, String remark) {
        // 解绑附件
        List<AttachDo> list = listAttach(taskId, taskBelong);
        deleteAttach(list);
        // 新建绑定
        AttachDo record = new AttachDo();
        record.setTaskId(taskId);
        record.setTaskBelong(taskBelong);
        record.setStatus("1");// 状态至为已存
        if (StringUtils.isNotBlank(ids)) {
            String[] idArr = ids.split(",");
			// for (String id : idArr) {
			for (int i = 0; i < idArr.length; i++) {
				String id = idArr[i];
                if (!StringUtils.isEmpty(id)) {
					record.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForUpdate(record, currentUserService.getPvgInfo());
                    attachDoMapperExt.updateByPrimaryKeySelective(record);
                }
            }
        }
    }

    /**
     * 获得附件（显示图片或下载）
     */
    @Override
    public void getAttach(HttpServletResponse response, String id)
            throws IOException {
        if (StringUtils.isNotBlank(id)) {
            AttachDo attachDo = attachDoMapperExt.selectByPrimaryKey(Long.parseLong(id));
            String filePath = uploadPath + "/" + attachDo.getPath();
            byte[] bytes = FileUtils.getBytes(filePath);
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("text/html;charset=utf-8");
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
            os.close();
        }
    }

    /**
     * Y删除，同时把业务类型和业务id置空
     */
    @Override
	@Transactional
    public void deleteAttach(String ids) {
        if (!StringUtils.isEmpty(ids)) {
            String[] idArr = ids.split(",");
            for (String id : idArr) {
                if (!StringUtils.isEmpty(id)) {
                    AttachDo attachDo = new AttachDo();
                    attachDo.setId(Long.parseLong(id));
                    DomainUtil.setCommonValueForDelete(attachDo, currentUserService.getPvgInfo());
                    attachDoMapperExt.updateToUnuse(attachDo);
                    // AttachDo attachDo =
                    // attachDoMapperExt.selectByPrimaryKey(Long.parseLong(id));
                    // if (null != attachDo) {
                    // DomainUtil.setCommonValueForDelete(attachDo,
                    // currentUserService.getPvgInfo());
                    // attachDoMapperExt.deleteByPrimaryKey(attachDo); //
                    // 删除记录
                    // FileUtils.deleteSpecificFile(uploadPath + "/"
                    // + attachDo.getPath()); // 删除文件
                    // }
                }
            }
        }
    }

    /**
     * Y删除，同时把业务类型和业务id置空
     *
     * @param
     */
	@Transactional
    public void deleteAttach(List<AttachDo> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            for (AttachDo attachDo : list) {
                DomainUtil.setCommonValueForDelete(attachDo, currentUserService.getPvgInfo());
                attachDoMapperExt.updateToUnuse(attachDo);
            }
        }
    }

    /**
     * 接收base64编码，保存附件同文件上传
     */
    @Override
    public AttachDo uploadBase64(String base64, String fileName) {
        byte[] bytes = BASE64Util.base64ToByte(base64);
        AttachDo attachDo = new AttachDo();
        saveFile(attachDo, bytes, fileName);
        return attachDo;
    }

    /**
     * fileName文件名 例如 test.txt
     *
     * @param bytes
     * @param fileName
     * @return
     */
	private void saveFile(AttachDo attachDo, byte[] bytes, String fileName) {
        String today = DateUtil.getDate();// 获取当天日期(yyyy/MM/dd)
        String filePath = uploadPath + "/" + today + "/";// 文件路径
        String saveName = MD5Util.md5ForFile(bytes) + "."
                + FileUtils.getExtend(fileName);// 保存后文件名（带后缀）
        try {
            FileUtils.uploadFile(bytes, filePath, saveName);
            attachDo.setFileName(fileName);// 文件真实名称
            attachDo.setPath(today + "/" + saveName);
            attachDo.setSize((long) bytes.length / 1024); // 单位为KB
            attachDo.setStatus("0");// 状态为暂存
            attachDo.setIsDeleted(CommConfig.DELETED.NO.getName());
            DomainUtil.setCommonValueForCreate(attachDo,
                    currentUserService.getPvgInfo());
            attachDoMapperExt.insertSelective(attachDo);
        } catch (Exception e) {
            logger.error("上传出错", e);
        }
    }

    /**
     * 获得附件列表返回给前台，编辑和查看页面用
     */
    @Override
    public List<AttachDo> listAttach(Long taskId, String taskBelong) {
        List<AttachDo> resultList = new ArrayList<>();
        AttachDoExample example = new AttachDoExample();
        AttachDoExample.Criteria criteria = example.createCriteria();
        if (null != taskId && StringUtils.isNoneBlank(taskBelong)) {
            criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
            criteria.andTaskIdEqualTo(taskId);
            criteria.andTaskBelongEqualTo(taskBelong);
            resultList = attachDoMapperExt.selectByExample(example);
        }
        return resultList;
    }

    /**
     * 获得该业务下所有附件的id，逗号拼接
     */
    @Override
    public String getAttachIds(Long taskId, String taskBelong) {
        List<AttachDo> resultList = new ArrayList<>();
        AttachDoExample example = new AttachDoExample();
        AttachDoExample.Criteria criteria = example.createCriteria();
        // 查列表
        if (null != taskId && StringUtils.isNoneBlank(taskBelong)) {
            criteria.andIsDeletedEqualTo(CommConfig.DELETED.NO.getName());
            criteria.andTaskIdEqualTo(taskId);
            criteria.andTaskBelongEqualTo(taskBelong);
            resultList = attachDoMapperExt.selectByExample(example);
        }
        // 拼接ids
        String ids = "";
        if (CollectionUtils.isNotEmpty(resultList)) {
            for (AttachDo attachDo : resultList) {
                if (StringUtils.isBlank(ids)) {
                    ids = attachDo.getId() + "";
                } else {
                    ids += "," + attachDo.getId();
                }
            }
        }
        return ids;
    }

	/**
	 * 上传人脸照片
	 *
	 * @param attachDo
	 * @param bytes
	 * @param fileName
	 */
	@Override
	public AttachDo uploadFaceImg(String path) {
		File file = new File(path);
		AttachDo attachDo = new AttachDo();
		String fileName = "faceImg.jpg";
		if (!file.exists()) {
			logger.error("没找到文件");
			return null;
		}
		byte[] bytes = FileUtils.getBytes(path);
		String today = DateUtil.getDate();// 获取当天日期(yyyy/MM/dd)
		String filePath = uploadPath + "/" + today + "/";// 文件路径
		String saveName = MD5Util.md5ForFile(bytes) + "." + FileUtils.getExtend(fileName);// 保存后文件名（带后缀）
		try {
			FileUtils.uploadFile(bytes, filePath, saveName);
			attachDo.setFileName(fileName);// 文件真实名称
			attachDo.setPath(today + "/" + saveName);
			attachDo.setSize((long) bytes.length / 1024); // 单位为KB
			attachDo.setStatus("0");// 状态为暂存
			attachDo.setIsDeleted(CommConfig.DELETED.NO.getName());
			DomainUtil.setCommonValueForCreate(attachDo, currentUserService.getPvgInfo());
			attachDoMapperExt.insertSelective(attachDo);
			return attachDo;
		} catch (Exception e) {
			logger.error("上传出错", e);
			return null;
		}
	}

}
