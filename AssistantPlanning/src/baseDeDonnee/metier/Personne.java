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
public class Personne {
    private String nom;
    private String prenom;
    private int nationalite;

    public String getNom() {
        return nom;
    }

    public int getNationalite() {
        return nationalite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNationalite(int nationalite) {
        this.nationalite = nationalite;
    }
    
    
}
