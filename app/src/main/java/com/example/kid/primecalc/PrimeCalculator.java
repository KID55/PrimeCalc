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


    public ArrayList<Boolean> createList(int b) throws IOException {
        ArrayList<Boolean> fillArray = new ArrayList<Boolean>();
        for (int x = 0; x <= b; x++) fillArray.add(true);
        return fillArray;
    }

    public ArrayList<String> calc(ArrayList<Boolean> arrList, int a) {
        arrList.set(0, false);
        arrList.set(1, false);
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

    public String calcRandomArray(ArrayList<Boolean> arrList, int a, int p) {
        arrList.set(0, false);
        arrList.set(1, false);
        ArrayList<Integer> intArrList = new ArrayList<Integer>();
        ArrayList<Integer> rezArrList = new ArrayList<Integer>();
        Random rand = new Random();

        for (int i = 2; i < arrList.size(); i++) {
            if (arrList.get(i)) {
                for (int j = 2; i * j < arrList.size(); j++) {
                    arrList.set(j * i, false);
                }
            }
        }

        for (int k = 0; k <= a; k++) arrList.set(k, false);

        for (int z = 0; z < arrList.size(); z++){
            if (arrList.get(z)) intArrList.add(z);
        }


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
        double p_min = p - 1.0;
        double p_max = p + 1.0;
        double eps = 1.0e-2;
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
