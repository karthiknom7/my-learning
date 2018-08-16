package com.logical.prog;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class JMP2 {

	public static void main(String[] args) throws IOException {
		/*InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(reader);
		String line;
		while ((line = in.readLine()) != null) {*/
			// System.out.println(line);

		String line = "1122345667";
			String outPut = "";
			for (int i = 0; i < line.length(); i++) {
				if (i + 1 == line.length()) {
					outPut += line.substring(i);
				} else {
					int num1 = Integer.parseInt(line.substring(i, i + 1));
					int num2 = Integer.parseInt(line.substring(i, i + 2));
					if (isBothEven(num1, num2)) {
						outPut += num1 + "*";
					} else if (isBothOdd(num1, num2)) {
						outPut += num1 + "-";
					} else {
						outPut += num1;
					}
				}
			}
			System.out.println(outPut);
		//}

	}

	public static boolean isBothEven(int num1, int num2) {
		return (num1 % 2 == 0 && num2 % 2 == 0);
	}

	public static boolean isBothOdd(int num1, int num2) {
		return (num1 % 2 != 0 && num2 % 2 != 0);
	}

}
