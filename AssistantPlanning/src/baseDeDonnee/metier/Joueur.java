package baseDeDonnee.metier;

public class Joueur extends Personne
{

	public Joueur(int id, String nom, String prenom, int nationalite)
	{
		super(id, nom, prenom, nationalite);
	}

	@Override
	public String toString()
	{
		return "Joueur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + "]";
	}

}
