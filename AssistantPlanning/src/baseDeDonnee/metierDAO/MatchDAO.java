package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.Date;

import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metier.Match;

public class MatchDAO extends DAO
{
	public MatchDAO(Connection connection)
	{
		super(connection);
	}
	
	public ArrayList<Match> getMatchsByDay(Date date)
	{
		ArrayList<Match> matchs = new ArrayList<Match>();
		//TODO getMatchsByDay
		return matchs;
	}
	
	public boolean isCoursDispo(Date date)
	{
		//TODO isCoursDispo
		return false;
	}
	
	public boolean isJoueurDispo(Date date, Joueur joueur)
	{
		//TODO isJoueurDispo
		return false;
	}
	
	public boolean isEquipeArbitreDispo(Date date, int equipe)
	{
		//TODO isEquipeArbitreDispo
		return false;
	}
	
	//WARNING utilisation de sql.date / calendrier -> util.date
}
