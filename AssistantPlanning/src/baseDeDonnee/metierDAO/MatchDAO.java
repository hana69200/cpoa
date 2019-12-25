package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metier.Match;
import baseDeDonnee.metier.Score;

public class MatchDAO extends DAO
{
	public MatchDAO(Connection connection)
	{
		super(connection);
	}

	public ArrayList<Match> getMatchsByDay(Calendar date) throws SQLException
	{
		ArrayList<Match> matchs = new ArrayList<Match>();

		String day = Integer.toString(date.get(Calendar.YEAR)) + Integer.toString(date.get(Calendar.MONTH))
				+ Integer.toString(date.get(Calendar.DAY_OF_MONTH));
		String sql = "select * from MatchTournois where DateDebut between '" + day + " 00:00:00.000' and '" + day
				+ " 23:59:59.999'";
		Statement smt = getStm();
		ResultSet rs = smt.executeQuery(sql);

		JoueurDAO jDAO = new JoueurDAO(this.getConnection());
		Calendar cal = new GregorianCalendar();

		while (rs.next())
		{
			cal.setTime(rs.getDate("DateDebut"));
			matchs.add(new Match(jDAO.getPlayerByID(rs.getInt("Participant1")),
					jDAO.getPlayerByID(rs.getInt("Participant2")), rs.getInt("equipeArbitre"), cal,
					new Score(rs.getString("Score"))));
		}

		return matchs;
	}

	public boolean isCoursDispo(Calendar date)
	{
		// TODO isCoursDispo
		return false;
	}

	public boolean isJoueurDispo(Calendar date, Joueur joueur)
	{
		// TODO isJoueurDispo
		return false;
	}

	public boolean isEquipeArbitreDispo(Calendar date, int equipe)
	{
		// TODO isEquipeArbitreDispo
		return false;
	}

	// WARNING utilisation de sql.date / calendrier -> util.date
}
