package test;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Hashtable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metierDAO.UtilisateurDAO;

class TestUserDAO extends TestSup
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
	void testIsUserOK() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
            
		UtilisateurDAO UDAO = new UtilisateurDAO(getCon());

		assertEquals(true, UDAO.isUserOK("A1"));
		assertEquals(true, UDAO.isUserOK("J1"));
		assertEquals(false, UDAO.isUserOK("ghjkl"));
	}

	@Test
	void testIsPasswordOK() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		UtilisateurDAO UDAO = new UtilisateurDAO(getCon());

		assertEquals(true, UDAO.isPasswordOK("A1", "A1mdp"));
		assertEquals(false, UDAO.isPasswordOK("A1", "sdrftghij"));
		assertEquals(false, UDAO.isPasswordOK("xdcfvgbhnj", "A1mdp"));
		assertEquals(false, UDAO.isPasswordOK("bgt", "zsedfghj"));
	}

	@Test
	void testGetJoueur() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		UtilisateurDAO UDAO = new UtilisateurDAO(getCon());

		assertEquals(null, UDAO.getJoueur("A1"));
		Joueur j = new Joueur(1, "ISNER", "John", 3);
		assertEquals(j, UDAO.getJoueur("J1"));
		assertEquals(null, UDAO.getJoueur("sdxgbh"));
	}

	@Test
	void testGetAutorisation()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException
	{
		UtilisateurDAO UDAO = new UtilisateurDAO(getCon());

		Hashtable<String, Boolean> ta = new Hashtable<String, Boolean>();
		ta.put("ReservationEntr", false);
		ta.put("AjoutMatch", true);
		ta.put("ModifMatch", true);
		ta.put("AjoutResult", true);
		Hashtable<String, Boolean> tj = new Hashtable<String, Boolean>();
		tj.put("ReservationEntr", true);
		tj.put("AjoutMatch", false);
		tj.put("ModifMatch", false);
		tj.put("AjoutResult", false);
		assertEquals(null, UDAO.getAutorisation("qsdfghj"));
		assertEquals(ta, UDAO.getAutorisation("A1"));
		assertEquals(tj, UDAO.getAutorisation("J1"));
	}
}