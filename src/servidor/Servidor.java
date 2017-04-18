package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) {
		try {
			// Crea un servidor de socket para comunicarse con clientes.
			ServerSocket server = new ServerSocket(8000);
			System.out.println("Esperando llamadas...");

			while (true) {
				// Espera la llamada de un cliente y lo identifica.
				Socket cliente = server.accept();
				System.out.println("Llamada recibida");
				BufferedReader bf = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String s = bf.readLine();

				// Crea un nuevo hilo para atender al cliente dependiendo del
				// tipo de app.
				switch (s) {
				case "aaa":
					System.out.println("Cliente tipo 1");
					new HiloCliente1(cliente).start();
					break;
				case "bbb":
					System.out.println("Cliente tipo 2");
					new HiloCliente2(cliente).start();
					break;
				}
			}
		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
}
