package ar.edu.unlam.dominio;

import java.util.Objects;

public abstract class Nave implements Comparable<Nave> {

	private String codigo, nombre;
	private Double capacidadMaxCombustible;
	private Double consumoBase;
	private Integer tonalaje;

	public Nave(String codigo, String nombre, Double capacidadMaxCombustible) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.capacidadMaxCombustible = capacidadMaxCombustible;
		this.consumoBase = 0.5;
		this.tonalaje = 0;
	}

	public Integer getTonalaje() {
		return tonalaje;
	}

	public Double getConsumoBase() {
		return consumoBase;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public Double getCapacidadMaxCombustible() {
		return capacidadMaxCombustible;
	}

	@Override
	public int compareTo(Nave o) {
		return this.codigo.compareTo(o.getCodigo());
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nave other = (Nave) obj;
		return Objects.equals(codigo, other.codigo);
	}

	public abstract Double calcularConsumo(Integer cantidadHora);

}
