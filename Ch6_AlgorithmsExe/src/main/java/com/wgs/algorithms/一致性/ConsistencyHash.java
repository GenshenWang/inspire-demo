package com.wgs.algorithms.一致性;

import lombok.Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 分布式一致性哈希算法示例
 */
public class ConsistencyHash {

    /**
     * 哈希环虚拟节点个数
     */
    private final int CONSISTENT_HASH_NODES;
    private final TreeMap<Long, Node> virtualNodes;
    private static ThreadLocal<MessageDigest> MdThreadLocal = new ThreadLocal<>();


    public ConsistencyHash(int num, List<Node> nodes) {
        this.virtualNodes = new TreeMap<>();
        this.CONSISTENT_HASH_NODES = num;

        for (Node node : nodes) {
            // 对node节点多次哈希
            for (int i = 0; i < CONSISTENT_HASH_NODES / 4; i++) {
                String address = node.getAddress();
                byte[] md5 = getMD5(address + i);
                for (int j = 0; j < 4; j++) {
                    long hash = hash(md5, j);
                    virtualNodes.put(hash, node);
                }
            }
        }

        System.out.println("虚拟节点生成完毕，virtualNodes=" + virtualNodes.toString());
    }

    public Node select(String key) {
        // 哈希
        byte[] digest = getMD5(key);
        long hash = hash(digest, 0);

        System.out.println("key=" + key + ", hash=" + hash + ", 现有环是否存在该hash:" + (virtualNodes.containsKey(hash) ? "true" : "false")
                + ", cellingEntry=" + virtualNodes.ceilingEntry(hash));

        // 计算近似值，得到节点
        Map.Entry<Long, Node> ceilingEntry = virtualNodes.ceilingEntry(hash);
        if (ceilingEntry == null) {
            return virtualNodes.firstEntry().getValue();
        }
        return ceilingEntry.getValue();
    }

    private long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL;
    }


    private static byte[] getMD5(String str) {
        MessageDigest md = getMessageDigest();
        return md.digest(str.getBytes());
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest ret = MdThreadLocal.get();
        if (ret == null) {
            try {
                ret = MessageDigest.getInstance("MD5");
                MdThreadLocal.set(ret);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return ret;
    }

    @Data
    private static class Node {
        private String ip;
        private String address;
        private String hostName;

        public Node(String address) {
            this.address = address;
        }
    }


    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("192.168.0.1"));
        nodes.add(new Node("192.169.0.1"));
        nodes.add(new Node("192.178.0.1"));
        nodes.add(new Node("192.179.0.1"));
        nodes.add(new Node("192.198.0.1"));

        ConsistencyHash consistencyHash = new ConsistencyHash(20, nodes);

        Node select = consistencyHash.select("123455");
        Node select2 = consistencyHash.select("123456");
    }
}
