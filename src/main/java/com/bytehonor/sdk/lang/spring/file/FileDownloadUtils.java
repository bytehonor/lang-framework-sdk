package com.bytehonor.sdk.lang.spring.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.util.StringObject;

/**
 * @author lijianqiang
 *
 */
public class FileDownloadUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileDownloadUtils.class);

    /**
     * 获得指定文件的byte数组
     * 
     * @param filePath 文件绝对路径
     * @return
     */
    public static byte[] file2Byte(String filePath) {
        ByteArrayOutputStream bos = null;
        BufferedInputStream in = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                throw new FileNotFoundException("file not exists");
            }
            bos = new ByteArrayOutputStream((int) file.length());
            in = new BufferedInputStream(new FileInputStream(file));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (Exception e) {
            LOG.error("file2Byte error", e);
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (bos != null) {
                    bos.close();
                }
            } catch (Exception e) {
                LOG.error("finally", e);
            }
        }
    }

    /**
     * 根据byte数组，生成文件
     * 
     * @param bfile    文件数组
     * @param fileDir  文件存放路径
     * @param fileName 文件名称
     */
    public static File byte2File(byte[] bfile, String fileDir, String fileName) {
        if (StringObject.isEmpty(fileDir) || StringObject.isEmpty(fileName)) {
            throw new RuntimeException("byte2File param is invalid");
        }
        FileReadWriteUtils.isExistDir(fileDir);// 判断文件目录是否存在
        String filePath = FileReadWriteUtils.connectPath(fileDir, fileName);
        File file = new File(filePath);
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            LOG.error("byte2File error", e);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (Exception e) {
                LOG.error("byte2File finally error", e);
            }
        }
        return file;
    }


    public static File download(String fileUrl, String fileDir, String fileName) {
        if (StringObject.isEmpty(fileUrl) || StringObject.isEmpty(fileDir) || StringObject.isEmpty(fileName)) {
            throw new RuntimeException("download file param is invalid");
        }
        FileReadWriteUtils.isExistDir(fileDir);
        LOG.debug("download fileDir:{}, fileName:{}", fileDir, fileName);
        String filePath = FileReadWriteUtils.connectPath(fileDir, fileName);
        File file = new File(filePath);
        // 获取连接
        InputStream in = null;
        OutputStream out = null;
        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(8 * 1000);
            // 设置请求头
            // connection.setRequestProperty("User-Agent", "Mozilla/5.0");
            // 获取输入流
            in = connection.getInputStream();
            out = new FileOutputStream(file);

            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = in.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
        } catch (Exception e) {
            LOG.error("byte2File error", e);
            throw new RuntimeException("下载文件失败");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                LOG.error("byte2File finally error", e);
            }
        }

        return file;
    }
}
