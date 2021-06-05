package com.swpu.queue;

import java.util.Scanner;

public class CircleQueueDemo {
    public static void main(String[] args) {
        CircleArray circleArray = new CircleArray(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        System.out.println("环形队列：");
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出");
            System.out.println("a(add):添加数据");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    circleArray.showQueue();
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    circleArray.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = circleArray.getQueue();
                        System.out.println("取出队头:" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = circleArray.headQueue();
                        System.out.println("队头：" + res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                default:
                    break;
            }
        }
    }
}

class CircleArray {
    private int maxSize;
    private int front;//队列头
    private int rear;//队列尾
    private int[] arr;//该数据用于存放数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize) {
        maxSize = arrMaxSize;
        arr = new int[maxSize];
    }

    //判断队列满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    //入队
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //出队
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        /*
        这里需要分析出front是指向队列的第一个元素
        1.先把front对应的值保留到一个临时变量
        2.将front后移
        3.将临时保存的变量返回
         */
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.println("arr[" + i % maxSize + "] = " + arr[i % maxSize]);
        }
    }

    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队头，不是取出数据

    public int headQueue() {
        //判断
        if (isEmpty()) {
            throw new RuntimeException("队列为空，无数据");
        }
        return arr[front];
    }

}