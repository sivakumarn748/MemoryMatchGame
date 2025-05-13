package gamePack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class GameSetup {
	public Box a = new Box(this, '.');
	public Box b = new Box(this, ',');
	
	private int length = 0;
	private int height = 0;
	
	public JFrame w = new JFrame();
	public JPanel panel = new JPanel();
	
	
	// Setting up Window Layout
	private void setupWindow() {
		w.add(panel);

		w.setBounds(0, 0, (length+2)*20, (height+2)*20);
		
		w.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// Exits on Right Click
				if(e.getButton() == MouseEvent.BUTTON3)
					System.exit(0);
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		w.setLayout(null);
	}
	
	// Holds all the keys
	private ArrayList<Box> keys = new ArrayList<Box>();
	
	// sets a new box of specified key on specific location
	private void newBox(int x, int y, char s) {
		Box b = new Box(this, s);
		keys.add(b);
		
		panel.add(b.l);
		b.l.setLocation(x, y);
	}
	
	// key variables
	String data = "ABCXYZIJKLMN";
	Random random = new Random();
	
	// returns the string of all the keys
	private String getKeys() {
		String keys = "";
		int index = 0;
		
		for(int i = 0; i < length*height/2; i++) {
			index = random.nextInt(data.length());
			keys = keys + data.charAt(index);
		}// half amount of keys are ready
	
		// half amount of keys is doubled
		keys = keys + keys;
			
		return keys;
	}
	
	// Setting up of the panel inside the main window
	private void setupPanel() {
		String k = getKeys();
		
		int x = 0;
		
		// Adding keys to the panel by looping through length and width
		for(int i = 0; i < length; i++) {
			for(int j = 0; j < height; j++) {
				newBox(i*20 + i, j*20 + j, k.charAt(x));  // Calls for a new box on specified location and specified key
				x = x + 1;
			}
		}
		
		panel.setBackground(Color.cyan);
		panel.setBounds(0, 0, length*20 + length, height*20 + height);
		panel.setLayout(null);
	}
	
	// returns if the selected keys are same
	public boolean evaluate(Box i) {
		a.hide();

		// Algorithm for next step
		a = b;
		b = i;
		
		// Checks the keys whether they are same
		if(a.key.compareTo(b.key) == 0) {
			
			// if same, their layout and accessibility are changed
			a.l.setBackground(Color.cyan);
			b.l.setBackground(Color.cyan);
			a.l.setForeground(Color.white);
			b.l.setForeground(Color.white);
			
			a.l.removeMouseListener(a);
			b.l.removeMouseListener(b);
			a = new Box(null, '.');
			b = new Box(null, ',');
			
			return true;
		}
		return false;
	}

	// Constructor of the GameSetup for preferred size
	public GameSetup(int l, int h) {
			if(l%2 == 1)	l = l + 1;
			if(h%2 == 1)	h = h + 1;
			
			length = l;
			height = h;
			
			setupWindow();
			setupPanel();			
		}
	
	// Default GameSetup
	public GameSetup() {
		length = 10;
		height = 10;
		
		setupWindow();
		setupPanel();				
	}
	
	// Sets the screen visible to player
	public void startGame() {
		w.setVisible(true);
	}
	}
