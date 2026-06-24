package ar.edu.unlam.exception;

public class NaveDuplicadaException extends Exception {

	private static final long serialVersionUID = 114314L;

	public NaveDuplicadaException() {
		super("El codigo de la nave ya existe, intente con una nueva nave");
	}
	

}
