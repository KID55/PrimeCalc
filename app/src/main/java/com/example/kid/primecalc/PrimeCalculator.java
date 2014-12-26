package com.example.kid.primecalc;

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


    ArrayList<String> res = new ArrayList<String>();
    String resRandom = "";
    int counter = 0;
    double preresult = 0;
    double result = 0;


    public void setA(int x){
        if (x <= 0) System.out.println("Incorrect input. Number must be > 0");
        this.a = x;
    }

    public void setB(int x){
        if (x <= 0) System.out.println("Incorrect input. Number must be > 0");
        else if (x < this.a) System.out.println("Incorrect input. 'B' must be > 'A'");
        this.b = x;
    }

    public void setP(int x){
        if (x <= 0) System.out.println("Incorrect input. Number must be > 0");
        this.p = x;
    }

    public ArrayList<Boolean> createList(int b) throws IOException {
        //BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Boolean> fillArray = new ArrayList<Boolean>();
        /*System.out.println("Input 'A'");
        try {
            setA(Integer.parseInt(is.readLine()));
        } catch (NumberFormatException e) {
            System.out.println("'A' must be a number!");
        }
        System.out.println("Input 'B'");
        try {
            setB(Integer.parseInt(is.readLine()));
        } catch (NumberFormatException e) {
            System.out.println("'B' must be a number!");
        }
        System.out.println("Input 'P'");
        try {
            setP(Integer.parseInt(is.readLine()));
        } catch (NumberFormatException e) {
            System.out.println("'P' must be a number!");
        }*/
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


        //if (res == ) res = "Within a predetermined range no primes";

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
        counter++;
        while (result >= p+1 || result <= p-1 || result != p){
            rezArrList.add(intArrList.get(rand.nextInt(intArrList.size())));
            counter++;
            for (int i = 0; i < rezArrList.size()-1; i++){
                for (int j = 1; j < rezArrList.size(); j++){
                    preresult += Math.abs(rezArrList.get(i)-rezArrList.get(j));
                }
            }
            result = preresult/counter;
        }
        resRandom = rezArrList.toString();
        return resRandom;
    }
}
