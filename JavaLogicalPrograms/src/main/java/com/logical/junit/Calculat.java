package com.logical.junit;

public class Calculat {
	
	public static int findMax(int arr[]){  
        int max=0;  
        for(int i=1;i<arr.length;i++){  
            if(max<arr[i])  
                max=arr[i];  
        }  
        return max;  
    }  
    //method that returns cube of the given number  
    public static int cube(int n){  
        return n*n*n;  
    }  
    //method that returns reverse words   
    public static String reverseWord(String str){  
  
    	if(str == null) return str;
        StringBuilder result=new StringBuilder(str);  
       
        return result.reverse().toString();
    }  

}
