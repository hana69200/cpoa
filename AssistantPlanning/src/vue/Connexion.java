/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import baseDeDonnee.metierDAO.UtilisateurDAO;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Hashtable;

public class Connexion extends javax.swing.JFrame
{
	public Connexion()
	{
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents()
	{

		header = new javax.swing.JPanel();
		name = new javax.swing.JLabel();
		connexion = new javax.swing.JButton();
		noir = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		jPasswordField1 = new javax.swing.JPasswordField();
		jTextField1 = new javax.swing.JTextField();
		Valider = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setPreferredSize(new java.awt.Dimension(1000, 800));

		header.setBackground(new java.awt.Color(255, 255, 255));
		header.setPreferredSize(new java.awt.Dimension(408, 60));

		name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		name.setText("Assistant Planning");
		name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		connexion.setText("Retour");
		connexion.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				connexionActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
		header.setLayout(headerLayout);
		headerLayout.setHorizontalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, headerLayout.createSequentialGroup()
						.addContainerGap(247, Short.MAX_VALUE)
						.addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 531,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
						.addComponent(connexion).addGap(39, 39, 39)));
		headerLayout.setVerticalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						headerLayout.createSequentialGroup().addContainerGap()
								.addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
										.addComponent(connexion))
								.addContainerGap()));

		noir.setBackground(new java.awt.Color(0, 0, 0));

		javax.swing.GroupLayout noirLayout = new javax.swing.GroupLayout(noir);
		noir.setLayout(noirLayout);
		noirLayout.setHorizontalGroup(noirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		noirLayout.setVerticalGroup(noirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0,
				5, Short.MAX_VALUE));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		jPasswordField1.setText("exemple");
		jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter()
		{
			public void focusGained(java.awt.event.FocusEvent evt)
			{
				jPasswordField1FocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt)
			{
				jPasswordField1FocusLost(evt);
			}
		});
		jPasswordField1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jPasswordField1ActionPerformed(evt);
			}
		});

		jTextField1.setText("Identifiant");
		jTextField1.setName(""); // NOI18N
		jTextField1.addFocusListener(new java.awt.event.FocusAdapter()
		{
			public void focusGained(java.awt.event.FocusEvent evt)
			{
				jTextField1FocusGained(evt);
			}

			public void focusLost(java.awt.event.FocusEvent evt)
			{
				jTextField1FocusLost(evt);
			}
		});
		jTextField1.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				jTextField1ActionPerformed(evt);
			}
		});

		Valider.setText("Valider");
		Valider.addActionListener(new java.awt.event.ActionListener()
		{
			public void actionPerformed(java.awt.event.ActionEvent evt)
			{
				ValiderActionPerformed(evt);
			}
		});

		jLabel1.setFocusable(false);
		jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		jLabel2.setToolTipText("");
		jLabel2.setFocusable(false);
		jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(420, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(Valider))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addComponent(jPasswordField1).addComponent(jTextField1,
														javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 178,
														javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(249, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap(151, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
						.addComponent(Valider).addGap(307, 307, 307)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1037, Short.MAX_VALUE)
				.addComponent(noir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(noir, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0).addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void connexionActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_connexionActionPerformed
		this.setVisible(false);
		Main m = new Main();
		m.setVisible(true);
	}// GEN-LAST:event_connexionActionPerformed

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jTextField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jTextField1ActionPerformed

	private void ValiderActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_ValiderActionPerformed

		try
		{
			Class<?> c = Class.forName("com.mysql.cj.jdbc.Driver");
			Driver pilote = (Driver) c.getDeclaredConstructor().newInstance();
			DriverManager.registerDriver(pilote);
			final String protocole = "jdbc:mysql:";
			String ip = "iutdoua-web.univ-lyon1.fr";
			String port = "3306";
			String nomBase = "p1607863";
			String conString = protocole + "//" + ip + ":" + port + "/" + nomBase;
			String nomConnexion = "p1607863";
			String motDePasse = "270858";

			Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse);

			UtilisateurDAO u = new UtilisateurDAO(con);
			String myPass = String.valueOf(jPasswordField1.getPassword());

			if (!u.isUserOK(jTextField1.getText()))
			{
				jLabel2.setText("L'identifiant est inconnu");
			} else
			{
				jLabel2.setText("");

				if (!u.isPasswordOK(jTextField1.getText(), myPass))
				{
					jLabel1.setText("Le mot de passe est erroné");
				} else
				{
					jLabel1.setText("");
				}
			}

			if (u.isUserOK(jTextField1.getText()) && u.isPasswordOK(jTextField1.getText(), myPass))
			{
				Hashtable<String, Boolean> autorisation = u.getAutorisation(jTextField1.getText());
				autorisation.get("");

				this.setVisible(false);
				Modification m = new Modification();
				m.setVisible(true);

			}

		} catch (Exception e)
		{
			System.out.println(e.getMessage());
			// gestion des exceptions
		}

	}// GEN-LAST:event_ValiderActionPerformed

	private void jTextField1FocusGained(java.awt.event.FocusEvent evt)
	{// GEN-FIRST:event_jTextField1FocusGained
		if (jTextField1.getText().contentEquals("Identifiant"))
			jTextField1.setText("");
	}// GEN-LAST:event_jTextField1FocusGained

	private void jTextField1FocusLost(java.awt.event.FocusEvent evt)
	{// GEN-FIRST:event_jTextField1FocusLost
		if (jTextField1.getText().contentEquals(""))
			jTextField1.setText("Identifiant");
	}// GEN-LAST:event_jTextField1FocusLost

	private void jPasswordField1ActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_jPasswordField1ActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_jPasswordField1ActionPerformed

	private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt)
	{// GEN-FIRST:event_jPasswordField1FocusLost
		String myPass = String.valueOf(jPasswordField1.getPassword());
		if (myPass.contentEquals(""))
			jPasswordField1.setText("exemple");
	}// GEN-LAST:event_jPasswordField1FocusLost

	private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt)
	{// GEN-FIRST:event_jPasswordField1FocusGained
		String myPass = String.valueOf(jPasswordField1.getPassword());
		if (myPass.contentEquals("exemple"))
			jPasswordField1.setText("");
	}// GEN-LAST:event_jPasswordField1FocusGained

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[])
	{
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
			java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(Connexion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				new Connexion().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton Valider;
	private javax.swing.JButton connexion;
	private javax.swing.JPanel header;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPasswordField jPasswordField1;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JLabel name;
	private javax.swing.JPanel noir;
	// End of variables declaration//GEN-END:variables
}
