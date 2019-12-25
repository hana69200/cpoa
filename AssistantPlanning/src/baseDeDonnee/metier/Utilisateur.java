package baseDeDonnee.metier;

import java.util.Hashtable;

public class Utilisateur
{
	private Joueur joueur;
	private Hashtable<String, Boolean> autorisations;
	
	/**
	 * @param joueur : joueur connecté (permet les reservation des cours d'entrainement). null si administrateur
	 * @param autorisations : table contenant les autorisations et leurs noms
	 */
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
