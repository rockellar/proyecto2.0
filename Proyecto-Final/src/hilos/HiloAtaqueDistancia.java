package hilos;

import interfaz.PanelJuego;
import interfaz.VentanaPrincipal;
import modelo.AtaqueDistancia;
import modelo.Juego;

public class HiloAtaqueDistancia extends Thread{

	private Juego miJuego;

	private VentanaPrincipal interfaz;

	public HiloAtaqueDistancia(Juego game, VentanaPrincipal v) {

		miJuego = game;

		interfaz = v;
	}

	public void run() {

		PanelJuego panel = interfaz.darPanelJuego();

		while(true && !panel.darAcabo()) {


			AtaqueDistancia actualUno = miJuego.darBatalla().darJugador1().darPersonaje().darAtaqueDistancia();
			AtaqueDistancia actualDos = miJuego.darBatalla().darJugador2().darPersonaje().darAtaqueDistancia();

			int i =1;
			while (actualUno != null) {

				actualUno.moverX();
				actualUno = actualUno.darSiguiente();

				System.out.println(i);
				i++;
			}

			while (actualDos != null) {

				actualDos.moverX();
				actualDos = actualDos.darSiguiente();

			}

			miJuego.darBatalla().darJugador1().darPersonaje().limpiarAtaques();
			miJuego.darBatalla().darJugador2().darPersonaje().limpiarAtaques();

			try {
				sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
