/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import java.awt.BorderLayout;

public class Main extends javax.swing.JFrame
{

	/**
	 * Creates new form Main
	 */
	public Main()
	{
		initComponents();
		Planning c = new Planning();
		this.jPanel2.add(c.getCalendar(), BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	private void initComponents()
	{

		header = new javax.swing.JPanel();
		name = new javax.swing.JLabel();
		connexion = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jPanel3 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setBackground(new java.awt.Color(0, 0, 0));
		setPreferredSize(new java.awt.Dimension(1000, 800));
		setResizable(false);

		header.setBackground(new java.awt.Color(255, 255, 255));
		header.setPreferredSize(new java.awt.Dimension(408, 60));

		name.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		name.setText("Assistant Planning");
		name.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

		connexion.setText("Connexion");
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
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						headerLayout.createSequentialGroup()
								.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, 531,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(110, 110, 110).addComponent(connexion).addGap(39, 39, 39)));
		headerLayout.setVerticalGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						headerLayout.createSequentialGroup().addContainerGap()
								.addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(name, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
										.addComponent(connexion))
								.addContainerGap()));

		jPanel2.setBackground(new java.awt.Color(255, 255, 255));
		jPanel2.setPreferredSize(new java.awt.Dimension(700, 390));

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 1000, Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 736, Short.MAX_VALUE));

		jPanel3.setBackground(new java.awt.Color(0, 0, 0));

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 5, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, 0)
						.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(0, 0, Short.MAX_VALUE).addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
								736, javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void connexionActionPerformed(java.awt.event.ActionEvent evt)
	{// GEN-FIRST:event_connexionActionPerformed
		this.setVisible(false);
		Connexion c = new Connexion();
		c.setVisible(true);
	}// GEN-LAST:event_connexionActionPerformed

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
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex)
		{
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex)
		{
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex)
		{
			java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				Main a = new Main();
				a.setVisible(true);
				a.setResizable(false);

			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton connexion;
	private javax.swing.JPanel header;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JLabel name;
	// End of variables declaration//GEN-END:variables
}