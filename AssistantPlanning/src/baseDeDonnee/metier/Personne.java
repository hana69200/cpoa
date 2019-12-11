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
}
