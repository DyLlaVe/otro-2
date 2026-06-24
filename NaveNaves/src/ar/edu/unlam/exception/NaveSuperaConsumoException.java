package ar.edu.unlam.exception;

public class NaveSuperaConsumoException extends Exception {

	private static final long serialVersionUID = 14332424L;

	public NaveSuperaConsumoException() {
		super("Has superado el consumo limite de la nave");
	}
	

}
