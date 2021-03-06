/*
 */
package bitmapper.gui;

import bitmapper.MulticolorBitmap;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

/**
 *
 * @author merten
 */
public class GUI extends javax.swing.JFrame {

	private MulticolorBitmap bitmap;
	private ImagePanel imagepanel;
	private Image encodedImage;
	
	
	private void encode() {
		File f = new File(jTextFieldFile.getText());
		jButtonSave.setEnabled(false);
		
		try {
			BufferedImage buf = ImageIO.read(f);
			bitmap = new MulticolorBitmap();
			bitmap.setRounds(jSliderRounds.getValue());
			bitmap.setDitherScale((jSliderDither.getValue() * 1.0) / 100.0);
			bitmap.setScatterScale((jSliderScatter.getValue() * 1.0) / 100.0);
			
			bitmap.encodeImage(buf);
			
			encodedImage = bitmap.getEncodedImage().getScaledInstance(640, 400, Image.SCALE_FAST);
			imagepanel.setImage(encodedImage);
			
			jButtonSave.setEnabled(true);
			
		} catch (Exception e) {
			// yes, do nothing here
		}
	}
	
	/**
	 * Creates new form GUI
	 */
	public GUI() {
		initComponents();
		
		jPanelOutput.setLayout(new BorderLayout());
		imagepanel = new ImagePanel();
		jPanelOutput.add(imagepanel, BorderLayout.CENTER);
		
	}

	/**
	 * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
	 * content of this method is always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextFieldFile = new javax.swing.JTextField();
        jButtonChooseFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSliderRounds = new javax.swing.JSlider();
        jSliderDither = new javax.swing.JSlider();
        jSliderScatter = new javax.swing.JSlider();
        jLabel3 = new javax.swing.JLabel();
        jButtonConvert = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelOutput = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("C64 Bitmap Converter");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Settings"));

        jTextFieldFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFileActionPerformed(evt);
            }
        });

        jButtonChooseFile.setText("Choose File");
        jButtonChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChooseFileActionPerformed(evt);
            }
        });

        jLabel1.setText("Refinement rounds:");

        jLabel2.setText("Dither strength (%):");

        jSliderRounds.setMajorTickSpacing(1);
        jSliderRounds.setMaximum(10);
        jSliderRounds.setMinimum(1);
        jSliderRounds.setPaintLabels(true);
        jSliderRounds.setPaintTicks(true);
        jSliderRounds.setSnapToTicks(true);
        jSliderRounds.setValue(5);

        jSliderDither.setMajorTickSpacing(10);
        jSliderDither.setMinorTickSpacing(1);
        jSliderDither.setPaintLabels(true);
        jSliderDither.setPaintTicks(true);
        jSliderDither.setSnapToTicks(true);
        jSliderDither.setValue(10);

        jSliderScatter.setMajorTickSpacing(10);
        jSliderScatter.setMinorTickSpacing(1);
        jSliderScatter.setPaintLabels(true);
        jSliderScatter.setPaintTicks(true);
        jSliderScatter.setSnapToTicks(true);

        jLabel3.setText("Dither scatter (%):");

        jButtonConvert.setText("Convert");
        jButtonConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConvertActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.setEnabled(false);
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSliderRounds, javax.swing.GroupLayout.DEFAULT_SIZE, 595, Short.MAX_VALUE)
                            .addComponent(jSliderDither, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSliderScatter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonChooseFile, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConvert)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonChooseFile)
                    .addComponent(jButtonConvert)
                    .addComponent(jButtonSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jSliderRounds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jSliderDither, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSliderScatter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Result"));

        jPanelOutput.setMinimumSize(new java.awt.Dimension(640, 400));

        javax.swing.GroupLayout jPanelOutputLayout = new javax.swing.GroupLayout(jPanelOutput);
        jPanelOutput.setLayout(jPanelOutputLayout);
        jPanelOutputLayout.setHorizontalGroup(
            jPanelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 754, Short.MAX_VALUE)
        );
        jPanelOutputLayout.setVerticalGroup(
            jPanelOutputLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 423, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanelOutput);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFileActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFileActionPerformed

    private void jButtonChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChooseFileActionPerformed
		JFileChooser fc = new JFileChooser();
		int retval = fc.showOpenDialog(jPanel1);

		if (retval == JFileChooser.APPROVE_OPTION) {
			jTextFieldFile.setText(fc.getSelectedFile().getAbsolutePath());
		}

    }//GEN-LAST:event_jButtonChooseFileActionPerformed

    private void jButtonConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConvertActionPerformed
		encode();
    }//GEN-LAST:event_jButtonConvertActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
		JFileChooser fc = new JFileChooser();
		
		int retval = fc.showSaveDialog(this);
		if(retval == JFileChooser.APPROVE_OPTION) {
			FileOutputStream fos = null;
			try {
				File f = fc.getSelectedFile();
				fos = new FileOutputStream(f);
				
				bitmap.writeKoala(fos);
				
			} catch (Exception ex) {
				Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					fos.close();
				} catch (IOException ex) {
					Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
    }//GEN-LAST:event_jButtonSaveActionPerformed

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new GUI().setVisible(true);
			}
		});
	}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonChooseFile;
    private javax.swing.JButton jButtonConvert;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelOutput;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSliderDither;
    private javax.swing.JSlider jSliderRounds;
    private javax.swing.JSlider jSliderScatter;
    private javax.swing.JTextField jTextFieldFile;
    // End of variables declaration//GEN-END:variables
}
