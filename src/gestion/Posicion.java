package gestion;

import java.io.Serializable;

public class Posicion implements Serializable {

	private String nombre;
	private double longitud;
	private double latitud;

	// Constructor
	public Posicion(String nombre, double longitud, double latitud) {
		this.nombre = nombre;
		this.longitud = longitud;
		this.latitud = latitud;
	}

	// Getter y setter nombre
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// Getter y setter longitud
	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	// Getter y setter latitud
	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	@Override
	public String toString() {
		return nombre + "|" + longitud + "|" + latitud;
	}

}
