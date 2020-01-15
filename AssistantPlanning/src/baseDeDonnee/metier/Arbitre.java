package baseDeDonnee.metier;

public class Arbitre extends Personne
{
	private int equipe;

	public Arbitre(int id, String nom, String prenom, int nationalite, int equipe)
	{
		super(id, nom, prenom, nationalite);
		this.equipe = equipe;
	}

	public int getEquipe()
	{
		return equipe;
	}

    @Override
    public String toString() {
        return "Equipe d'arbitre numéro " + equipe;
    }
        
        
}
