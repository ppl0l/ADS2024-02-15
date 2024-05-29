package by.it.group351004.romanovskiy.lesson08;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: рюкзак без повторов
Первая строка входа содержит целые числа
    1<=W<=100000     вместимость рюкзака
    1<=n<=300        число золотых слитков
                    (каждый можно использовать только один раз).
Следующая строка содержит n целых чисел, задающих веса каждого из слитков:
  0<=w[1]<=100000 ,..., 0<=w[n]<=100000
Найдите методами динамического программирования
максимальный вес золота, который можно унести в рюкзаке.
Sample Input:
10 3
1 4 8
Sample Output:
9
*/

public class B_Knapsack {

    int getMaxWeight(InputStream stream ) {
        // Создаем объект Scanner для считывания входных данных
        Scanner scanner = new Scanner(stream);
        // Считываем вместимость рюкзака
        int w = scanner.nextInt();
        // Считываем количество золотых слитков
        int n = scanner.nextInt();
        // Создаем массив для хранения весов слитков
        int gold[] = new int[n];
        // Заполняем массив весами слитков
        for (int i = 0; i < n; i++) {
            gold[i] = scanner.nextInt();
        }
        int result = 0;

        // Сортируем веса слитков по убыванию
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                if(gold[i] < gold[j]) {
                    int temp = gold[i];
                    gold[i] = gold[j];
                    gold[j] = temp;
                }
            }
        }
        // Перебираем слитки и добавляем их в рюкзак, пока есть свободное место
        for(int i = 0; i < n; i++) {
            if(w >= gold[i]) {
                w -= gold[i];
                result += gold[i];
            }
        }



        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result;
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_Knapsack.class.getResourceAsStream("dataB.txt");
        B_Knapsack instance = new B_Knapsack();
        int res=instance.getMaxWeight(stream);
        System.out.println(res);
    }

}