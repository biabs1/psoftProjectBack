package psoftProjectBack.psoftProjectBack.comparadores;

import java.util.Comparator;

import psoftProjectBack.psoftProjectBack.entidades.Campanha;

public class ComparadorMeta implements Comparator<Campanha> {

	@Override
	public int compare(Campanha camp1, Campanha camp2) {
		return Double.compare(camp1.quantoFaltaPraMeta(), camp2.quantoFaltaPraMeta());
	}

}
