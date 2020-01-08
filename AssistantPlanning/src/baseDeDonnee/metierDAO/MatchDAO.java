package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import baseDeDonnee.metier.Match;
import baseDeDonnee.metier.Score;

public class MatchDAO extends DAO
{
	public MatchDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 * renvoie une liste des matchs d'un jour
	 * 
	 * @param date : le jour souhaité
	 * @return List des matchs du jour
	 * @throws SQLException
	 */
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
			cal = this.getDateTime(rs, "DateDebut");
			matchs.add(new Match(jDAO.getPlayerByID(rs.getInt("Participant1")),
					jDAO.getPlayerByID(rs.getInt("Participant2")), rs.getInt("equipeArbitre"), cal,
					new Score(rs.getString("Score"))));
		}

		return matchs;
	}

	/**
	 * @param date : le jour et l'heure souhaité
	 * @return true si le cours est libre a l'heure souhaiter (avec la marge definis
	 *         dans la classe Match)
	 * @throws SQLException
	 */
	public boolean isCoursDispo(Calendar date) throws SQLException
	{
		String day = getDay(date);
		String timeBefore = getTime(date, -Match.DUREE_MATCH);
		String timeAfter = getTime(date, Match.DUREE_MATCH);

		String sql = "select * from MatchTournois where DateDebut between '" + day + " " + timeBefore + "' and '" + day
				+ " " + timeAfter + "'";

		ResultSet rs = getRs(sql);
		return !rs.next();
	}

	public void createMatch(Match match) throws SQLException
	{
		String sql = "INSERT INTO MatchTournois (Participant1, Participant2, equipeArbitre, DateDebut, Score) VALUES "
				+ "(? , ?, ?, ?, ?)";
		PreparedStatement stm = getConnection().prepareStatement(sql);
		stm.setInt(1, match.getParticipant1().getId());
		stm.setInt(2, match.getParticipant2().getId());
		stm.setInt(3, match.getEquipeArbitre());
		stm.setTimestamp(4, new java.sql.Timestamp(match.getDate().getTime().getTime()));
		stm.setString(5, match.getScore().toString());
		
		stm.execute();
	}

	public void deleteMatch(Match match) throws SQLException
	{
		String sql = "delete from MatchTournois where Participant1 = ? and Participant2 = ? and DateDebut = ?";
		PreparedStatement stm = getConnection().prepareStatement(sql);
		stm.setInt(1, match.getParticipant1().getId());
		stm.setInt(2, match.getParticipant2().getId());
		stm.setTimestamp(3, new java.sql.Timestamp(match.getDate().getTime().getTime()));
		
		stm.execute();
	}
}
