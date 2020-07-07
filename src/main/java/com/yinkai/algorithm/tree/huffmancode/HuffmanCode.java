package com.yinkai.algorithm.tree.huffmancode;

import java.util.*;

/**
 * 霍夫曼树编码
 */
public class HuffmanCode {
    public static void main(String[] args) {
        String str = "i like like like java do you like a java";
        // 压缩成霍夫曼树
        byte[] bytes = str.getBytes();
        List<Node> list = byteToList(bytes);
        Node root = HuffmanTree(list);
        Map<Byte, String> huffmanCodes = getCode(root);
        //解压霍夫曼树变成原来的字符串
        byte[] zip = zip(bytes, huffmanCodes);
        System.out.println(Arrays.toString(zip));
        byte[] decode = decode(huffmanCodes, zip);
        System.out.println(new String(decode));


    }

    /**
     *  转为霍夫曼编码
     * @param flag
     * @param b
     * @return
     */
    private static String byteToBitString(Boolean flag,Byte b){
        int temp = b;
        //补高位
        if (flag){
            temp |=256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag){
            //截取后面8位
            return str.substring(str.length()-8);
        }else {
            return str;
        }
    }

    /**
     * 霍夫曼编码转为原来的字符串
     * @param huffmanCodes
     * @param zip
     * @return
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes,byte[] zip){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<zip.length;i++){
            byte b = zip[i];
            Boolean flag = (i==(zip.length-1));
            stringBuilder.append(byteToBitString(!flag,b));
        }
        //
        Map<String, Byte> dcHuffmanCodes = new HashMap<>();
        for (Map.Entry<Byte,String> codes:huffmanCodes.entrySet()){
            dcHuffmanCodes.put(codes.getValue(),codes.getKey());
        }

        List<Byte> bytes = new ArrayList<>();
        for (int i=0;i<stringBuilder.length();){
            int count=1;
            while (true){
                if (dcHuffmanCodes.get(stringBuilder.substring(i,i+count))!=null){
                    break;
                }
                count++;
            }
            bytes.add(dcHuffmanCodes.get(stringBuilder.substring(i,i+count)));
            i = i+count;
        }
        byte[] bytes1 = new byte[bytes.size()];
        for (int i=0;i<bytes.size();i++){
            bytes1[i] = bytes.get(i);
        }
        return bytes1;
    }

    /**
     * 压缩成霍夫曼树对应的编码
     * @param bytes
     * @param huffmanCodes
     * @return
     */
    private static byte[] zip(byte[] bytes,Map<Byte, String> huffmanCodes){
        StringBuilder stringBuilder1 = new StringBuilder();
        for (byte aByte : bytes) {
            //Ascill码转为霍夫曼树编码
             stringBuilder1.append(huffmanCodes.get(aByte));
        }
        int len;
        if (stringBuilder1.length()% 8 ==0){
            len = stringBuilder1.length() / 8;
        }else {
            len = stringBuilder1.length() / 8 +1;
        }
        byte[] huffmanCodesBytes = new byte[len];
        int index = 0;
        for (int i=0;i<stringBuilder1.length();i +=8){
            String str;
            if (i+8 > stringBuilder1.length()){
                str = stringBuilder1.substring(i);
            }else {
                str = stringBuilder1.substring(i,i+8);
            }
            huffmanCodesBytes[index] = (byte) Integer.parseInt(str,2);
            index++;
        }
        return huffmanCodesBytes;
    }

    static Map<Byte,String> huffmanCodes = new HashMap<>();
    static StringBuilder stringBuilder = new StringBuilder();

    private static Map<Byte,String> getCode(Node root){
        if (root==null){
            return null;
        }
        getCode(root.left,"0",stringBuilder);
        getCode(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    /**
     * 霍夫曼树编码表
     * @param root
     * @param code
     * @param stringBuilder
     */
    public static void getCode(Node root,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        if (root!=null){
            if (root.getData()==null){
                getCode(root.left,"0",stringBuilder2);
                getCode(root.right,"1",stringBuilder2);
            }else {
                huffmanCodes.put(root.getData(),stringBuilder2.toString());
            }
        }
    }

    private static void preOrder(Node root){
        if (root==null){
            System.out.println("树为空");
        }else {
            root.preOrder();
        }
    }

    /**
     * 集合转成霍夫曼树
     * @param nodeList
     * @return
     */
    private static Node HuffmanTree(List<Node> nodeList){
        while (nodeList.size()>1){
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);
            Node parent = new Node(null,leftNode.getWeight()+rightNode.getWeight());
            parent.left = leftNode;
            parent.right = rightNode;
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(parent);
        }
        return nodeList.get(0);
    }

    /**
     * 返回Node的集合
     * @param bytes
     * @return
     */
    private static List<Node> byteToList(byte[] bytes){
        //key 记录byte vaule记录出现的次数
        Map<Byte,Integer> byteIntegerMap = new HashMap<>();
        List<Node> list = new ArrayList<>();
        for (Byte aByte : bytes) {
            Integer count = byteIntegerMap.get(aByte);
            if (count==null){
                byteIntegerMap.put(aByte, 1);
            }else {
                byteIntegerMap.put(aByte, count+1);
            }
        }
        for (Map.Entry<Byte,Integer> entry:byteIntegerMap.entrySet()){
            list.add(new Node(entry.getKey(),entry.getValue()));
        }
        return list;
    }
}


class Node implements Comparable<Node>{
    private Byte data;
    private int weight;
    public Node left;
    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public Byte getData() {
        return data;
    }

    public void setData(Byte data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight-o.weight;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }
}
