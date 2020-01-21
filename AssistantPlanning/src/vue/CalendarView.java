/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import assistantplanning.AssistantPlanning;
import baseDeDonnee.metier.Joueur;
import baseDeDonnee.metier.Match;
import baseDeDonnee.metier.Score;
import baseDeDonnee.metierDAO.MatchDAO;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cleme
 */
public class CalendarView extends javax.swing.JPanel {

    /**
     * Creates new form JourCalendar
     */
    public CalendarView() throws SQLException {
        initComponents();
        GregorianCalendar cal = new GregorianCalendar(); //Create calendar
        Integer realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
        Integer realMonth = cal.get(GregorianCalendar.MONTH); //Get month
        Integer realYear = cal.get(GregorianCalendar.YEAR); //Get year
        
         for (int i=realYear-100; i<=realYear+100; i++){
            sltAnnee.addItem(String.valueOf(i));
        }
               
        currentMonth = realMonth; //Match month and year
        setlblMois(currentMonth);
        currentYear = realYear;
        setsltAnnee(currentYear);
              
        RefreshDate();
    }

    private void RefreshDate() throws SQLException{
        //On vide le panel
        panelJours.removeAll();
        
        String[] joursSemaine = {"Dimanche", "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi"};
        
        GregorianCalendar cal = new GregorianCalendar(currentYear, currentMonth, 1);
        Integer maxJoursMois = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH); //jour max dans le mois
        Integer firstDay = cal.get(GregorianCalendar.DAY_OF_WEEK) - 1;
        
         ArrayList<ArrayList<Match>> ListMatchsMois = getListeMatch(maxJoursMois);
        
        Integer totalHeight = 0;       
        for(int i = 0; i < maxJoursMois; i++){
            
            JourCalendar jc = new JourCalendar(i + 1, joursSemaine[firstDay], ListMatchsMois.get(i));
            panelJours.add(jc);
            
            totalHeight += 82;
            firstDay += 1;
            if(firstDay == 7) {
                firstDay = 0;
            }
        }

        panelJours.setPreferredSize(new Dimension(panelJours.getWidth(), totalHeight));
        panelJours.repaint();
        panelJours.revalidate();
        
    }
    
    private ArrayList<ArrayList<Match>> getListeMatch(Integer nbJourMois) throws SQLException {
        //requete SQL -> on récupère les matchs pour 1 mois et 1 année 
        //currentYear / currentMonth
        Connection c = AssistantPlanning.getConnection();
        MatchDAO mDao = new MatchDAO(c);
        
        ArrayList<Match> listMatchs = mDao.getMatchByMonth(currentYear, currentMonth + 1);
        //SIMULATION D'UNE LISTE retournée par la requête SQL
        /*Calendar c = new GregorianCalendar();
        c.set(2020, 1, 3, 10, 3);
        Match m1 = new Match(1, new Joueur(1, "MIRABEL", "Marion", 0), new Joueur(2, "REGNIER", "Clement", 0), 0, c, null);
        c = new GregorianCalendar();
        c.set(2020, 1, 5,  15, 30);
        Match m2 = new Match(1, new Joueur(1, "MIRABEL", "Marion", 0), new Joueur(2, "REGNIER", "Clement", 0), 0, c, null);       
        c = new GregorianCalendar();
        c.set(2020, 1, 5, 11, 50);
        Match m3 = new Match(1, new Joueur(3, "DOUDOU", "Cléo", 0), new Joueur(4, "MICH", "ZIZOU", 0), 0, c, new Score(2, 1));
        
        
        
        listMatchs.add(m1);
        listMatchs.add(m2);
        listMatchs.add(m3);*/
        //FIN SIMULATION
        
        ArrayList<ArrayList<Match>> ListMatchsMois = new ArrayList<>(nbJourMois);
        for(int i = 0; i < nbJourMois; i++){
            ArrayList<Match> temp = new ArrayList<>();
            for(Match m : listMatchs){
                Calendar cal = m.getDate();
                if(cal.get(GregorianCalendar.DAY_OF_MONTH) - 1 == i){
                    temp.add(m);
                }              
            }
            ListMatchsMois.add(i, temp);
        }
               
        return ListMatchsMois;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btPrecedent = new javax.swing.JButton();
        btSuivant = new javax.swing.JButton();
        lblMois = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        sltAnnee = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelJours = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btPrecedent.setText("<<");
        btPrecedent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrecedentActionPerformed(evt);
            }
        });

        btSuivant.setText(">>");
        btSuivant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSuivantActionPerformed(evt);
            }
        });

        lblMois.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMois.setText("Janvier");

        jLabel1.setText("Année :");

        sltAnnee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sltAnneeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(308, 308, 308)
                .addComponent(btPrecedent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMois, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btSuivant)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 157, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sltAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btPrecedent)
                            .addComponent(btSuivant)
                            .addComponent(jLabel1)
                            .addComponent(sltAnnee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lblMois, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jScrollPane1.setBorder(javax.swing.BorderFactory.createTitledBorder("Calendrier"));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(30000, 30000));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(800, 100));

        panelJours.setMaximumSize(new java.awt.Dimension(900, 32767));
        panelJours.setMinimumSize(new java.awt.Dimension(900, 600));
        panelJours.setPreferredSize(new java.awt.Dimension(900, 600));
        jScrollPane1.setViewportView(panelJours);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btSuivantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSuivantActionPerformed
        if (currentMonth == 11){ //Foward one year
            currentMonth = 0;
            currentYear += 1;
            setsltAnnee(currentYear);
        }
        else{ //Foward one month
            currentMonth += 1;
        }
        setlblMois(currentMonth);
        
        try {
            RefreshDate();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btSuivantActionPerformed

    private void btPrecedentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPrecedentActionPerformed
        if (currentMonth == 0){ //Back one year
            currentMonth = 11;
            currentYear -= 1;
            setsltAnnee(currentYear);
        }
        else{ //Back one month
            currentMonth -= 1;
        }
        setlblMois(currentMonth);
        
        try {
            RefreshDate();
        } catch (SQLException ex) {
            Logger.getLogger(CalendarView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btPrecedentActionPerformed

    private void sltAnneeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sltAnneeActionPerformed
        //La fonction est appelée au 1er chargement egalement, d'où le try and catch
        if (sltAnnee.getSelectedItem() != null){
            currentYear = Integer.parseInt(sltAnnee.getSelectedItem().toString());
            try{
             RefreshDate();
            } catch(Exception e){}
            
        }
    }//GEN-LAST:event_sltAnneeActionPerformed

 private void setlblMois(Integer mois){
        String[] months =  {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Août", "Septembre", "Octobre", "Novembre", "Decembre"};
        lblMois.setText(months[mois]);
    }
    private void setsltAnnee(Integer annee){
        sltAnnee.setSelectedItem(String.valueOf(annee));
    }
    
    private Integer currentMonth;
    private Integer currentYear;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btPrecedent;
    private javax.swing.JButton btSuivant;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMois;
    private javax.swing.JPanel panelJours;
    private javax.swing.JComboBox<String> sltAnnee;
    // End of variables declaration//GEN-END:variables
}
