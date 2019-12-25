package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metier.Match;
import baseDeDonnee.metier.Score;

public class MatchDAO extends DAO
{
	public MatchDAO(Connection connection)
	{
		super(connection);
	}

	private String getDay(Calendar date)
	{
		return Integer.toString(date.get(Calendar.YEAR)) + "-" + Integer.toString(date.get(Calendar.MONTH) + 1) + "-"
				+ Integer.toString(date.get(Calendar.DAY_OF_MONTH));
	}

	public List<Match> getMatchsByDay(Calendar date) throws SQLException
	{
		List<Match> matchs = new ArrayList<Match>();

		String day = getDay(date);
		String sql = "select * from MatchTournois where DateDebut between '" + day + " 00:00:00.000' and '" + day
				+ " 23:59:59.999'";
		ResultSet rs = getRs(sql);

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

	public boolean isCoursDispo(Calendar date) throws SQLException
	{
		String day = getDay(date);
		date.add(Calendar.MINUTE, -Match.DUREE_MATCH);
		String timeBefore = Integer.toString(date.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(date.get(Calendar.MINUTE)) + ":00.000";
		date.add(Calendar.MINUTE, 2 * (Match.DUREE_MATCH));
		String timeAfter = Integer.toString(date.get(Calendar.HOUR_OF_DAY)) + ":"
				+ Integer.toString(date.get(Calendar.MINUTE)) + ":00.000";

		String sql = "select * from MatchTournois where DateDebut between '" + day + " " + timeBefore + "' and '" + day
				+ " " + timeAfter + "'";

		ResultSet rs = getRs(sql);
		return !rs.next();
	}

	public void createMatch(Match match)
	{
		// TODO createMatch
	}

	public void deleteMatch(Match match)
	{
		// TODO deleteMatch
	}
}
