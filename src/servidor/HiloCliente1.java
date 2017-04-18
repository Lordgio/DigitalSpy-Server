package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import gestion.GestionDatosApp1;
import gestion.Posicion;

public class HiloCliente1 extends Thread {

	Socket scCliente;
	GestionDatosApp1 gestion;

	public HiloCliente1(Socket sc) {
		scCliente = sc;
		gestion = new GestionDatosApp1();
	}

	@Override
	public void run() {
		try {
			/*
			 * //Leer JSON y cerrar socket. BufferedReader bf=new
			 * BufferedReader(new
			 * InputStreamReader(scCliente.getInputStream())); String
			 * s=bf.readLine(); System.out.println(s);
			 */

			ObjectInputStream ois = new ObjectInputStream(scCliente.getInputStream());
			Posicion p = (Posicion) ois.readObject();
			System.out.println(p.toString());
			scCliente.close();
			gestion.guardarDatos(p);

		} catch (IOException ex) {
			ex.getStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
