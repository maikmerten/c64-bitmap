/*
 */
package bitmapper.gui;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author merten
 */
public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	Image img;

	public void setImage(Image img) {
		this.img = img;
		if (img != null) {
			this.setSize(img.getWidth(this), img.getHeight(this));
		}

		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.drawImage(img, 0, 0, this);
		}

	}
}
