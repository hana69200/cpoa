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
		//return "Joueur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", nationalite=" + nationalite + "]";
                return prenom + " " + nom;
	}

	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

}
