package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gestion.GestionDatosApp2;
import gestion.Posicion;

public class HiloCliente3 extends Thread {

	Socket scCliente;
	GestionDatosApp2 gestion;

	public HiloCliente3(Socket sc) {
		scCliente = sc;
		gestion = new GestionDatosApp2();
	}

	@Override
	public void run() {
		try {
			PrintStream salida = new PrintStream(scCliente.getOutputStream());
			salida.println(enviar());
			scCliente.close();
		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}

	private String enviar() {
		ArrayList<String> nombres = gestion.getNombres();
		JSONArray jarray = new JSONArray();
		for(int i = 0; i < nombres.size(); i++) {
			System.out.println(nombres.get(i));
		}
		for (int i = 0; i < nombres.size(); i++) {
			JSONObject job = new JSONObject();
			job.put("nombre", nombres.get(i));
			jarray.add(job);
		}
		String s = jarray.toJSONString();
		return s;
	}
}