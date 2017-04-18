package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClienteMock2 {

	public static void main(String[] args) {
		try {
			Socket sc = new Socket("51.254.214.165", 8080);

			PrintStream salida = new PrintStream(sc.getOutputStream());
			salida.println("bbb");
			salida.println("Jorge");

			BufferedReader bf = new BufferedReader(new InputStreamReader(sc.getInputStream()));
			String s = bf.readLine();
			System.out.println(s);
			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
