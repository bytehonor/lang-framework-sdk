package com.bytehonor.sdk.lang.spring.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytehonor.sdk.lang.spring.exception.SpringLangException;
import com.bytehonor.sdk.lang.spring.string.SpringString;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 * 20211130
 * 
 * @author lijianqiang
 *
 */
public class FileHelper {

    private static final Logger LOG = LoggerFactory.getLogger(FileHelper.class);

    private static final String SPL = "/";

    private Cache<String, Boolean> cache;

    private FileHelper() {
        this.cache = CacheBuilder.newBuilder().initialCapacity(1024) // 设置初始容量为100
                .maximumSize(1024 * 1024) // 设置缓存的最大容量
                .expireAfterWrite(3, TimeUnit.DAYS) // 设置缓存在写入一分钟后失效
                .concurrencyLevel(20) // 设置并发级别为10
                .build(); // .recordStats() // 开启缓存统计
    }

    private static class LazyHolder {
        private static FileHelper SINGLE = new FileHelper();
    }

    public static FileHelper self() {
        return LazyHolder.SINGLE;
    }

    private static void put(String key, Boolean value) {
        Objects.requireNonNull(key, "key");
        Objects.requireNonNull(value, "value");
        self().cache.put(key, value);
    }

    private static Boolean getIfPresent(String key) {
        Objects.requireNonNull(key, "key");
        return self().cache.getIfPresent(key);
    }

    /**
     * <pre>
     * 读取目录下的文件 指定后缀的
     * 仅当前一级的非目录文件名
     * </pre>
     * 
     * @param dir
     * @param type
     * @return
     */
    public static List<String> readDir(String dir, String type) {
        Objects.requireNonNull(dir, "dir");

        List<String> list = new ArrayList<String>();
        if (SpringString.isEmpty(dir)) {
            LOG.error("filesFromDir dir null");
            return list;
        }
        File file = new File(dir);
        if (file == null || file.isDirectory() == false) {
            throw new SpringLangException("not dir:" + dir);
        }
        File[] files = file.listFiles();
        int length = files.length;
        if (length < 1) {
            return list;
        }
        boolean filter = SpringString.isEmpty(type) == false;
        for (int i = 0; i < length; i++) {
            if (files[i].isDirectory()) {
                continue;
            }
            if (filter && files[i].getPath().endsWith(type) == false) {
                continue;
            }
            list.add(files[i].getPath());
        }
        return list;
    }

    /**
     * <pre>
     * 读取目录下的文件
     * 仅当前一级的非目录文件名
     * </pre>
     * 
     * @param dir
     * @return
     */
    public static List<String> filesFromDir(String dir) {
        Objects.requireNonNull(dir, "dir");

        return readDir(dir, null);
    }

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
        } catch (Exception e) {
            LOG.error("read error", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    LOG.error("reader.close error", e1);
                }
            }
        }
        return sb.toString();
    }

    public static void write(String filePath, String content) {
        RandomAccessFile raf = null;
        try {
            pathExistOrMake(filePath);
            raf = new RandomAccessFile(filePath, "rw");
            raf.write(content.getBytes());// 写入txt文件
        } catch (Exception e) {
            LOG.error("write error, filePath:{}", filePath);
            throw new SpringLangException("write error", e);
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

    /**
     * 判断多级路径是否存在，不存在就创建
     * 
     * @param filePath 支持带文件名的Path：如：D:\news\2014\12\abc.text，和不带文件名的Path：如：D:\news\2014\12
     */
    public static void pathExistOrMake(String filePath) {
        Objects.requireNonNull(filePath, "filePath");

        String paths[] = { "" };
        // 切割路径
        try {
            String tempPath = new File(filePath).getCanonicalPath();// File对象转换为标准路径并进行切割，有两种windows和linux
            paths = tempPath.split("\\\\");// windows
            if (paths.length == 1) {
                paths = tempPath.split("/");
            } // linux
        } catch (IOException e) {
            LOG.error("切割路径错误, filePath:{}, error:{}", filePath, e.getMessage());
        }
        // 判断是否有后缀
        boolean hasType = false;
        if (paths.length > 0) {
            String tempPath = paths[paths.length - 1];
            if (tempPath.length() > 0) {
                if (tempPath.indexOf(".") > 0) {
                    hasType = true;
                }
            }
        }

        if (hasType == false) {
            if (getIfPresent(filePath) != null) {
                return;
            }
            put(filePath, true);
        }

        // 创建文件夹
        String dir = paths[0];
        int end = paths.length - (hasType ? 2 : 1);
        for (int i = 0; i < end; i++) {// 注意此处循环的长度，有后缀的就是文件路径，没有则文件夹路径
            try {
                dir = dir + "/" + paths[i + 1];// 采用linux下的标准写法进行拼接，由于windows可以识别这样的路径，所以这里采用警容的写法

                if (getIfPresent(dir) != null) {
                    continue;
                }
                put(dir, true);

                File dirFile = new File(dir);
                if (!dirFile.exists()) {
                    dirFile.mkdir();
                    LOG.info("成功创建目录：{}", dirFile.getCanonicalFile());
                }
            } catch (Exception e) {
                LOG.error("文件夹创建发生异常", e);
            }
        }
    }

    /**
     * 有/结尾
     * 
     * @param path1
     * @param path2
     * @return
     */
    public static String connectWithEnd(String path1, String path2) {
        Objects.requireNonNull(path1, "path1");
        String path = connect(path1, path2);
        if (path.endsWith(SPL) == false) {
            path += SPL;
        }
        return path;
    }

    /**
     * 简单连接
     * 
     * @param path1
     * @param path2
     * @return
     */
    public static String connect(String path1, String path2) {
        Objects.requireNonNull(path1, "path1");
        Objects.requireNonNull(path2, "path2");

        StringBuilder sb = new StringBuilder();
        sb.append(path1);
        if (path1.endsWith(SPL) == false) {
            sb.append(SPL);
        }
        if (path2.startsWith(SPL)) {
            path2 = path2.substring(1);
        }
        sb.append(path2);
        return sb.toString();
    }

    public static String subfixNoDot(String path) {
        String subfix = subfixWithDot(path);
        if (SpringString.isEmpty(subfix)) {
            return "";
        }

        return subfix.substring(1, subfix.length());
    }

    public static String subfixWithDot(String path) {
        Objects.requireNonNull(path, "path");
        int at = path.indexOf('?');
        if (at > 1) {
            path = path.substring(0, at);
        }
        at = path.lastIndexOf('.');
        if (at < 0) {
            return "";
        }
        return path.substring(at).toLowerCase();
    }
}
