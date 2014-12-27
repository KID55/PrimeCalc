package com.example.kid.primecalc;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.view.Gravity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by KID on 25.12.2014.
 */


public class PrimeCalculator {
    private int a;
    private int b;
    private int p;
    private boolean ok = false;
    public static int duration = Toast.LENGTH_LONG;
    ArrayList<String> res = new ArrayList<String>();
    String resRandom = "";
    int counter = 0;
    double preresult = 0;
    double result = 0;

    /**
     * Метод для создания коллекции чисел, которые потом будут обработаны решетом Эратосфена
     * и найдены простые числа
     * @param b правая граница диапазона в котором надо искать простые числа
     * @return возвращает ArrayList<Boolean> необходимый для обработки решетом Эратосфена
     */
    public ArrayList<Boolean> createList(int b) throws IOException {
        ArrayList<Boolean> fillArray = new ArrayList<Boolean>();
        for (int x = 0; x <= b; x++) fillArray.add(true);
        return fillArray;
    }

    /**
     * Метод находящий простые числа в заданном диапазоне
     * @param arrList ArrayList<Boolean> необходимый для решета Эратосфена
     * @param a левая граница диапазона в котором надо искать простые числа
     * @return возвращает ArrayList<String> с найденными простыми числами
     */
    public ArrayList<String> calc(ArrayList<Boolean> arrList, int a) {
        arrList.set(0, false); // 0 и 1
        arrList.set(1, false); // не простые числа
        for (int i = 2; i < arrList.size(); i++) {
            if (arrList.get(i)) {
                for (int j = 2; i * j < arrList.size(); j++) {
                    arrList.set(j * i, false);
                }
            }
        }

        for (int k = 0; k <= a; k++) arrList.set(k, false);

        for (int y = 0; y < arrList.size(); y++) {
            if (arrList.get(y)) {
                res.add(Integer.toString(y));
            }
        }

        return res;
    }

    /**
     * Метод для нахождения массива случайных чисел с максимально близким к заданному средним-арифметическим модулей разностей
     * всех элементов массива. ОСТОРОЖНО ИНДИЯ И ЧЁРНАЯ МАГИЯ!
     * @param arrList ArrayList<Boolean> необходимый для решета Эратосфена
     * @param a левая граница диапазона в котором надо искать простые числа
     * @param p Заданное среднее-арифметическое
     * @return Возвращает строку с искомым массивом (иногда)
     */
    public String calcRandomArray(ArrayList<Boolean> arrList, int a, int p) {
        arrList.set(0, false); // 0 и 1
        arrList.set(1, false); // не простые числа
        ArrayList<Integer> intArrList = new ArrayList<Integer>();
        ArrayList<Integer> rezArrList = new ArrayList<Integer>();
        Random rand = new Random();
        double p_min = p - 1.0;
        double p_max = p + 1.0;
        double eps = 1.0e-2;

        // Отсеиваем ненужные составные числа
        for (int i = 2; i < arrList.size(); i++) {
            if (arrList.get(i)) {
                for (int j = 2; i * j < arrList.size(); j++) {
                    arrList.set(j * i, false);
                }
            }
        }

        // Откусываем ненужное начало диапазона
        for (int k = 0; k <= a; k++) arrList.set(k, false);

        // Делаем int-овую коллекцию из булевой
        for (int z = 0; z < arrList.size(); z++){
            if (arrList.get(z)) intArrList.add(z);
        }

        // ОСТОРОЖНО, НАЧИНАЕТСЯ ЧЕРНАЯ МАГИЯ!

        // Добавляем в результирующую коллекцию два случайных простых числа и находим его среднее-арифметическое (вдруг повезет? иногда везет.)
        rezArrList.add(intArrList.get(rand.nextInt(intArrList.size())));
        rezArrList.add(intArrList.get(rand.nextInt(intArrList.size())));
        for (int i = 0; i < rezArrList.size()-1; i++){
            for (int j = i+1; j < rezArrList.size(); j++){
                preresult += Math.abs(rezArrList.get(i)-rezArrList.get(j));
                counter++;
            }
        }
        int index = intArrList.indexOf(rezArrList.get(rezArrList.size()-1));
        int size = intArrList.size();
        result = preresult/counter;

        // Чудо-цикл
        /*
        Обнуляем сумму модулей разностей preresult;
        Обнуляем счетчик разностей counter;
        if(последний элемент в rezArrayList == последний элемент в intArrayList)
        добавляем случайный элемент из intArrayList, кроме последнего; //чтобы избежать бесконечного уменьшения result
        else if(result >= p+1)
        добавляем случайный элемент из intArrayList, <= последний элемент в rezArrayList;
        else if(result <= p-1)
        добавляем случайный элемент из intArrayList, > последний элемент в rezArrayList;
        В цикле находим сумму модулей разностей всех элементов (сохраняем в preresult) и количество разностей (сохраняем в counter);
        Вычисляем result = preresult/counter;
        */
        while (!(result >= p_min+eps && result <= p_max+eps)){
            preresult = 0.0;
            counter = 0;
            if (rezArrList.get(rezArrList.size()-1) == intArrList.get(size-1)){
                rezArrList.add(intArrList.get(rand.nextInt(size-1)));
            } else if (result >= p+0.5){
                rezArrList.add(intArrList.get(rand.nextInt(index)));
            } else if (result < p-0.5){
                rezArrList.add(intArrList.get(rand.nextInt(size-index)+index));
            }
            for (int i = 0; i < rezArrList.size()-1; i++){
                for (int j = i+1; j < rezArrList.size(); j++){
                    preresult += Math.abs(rezArrList.get(i)-rezArrList.get(j));
                    counter++;
                }
            }
            result = preresult/counter;
        }
        resRandom = rezArrList.toString();
        rezArrList.clear();
        return resRandom;
    }
}
