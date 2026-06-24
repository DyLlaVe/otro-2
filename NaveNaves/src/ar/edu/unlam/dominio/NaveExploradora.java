package ar.edu.unlam.dominio;

public class NaveExploradora extends Nave {

	private SelecionarAlcance alcance;

	public NaveExploradora(String codigo, String nombre, Double capacidadMaxCombustible, SelecionarAlcance alcance) {
		super(codigo, nombre, capacidadMaxCombustible);
		this.alcance = alcance;
	}

	public SelecionarAlcance getAlcance() {
		return alcance;
	}

	@Override
	public Double calcularConsumo(Integer cantidadHora) {
		Double total = 0.0;
		switch (this.alcance) {
		case LARGO_ALCANCE:
			total = super.getConsumoBase() * 0.9;
			break;
		case CORTO_ALCANCE:
			total = super.getConsumoBase() * 0.8;
			break;

		}
		return total * cantidadHora;
	}

}
