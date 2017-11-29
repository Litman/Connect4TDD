package com.ghostl.java.connect4;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Connect4 {
	
	private static final int ROWS = 6;
	private static final int COLUMNS = 7;
	private static final String EMPTY = " ";
	private static final String RED = "R";
	private static final String GREEN = "G";
	private static final String DELIMITER = "|";
	
	private static final int DISCS_FOR_WIN = 4;
	private String currentPlayer = RED;
	
	private String winner = "";
	

	private String [][] board = new String[ROWS][COLUMNS];
	private PrintStream outputChanel;
	
	public Connect4(PrintStream outputStream){
		outputChanel = outputStream;
		for (String[] row : board)
			Arrays.fill(row, EMPTY);
	}

	public int getNumberOfDiscs() {
		
		return IntStream.range(0,COLUMNS).map(this::getNumberOfDiscsInColumn).sum();
	}

	public int putDiscInColumn(int column) {
		
		checkColumn(column);
		
		int row = getNumberOfDiscsInColumn(column);//(int) IntStream.range(0, ROWS).filter(row -> !EMPTY.equals(board[row][column])).count();
		checkPositionToInsert(row, column);
		board[row][column] = "R";
		printBoard();
		checkWinner(row,column);
		switchPlayer();
		
		
		return row;
		
	}
	
	private void printBoard() {
		for(int row = ROWS-1; row >= 0; row--){
			StringJoiner stringJoiner = new StringJoiner(DELIMITER,
					DELIMITER,
					DELIMITER);
			Stream.of(board[row]).forEachOrdered(stringJoiner::add);
			outputChanel.println(stringJoiner.toString());
		}
		
	}

	private void switchPlayer() {
		if(currentPlayer.equals("R"))
			currentPlayer=GREEN;
		else
			currentPlayer=RED;
		
		System.out.println("Current turn: " + currentPlayer);
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

	public String getCurrentPlayer() {
		outputChanel.printf("Player %s turn%n", currentPlayer);
		return currentPlayer;
	}

	public boolean isFinished() {
		// TODO Auto-generated method stub
		return getNumberOfDiscs() == ROWS * COLUMNS;
	}

	

	public String getWinner() {
		return winner;
	}
	
	
	private void checkWinner(int row, int column) {
		Pattern winPattern = Pattern.compile(".*" + currentPlayer + "{" + DISCS_FOR_WIN + "}.*");
		if (winner.isEmpty()) {
	        String colour = board[row][column];
	        
	        String vertical = IntStream.range(0, ROWS)
	                .mapToObj(r -> board[r][column])
	                .reduce(String::concat).get();
	        if (winPattern.matcher(vertical).matches())
	            winner = colour;
	        
	        String horizontal = Stream.of(board[row]).reduce(String::concat).get();
	        if(winPattern.matcher(horizontal).matches())
	        	winner = colour;
	        
	    }
	    if (winner.isEmpty()) {
	        int startOffset = Math.min(column, row);
	        int myColumn = column - startOffset , myRow = row -startOffset;
	        StringJoiner stringJoiner = new StringJoiner("");
	        do{
	        	stringJoiner.add(board[myRow++][myColumn++]);
	        	
	        }while( myColumn < COLUMNS && myRow < ROWS);
	        
	        if(winPattern.matcher(stringJoiner.toString()).matches()){
	        	winner = currentPlayer;
	        }
	        
	    }
	    
	    if(winner.isEmpty()){
	    	int startOffset = Math.min(column, ROWS - 1 - row);
	    	int myColumn = column - startOffset, myRow = row + startOffset;
	    	
	    	StringJoiner stringJoiner = new StringJoiner("");
	    	do{
	    		stringJoiner.add(board[myRow--][myColumn++]);
	    	}while(myColumn < COLUMNS && myRow >= 0);
	    	
	    	if(winPattern.matcher(stringJoiner.toString()).matches()){
	    		winner = currentPlayer;
	    	}
	    }
	    
	}
	
	

}
