package road_Fighter.server;

import java.util.Objects;

public class Sala {
	protected String nombre;
	protected String entorno;
	protected int cantidadMaxima;
	protected int cantidadActual;

	public Sala(String nombre, int cantMax, String entorno) {
		this.nombre = nombre.toUpperCase();
		this.cantidadMaxima = cantMax;
		this.entorno = entorno;
	}

	public Sala(String nombre, String entorno) {
		this.nombre = nombre;
		this.entorno = entorno;
	}

	public void setCantidadActual(int cantidadActual) {
		this.cantidadActual = cantidadActual;
	}

	public String getOwner() {
		return entorno;
	}

	public int getCantidadMaxima() {
		return cantidadMaxima;
	}

	public int getCantidadActual() {
		return cantidadActual;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, entorno);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sala other = (Sala) obj;
		return nombre.equals(other.nombre) && entorno.equals(other.entorno);
	}

	@Override
	public String toString() {
		return nombre + " - [" + cantidadActual + "/" + cantidadMaxima + "]";
	}
}
