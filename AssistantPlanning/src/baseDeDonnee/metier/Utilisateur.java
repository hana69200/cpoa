package baseDeDonnee.metier;

import java.util.Hashtable;

public class Utilisateur
{
	private Joueur joueur;
	private Hashtable<String, Boolean> autorisations;
	
	public Utilisateur(Joueur joueur, Hashtable<String, Boolean> autorisations)
	{
		this.joueur = joueur;
		this.autorisations = autorisations;
	}

	public Joueur getJoueur()
	{
		return joueur;
	}

	public Hashtable<String, Boolean> getAutorisations()
	{
		return autorisations;
	}
	
}
