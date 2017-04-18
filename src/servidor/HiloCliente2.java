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

public class HiloCliente2 extends Thread {

	Socket scCliente;
	GestionDatosApp2 gestion;

	public HiloCliente2(Socket sc) {
		scCliente = sc;
		gestion = new GestionDatosApp2();
	}

	@Override
	public void run() {
		try {
			BufferedReader bf = new BufferedReader(new InputStreamReader(scCliente.getInputStream()));
			String nombre = bf.readLine();

			PrintStream salida = new PrintStream(scCliente.getOutputStream());
			salida.println(enviar(nombre));
			scCliente.close();
		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}

	private String enviar(String nombre) {
		ArrayList<Posicion> pos = gestion.getDatos(nombre);
		JSONArray jarray = new JSONArray();

		for (int i = 0; i < pos.size(); i++) {
			JSONObject job = new JSONObject();
			job.put("nombre", pos.get(i).getNombre());
			job.put("longitud", pos.get(i).getLongitud());
			job.put("Latitud", pos.get(i).getLatitud());
			jarray.add(job);
		}
		String s = jarray.toJSONString();
		return s;
	}
}
