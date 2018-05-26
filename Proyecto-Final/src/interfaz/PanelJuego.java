package interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hilos.HiloAnimaciones;
import hilos.HiloAtaqueDistancia;
import hilos.HiloJuego;
import modelo.AtaqueDistancia;
import modelo.Jugador;
import modelo.JugadorNoSeleccionadoException;

public class PanelJuego extends JPanel implements KeyListener{
	
	//Relaciones 
	public VentanaPrincipal ventana;
	
	//Constantes
	public final static int FLECHA_ARRIBA = 40;
	public final static int FLECHA_DERECHA = 39;
	public final static int FLECHA_ABAJO = 38;
	public final static int FLECHA_IZQUIERDA = 37;
	public static final int NUMERO_CERO = 96;
	public static final int NUMERO_UNO = 97;
	public static final int NUMERO_DOS = 98;
	public static final int NUMERO_TRES = 99;
	public static final int NUMERO_CUATRO = 100;
	public static final int NUMERO_CINCO = 101;
	public static final int NUMERO_SEIS = 102;
	
	//Teclas para el Jugador 2
	public final static int W = 83;
	public final static int D = 68;
	public static final int S = 87;
	public static final int A = 65;
	public static final int G = 71;
	public static final int H = 72;
	public static final int J = 74;
	public static final int R = 82;
	public static final int T = 84;
	public static final int Y = 89;
	public static final int U = 85;
	
	public final Set<Integer> pressed = new HashSet<Integer>();
	
	private boolean modificando;
	
	private boolean acabo;
	
	public PanelJuego(VentanaPrincipal ventana) {
		
		this.ventana = ventana;
		
		setVisible(true);

	}
	
	public boolean modificando(){
		return modificando;
	}
	
	@Override
	public void paint(Graphics g) {

		pintarFondo(g);

		pintarPersonajes(g);

		AtaqueDistancia a = ventana.darJuego().darBatalla().darJugador1().darPersonaje().darAtaqueDistancia();


		while (a != null) {
			ImageIcon spriteAtaque = new ImageIcon(a.darSprite());
			g.drawImage(spriteAtaque.getImage(), a.darPosX(), a.darPosY(), null);
			a = a.darSiguiente();
		}

		a = ventana.darJuego().darBatalla().darJugador2().darPersonaje().darAtaqueDistancia();
		while (a != null) {
			ImageIcon spriteAtaque = new ImageIcon(a.darSprite());
			g.drawImage(spriteAtaque.getImage(), a.darPosX(), a.darPosY(), null);
			a = a.darSiguiente();
		}
		
		pintarReloj(g);
		
		pintarBarras(g);
	
	}
	private void pintarReloj(Graphics g) {
		g.setColor(new Color((int) (Math.random()*5), (int) (Math.random()*5), (int) (Math.random()*5)));
		g.setFont(new Font("Arial", Font.BOLD, 40));
		g.drawString(ventana.darJuego().darBatalla().darTiempoActual() + "", 600, 80);
	}

	private void pintarPersonajes(Graphics g) {
		Jugador temp = ventana.darJuego().darBatalla().darJugador1();
		
		Image sprite = temp.darPersonaje().darSprite();
		g.drawImage(sprite, temp.darPersonaje().darPosX(),
				temp.darPersonaje().darPosY(), null);

		temp = ventana.darJuego().darBatalla().darJugador2();
		Image sprite2 = temp.darPersonaje().darSprite();
		g.drawImage(sprite2, temp.darPersonaje().darPosX(),
				temp.darPersonaje().darPosY(), null);
		
	}

	public void pintarFondo(Graphics g) {
		ImageIcon fondo = new ImageIcon("data/fondoEscenario/"+ventana.darJuego().darBatalla().darFondos()+".png");
		g.drawImage(fondo.getImage(), 0, 0, null);
	}

	public void pintarBarras(Graphics g) {

		g.setColor(Color.red);
		// Pintar vida de jugador 1
		g.drawRect(30, 50, 500, 30);
		g.fillRect(30, 50, ventana.darJuego().darBatalla().darJugador1().darSaludActual(), 30);
		// Pintar vida Jugador 2
		g.drawRect(730, 50, 500, 30);
		g.fillRect(730, 50, ventana.darJuego().darBatalla().darJugador2().darSaludActual(), 30);
		
		g.setColor(Color.BLUE);
		//Pintar Ki Jugador 1
		g.drawRect(30, 80, 500, 30);
		g.fillRect(30, 80, ventana.darJuego().darBatalla().darJugador1().darKiActual(), 30);
		//Pintar Ki Jugador 2
		g.drawRect(730, 80, 500, 30);
		g.fillRect(730, 80, ventana.darJuego().darBatalla().darJugador2().darKiActual(), 30);
		
		
		g.drawString(ventana.darJuego().darBatalla().darJugador1().darNickName(), 30, 150);
		g.drawString(ventana.darJuego().darBatalla().darJugador2().darNickName(), 730, 150);

	}

	@Override
	public synchronized void keyPressed(KeyEvent e) {
		modificando = true;
		pressed.add(e.getKeyCode());
		modificando = false;
	}

	public void moverPersonaje1() {
		
		Jugador temporal = ventana.darJuego().darBatalla().darJugador1();

		if(!temporal.darPersonaje().atacando()) {
			temporal.darPersonaje().quietotrue();
		}

		if (!modificando) {
			Set<Integer> temp = new HashSet<Integer>(pressed);

			if (temp.size() > 0) {
				for (int c : temp) {
					System.out.println(c);
					if (c == FLECHA_IZQUIERDA) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().moverX(-12);
					} else if (c == FLECHA_ABAJO) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().moverY(-12);
					} else if (c == FLECHA_DERECHA) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().moverX(12);
					} else if (c == FLECHA_ARRIBA) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().moverY(12);
					} else if (c == NUMERO_UNO) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().atacarPuño();
					} else if(c == NUMERO_DOS) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().atacarPatada();
					} else if (c == NUMERO_CUATRO) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().lanzarAtaqueDistanteMediano();
					} else if (c == NUMERO_CINCO) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().lanzarAtaqueDistantePequeño();
					} else if (c == NUMERO_SEIS) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().lanzarAtaqueDistanteGrande();
					} else if(c==NUMERO_CERO) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().recargarKi();
					} else if(c==NUMERO_TRES) {
						ventana.darJuego().darBatalla().darJugador1().darPersonaje().defender();
					}

				}
			}
		}

	}

	public void moverPersonaje2() {
		
		Jugador temporal = ventana.darJuego().darBatalla().darJugador2();

		if(!temporal.darPersonaje().atacando()) {
			temporal.darPersonaje().quietotrue();
		}

		if (!modificando) {
			Set<Integer> temp = new HashSet<Integer>(pressed);

			if (temp.size() > 0) {
				for (int c : temp) {
					if(c == A) {
						temporal.darPersonaje().moverX(-12);
					}else if(c == S) {
						temporal.darPersonaje().moverY(-12);
					}else if(c == D) {
						temporal.darPersonaje().moverX(12);
					}else if(c == W) {
						temporal.darPersonaje().moverY(12);
					}else if(c == G) {
						temporal.darPersonaje().atacarPuño();
					}else if(c == H){
						temporal.darPersonaje().atacarPatada();
					}else if(c == T) {
						temporal.darPersonaje().lanzarAtaqueDistanteMediano();
					}else if(c == Y) {
						temporal.darPersonaje().lanzarAtaqueDistantePequeño();
					}else if(c == U) {
						temporal.darPersonaje().lanzarAtaqueDistanteGrande();
					}else if(c == R) {
						temporal.darPersonaje().recargarKi();;
					}else if(c == J) {
						temporal.darPersonaje().defender();
					}

				}
			}
		}

	}

	@Override
	public synchronized void keyReleased(KeyEvent e) {
		modificando = true;
		pressed.remove(e.getKeyCode());
		modificando = false;
		
		try {
			
			int c = e.getKeyCode();
			
			if (c == FLECHA_IZQUIERDA) {
				ventana.darJuego().darBatalla().darJugador1().darPersonaje().quietotrue();
			} else if (c == FLECHA_DERECHA) {
				ventana.darJuego().darBatalla().darJugador1().darPersonaje().quietotrue();
			} else if (c == A) {
				ventana.darJuego().darBatalla().darJugador2().darPersonaje().quietotrue();
			} else if (c == D) {
				ventana.darJuego().darBatalla().darJugador2().darPersonaje().quietotrue();
			} else if (c == J) {
//				ventana.darJuego().darBatalla().darJugador2().darPersonaje().normalizarResistencia();
			}
		}catch(NullPointerException exception) {
			JOptionPane.showMessageDialog(null, "Guarde sus ataques para el juego", "Tecla presionado antes de empezar", JOptionPane.INFORMATION_MESSAGE);
		}

	}
	
	public boolean darAcabo() {
		return acabo;
	}
	
	public void cambiarAcabo(boolean parametro) {
		acabo = parametro;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mostrarMensajeFianal(int personaje) {

		acabo = true;
		
		personaje = personaje == 1? 2:1;
		
		String nombre = personaje == 1? ventana.darJuego().darBatalla().darJugador1().darNickName() : ventana.darJuego().darBatalla().darJugador2().darNickName();
		
		JOptionPane.showMessageDialog(this, "Felicidades " + nombre + " Has ganado 100 puntos", "Fin de la batalla", JOptionPane.DEFAULT_OPTION);
		
		ventana.darJuego().darPuntos(personaje);
		
		ventana.agregarPanelMenuPrincipal(this);
		
	}

}

