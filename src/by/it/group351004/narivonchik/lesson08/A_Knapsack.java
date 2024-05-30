package by.it.group351004.narivonchik.lesson08;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак с повторами

Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        сколько есть вариантов золотых слитков
                     (каждый можно использовать множество раз).
Следующая строка содержит n целых чисел, задающих веса слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000

Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.


Sample Input:
10 3
1 4 8
Sample Output:
10

Sample Input 2:

15 3
2 8 16
Sample Output 2:
14

*/

public class A_Knapsack {

    int getMaxWeight(InputStream stream ) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        Scanner scanner = new Scanner(stream);
        int w=scanner.nextInt();
        int n=scanner.nextInt();
        int gold[]=new int[n];
        for (int i = 0; i < n; i++) {
            gold[i]=scanner.nextInt();
        }
        //всего предметов n
        //рюкзак вмещает w
        //вес каждого i-ого слитка - gold[i]
        //А - оптимальная заполненность рюкзаков размером s из k предметов (n строк w столбцов)
        int[][] A = new int[n + 1][w + 1];

        //считаем что на текущей итерации есть k разных слитков
        for (int k = 0; k <= n; k++) {
            //рассмотрим рюкзаки вместительностью s
            for (int s = 0; s <= w; s++) {
                //если предметов 0 или рюкзак вмещает 0 - то максимальная заполненность такого рюкзака - 0
                if (k == 0 || s == 0) {
                    A[k][s] = 0;
                }
                else {
                    //если размер рюкзака больше размера слитка (слиток влазит)
                    if (s >= gold[k - 1]) {
                        A[k][s] = Math.max(A[k-1][s], A[k][s - gold[k - 1]] + gold[k - 1]);
                        //максимальная заполненность рюкзака будет равна наибольшему значению из:
                        //1)максимальной заполненности такого же рюкзака но без k-ого предмета
                        //2)максимальной заполненности такого же рюкзака c набором включающим k предметов + вес k-ого предмета(слиток положили)
                    }
                    else {
                        //слиток не влазит в рюкзак: максимальная заполненность рюкзака
                        //будет равна максимальной заполненности такого же рюкзака но без k-ого предмета
                        A[k][s] = A[k - 1][s];
                    }
                }
        }
        }
        //результат оказывается в таблице в ячейке A[n][w]
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return A[n][w];
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = A_Knapsack.class.getResourceAsStream("dataA.txt");
        A_Knapsack instance = new A_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }
}
