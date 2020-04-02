package com.wgs.codedesign.组合模式.fileTree_v1;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 10:51.
 * @description: 组合模式处理树形结构数据
 */
public class FileSystemNode {

    private String path;
    private boolean isFile;
    private List<FileSystemNode> subNodes = new ArrayList<>();

    public FileSystemNode(String path, boolean isFile) {
        this.path = path;
        this.isFile = isFile;
    }

    public String getPath() {
        return path;
    }

    /**
     * 查询目录下文件大小
     *
     * @return
     */
    public int countNumOfFiles() {
        // 是文件
        if (isFile) {
            return 1;
        }

        // 递归遍历目录下的文件数
        int count = 0;
        for (FileSystemNode node : subNodes) {
            count += node.countNumOfFiles();
        }
        return count;
    }

    /**
     * 查询目录下文件大小
     *
     * @return
     */
    public long countSizeOfFiles() {
        if (isFile) {
            File file = new File(path);
            if (!file.exists()) {
                return 0;
            }
            return file.length();
        }

        long sizeOfFiles = 0;
        for (FileSystemNode node : subNodes) {
            sizeOfFiles += node.countSizeOfFiles();
        }
        return sizeOfFiles;
    }

   public void addSubNode(FileSystemNode fileOrDir) {
       subNodes.add(fileOrDir);
   }

    public void removeSubNode(FileSystemNode fileOrDir) {

        Iterator<FileSystemNode> iterator = subNodes.iterator();
        while (iterator.hasNext()) {
            FileSystemNode fileSystemNode = iterator.next();
            if (fileSystemNode != null) {
                if (fileSystemNode.getPath().equalsIgnoreCase(fileOrDir.getPath())) {
                   iterator.remove();
                    break;
                }
            }
        }
    }




    
}
