package baseDeDonnee.metierDAO;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import baseDeDonnee.metier.Entrainement;
import baseDeDonnee.metier.Joueur;

public class EntrainementDAO extends DAO
{

	public EntrainementDAO(Connection connection)
	{
		super(connection);
	}

	public List<Entrainement> getEntrainementByDay(Calendar date)
	{
		List<Entrainement> liste = new ArrayList<Entrainement>();
		// TODO getEntrainementByDay
		return liste;
	}

	public boolean isJoueurDispo(Joueur joueur, Calendar date)
	{
		// TODO isJoueurDispo
		return false;
	}

	public boolean isCoursDispo(int numero, Calendar date)
	{
		// TODO isCoursDispo
		return false;
	}

	public int getCoursDispo(Calendar date)
	{
		// TODO getCoursDispo
		return 0;
	}
}
