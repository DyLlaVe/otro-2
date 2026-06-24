package ar.edu.unlam.dominio;

import java.util.Comparator;

public class ComparatorNombreDesc implements Comparator<Nave> {

	@Override
	public int compare(Nave o1, Nave o2) {
		return o2.getNombre().compareTo(o1.getNombre());
	}

}
