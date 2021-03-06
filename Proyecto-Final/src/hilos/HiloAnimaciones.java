package hilos;

import interfaz.PanelJuego;
import interfaz.VentanaPrincipal;
import modelo.Juego;
import modelo.Jugador;

public class HiloAnimaciones extends Thread {

	private Juego mundo;
	private VentanaPrincipal ventana;
	private int personaje;
	int time;


	public HiloAnimaciones(Juego m, VentanaPrincipal v, int personaje) {

		mundo = m;
		ventana = v;
		this.personaje = personaje;
	}

	@Override
	public void run() {

		Jugador playerDelHilo = mundo.darJugadoresEnBatalla()[personaje-1];

		while (!ventana.darPanelJuego().darAcabo() && playerDelHilo.darSaludActual() > 0) {

			playerDelHilo.darPersonaje().actualizar();
			if (!ventana.darPanelJuego().modificando()) {
				if (personaje == 1) {
					ventana.darPanelJuego().moverPersonaje1();
				}else {
					ventana.darPanelJuego().moverPersonaje2();
				}
			}
			time++;
			if ((playerDelHilo.darKiActual() / 500) * playerDelHilo.darKiMaximo() != playerDelHilo.darKiMaximo() && time > 13) {
				playerDelHilo.darPersonaje().recuperarKi();
				time = 0;
			}

			try {
				int i = (int) (playerDelHilo.darPersonaje().darVelocidad() * 0.4);
//				System.out.println(personaje + ": " +i);
				sleep(50+i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		if (!ventana.darPanelJuego().darAcabo()) {
			ventana.darPanelJuego().mostrarMensajeFianal(personaje);
		}

		

	}

}
