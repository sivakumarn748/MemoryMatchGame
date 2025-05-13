package gamePack;

import javax.swing.*;
import java.awt.event.*;

public class Box implements MouseListener {
	
	public JLabel l = new JLabel();
	public String key;
	private GameSetup master;
	public boolean shown = false;
	
	public void show() {
		l.setText(key);
		shown = true;
	}
	
	public void hide() {
		l.setText("");
		shown = false;
	}
	
	public String name() {
		return key;
	}
	
	private void setup() {
		l.setOpaque(true);
		l.setSize(20, 20);
		l.addMouseListener(this);
	}
	
	public Box(GameSetup g, char a) {
		master = g;
		key = ""+a;
		setup();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if (shown) {
			hide();
			
			if(this == master.a) {
				master.a = master.b;
				master.b = new Box(null, ',');
			}
			else if(this == master.b) {
				master.b = new Box(null, ',');
			}
			
		}
		else {
			show();

			if(master.a == null)
				master.a = new Box(null, ',');
			
			master.evaluate(this);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
