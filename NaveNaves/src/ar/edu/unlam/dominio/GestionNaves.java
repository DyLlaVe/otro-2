package ar.edu.unlam.dominio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import ar.edu.unlam.exception.NaveCarguregeraSobrePesoException;
import ar.edu.unlam.exception.NaveDuplicadaException;
import ar.edu.unlam.exception.NaveSuperaConsumoException;

public class GestionNaves {
	private static final Integer LIMITE_TONELADA = 30;
	private Set<Nave> listaDeNaves;
	private List<Mision> listaDeMisiones;

	public GestionNaves() {
		this.listaDeNaves = new TreeSet<>();
		this.listaDeMisiones = new ArrayList<>();
	}

	public Boolean registrarNaves(Nave nave) throws NaveCarguregeraSobrePesoException, NaveDuplicadaException {
		Boolean registrada = false;
		if (nave.getTonalaje() > LIMITE_TONELADA) {
			throw new NaveCarguregeraSobrePesoException("Has sobrepasado el limite perminitido de carga en la Nave Cargera");
		}
		if (!this.listaDeNaves.add(nave)) {
			throw new NaveDuplicadaException();
		} else {
			registrada = true;
		}
		return registrada;
	}

	public Boolean registrarMision(Nave nave, Integer hora) throws NaveSuperaConsumoException {
		if (this.listaDeNaves.contains(nave) && obtenerConsumo(nave, hora) <= nave.getCapacidadMaxCombustible()) {
			Mision misionAceptada = new Mision(nave, hora);
			return listaDeMisiones.add(misionAceptada);
		} else {
			throw new NaveSuperaConsumoException();
		}

	}

	public Double obtenerConsumo(Nave nave, Integer hora) {
		return nave.calcularConsumo(hora);
	}

	public Set<Nave> obtenerSoloNavesSondas() {
		Set<Nave> navesSondas = new TreeSet<>();
		for (Nave nave : listaDeNaves) {
			if (nave instanceof NaveSonda) {
				navesSondas.add(nave);
			}
		}

		return navesSondas;
	}

	public Set<Nave> obteneerNaveSondaPorNombreDesc() {
		Set<Nave> ordenado = new TreeSet<>(Comparator.comparing(Nave::getNombre).reversed());
		ordenado.addAll(obtenerSoloNavesSondas());
		return ordenado;
	}

	public Map<Nave,ArrayList<Mision>> obtenerRegistroDeNavesPorMision(){
		Map<Nave,ArrayList<Mision>> obtenerRegistroDeNavesPorMision =new TreeMap<>();
		for (Mision mision : listaDeMisiones) {
			Nave nave = mision.getNave();
			obtenerRegistroDeNavesPorMision.putIfAbsent(nave, new ArrayList<>());
			obtenerRegistroDeNavesPorMision.get(nave).add(mision);
		}
		return obtenerRegistroDeNavesPorMision;
	}

	public Set<Nave> ObtenerTodasLasNaves() {
		return this.listaDeNaves;
	}

}
