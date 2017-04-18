package servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import gestion.Posicion;

public class ClienteMock {

	public static void main(String[] args) {
		try {
			Socket sc = new Socket("51.254.214.165", 8080);

			PrintStream salida = new PrintStream(sc.getOutputStream());
			salida.println("aaa");
			
			ObjectOutputStream oos=new ObjectOutputStream(sc.getOutputStream());
			Posicion p=new Posicion("Jorge",25.51,36.21);
			oos.writeObject(p);
			System.out.println(p.toString());
			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
