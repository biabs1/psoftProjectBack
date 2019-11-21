package psoftProjectBack.psoftProjectBack.enumerador;

public enum StatusCampanha {

	ATIVA("Ativa"), ENCERRADA("Encerrada"), VENCIDA("Vencida"), CONCLUIDA("Concluída");

	private String status;

	private StatusCampanha(String statusName) {
		this.status = statusName;
	}

	public boolean equalsStatus(String s) {
		return status.equals(s);
	}

	@Override
	public String toString() {
		return this.status;
	}

}
