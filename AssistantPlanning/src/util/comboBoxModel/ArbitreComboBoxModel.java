/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.comboBoxModel;

import baseDeDonnee.metier.Arbitre;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author MarionM
 */
public class ArbitreComboBoxModel extends DefaultComboBoxModel<Integer> {
    public ArbitreComboBoxModel(Integer[] item)
    {
        super(item);
    }
    
    public Integer getSelectedItem() {
        Integer selectedJoueur = (Integer) super.getSelectedItem();
 
 
        return selectedJoueur;
    }
}
