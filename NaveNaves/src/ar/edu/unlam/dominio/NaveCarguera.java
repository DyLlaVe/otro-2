package ar.edu.unlam.dominio;

public class NaveCarguera extends Nave {

	private Integer tonalada, cilindrada;

	public NaveCarguera(String codigo, String nombre, Double capacidadMaxCombustible, Integer tonelada,
			Integer cilindrada) {
		super(codigo, nombre, capacidadMaxCombustible);
		this.tonalada = tonelada;
		this.cilindrada = cilindrada;
	}

	@Override
	public Integer getTonalaje() {
		return tonalada;
	}

	public Integer getCilindrada() {
		return cilindrada;
	}

	@Override
	public Double calcularConsumo(Integer cantidadHora) {

		Double total = super.getConsumoBase() + (super.getConsumoBase() * 0.05 * this.getTonalaje());
		return total * cantidadHora;
	}

}
