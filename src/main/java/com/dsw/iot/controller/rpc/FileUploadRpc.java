package com.dsw.iot.controller.rpc;

import com.dsw.iot.model.AttachDo;
import com.dsw.iot.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


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

    /**
     * 上传（所有文件）
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public AttachDo upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        return fileUploadService.upload(file);
    }

    /**
     * 上传录音文件（所有文件）
     * TODO;子配录音盒上传 比较特殊，上传的时候 就和业务绑定，前端不做预览和表单事件
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadAudio", method = RequestMethod.POST)
    public AttachDo uploadAudio(@RequestParam("filedata1") MultipartFile file, Long taskId) {
        AttachDo attachDo = fileUploadService.upload(file);
        if (null != attachDo) {
            fileUploadService.updateAttach(String.valueOf(attachDo.getId()), taskId, "PHONE_RECORD", "录音文件");
        }
        return attachDo;
    }

    /**
     * 更新附件信息
     *
     * @param ids
     * @param taskId
     * @param taskBelong
     * @param remark
     * @return
     */
    @RequestMapping("/updateAttach")
    public AttachDo updateAttach(String ids, Long taskId, String taskBelong, String remark) {
        fileUploadService.updateAttach(ids, taskId, taskBelong, remark);
        return null;
    }

    /**
     * 获得附件（显示图片或下载）
     *
     * @param request
     * @param response
     * @param path
     * @throws IOException
     */
    @RequestMapping("/getAttach")
    public void getAttach(HttpServletResponse response, String id)
            throws IOException {
        fileUploadService.getAttach(response, id);
    }

    /**
     * 删除（改变is_deleted状态）
     *
     * @param ids
     */
    @RequestMapping("/deleteAttachFalse")
    public void deleteAttachFalse(String ids) {
        fileUploadService.deleteAttach(ids);
    }

    /**
     * 接收base64编码，保存附件同文件上传
     *
     * @param base64
     * @return
     */

    @RequestMapping("/uploadBase64")
    public AttachDo uploadBase64(String base64, String fileName) {
        return fileUploadService.uploadBase64(base64, fileName);
    }


    /**
     * 获得附件列表返回给前台，编辑和查看页面用
     *
     * @param taskId
     * @param taskBelong
     * @return
     */
    @RequestMapping("/listAttach")
    public List<AttachDo> listAttach(Long taskId, String taskBelong) {
        List<AttachDo> resultList = fileUploadService.listAttach(taskId, taskBelong);
        return resultList;
    }

}
