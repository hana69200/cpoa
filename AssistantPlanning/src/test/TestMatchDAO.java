package test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
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

class TestMatchDAO
{
	private Connection getCon()
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
		Driver pilote = (Driver) c.newInstance();
		DriverManager.registerDriver(pilote);
		String protocole = "jdbc:mysql:";
		String ip = "iutdoua-web.univ-lyon1.fr";
		String port = "3306";
		String nomBase = "p1607863";
		String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
		String nomConnexion = "p1607863";
		String motDePasse = "270858";

		return DriverManager.getConnection(conString, nomConnexion, motDePasse);
	}

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@AfterEach
	void tearDown() throws Exception
	{
	}

	@Test
	void testGetMatchsByDay()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		MatchDAO mDAO = new MatchDAO(getCon());
		
		GregorianCalendar d1 = new GregorianCalendar(2019, 11, 25);
		List<Match> l1 = mDAO.getMatchsByDay(d1);
		List<Match> t1 = new ArrayList<Match>();
		
		GregorianCalendar d2 = new GregorianCalendar(2019, 11, 20);
		List<Match> l2 = mDAO.getMatchsByDay(d2);
		List<Match> t2 = new ArrayList<Match>();
		t2.add(new Match(new Joueur(23, "NADAL", "Rafael", 1), new Joueur(6, "CHARDY", "Jérémy", 2), 1, d2, new Score(15, 15)));
		
		assertEquals(t1, l1);
		assertEquals(t2, l2);
	}

}
