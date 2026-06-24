package ar.edu.unlam.dominio;



public class Mision {
	private Nave nave;
	private Integer hora;
	private Integer id, proximoId = 0;

	public Mision(Nave nave, Integer hora) {
		this.nave = nave;
		this.hora = hora;
		this.id = ++proximoId;
	}

	public Nave getNave() {
		return nave;
	}

	public Integer getHora() {
		return hora;
	}

	public Integer getId() {
		return id;
	}

}
