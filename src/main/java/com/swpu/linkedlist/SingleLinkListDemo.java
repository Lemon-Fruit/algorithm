package com.swpu.linkedlist;

public class SingleLinkListDemo {
    public static void main(String[] args) {
        //进行测试
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星");
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头");


        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        singleLinkedList.add(heroNode1);
//        singleLinkedList.add(heroNode2);
//        singleLinkedList.add(heroNode3);
//        singleLinkedList.add(heroNode4);

        //乱序添加
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);

        singleLinkedList.list();

        HeroNode newHeroNode = new HeroNode(5, "小卢", "玉麒麟~");
        singleLinkedList.update(newHeroNode);

        singleLinkedList.list();

        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println();
        singleLinkedList.list();

        System.out.println(getLength(singleLinkedList.getHead()));
    }

    //方法：获取到单链表的节点的个数

    /**
     * @param head
     * @return 返回有效节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) return 0;
        int length = 0;//没有统计头节点
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//下一节点

    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

/**
 * 创建链表
 */
class SingleLinkedList {
    //头节点
    private HeroNode head = new HeroNode(0, "", "");

    //返回头节点
    public HeroNode getHead() {
        return head;
    }

    //添加节点
    public void add(HeroNode heroNode) {

        //头节点不能动，辅助遍历
        HeroNode temp = head;
        //遍历链表，找到最后
        while (temp.next != null) {
            //temp后移
            temp = temp.next;
        }
        //退出循环后，指向最后
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = head;
        boolean flag = false; //编号是否存在
        while (temp.next != null) {
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;//后移一个位置
        }
        if (flag) {
            System.out.println("准备插入的人物编号" + heroNode.no + "已经存在，不能添加");
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //根据no，修改节点信息
    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (temp != null) {
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        } else {
            System.out.println("没有找到编号" + heroNode.no + "的节点，不能修改");
        }
    }

    //删除节点
    public void del(int no) {
        HeroNode temp = head;
        while (temp.next != null) {
            if (temp.next.no == no) {
                temp.next = temp.next.next;
                return;
            }
            temp = temp.next;
        }
        System.out.println("待删除的节点不存在");
    }

    //遍历
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
        }
        //头节点不能动，辅助节点
        HeroNode temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            //temp后移
            temp = temp.next;
        }
    }
}