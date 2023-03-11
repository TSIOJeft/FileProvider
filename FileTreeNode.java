package com.farplace.qingzhuo.provider;

import java.util.HashMap;
import java.util.Map;

public class FileTreeNode {
    public String nodeName;
    private long nodeHash;
    public boolean hasFile;

    public FileTreeNode(String node) {
        nodeName = node;
        nodeHash = node.hashCode();
    }

    public Map<Long, FileTreeNode> childNodes = new HashMap<>();

    // public List<FileTreeNode> childNodes = new List<FileTreeNode>();
    public void addChild(FileTreeNode fileTreeNode) {
        if (!childNodes.containsKey(fileTreeNode.nodeHash)) {
            childNodes.put(fileTreeNode.nodeHash, fileTreeNode);
        }
    }

    public FileTreeNode containChild(FileTreeNode fileTreeNode) {
        if (childNodes.containsKey(fileTreeNode.nodeHash)) {
            return childNodes.get(fileTreeNode.nodeHash);
        }

        return null;
    }

    public FileTreeNode containChild(String nodeName) {
        long hash = nodeName.hashCode();
        if (childNodes.containsKey(hash)) {
            return childNodes.get(hash);
        }

        return null;
    }

    public boolean equal(String p) {
        return p.hashCode() == nodeHash;
    }

}
