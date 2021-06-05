package com.swpu.binarytree;

public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i : arr) {
            binarySortTree.add(new Node(i));
        }
        binarySortTree.infixOrder();

        //删除叶子节点
        System.out.println("删除后：");
        binarySortTree.delNode(7);
        binarySortTree.delNode(5);
        binarySortTree.delNode(3);
        binarySortTree.delNode(12);
        binarySortTree.delNode(9);
        binarySortTree.delNode(2);
        binarySortTree.delNode(10);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private Node root;

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else System.out.println("二叉树排序树为空");
    }

    //查找节点
    public Node search(int value) {
        if (root == null) return null;
        else return root.search(value);
    }


    //查找父节点
    public Node searchParent(int value) {
        if (root == null) return null;
        else return root.searchParent(value);
    }

    public int delRightTreeMin(Node node) {
        Node target = node;
        while (target.left != null) {
            target = target.left;
        }
        //  target指向最小节点
        delNode(target.value);
        return target.value;
    }

    //删除节点
    public void delNode(int value) {
        if (root == null) return;
        else {
            Node targetNode = search(value);
            if (targetNode == null) return;
            //二叉树只有一个节点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }
            Node parent = searchParent(value);

            //删除的是叶子节点
            if (targetNode.left == null && targetNode.right == null) {
                //判断targetNode是父节点的左/右节点
                if (parent.left != null && parent.left.value == value) {
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) {
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null)
                targetNode.value = delRightTreeMin(targetNode.right);
            else { //删除只有一颗子树的节点

                //   10
                //   /
                //  1

                if (targetNode.left != null) {
                    //左左
                    if (parent != null) {
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else {
                    if (parent != null) {
                        //左右
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else {
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }
}

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            if (this.left == null) return null;
            return this.left.search(value);
        } else {
            if (this.right == null) return null;
            return this.right.search(value);
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {
            if (value < this.value && this.left != null) {
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }
    }

    //添加节点
    public void add(Node node) {
        if (node == null) return;

        if (node.value < this.value) {
            //如果左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                //递归
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}