package gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GestionDatosApp1 {
	
	private String nombredb="control";
    private String ip="localhost";
    private String port="3306";
    private String user="root";
    private String password="root";

	public void guardarDatos(Posicion pos) {
		// Comprobar si existen registros
		Boolean res = comprobarNombre(pos.getNombre());
		System.out.println(res);
		// Si existen registros
		if (res) {
			int id = getId(pos.getNombre());
			guardarPosicion(pos, id);
			System.out.println("Datos guardados, ID conocido");
		} else { // Si no existen registros
			int newId;
			boolean existe;

			do {
				newId = (int) (Math.random() * 1000);
				existe = comprobarId(newId);
			} while (existe == true);

			guardarDatos(pos.getNombre(), newId);
			guardarPosicion(pos, newId);
			System.out.println("Datos guardados, ID creado");
		}
	}

	private boolean comprobarNombre(String nombre) {
		boolean res = false;
		int total = 0;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos where nombre='" + nombre + "'";
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				total++;
			}
			if (total > 0) {
				res = true;
			}
			// 4. cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return res;
	}

	private boolean comprobarId(int id) {
		boolean res = false;
		int total = 0;
		try {
			// 1. establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);
			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "select * from datos where id=" + id;
			// 3. Manipulacion resultados
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				total++;
			}
			if (total > 0) {
				res = true;
			}
			// 4. cierre conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return res;
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

	private void guardarDatos(String nombre, int id) {
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "insert into datos(nombre,id)";
			sql += " values('" + nombre + "'," + id + ")";
			st.execute(sql);

			// 4. Cierre de conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	private void guardarPosicion(Posicion pos, int id) {
		try {
			// Establecer conexión con BD
			Connection cn = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+nombredb, user, password);

			// 2. Enviar instrucción SQL
			Statement st = cn.createStatement();
			String sql = "insert into posicion(id,longitud,latitud)";
			sql += " values(" + id + "," + pos.getLongitud() + "," + pos.getLatitud() + ")";
			st.execute(sql);

			// 4. Cierre de conexión
			cn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
}
