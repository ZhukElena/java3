package com.geekbrains;

import java.util.ArrayList;
import java.util.Arrays;

public class ArraysGenericUtil<E> {

    public void changePlaces(E[] arr, int firstPlace, int secondPlace) {
        E a = arr[firstPlace];
        arr[firstPlace] = arr[secondPlace];
        arr[secondPlace] = a;
    }

    public ArrayList<E> toArrayList(E[] arr) {
        return new ArrayList<>(Arrays.asList(arr));
    }
}
