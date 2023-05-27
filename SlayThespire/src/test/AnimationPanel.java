package test;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class AnimationPanel extends JPanel implements ActionListener {
	private BufferedImage[] frames;
	private int currentFrame = 0;
	private Timer timer;
	private int delay;
	private int frameCount;

	public AnimationPanel(String filename, int tdelay, int frameCount) {
		super();
		this.delay = tdelay;
		this.frameCount = frameCount;
		BufferedImage image = loadImage(filename);
		frames = new BufferedImage[frameCount];
		int frameWidth = image.getWidth() / frameCount;
		int frameHeight = image.getHeight();
		for (int i = 0; i < frameCount; i++) {
			int x = (i % frameCount) * frameWidth;
			int y = 0;
        frames[i] = image.getSubimage(x, y, frameWidth, frameHeight);
		}
		timer = new Timer(delay, (ActionListener) this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(frames[currentFrame], 0, 0, null);
	}

	public void actionPerformed(ActionEvent e) {
		currentFrame = (currentFrame + 1) % frameCount;
		repaint();
	}

	private static BufferedImage loadImage(String filename) {
		try {
			return ImageIO.read(new File(filename));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void showAnimation() {
	    JFrame frame = new JFrame("Character Animation");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(300, 300);
	    frame.setContentPane(this);
	    frame.setVisible(true);
	}
}
