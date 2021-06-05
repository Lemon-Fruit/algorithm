package com.swpu.sparsearray;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0：表示没有棋子，1表示黑子 2 表示白子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组:");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }

        //将二维数组 转为 稀疏数组
        //1. 先遍历二维数组 得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];

        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历二位数组，将非0的值存放到sparseARR[]
        int count = 0;//用于记录第几个非零数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组的形式
        System.out.println("得到的稀疏数组：");
        for (int[] row : sparseArr) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
        /*
        将稀疏数组 恢复成原始的二维数组
        1. 先读取稀疏数组第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr2 = int[11][11]
        2.在读取稀疏数组后几行的数据，并赋给原始的二维数据 即可
         */

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];

        System.out.println("恢复后的二维数组:");

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.print(data + " ");
            }
            System.out.println();
        }
    }
}