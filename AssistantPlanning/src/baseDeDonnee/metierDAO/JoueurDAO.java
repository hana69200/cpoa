package baseDeDonnee.metierDAO;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;

import baseDeDonnee.metier.Joueur;

public class JoueurDAO
{
	private Connection connection;

	public JoueurDAO(Connection connection)
	{
		this.connection = connection;
	}

	public List<Joueur> getAllPlayers()
	{
		List<Joueur> liste = new ArrayList<Joueur>();
		// TODO acces BD
		return liste;
	}

	public Joueur getPlayerByID(int ID)
	{
		Joueur j = new Joueur();
		// TODO acces BD
		return j;
	}
}
