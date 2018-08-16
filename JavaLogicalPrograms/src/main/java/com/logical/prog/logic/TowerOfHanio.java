package com.logical.prog.logic;

public class TowerOfHanio {

	public void move(int numberOfPlates, char src, char dest, char inter) {
		if (numberOfPlates == 1) {
			System.out.println("Move " + numberOfPlates + " from " + src + " to " + dest);
		} else {
			move(numberOfPlates - 1, src, inter, dest);
			System.out.println("Move " + numberOfPlates + " from " + src + " to " + dest);
			move(numberOfPlates - 1, inter, dest, src);
		}
	}
	
	public static void main(String[] args) {
		TowerOfHanio hanio = new TowerOfHanio();
		hanio.move(2, 'A', 'C', 'B');
	}
}
