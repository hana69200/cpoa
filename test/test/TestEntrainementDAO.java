package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import baseDeDonnee.metier.Entrainement;
import baseDeDonnee.metier.Joueur;
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
	void testGetEntrainementByDay() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
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

	@Test
	void testIsCoursDispo() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		EntrainementDAO eDAO = new EntrainementDAO(getCon());

		GregorianCalendar d1 = new GregorianCalendar(2019, 11, 29, 5, 00);
		GregorianCalendar d2 = new GregorianCalendar(2019, 11, 30, 5, 00);
		GregorianCalendar d3 = new GregorianCalendar(2019, 11, 30, 5, 00);
		GregorianCalendar d4 = new GregorianCalendar(2019, 11, 30, 17, 30);
		GregorianCalendar d5 = new GregorianCalendar(2019, 11, 29, 9, 40);

		assertTrue(eDAO.isCoursDispo(1, d1));
		assertFalse(eDAO.isCoursDispo(1, d2));
		assertTrue(eDAO.isCoursDispo(2, d3));
		assertFalse(eDAO.isCoursDispo(2, d4));
		assertFalse(eDAO.isCoursDispo(3, d5));
	}

	@Test
	void testIsJoueurDispo() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			SQLException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		EntrainementDAO eDAO = new EntrainementDAO(getCon());

		GregorianCalendar d1 = new GregorianCalendar(2019, 11, 30, 5, 00);
		Joueur j1 = new Joueur(1, "", "", 1);
		GregorianCalendar d2 = new GregorianCalendar(2019, 11, 30, 5, 00);
		Joueur j2 = new Joueur(2, "", "", 1);
		GregorianCalendar d3 = new GregorianCalendar(2019, 11, 30, 4, 30);
		Joueur j3 = new Joueur(1, "", "", 1);
		GregorianCalendar d4 = new GregorianCalendar(2019, 11, 30, 5, 30);
		Joueur j4 = new Joueur(1, "", "", 1);
		GregorianCalendar d5 = new GregorianCalendar(2019, 11, 29, 5, 00);
		Joueur j5 = new Joueur(1, "", "", 1);

		assertFalse(eDAO.isJoueurDispo(j1, d1));
		assertTrue(eDAO.isJoueurDispo(j2, d2));
		assertFalse(eDAO.isJoueurDispo(j3, d3));
		assertFalse(eDAO.isJoueurDispo(j4, d4));
		assertTrue(eDAO.isJoueurDispo(j5, d5));
	}

	@Test
	void testGetCoursDispo() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SQLException
	{
		EntrainementDAO eDAO = new EntrainementDAO(getCon());

		GregorianCalendar d1 = new GregorianCalendar(2019, 11, 30, 17, 00);
		GregorianCalendar d2 = new GregorianCalendar(2020, 1, 25, 5, 00);
		
		List<Integer> t1 = eDAO.getCoursDispo(d1);
		List<Integer> t2 = eDAO.getCoursDispo(d2);

		List<Integer> l1 = new ArrayList<Integer>();
		l1.add(1);
		l1.add(3);
		
		List<Integer> l2 = new ArrayList<Integer>();
		l2.add(1);
		l2.add(2);
		l2.add(3);
		
		assertEquals(l1, t1);
		assertEquals(l2, t2);
	}
}
