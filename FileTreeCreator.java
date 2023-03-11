package com.farplace.qingzhuo.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileTreeCreator {
//    public FileTreeNode createFileTree(IEnumerable<string> paths, string rootNodeName)
//    {
//        FileTreeNode rootNode = new FileTreeNode(rootNodeName);
//        foreach (string path in paths)
//        {
//            string[] ps = path.Split('\\');
//            var currentNode = rootNode;
//            for (int i = 1; i < ps.Length; i++)
//            {
//                string p = ps[i];
//                FileTreeNode fileTreeNode = currentNode.containChild(p);
//                if (fileTreeNode != null)
//                {
//                    currentNode = fileTreeNode;
//                    continue;
//                }
//
//                FileTreeNode newNode = new FileTreeNode(p);
//                currentNode.addChild(newNode);
//                currentNode = newNode;
//            }
//        }
//
//        return rootNode;
//    }

    public FileTreeNode createFileTree(String path, FileTreeNode rootNode) {
        String[] ps = path.split("/");
        var currentNode = rootNode;
        for (int i = 2; i < ps.length; i++) {
            String p = ps[i];
            if (p.length() == 0) continue;
            FileTreeNode fileTreeNode = currentNode.containChild(p);
            if (fileTreeNode != null) {
                currentNode = fileTreeNode;
                continue;
            }

            FileTreeNode newNode = new FileTreeNode(p);
            currentNode.addChild(newNode);
            currentNode = newNode;
        }

        currentNode.hasFile = true;
        return rootNode;
    }

    public FileTreeNode getFolderNode(FileTreeNode rootNode, String folderPath) {
        String[] ps = folderPath.split("/");
        int length = ps.length;
        var currentNode = rootNode;
        for (int i = 2; i < length; i++) {
            String p = ps[i];
            var tmpNode = currentNode.containChild(p);
            if (tmpNode == null) {
                return null;
            } else {
                currentNode = tmpNode;
            }
        }

        return currentNode;
    }


    public List<String> getFolderNodeChilds(FileTreeNode rootNode, String folderPath) {
        String[] ps = folderPath.split("/");
        int length = ps.length;
        var currentNode = rootNode;
        for (int i = 2; i < length; i++) {
            String p = ps[i];
            var tmpNode = currentNode.containChild(p);
            if (tmpNode == null) {
                return null;
            } else {
                currentNode = tmpNode;
            }
        }

        return getAllChilds(currentNode, folderPath);
    }

    private List<String> getAllChilds(FileTreeNode fileTreeNode, String parentPath) {
        List<String> childs = new ArrayList<>();
        if (fileTreeNode.hasFile) {
            childs.add(parentPath);
        }

        List<FileTreeNode> fileTreeNodes = new ArrayList<>(fileTreeNode.childNodes.values());
        for (FileTreeNode childNode : fileTreeNodes) {
            String path = parentPath + "/" + childNode.nodeName;
            childs.addAll(getAllChilds(childNode, path));
        }
        return childs;
    }

}
