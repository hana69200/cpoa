/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Modification extends javax.swing.JFrame
{

	/**
	 * Creates new form Admin
	 */
	public Modification()
	{
		initComponents();
	}

	private void initComponents()
	{

		jDModifier = new javax.swing.JDialog();
		jPanel2 = new javax.swing.JPanel();
		jComboBox1 = new javax.swing.JComboBox<>();
		jLabel1 = new javax.swing.JLabel();
		jComboBox2 = new javax.swing.JComboBox<>();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jComboBox3 = new javax.swing.JComboBox<>();
		jComboBox4 = new javax.swing.JComboBox<>();
		header = new javax.swing.JPanel();
		name = new javax.swing.JLabel();
		retour = new javax.swing.JButton();
		noir = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		modif = new javax.swing.JButton();
		resultat = new javax.swing.JButton();
		match = new javax.swing.JButton();
		reserver = new javax.swing.JButton();

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));

		jComboBox1.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		jComboBox1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jComboBox1ActionPerformed(evt);
			}
		});

		jLabel1.setText("Match :");

		jComboBox2.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jLabel2.setText("Date :");

		jLabel3.setText("Joueur 1 :");

		jLabel4.setText("Joueur 2 :");

		jComboBox3.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		jComboBox4.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(44, 44, 44)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel1).addComponent(jLabel2).addComponent(jLabel3)
								.addComponent(jLabel4))
						.addGap(42, 42, 42)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(192, Short.MAX_VALUE)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addGap(35, 35, 35)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addGap(18, 18, 18)
						.addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel2Layout.createSequentialGroup().addComponent(jLabel1).addGap(34, 34, 34)
										.addComponent(jLabel3).addGap(51, 51, 51).addComponent(jLabel4))
								.addGroup(jPanel2Layout.createSequentialGroup()
										.addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(58, 58, 58)
										.addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18).addComponent(jComboBox4,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(83, Short.MAX_VALUE)));

		javax.swing.GroupLayout jDModifierLayout = new javax.swing.GroupLayout(jDModifier.getContentPane());
		jDModifier.getContentPane().setLayout(jDModifierLayout);
		jDModifierLayout.setHorizontalGroup(
				jDModifierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		jDModifierLayout.setVerticalGroup(
				jDModifierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jPanel2,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(1000, 800));

		header.setBackground(new java.awt.Color(255, 255, 255));
		header.setMinimumSize(new java.awt.Dimension(1000, 800));
		header.setPreferredSize(new java.awt.Dimension(408, 60));

		name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		name.setText("Assistant Planning");
		name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		retour.setText("Retour");
		retour.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				retourActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
		header.setLayout(headerLayout);
		headerLayout.setHorizontalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						headerLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 531,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(retour).addGap(39, 39, 39)));
		headerLayout.setVerticalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(retour))
						.addContainerGap()));

		noir.setBackground(new java.awt.Color(0, 0, 0));
		noir.setPreferredSize(new java.awt.Dimension(0, 5));

		javax.swing.GroupLayout noirLayout = new javax.swing.GroupLayout(noir);
		noir.setLayout(noirLayout);
		noirLayout.setHorizontalGroup(noirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		noirLayout.setVerticalGroup(noirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				5, Short.MAX_VALUE));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		jPanel1.setPreferredSize(new java.awt.Dimension(1000, 800));

		modif.setText("Modifier");
		modif.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				modifActionPerformed(evt);
			}
		});

		resultat.setText("Ajouter un résultat");
		resultat.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				resultatActionPerformed(evt);
			}
		});

		match.setText("Ajouter un Match");
		match.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				matchActionPerformed(evt);
			}
		});

		reserver.setText("Réserver un cours");
		reserver.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				reserverActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(modif, javax.swing.GroupLayout.PREFERRED_SIZE, 245,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(match, javax.swing.GroupLayout.PREFERRED_SIZE, 245,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(reserver, javax.swing.GroupLayout.PREFERRED_SIZE, 245,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(resultat, javax.swing.GroupLayout.PREFERRED_SIZE, 245,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(modif, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(match, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(resultat, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(reserver, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(659, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
				.addComponent(noir, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(noir, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void retourActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_retourActionPerformed
		this.setVisible(false);
		Connexion c = new Connexion();
		c.setVisible(true);
	}// GEN-LAST:event_retourActionPerformed

	private void modifActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_modifActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_modifActionPerformed

	private void reserverActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_reserverActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_reserverActionPerformed

	private void resultatActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_resultatActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_resultatActionPerformed

	private void matchActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_matchActionPerformed

		try
		{
			AjouterMatch m = new AjouterMatch();
			m.setVisible(true);
		} catch (SQLException ex)
		{
			Logger.getLogger(Modification.class.getName()).log(Level.SEVERE, null, ex);
		}
	}// GEN-LAST:event_matchActionPerformed

	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jComboBox1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jComboBox1ActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
		// (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
		 */
		try
		{
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
			{
				if ("Nimbus".equals(info.getName()))
				{
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex)
		{
			java.util.logging.Logger.getLogger(Modification.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(Modification.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(Modification.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(Modification.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new Modification().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JPanel header;
	private javax.swing.JComboBox<String> jComboBox1;
	private javax.swing.JComboBox<String> jComboBox2;
	private javax.swing.JComboBox<String> jComboBox3;
	private javax.swing.JComboBox<String> jComboBox4;
	private javax.swing.JDialog jDModifier;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JButton match;
	private javax.swing.JButton modif;
	private javax.swing.JLabel name;
	private javax.swing.JPanel noir;
	private javax.swing.JButton reserver;
	private javax.swing.JButton resultat;
	private javax.swing.JButton retour;
	// End of variables declaration//GEN-END:variables
}
