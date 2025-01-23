package com.pancm.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;


/**
 * @Author pancm
 * @Description
 * @Date  2019/2/17
 * @Param
 * @return
 **/
public class FileHelper {

    private final static Logger log = LoggerFactory.getLogger(FileHelper.class);

    /**
     * 行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param folder   文件目录
     * @param fileName 文件名
     * @return 文件内容，字符串格式
     */
    public static String readFileToString(String folder, String fileName) {
        return readFileToString(folder + "/" + fileName);
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     *
     * @param filePath 文件路径
     * @return 文件内容，字符串
     */
    public static String readFileToString(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuilder builder = new StringBuilder();
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                builder.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("file error#" + filePath, e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return builder.toString();
    }

    /**
     * 读取文件，处理成 JSON格式
     *
     * @param filePath 文件绝对路径
     * @return json格式
     */
    public static Object readFileToJson(String filePath) {
        String content = FileHelper.readFileToString(filePath);
        return JSONObject.parse(content);
    }

    /**
     * 相对路径转换为绝对路径
     *
     * @param folderInput 相对路径，如./
     * @return 文件目录的绝对路径
     */
    public static String convertFolder(String folderInput) {
        String folder = folderInput;
        if (folderInput.startsWith("./")) {
            String relativeFolder = folderInput.replace("./", "");
            String dir = System.getProperty("user.dir");
            folder = dir + "/" + relativeFolder;
        }
        return folder;
    }


    /**
     * @return boolean
     * @Author pancm
     * @Description 创建文件夹--多层
     * @Date 2021/2/24
     * @Param [dir]
     **/
    public static boolean createMultilayerFile(String dir) {
        try {
            File dirPath = new File(dir);
            if (!dirPath.exists()) {
                dirPath.mkdirs();
            }
        } catch (Exception e) {
            log.error("创建多层目录操作出错: " + e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * 文件本地存储
     *
     * @param file         上传文件格式
     * @param destFolder   目标目录
     * @param destFileName 目标文件名
     * @return
     */
    public static boolean saveFile(MultipartFile file, String destFolder, String destFileName) {
        File dest = new File(destFolder + "/" + destFileName);
//        if(!dest.getParentFile().exists()){
//            dest.getParentFile().mkdir();
//        }
        createMultilayerFile(destFolder);
        try {
            file.transferTo(dest);
            return true;
        } catch (Exception e) {
            log.error("file save error", e);
        }
        return false;
    }


    public static boolean saveFile(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            createMultilayerFile(filePath);
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            log.error("file save error", e);
            return false;
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }


    /**
     * 文件大小，单位 MB
     *
     * @param size 文件大小，单位byte
     * @return 文件大小，MB
     */
    public static long getSizeInMb(long size) {
        return size / 1024 / 1024;
    }

    /**
     * 文件大小，单位 GB
     *
     * @param size 文件大小，单位byte
     * @return 文件大小，GB
     */
    public static long getSizeInGb(long size) {
        return size / 1024 / 1024 / 1024;
    }

    /**
     * 获得文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名，dot 后面的，如xls
     */
    public static String getFileExtension(String fileName) {
        if (fileName == null) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index == -1) {
            return null;
        }
        return fileName.substring(index + 1);
    }

    /**
     * 删除文件
     *
     * @param folder   目录
     * @param fileName 文件名
     * @return 删除是否成功
     */
    public static boolean deleteFile(String folder, String fileName) {
        return deleteFile(folder + "/" + fileName);
    }

    /**
     * 删除文件
     *
     * @param filePath 文件的绝对路径
     * @return 删除是否成功
     */
    public static boolean deleteFile(String filePath) {
        if (filePath == null) {
            return false;
        }
        try {
            File f = new File(filePath);
            if (f.exists()) {
                return f.delete();
            }
        } catch (Exception ex) {
            log.error("delete file error#" + filePath, ex);
        }
        return false;
    }


    public static String readResourcesFile(String path) {
        StringBuilder sb = new StringBuilder();
        try (InputStream in = FileHelper.class.getClassLoader().getResourceAsStream(path)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String msg = null;
            while ((msg = reader.readLine()) != null) {
                sb.append(msg);
//                        .append("\n")
            }

        } catch (IOException e) {
            log.error("read file error#", e);
        }
        return sb.toString();
    }


    /**
    * @Author beixing
    * @Description  文件夹复制
    * @Date  2021/4/27
    * @Param
    * @return
    **/
    public static void copyFolder(String src, String des)  {
        //初始化文件复制
        File file1 = new File(src);
        //把文件里面内容放进数组
        File[] fs = file1.listFiles();
        //初始化文件粘贴
        File file2 = new File(des);
        //判断是否有这个文件有不管没有创建
        if (!file2.exists()) {
            file2.mkdirs();
        }
        //遍历文件及文件夹
        for (File f : fs) {
            if (f.isFile()) {
                ///调用文件拷贝的方法
                fileCopy(f.getPath(), des + File.separator + f.getName());
            } else if (f.isDirectory()) {
                //文件夹
                copyFolder(f.getPath(), des + File.separator + f.getName());
            }
        }

    }

    /**
     * 文件复制的具体方法
     */
    public static void fileCopy(String src, String des) {
        //io流固定格式
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
             BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des))) {
            int i = -1;//记录获取长度
            byte[] bt = new byte[2014];//缓冲区
            while ((i = bis.read(bt)) != -1) {
                bos.write(bt, 0, i);
            }
        } catch (IOException e) {
            log.error("read file error#", e);
        }
    }


    public static void main(String[] args) {
        String source = "D:\\video\\ceshi\\2021\\4\\26\\18\\34020000001310000001";
        String target = "D:\\video\\diagnosis\\2021\\4\\26\\18\\34020000001310000001";
//        copyFolder(source,target);

        String srcFile = "D:\\video\\ceshi\\2021\\5\\7\\10\\34020000001310000001\\34020000001310000001_20210507105454_00001.jpg";
        String desFile = "D:\\video\\34020000001310000001_20210507105454_测试.jpg";
        fileCopy(srcFile,desFile);

    }


}
