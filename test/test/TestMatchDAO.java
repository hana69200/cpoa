package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metier.Match;
import baseDeDonnee.metier.Score;
import baseDeDonnee.metierDAO.MatchDAO;

class TestMatchDAO extends TestSup
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
	void testGetMatchsByDay() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		MatchDAO mDAO = new MatchDAO(getCon());

		GregorianCalendar d1 = new GregorianCalendar(2019, 11, 25);
		List<Match> l1 = mDAO.getMatchsByDay(d1);
		List<Match> t1 = new ArrayList<Match>();

		GregorianCalendar d2 = new GregorianCalendar(2019, 11, 20);
		GregorianCalendar db2 = new GregorianCalendar(2019, 11, 20, 12, 20, 00);
		List<Match> l2 = mDAO.getMatchsByDay(d2);
		List<Match> t2 = new ArrayList<Match>();
		t2.add(new Match(new Joueur(23, "NADAL", "Rafael", 1), new Joueur(6, "CHARDY", "Jérémy", 2), 1, db2,
				new Score(15, 15)));

		assertEquals(t1, l1);
		assertEquals(t2, l2);
	}

	@Test
	void testIsCoursDispo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		MatchDAO mDAO = new MatchDAO(getCon());

		GregorianCalendar d1 = new GregorianCalendar(2019, 11, 20, 13, 00);
		GregorianCalendar d2 = new GregorianCalendar(2019, 11, 20, 11, 30);
		GregorianCalendar d3 = new GregorianCalendar(2019, 11, 20, 12, 00);
		GregorianCalendar d4 = new GregorianCalendar(2019, 11, 20, 12, 40);
		GregorianCalendar d5 = new GregorianCalendar(2019, 11, 21, 12, 40);
		assertTrue(mDAO.isCoursDispo(d1));
		assertTrue(mDAO.isCoursDispo(d2));
		assertFalse(mDAO.isCoursDispo(d3));
		assertFalse(mDAO.isCoursDispo(d4));
		assertTrue(mDAO.isCoursDispo(d5));
	}

}
