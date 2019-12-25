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
public class Arbitre extends Personne
{
	private int equipe;

	public Arbitre(int id, String nom, String prenom, int nationalite, int equipe)
	{
		super(id, nom, prenom, nationalite);
		this.equipe = equipe;
	}
}
