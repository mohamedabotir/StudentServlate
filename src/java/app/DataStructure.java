/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.Arrays;

/**
 *
 * @author Threading
 */
public class DataStructure <T>{
    T data[];
    int index;
    int size;
    final int CAPACITY=50;
    DataStructure(){
    data= (T[])new Object[CAPACITY];
    index=0;
    size=CAPACITY;
    }
    void push(T element){
        if(index==size)
            ensureCapacity(Size()+1);
        data[index++]=element;
    }
    void ensureCapacity(int minCapacity){
    int oldCapacity= Size();
    int newCapacity=0;
		if(minCapacity>oldCapacity)
		newCapacity=oldCapacity*2;
			if(newCapacity<minCapacity)
				newCapacity=minCapacity;
			data=Arrays.copyOf(data, newCapacity);
    }
    int Size(){
    return size;
    }
 T get(int index){
 return data[index];
 }
    
}
