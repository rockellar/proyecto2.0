package interfaz;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import hilos.HiloCreditos;

public class PanelCreditos extends JPanel  {
	
	private boolean muere;
	
	private int posY; 
	
	public PanelCreditos() {
		
		setVisible(true);
		muere = false;
		posY = 0;
		
	}
	
	public boolean darMuere() {
		
		return muere;
	}
	
	public void cambiarMuere(boolean mu) {
		muere = mu;
	}
	
	public void paint(Graphics g) {
		
		Image fondo = new ImageIcon("data/fondo/fondoCreditos.png").getImage();
		g.drawImage(fondo, 0, 0,null);
		
		pintarMuņecos(g);
		pintarCreditos(g);
	}
	
	public void pintarMuņecos(Graphics g) {
		//Image escenario = new ImageIcon("data/vistaPreviaPersonajes/NPE" +i + ".png").getImage();
		//System.out.println("data/fondoEscenario/escenario" + + ".png");
		//g.drawImage(escenario, 0, 0,null);
		Image fondo = new ImageIcon("data/vistaPreviaPersonajes/NPE3.png").getImage();
		g.drawImage(fondo, 0, 0,null);
		//g.getFont().drawString(0, 0, "Lero");
		
	}
	public void pintarCreditos(Graphics g) {
		Image creditos = new ImageIcon("data/fondo/creditos.png").getImage();
		g.drawImage(creditos, 640, posY++,null);
		
	}	
	
}
