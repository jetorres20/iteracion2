package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Vivienda del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Vivienda implements VOVivienda {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del recinto que es esta vivienda 
	 */
	private long idVivienda;
	
	/**
	 * id unico de la persona operaria de la vivienda
	 */
	private long idOperario;
	
	/**
	 * numero de habitaciones de la casa
	 */
	private int numeroHabitaciones;
	
	/**
	 * precio a pagar por noche de estadia
	 */
	private double precioNoche;
	
	/**
	 * precio del abono a pagar junto con el precio noche
	 */
	private double precioSeguro;
	
	/**
	 * direccion de la vivienda
	 */
	private String direccion;
	
	/**
	 * cuenta cuantas veces ha sido reservada la casa durante el año
	 */
	private int diasUtilizadaAñoActual;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Vivienda() {
		this.idVivienda = 0;
		this.idOperario = 0;
		this.numeroHabitaciones = 0;
		this.precioNoche = 0;
		this.precioSeguro = 0;
		this.direccion = "";
		this.diasUtilizadaAñoActual = -1;
	}
	
	/**Constructor con valores
	 * @param idVivienda
	 * @param idOperario
	 * @param numeroHabitaciones
	 * @param precioNoche
	 * @param precioSeguro
	 * @param direccion
	 * @param diasUtilizadaAñoActual
	 */
	public Vivienda(long idVivienda, long idOperario, int numeroHabitaciones, double precioNoche, double precioSeguro,
			String direccion, int diasUtilizadaAñoActual) {
		this.idVivienda = idVivienda;
		this.idOperario = idOperario;
		this.numeroHabitaciones = numeroHabitaciones;
		this.precioNoche = precioNoche;
		this.precioSeguro = precioSeguro;
		this.direccion = direccion;
		this.diasUtilizadaAñoActual = diasUtilizadaAñoActual;
	}

	@Override
	public String toString() {
		return "Vivienda [idVivienda=" + idVivienda + ", idOperario=" + idOperario + ", numeroHabitaciones="
				+ numeroHabitaciones + ", precioNoche=" + precioNoche + ", precioSeguro=" + precioSeguro
				+ ", direccion=" + direccion + ", diasUtilizadaAñoActual=" + diasUtilizadaAñoActual + "]";
	}

	/**
	 * @return the idVivienda
	 */
	public long getIdVivienda() {
		return idVivienda;
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
	 * @return the precioNoche
	 */
	public double getPrecioNoche() {
		return precioNoche;
	}

	/**
	 * @return the precioSeguro
	 */
	public double getPrecioSeguro() {
		return precioSeguro;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @return the diasUtilizadaAñoActual
	 */
	public int getDiasUtilizadaAñoActual() {
		return diasUtilizadaAñoActual;
	}

	/**
	 * @param idVivienda the idVivienda to set
	 */
	public void setIdVivienda(long idVivienda) {
		this.idVivienda = idVivienda;
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
	 * @param precioNoche the precioNoche to set
	 */
	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}

	/**
	 * @param precioSeguro the precioSeguro to set
	 */
	public void setPrecioSeguro(double precioSeguro) {
		this.precioSeguro = precioSeguro;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @param diasUtilizadaAñoActual the diasUtilizadaAñoActual to set
	 */
	public void setDiasUtilizadaAñoActual(int diasUtilizadaAñoActual) {
		this.diasUtilizadaAñoActual = diasUtilizadaAñoActual;
	}
}