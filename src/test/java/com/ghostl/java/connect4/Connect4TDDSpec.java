package com.ghostl.java.connect4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;


public class Connect4TDDSpec {

	private Connect4 tested;
	
	@Rule 
	public ExpectedException exception = ExpectedException.none(); 
	
	
	@Before
	public void beforeEachTest(){
		tested = new Connect4();
	}
	
	
	@Test
	public void whenTheGameIsStartedThenBoardIsEmpty(){
		assertThat(tested.getNumberOfDiscs(), is(0));
	}
	
	@Test
	public void whenDiscOutsideBoardThenRuntimeException(){
		int column = -1;
		exception.expect(RuntimeException.class);
		exception.expectMessage("Invalid Column"+ column);
		tested.putDiscInColumn(column);
	}
	
	@Test
	public void whenFirstDiscInsertedInColumnThenPositionIsZero(){
		int column = 1;
		assertThat(tested.putDiscInColumn(column), is(0));
	}
	
	@Test
	public void whenSecondDiscInsertedInColumnThenPositionIsOne(){
		int column = 1;
		tested.putDiscInColumn(column);
		assertThat(tested.putDiscInColumn(column), is(1));
	}
	
	@Test
	public void whenDiscInsertedthenNumberOfDiscsIncrease(){
		int column = 1;
		tested.putDiscInColumn(column);
		assertThat(tested.getNumberOfDiscs(), is(1));
	}
	
	@Test
	public void whenNoMoreRoomInColumnThenRuntimeException(){
		int column = 1;
		int maxDiscsInColumn = 6; //the number of rows
		for(int times = 0; times < maxDiscsInColumn; ++times){
			tested.putDiscInColumn(column);
		}
		
		exception.expect(RuntimeException.class);
		exception.expectMessage("No more room in column "+ column);
		tested.putDiscInColumn(column);
	}
	
}
