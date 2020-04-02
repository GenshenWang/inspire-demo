package com.wgs.codedesign.组合模式.fileTree_v2;


/**
 * @author: wanggenshen
 * @date: 2020/4/2 13:31.
 * @description: 组合模式使用
 */
public class Main2 {

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
        DirectoryNode parentNode = new DirectoryNode("/Users/wanggenshen/code/node0");
        // 子目录
        DirectoryNode childNode1 = new DirectoryNode("/Users/wanggenshen/code/node0/node1");
        DirectoryNode childNode2 = new DirectoryNode("/Users/wanggenshen/code/node0/node2");
        DirectoryNode childNode3 = new DirectoryNode("/Users/wanggenshen/code/node0/node3");
        parentNode.addSubNode(childNode1);
        parentNode.addSubNode(childNode2);
        parentNode.addSubNode(childNode3);
        // 子目录下的文件
        FileNode file1 = new FileNode("/Users/wanggenshen/code/node0/node1/file1");
        FileNode file2 = new FileNode("/Users/wanggenshen/code/node0/node1/file2");
        childNode1.addSubNode(file1);
        childNode1.addSubNode(file2);
        FileNode file3 = new FileNode("/Users/wanggenshen/code/node0/node2/file3");
        childNode2.addSubNode(file3);
        FileNode file4 = new FileNode("/Users/wanggenshen/code/node0/node3/file4");
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
