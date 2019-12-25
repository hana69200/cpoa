package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import baseDeDonnee.metier.Score;

class TestScore
{

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@AfterEach
	void tearDown() throws Exception
	{
	}

	@Test
	void testConstru()
	{
		Score s1 = new Score(15, 15);
		Score s2 = new Score("15 - 15");
		Score s3 = new Score("15 - 30");
		// Score s4 = new Score("15 - 30 ");
		Score s5 = new Score("15 5 30");
		Score s6 = new Score("0 - 15");
		Score s7 = new Score("15 - 5");
		Score s8 = new Score("0 - 5");

		assertEquals(s1.toString(), "15 - 15");
		assertEquals(s2.toString(), "15 - 15");
		assertEquals(s3.toString(), "15 - 30");
		// assertEquals(s4.toString(), "15 - 30");
		assertEquals(s5.toString(), "0 - 0");
		assertEquals(s6.toString(), "0 - 15");
		assertEquals(s7.toString(), "15 - 5");
		assertEquals(s8.toString(), "0 - 5");
	}

}