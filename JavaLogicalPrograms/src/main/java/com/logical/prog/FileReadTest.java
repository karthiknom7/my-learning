package com.logical.prog;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class FileReadTest {

	private static String fileName = "D:\\project documents\\text_wc.txt";
	private static String outFileName = "D:\\project documents\\text_wc_out.txt";
	public static void main(String[] args) throws IOException {

		/*readFileWithStreams();
		readFileWithBuffer();
		readFileWithBUfferStream();*/
		writeToFileWithBuffer();
	}
	
	public static void readFileWithStreams() throws IOException{
		List<String> lines = Files.lines(new File(fileName).toPath()).collect(Collectors.toList());
		lines.forEach(System.out::println);
	}

	public static void readFileWithBuffer() throws IOException{
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		String line = null;
		System.out.println("Read buffer traditinal way...");
		while((line = bufferedReader.readLine()) != null){
			System.out.println(line);
		}
		
		bufferedReader.close();
	}
	
	public static void readFileWithBUfferStream() throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
		System.out.println("Stream from buffer reader....");
		bufferedReader.lines().forEach(System.out::println);
		bufferedReader.close();
	}
	
	public static void writeToFileWithBuffer() throws IOException{
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outFileName));
		bufferedWriter.write("Hello test");
		bufferedWriter.close();
		System.out.println("Writing is done");
	}
}
