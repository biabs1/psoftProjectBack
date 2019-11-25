package psoftProjectBack.psoftProjectBack.comparadores;

import java.util.Comparator;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;

public class ComparadorData implements Comparator<Campanha> {

	@Override
	public int compare(Campanha camp1, Campanha camp2) {
		return camp1.getDeadline().compareTo(camp2.getDeadline());
	}

}
