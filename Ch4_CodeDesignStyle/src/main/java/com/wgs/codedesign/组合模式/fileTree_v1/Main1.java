package com.wgs.codedesign.组合模式.fileTree_v1;

/**
 * @author: wanggenshen
 * @date: 2020/4/2 13:31.
 * @description: XXX
 */
public class Main1 {

    /**   Demo文件目录结构如下
     *                      node0(目录)
     *                     /      |    \
     *       目录:      node1    node2  node3
     *                  /  |       |     |
     *     文件:    file1  file2  file3  file4
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {
        // 根目录
        FileSystemNode parentNode = new FileSystemNode("/Users/wanggenshen/code/node0", false);
        // 子目录
        FileSystemNode childNode1 = new FileSystemNode("/Users/wanggenshen/code/node0/node1", false);
        FileSystemNode childNode2 = new FileSystemNode("/Users/wanggenshen/code/node0/node2", false);
        FileSystemNode childNode3 = new FileSystemNode("/Users/wanggenshen/code/node0/node3", false);
        parentNode.addSubNode(childNode1);
        parentNode.addSubNode(childNode2);
        parentNode.addSubNode(childNode3);
        // 子目录下的文件
        FileSystemNode file1 = new FileSystemNode("/Users/wanggenshen/code/node0/node1/file1", true);
        FileSystemNode file2 = new FileSystemNode("/Users/wanggenshen/code/node0/node1/file2", true);
        childNode1.addSubNode(file1);
        childNode1.addSubNode(file2);
        FileSystemNode file3 = new FileSystemNode("/Users/wanggenshen/code/node0/node2/file3", true);
        childNode2.addSubNode(file3);
        FileSystemNode file4 = new FileSystemNode("/Users/wanggenshen/code/node0/node3/file4", true);
        childNode3.addSubNode(file4);

        // 验证
        System.out.println(parentNode.countNumOfFiles() == 4);
        System.out.println(childNode1.countNumOfFiles() == 2);
        System.out.println(childNode2.countNumOfFiles() == 1);
        System.out.println(childNode3.countNumOfFiles() == 1);

        System.out.println("根目录下所有文件大小:" + parentNode.countSizeOfFiles());
        System.out.println("子目录1下所有文件大小:" + childNode1.countSizeOfFiles());
        System.out.println("子目录2下所有文件大小:" + childNode2.countSizeOfFiles());
        System.out.println("子目录3下所有文件大小:" + childNode3.countSizeOfFiles());
        long sizeOfChildNodeFiles = childNode1.countSizeOfFiles() + childNode2.countSizeOfFiles()
                + childNode3.countSizeOfFiles();
        System.out.println(parentNode.countSizeOfFiles() == sizeOfChildNodeFiles);



    }
}
