package servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

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
			
			 //Leer JSON y cerrar socket. 
			 BufferedReader bf=new BufferedReader(new InputStreamReader(scCliente.getInputStream())); 
			 String s=bf.readLine(); 
			 System.out.println(s);
			 try{
				 JSONParser jp=new JSONParser();
				 JSONArray jarray=(JSONArray) jp.parse(s);
				 JSONObject job=(JSONObject) jarray.get(0);
				 Posicion p=new Posicion(((String)job.get("nombre")),Double.parseDouble(job.get("longitud")+""),Double.parseDouble(job.get("latitud")+""));
				 gestion.guardarDatos(p);
			 } catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ObjectInputStream ois = new ObjectInputStream(scCliente.getInputStream());
			//Posicion p = (Posicion) ois.readObject();
			//System.out.println(p.toString());
			scCliente.close();

		} catch (IOException ex) {
			ex.getStackTrace();
		}
	}
}
