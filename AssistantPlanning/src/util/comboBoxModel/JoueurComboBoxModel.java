/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.comboBoxModel;

import baseDeDonnee.metier.Joueur;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author MarionM
 */
public class JoueurComboBoxModel extends DefaultComboBoxModel<Joueur> {
    public JoueurComboBoxModel(Joueur[] item)
    {
        super(item);
    }
    
    public Joueur getSelectedItem() {
        Joueur selectedJoueur = (Joueur) super.getSelectedItem();
 
 
        return selectedJoueur;
    }
}
