package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList<T> {
    private T[] myList;
    private int size;
    private int count = 0;

    // Constructor with initial size
    public MyArrayList(int size) {
        this.size = size;
        myList = (T[]) new Object[size]; // Generic arrays require casting
    }

    // Default constructor
    public MyArrayList() {
        this.size = 10;
        myList = (T[]) new Object[size];
    }

    // Add a new value to the list
    void add(T value) {
        if (size == count) {
            size += (size / 2);
            T[] temp = (T[]) new Object[size];
            for (int i = 0; i < count; i++) {
                temp[i] = myList[i];
            }
            myList = temp;
        }
        myList[count] = value;
        count++;
    }

    // Display all values in the list
    void display() {
        for (int i = 0; i < count; i++) {
            System.out.println(myList[i]);
        }
    }

    T get(int index){
        return myList[index];
    }

    // Inside your MyArrayList class:
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(myList[i]);
        }
        return list;
    }


    // Search for a value in the list
    void searchByValue(T ele) {
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (myList[i].equals(ele)) {
                found = true;
                break;
            }
        }
        System.out.println("VALUE FOUND: " + found);
    }

    // Find the index of an element
    void indexOf(T ele) {
        for (int i = 0; i < count; i++) {
            if (myList[i].equals(ele)) {
                System.out.println("Index of " + ele + ": " + i);
                return;
            }
        }
        System.out.println(ele + " not found in the list.");
    }

    // Check if the list is empty
    boolean isEmpty() {
        return count == 0;
    }

    // Remove an element by value
    void removeByValue(T value) {
        for (int i = 0; i < count; i++) {
            if (myList[i].equals(value)) {
                for (int j = i; j < count - 1; j++) {
                    myList[j] = myList[j + 1];
                }
                count--;
                System.out.println(value + " removed from the list.");
                return;
            }
        }
        System.out.println(value + " not found in the list.");
    }

    // Remove an element by index
    void removeByIndex(int index) {
        if (index < 0 || index >= count) {
            System.out.println("Index out of bounds.");
            return;
        }
        for (int i = index; i < count - 1; i++) {
            myList[i] = myList[i + 1];
        }
        count--;
        System.out.println("Element at index " + index + " removed.");
    }

    // Check if the list contains a value
    void contains(T value) {
        for (int i = 0; i < count; i++) {
            if (myList[i].equals(value)) {
                System.out.println("List contains " + value + ".");
                return;
            }
        }
        System.out.println("List does not contain " + value + ".");
    }

    // Get the current size of the list
    int size() {
        return count;
    }

    // Clear the list
    void clear() {
        count = 0;
        myList = (T[]) new Object[size];

    }

    // Convert the list to an array
    T[] toArray() {
        T[] result = (T[]) new Object[count];
        for (int i = 0; i < count; i++) {
            result[i] = myList[i];
        }
        return result;
    }
}