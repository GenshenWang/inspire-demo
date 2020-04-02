package com.wgs.codedesign.组合模式.fileTree_v2;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 15:06.
 * @description: 目录模型
 */
public class DirectoryNode extends FileSystemNodeV2 {

    private List<FileSystemNodeV2> subNodes = new ArrayList<>();

    public DirectoryNode(String path) {
        super(path);
    }

    @Override
    public int countNumOfFiles() {
        if (CollectionUtils.isEmpty(subNodes)) {
            return 0;
        }
        int numOfFiles = 0;
        for (FileSystemNodeV2 node : subNodes) {
            numOfFiles += node.countNumOfFiles();
        }
        return numOfFiles;
    }

    @Override
    public long countSizeOfFiles() {
        if (CollectionUtils.isEmpty(subNodes)) {
            return 0;
        }
        long sizeOfFiles = 0;
        for (FileSystemNodeV2 node : subNodes) {
            sizeOfFiles += node.countSizeOfFiles();
        }
        return sizeOfFiles;
    }

    public void addSubNode(FileSystemNodeV2 node) {
        subNodes.add(node);
    }

    public void removeNode(FileSystemNodeV2 toRemoveNode) {
        Iterator<FileSystemNodeV2> iterator = subNodes.iterator();
        while (iterator.hasNext()) {
            FileSystemNodeV2 node = iterator.next();
            if (node != null) {
                if (node.getPath().equalsIgnoreCase(toRemoveNode.getPath())) {
                    iterator.remove();
                    break;
                }
            }
        }
    }
}
