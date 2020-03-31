package com.simple.shop.util;

import java.util.Random;

public class RandomToken {

	public static StringBuffer makeToken() {
		StringBuffer buffer = new StringBuffer();
		Random random = new Random();
		
		for(int i = 0; i < 20; i++) {
			int index = random.nextInt(3);
			switch (index) {
			//a-z
			case 0:
				buffer.append((char) ((int) (random.nextInt(26)) + 97 ));
				break;
			//A-Z	
			case 1:
				buffer.append((char) ((int) ((int) random.nextInt(26)) +65 ));
				break;
			//0-9	
			case 2:
				buffer.append((random.nextInt(10)));
				break;
			}
		}
		return buffer;
	}
}