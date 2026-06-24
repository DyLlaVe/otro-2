package ar.edu.unlam.dominio;

public class NaveSonda extends Nave {

	public NaveSonda(String codigo, String nombre, Double capacidadMaxCombustible) {
		super(codigo, nombre, capacidadMaxCombustible);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Double calcularConsumo(Integer cantidadHora) {
		return super.getConsumoBase() * cantidadHora;
	}

	

}
