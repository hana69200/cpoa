/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseDeDonnee.metier;

/**
 *
 * @author MarionM
 */
public class Personne
{
	protected int id;
	protected String nom;
	protected String prenom;
	protected int nationalite;

	public Personne(int id, String nom, String prenom, int nationalite)
	{
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.nationalite = nationalite;
	}

	public int getId()
	{
		return id;
	}
	
	public String getNom()
	{
		return nom;
	}

	public String getPrenom()
	{
		return prenom;
	}
	
	public int getNationalite()
	{
		return nationalite;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (id != other.id)
			return false;
		if (nationalite != other.nationalite)
			return false;
		if (nom == null)
		{
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null)
		{
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
}
