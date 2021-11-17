package com.bytehonor.sdk.lang.bytehonor.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.bytehonor.exception.BytehonorLangException;

public class FileReadWriteUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileReadWriteUtils.class);

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String read(String filePath) {
        File file = new File(filePath);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tmp = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tmp = reader.readLine()) != null) {
                sb.append(tmp);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }

    public static void write(String filePath, String content) {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            raf.write(content.getBytes());// 写入txt文件
        } catch (Exception e) {
            LOG.error("write error, filePath:{}", filePath);
            throw new BytehonorLangException("write error", e);
        } finally {
            if (raf != null) {
                try {
                    raf.close();
                } catch (Exception e) {
                    LOG.error("raf.close error", e);
                }
            }
        }
    }
}
