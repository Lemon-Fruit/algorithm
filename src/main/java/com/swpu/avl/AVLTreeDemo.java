package com.swpu.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4, 3, 6, 5, 7, 8};
        int[] arr = {10, 12, 8, 9, 7, 6};
        //int[] arr = {}
        AVLTree avlTree = new AVLTree();
        for (int i : arr) {
            avlTree.add(new Node(i));
        }

        avlTree.infixOrder();

        System.out.println("平衡：");
        System.out.println(avlTree.getRoot().height());
        System.out.println("左：" + avlTree.getRoot().leftHeight());
        System.out.println("右：" + avlTree.getRoot().rightHeight());
    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

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

    public int leftHeight() {
        if (left == null) return 0;
        return left.height();
    }

    public int rightHeight() {
        if (right == null) return 0;
        return right.height();
    }

    //返回当前节点的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    //左旋
    private void leftRotate() {
        Node newNode = new Node(value);
        newNode.left = left;

        newNode.right = right.left;

        value = right.value;

        right = right.right;

        left = newNode;
    }

    //右旋
    public void rightRotate() {
        Node newNode = new Node(value);
        newNode.right = right;

        newNode.left = left.right;

        value = left.value;

        left = left.left;

        right = newNode;
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

        //当添加完一个节点，如果（右 - 左） > 1 ，左旋
        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else leftRotate();
            return;
        } else if (leftHeight() - rightHeight() > 1) {
            //如果它的左子树的右子树高度大于他的左子树高度
            if (left != null && left.rightHeight() > left.leftHeight()) {
                //先对当前节点的左节点 左旋转
                left.leftRotate();
            } else rightRotate();

            return;
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

