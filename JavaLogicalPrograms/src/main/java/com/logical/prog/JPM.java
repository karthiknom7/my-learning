package com.logical.prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JPM {

	
		  public static void main(String[] args) throws IOException {
			  InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
			    BufferedReader in = new BufferedReader(reader);
			    String line;
			    while ((line = in.readLine()) != null) {
			    	String[] words = line.split(",");
				    
				    String word1 = words[0].trim();
				    String word2 = words[1].trim();
				    
				    String commonSuffix = "";
				    
				    int wordOnelenght = word1.length();
				    int wordTwoLenght = word2.length();
				    
				    int minLenght = wordOnelenght<wordTwoLenght? wordOnelenght:wordTwoLenght;
				    
				    for (int i= 0; i < minLenght; i++){
				    	if(word1.charAt(wordOnelenght-1-i) != word2.charAt(wordTwoLenght-1-i)){
				    		break;
				    	}else{
				    		commonSuffix = word1.substring(wordOnelenght-1-i, wordOnelenght-i)+ commonSuffix;
				    	}
				    }
				    
				    if(commonSuffix.isEmpty()){
				    	commonSuffix = "NULL";
				    }
				    System.out.println(commonSuffix);
		    
			    }
			    
			    
			       
		  }
		

}
