package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import baseDeDonnee.metier.Entrainement;
import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metier.Match;

public class EntrainementDAO extends DAO
{

	public EntrainementDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 * renvoie une liste des entrainements d'un jour
	 * 
	 * @param date : le jour souhait�
	 * @return List des entrainements du jour
	 * @throws SQLException
	 */
	public List<Entrainement> getEntrainementByDay(Calendar date) throws SQLException
	{
		List<Entrainement> liste = new ArrayList<Entrainement>();

		String day = getDay(date);
		String sql = "select * from Entrainement where Date between '" + day + " 00:00:00.000' and '" + day
				+ " 23:59:59.999'";
		ResultSet rs = getRs(sql);

		Calendar cal = new GregorianCalendar();
		JoueurDAO jDAO = new JoueurDAO(this.getConnection());

		while (rs.next())
		{
			cal = this.getDateTime(rs, "Date");
			liste.add(new Entrainement(jDAO.getPlayerByID(rs.getInt("Joueur")), cal, rs.getInt("numerosCours")));
		}
		return liste;
	}

	public boolean isJoueurDispo(Joueur joueur, Calendar date)
	{
		// TODO isJoueurDispo
		return false;
	}

	public boolean isCoursDispo(int numero, Calendar date) throws SQLException
	{
		String day = getDay(date);
		String timeBefore = getTime(date, -Entrainement.DUREE_RESERVATION);
		String timeAfter = getTime(date, Entrainement.DUREE_RESERVATION);
		
		String sql = "select * from Entrainement where numerosCours = " + numero + " and Date between '" + day + " " + timeBefore + "' and '" + day
				+ " " + timeAfter + "'";

		ResultSet rs = getRs(sql);
		return !rs.next();
	}

	public int getCoursDispo(Calendar date)
	{
		// TODO getCoursDispo
		return 0;
	}
}
