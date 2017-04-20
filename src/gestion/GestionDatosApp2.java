package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GestionDatosApp2 {
	
	private String nombredb="control";
    private String ip="localhost";
    private String port="3306";
    private String user="root";
    private String password="root";

	public ArrayList<String> getNombres(){
		ArrayList<String> nombres=new ArrayList<>();
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos";
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				nombres.add(rs.getString("nombre"));
			}
			// 4. Cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return nombres;
	}
        
    public ArrayList<Posicion> getDatos(String nombre) {

		if (nombre.equals("Todos")) {
			return getTodos();
		} else {
			return getDatosNombre(getId(nombre), nombre);
		}
	}

	private int getId(String nombre) {
		int res = 0;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos where nombre='" + nombre + "'";
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				res = rs.getInt("id");
			}
			// 4. Cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	private ArrayList<Posicion> getTodos() {
		ArrayList<Posicion> pos = new ArrayList<>();
		ArrayList<String> nombres = new ArrayList<>();
		ArrayList<Integer> ids = new ArrayList<>();
		int id = 0;
		String nombre;
		Posicion p;
		double lat = 0;
		double lon = 0;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos";
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt("id");
				ids.add(id);
				nombre = rs.getString("nombre");
				nombres.add(nombre);
			}

			for (int i = 0; i < ids.size(); i++) {
				ResultSet rs2 = st.executeQuery("select * from posicion where id=" + ids.get(i));
				while (rs2.next()) {
					if (rs2.isLast()) {
						lat = rs2.getDouble("latitud");
						lon = rs2.getDouble("longitud");
					}
					p = new Posicion(nombres.get(i), lat, lon);
					pos.add(p);
				}
			}
			// 4. Cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pos;
	}

	private ArrayList<Posicion> getDatosNombre(int id, String nombre) {
		ArrayList<Posicion> pos = new ArrayList<>();
		Posicion p;
		double lat = 0;
		double lon = 0;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from posicion where id=" + id;
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				if (rs.isLast()) {
					lat = rs.getDouble("latitud");
					lon = rs.getDouble("longitud");
				}
			}
			// 4. Cierre conexión
			cn.close();

			p = new Posicion(nombre, lon, lat);
			pos.add(p);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return pos;
	}
}
