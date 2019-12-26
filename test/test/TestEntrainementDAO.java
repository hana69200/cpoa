package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import baseDeDonnee.metier.Entrainement;
import baseDeDonnee.metierDAO.EntrainementDAO;
import baseDeDonnee.metierDAO.JoueurDAO;

class TestEntrainementDAO extends TestSup
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
	void testGetEntrainementByDay() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		EntrainementDAO eDAO = new EntrainementDAO(getCon());
		JoueurDAO jDao = new JoueurDAO(getCon());
		
		Calendar d1 = new GregorianCalendar(2019, 11, 30);
		Calendar d2 = new GregorianCalendar(2019, 11, 29);
		Calendar d3 = new GregorianCalendar(2019, 11, 15);
		
		List<Entrainement> l1 = new ArrayList<Entrainement>();
		GregorianCalendar db1 = new GregorianCalendar(2019, 11, 30, 5, 00);
		GregorianCalendar db2 = new GregorianCalendar(2019, 11, 30, 18, 00);
		l1.add(new Entrainement(jDao.getPlayerByID(1), db1, 1));
		l1.add(new Entrainement(jDao.getPlayerByID(2), db2, 2));
		
		List<Entrainement> l2 = new ArrayList<Entrainement>();
		GregorianCalendar db3 = new GregorianCalendar(2019, 11, 29, 9, 00);
		l2.add(new Entrainement(jDao.getPlayerByID(4), db3, 3));
		
		List<Entrainement> l3 = new ArrayList<Entrainement>();
		
		assertEquals(l1, eDAO.getEntrainementByDay(d1));
		assertEquals(l2, eDAO.getEntrainementByDay(d2));
		assertEquals(l3, eDAO.getEntrainementByDay(d3));
	}

}
