package servidor;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import gestion.Posicion;

public class ClienteMock {

	public static void main(String[] args) {
		try {
			Socket sc = new Socket("localhost", 8000);

			PrintStream salida = new PrintStream(sc.getOutputStream());
			salida.println("aaa");
			
			//ObjectOutputStream oos=new ObjectOutputStream(sc.getOutputStream());
			Posicion p=new Posicion("Jorge",25.51,36.21);
			JSONObject job=new JSONObject();
			job.put("nombre", p.getNombre());
			job.put("longitud", p.getLongitud());
			job.put("latitud", p.getLatitud());
			
			JSONArray jarray=new JSONArray();
			jarray.add(job);
			String s=jarray.toJSONString();
			salida.println(s);
			//oos.writeObject(p);
			System.out.println(s);
			sc.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
