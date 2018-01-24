package com.dsw.iot.util;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 网络文件操作类 只适用 物联网系统 服务器就是本地
 */
@Service
public class FileUpload {
    private static final Logger logger = Logger.getLogger(FileUpload.class);
    @Value("${bl.file.url}")
    private String FILE_URL;
    @Value("${bl.file.root.path}")
    private String FILE_ROOT_PATH;
    @Value("${bl.file.dir}")
    private String FILE_DIR;
    //系统目录分隔符
    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * 把内容content写的path文件中
     */
    public String saveRemote(String content, String fileName) {
        String today = DateUtil.getToday();
        String path = FILE_ROOT_PATH + SEPARATOR + FILE_DIR + SEPARATOR + today;
        String fileAddress = FILE_URL + "/" + FILE_DIR + "/" + today + "/" + fileName;
        BufferedWriter bw = null;
        try {
            File dir = new File(path);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path + System.getProperty("file.separator") + fileName, true), "GBK"));
            if (content != null) {
                bw.write(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.flush();
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return fileAddress;
    }

    /**
     * 根据byte数组，生成文件
     */
    public String saveRemote(byte[] bfile, String fileName) {
        String today = DateUtil.getToday();
        String path = FILE_ROOT_PATH + SEPARATOR + FILE_DIR + SEPARATOR + today;
        String fileAddress = FILE_URL + "/" + FILE_DIR + "/" + today + "/" + fileName;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(path);
            if (!dir.exists()) {//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(path + SEPARATOR + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return fileAddress;
    }

    private String getLocalPath(String remotePath) {
        return remotePath.replaceAll(FILE_URL, FILE_ROOT_PATH);
    }

    /**
     * pdf转jpg，一个pdf可能对应多个jpg
     */
    public List<String> pdfToImage(String remotePdfPath) {
        List<String> filePaths = new ArrayList<>();
        //把网络文件地址 改成 本地文件
        String localPdfPath = getLocalPath(remotePdfPath);
        String remotePath = remotePdfPath.substring(0, remotePdfPath.lastIndexOf("/"));
        String localPath = localPdfPath.substring(0, localPdfPath.lastIndexOf("/"));
        String name = remotePdfPath.substring(remotePdfPath.lastIndexOf("/") + 1, remotePdfPath.lastIndexOf("."));
        File file = new File(localPdfPath);
        PDDocument doc = null;
        try {
            doc = PDDocument.load(file);
            PDFRenderer renderer = new PDFRenderer(doc);
            int pageCount = doc.getNumberOfPages();

            for (int i = 0; i < pageCount; i++) {
                BufferedImage image = renderer.renderImage(i, 2.5f);
                String imgFileName = name + "_" + i + ".jpg";
                ImageIO.write(image, "jpg", new File(localPath + SEPARATOR + imgFileName));
                filePaths.add(remotePath + "/" + imgFileName);
            }
        } catch (IOException e) {
            logger.error("PDF转JPG异常!", e);
        } finally {
            if (null != doc) {
                try {
                    doc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return filePaths;
    }
}
