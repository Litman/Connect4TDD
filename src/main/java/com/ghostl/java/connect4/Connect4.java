package com.ghostl.java.connect4;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Connect4 {
	
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final String EMPTY = " ";
	
	private String [][] board = new String[ROWS][COLUMNS];
	
	public Connect4(){
		for (String[] row : board)
			Arrays.fill(row, EMPTY);
	}

	public int getNumberOfDiscs() {
		return 0;
	}

	public int putDiscInColumn(int column) {
		
		checkColumn(column);
		
		int row = getNumberOfDiscsInColumn(column);//(int) IntStream.range(0, ROWS).filter(row -> !EMPTY.equals(board[row][column])).count();
		checkPositionToInsert(row, column);
		board[row][column] = "X";
		return row;
		
	}
	
	private int getNumberOfDiscsInColumn(int column) {
		return (int) IntStream.range(0, ROWS).filter(row -> !EMPTY.equals(board[row][column])).count();
	}

	private void checkColumn(int column){
		if(column < 0 || column >= COLUMNS)
			throw new RuntimeException("Invalid Column"+ column);
	}

	private void checkPositionToInsert(int position, int column) {
		if(position == ROWS)
			throw new RuntimeException("No more room in column " + column);
		
	}

}
