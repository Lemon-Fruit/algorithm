package com.swpu.linked.singlelist;

import java.util.Scanner;

/**
 * 基于链表的LRU算法
 *
 * @author: Lemon-Fruit
 * @date: 2021/6/5 23:00
 */
public class LRUBaseLinkedList<T> {
    //默认容量
    private final static Integer DEFAULT_CAPACITY = 10;

    //头结点
    private SNode<T> headNode;

    //链表长度
    private Integer length;

    //链表容量
    private Integer capacity;

    /* 懂的都懂
    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }*/

    private LRUBaseLinkedList() {
        this(DEFAULT_CAPACITY);
    }

    private LRUBaseLinkedList(Integer capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T data) {
        SNode preNode = findPreNode(data);

        if (preNode != null) {
            deleteElemOptim(preNode);
        } else {
            if (length >= this.capacity) {
                deleteElemAtEnd();
            }
        }
        insertElemAtBegin(data);
    }

    /**
     * 删除preNode结点下一个元素
     *
     * @param preNode
     */
    public void deleteElemOptim(SNode preNode) {
        SNode temp = headNode.getNext();
        preNode.setNext(temp.getNext());
        temp = null;
        --length;
    }

    /**
     * 链表头部插入结点
     *
     * @param data
     */
    public void insertElemAtBegin(T data) {
        SNode next = headNode.getNext();
        headNode.setNext(new SNode(data, next));
        ++length;
    }

    /**
     * 获取查找到的元素的前一个结点
     *
     * @param data
     * @return
     */
    private SNode findPreNode(T data) {
        SNode node = headNode;
        while (node.getNext() != null) {
            if (data.equals(node.getNext().getElement())) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    /**
     * 删除尾结点
     */
    private void deleteElemAtEnd() {
        SNode ptr = headNode;
        if (ptr.getNext() == null) {
            return;
        }

        while (ptr.getNext().getNext() != null) {
            ptr = ptr.getNext();
        }

        SNode tmp = ptr.getNext();
        ptr.setNext(null);
        tmp = null;
        --length;
    }

    private void printAll() {
        SNode node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + ",");
            node = node.getNext();
        }
        System.out.println();
    }

    public class SNode<T> {
        private T element;

        private SNode next;

        public SNode(T element) {
            this.element = element;
        }

        public SNode(T element, SNode next) {
            this.element = element;
            this.next = next;
        }

        public SNode() {
            this.next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList list = new LRUBaseLinkedList();
        Scanner in = new Scanner(System.in);
        while (true) {
            list.add(in.nextInt());
            list.printAll();
        }
    }
}
