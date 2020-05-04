package uniandes.isis2304.alohandes.negocio;

public class Apartamento implements VOApartamento {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unicpo del recinto que es el apartamento dentro de alohandes
	 */
	private long idApartamento;
	
	/**
	 * id de la persona que opera el apartamento
	 */
	private long idOperario;
	
	/**
	 * numero de habitaciones del apto
	 */
	private int numeroHabitaciones;
	
	/**
	 * precio de la estadia mensual
	 */
	private double precioMes;
	
	/**
	 * direccion del apto
	 */
	private String direccion;
	
	/**
	 * dice si esta amoblado o no el apto
	 */
	private int amoblado;
	


	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Apartamento() {
		this.idApartamento = 0;
		this.idOperario = 0;
		this.numeroHabitaciones = 0;
		this.precioMes = 0;
		this.direccion = "";
		this.amoblado = 0;
	}
	
	/** Constructor con valores
	 * @param idApartamento
	 * @param idOperario
	 * @param numeroHabitaciones
	 * @param precioMes
	 * @param direccion
	 * @param amoblado
	 * @param capacidadDisponible
	 */
	public Apartamento(long idApartamento, long idOperario, int numeroHabitaciones, double precioMes, String direccion,
			int amoblado) {
		this.idApartamento = idApartamento;
		this.idOperario = idOperario;
		this.numeroHabitaciones = numeroHabitaciones;
		this.precioMes = precioMes;
		this.direccion = direccion;
		this.amoblado = amoblado;
	}
	
	@Override
	public String toString() {
		return "Apartamento [idApartamento=" + idApartamento + ", idOperario=" + idOperario + ", numeroHabitaciones="
				+ numeroHabitaciones + ", precioMes=" + precioMes + ", direccion=" + direccion + ", amoblado="
				+ amoblado  + "]";
	}

	/**
	 * @return the idApartamento
	 */
	public long getIdApartamento() {
		return idApartamento;
	}

	/**
	 * @return the idOperario
	 */
	public long getIdOperario() {
		return idOperario;
	}

	/**
	 * @return the numeroHabitaciones
	 */
	public int getNumeroHabitaciones() {
		return numeroHabitaciones;
	}

	/**
	 * @return the precioMes
	 */
	public double getPrecioMes() {
		return precioMes;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return the amoblado
	 */
	public int getAmoblado() {
		return amoblado;
	}

	/**
	 * @param idApartamento the idApartamento to set
	 */
	public void setIdApartamento(long idApartamento) {
		this.idApartamento = idApartamento;
	}

	/**
	 * @param idOperario the idOperario to set
	 */
	public void setIdOperario(long idOperario) {
		this.idOperario = idOperario;
	}

	/**
	 * @param numeroHabitaciones the numeroHabitaciones to set
	 */
	public void setNumeroHabitaciones(int numeroHabitaciones) {
		this.numeroHabitaciones = numeroHabitaciones;
	}

	/**
	 * @param precioMes the precioMes to set
	 */
	public void setPrecioMes(double precioMes) {
		this.precioMes = precioMes;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @param amoblado the amoblado to set
	 */
	public void setAmoblado(int amoblado) {
		this.amoblado = amoblado;
	}


}
