package com.dsw.iot.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.text.MessageFormat;

/**
 * 文件操作工具类
 *
 * @author 张代浩
 */
public class FileUtils {
    private static final Logger logger = Logger.getLogger(FileUtils.class);
    private static final String SEPARATOR = System.getProperty("file.separator");

    /**
     * 获取文件扩展名
     */
    public static String getExtend(String filename) {
        return getExtend(filename, "");
    }

    /**
     * 获取文件扩展名
     */
    public static String getExtend(String filename, String defExt) {
        if ((filename != null) && (filename.length() > 0)) {
            int i = filename.lastIndexOf('.');

            if ((i > 0) && (i < (filename.length() - 1))) {
                return (filename.substring(i + 1)).toLowerCase();
            }
        }
        return defExt.toLowerCase();
    }

    /**
     * 获取文件名称[不含后缀名]
     *
     * @return String
     */
    public static String getFilePrefix(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex).replaceAll("\\s*", "");
    }

    /**
     * 获取文件名称[含后缀名]
     *
     * @param url
     * @return
     */
    public static String getFileName(String url) {
        int splitIndex = url.lastIndexOf("/");
        return url.substring(splitIndex + 1);
    }

    /**
     * 获取文件名称[不含后缀名] 不去掉文件目录的空格
     *
     * @return String
     */
    public static String getFilePrefix2(String fileName) {
        int splitIndex = fileName.lastIndexOf(".");
        return fileName.substring(0, splitIndex);
    }

    /**
     * 文件复制 方法摘要：这里一句话描述方法的用途
     *
     * @return void
     */
    public static void copyFile(String inputFile, String outputFile) throws FileNotFoundException {
        File sFile = new File(inputFile);
        File tFile = new File(outputFile);
        FileInputStream fis = new FileInputStream(sFile);
        FileOutputStream fos = new FileOutputStream(tFile);
        int temp = 0;
        byte[] buf = new byte[10240];
        try {
            while ((temp = fis.read(buf)) != -1) {
                fos.write(buf, 0, temp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除指定文件
     *
     * @param filePath
     */
    public static void deleteSpecificFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        file.delete();
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path 文件夹完整绝对路径
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + System.getProperty("file.separator") + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + System.getProperty("file.separator") + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹完整绝对路径
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            File folder = new File(folderPath);
            folder.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获得指定文件的byte数组
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    /**
     * 读文件
     *
     * @param filePath
     * @return
     */
    public static String read(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            logger.error(MessageFormat.format("[FileUtil]: File:{0} not exists!", filePath));
        }
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Throwable e) {
            logger.error(MessageFormat.format("[FileUtil]: File:{0} read error!", filePath));
        } finally {
            if (reader != null) {
                try {
                    //关闭文件
                    reader.close();
                } catch (Throwable e) {
                    logger.error(MessageFormat.format("[FileUtil]: File:{0} close error!", filePath));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 判断文件夹是否存在  不存在新建
     *
     * @param dirPath
     */
    public static void createDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }
}
