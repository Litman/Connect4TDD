package com.ghostl.java.connect4;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

public class Connect4TDDSpec {

	private Connect4 tested;
	
	@Before
	public void beforeEachTest(){
		tested = new Connect4();
	}
	
	@Test
	public void whenTheGameIsStartedThenBoardIsEmpty(){
		assertThat(tested.getNumberOfDiscs(),0);
	}
}
