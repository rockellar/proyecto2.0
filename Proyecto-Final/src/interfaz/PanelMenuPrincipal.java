/* Dragon battle PCS
 * @author Carlos Eduardo lizalda valencia
 * @author Paola Andrea Veloza
 * @author Santiago Chasqui
 * @version 0.1B
 */
package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
/*
 * Clase que modela el panelMenuPrincipal extendiendo de JPanel e implementando MouseListener
 */
public class PanelMenuPrincipal extends JPanel implements MouseListener{


	//--------------------------------------
	// Relaciones
	//--------------------------------------

	/*
	 * Relacion con la ventana principal
	 */
	private VentanaPrincipal ventana;
	
	/*
	 * Constructor del PanelMenuPrincipal 
	 * @param recibe a la ventanaPrincipal
	 */
	public PanelMenuPrincipal(VentanaPrincipal v) {
		addMouseListener(this);
		ventana = v;
	}
	
	/*
	 * Metodo que pinta un objeto de tipo Graphics
	 * @param objeto e tipo Graphics
	 */
	@Override
	public void paint(Graphics g) {

		// Cargo la imagen que sera usada como banner para el juego
		Image fondo = new ImageIcon("data/fondo/Banner.png").getImage();
		g.drawImage(fondo, 0, 0,null);


		// Selecciono una fuente para las opciones
		g.setFont(new Font("Helvetica", Font.BOLD, 35));
		// Selecciono un color para las opciones
		g.setColor(Color.WHITE);
		// Dibujo las opciones
		g.drawString("Seleccionar Jugador Uno", 110, 166);
		g.drawString("Seleccionar Jugador Dos", 110, 260);
		g.drawString("Iniciar Batalla", 110, 360);
		g.drawString("Ver Puntajes Guardados", 110, 454);
		g.drawString("Cr�ditos", 110, 548);

	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
	
	/*
	 * Metodo que escucha el evento del Listener
	 * @param un objeto de tipo MouseEvents
	 */
	@Override
	public void mousePressed(MouseEvent e) {

		int posX = e.getX();
		int posY = e.getY();

		//****************************************//
		// Area de opcion de seleccion jugador 1
		//****************************************//
		if (posX > 45 && posX < 625 && posY > 110 && posY < 190) {
			ventana.agregarPanelJugador(1);
		}
		//****************************************//
		// Area de opcion de seleccion jugador 2
		//****************************************//
		else if (posX > 45 && posX < 625 && posY > 195 && posY < 290) {
			ventana.agregarPanelJugador(2);
		}
		//****************************************
		// Area de opcion de seleccion Escenario
		//****************************************
		else if (posX > 45 && posX < 625 && posY > 295 && posY < 390) {
			ventana.agregarPanelEscenario();
		}

		//****************************************
		// Area de opcion de puntajes 
		//****************************************
		else if (posX > 45 && posX < 625 && posY > 395 && posY < 490) {
			ventana.agregarPanelPuntajes();
		}

		//****************************************
		// �rea de panelCreditos
		//****************************************
		else if(posX > 45 && posX < 625 && posY > 495 && posY < 590) {
			ventana.agregarPanelCreditos();
		}


		repaint();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
