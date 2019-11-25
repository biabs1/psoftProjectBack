package psoftProjectBack.psoftProjectBack.comparadores;

import java.util.Comparator;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;

public class ComparadorLike implements Comparator<Campanha> {

	@Override
	public int compare(Campanha camp1, Campanha camp2) {
		return Integer.compare(camp1.getCurtidas().size(), camp2.getCurtidas().size());
	}

}
