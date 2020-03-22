package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Vivienda.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOVivienda {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idVivienda
	 */
	public long getIdVivienda();

	/**
	 * @return the idOperario
	 */
	public long getIdOperario();

	/**
	 * @return the numeroHabitaciones
	 */
	public int getNumeroHabitaciones();

	/**
	 * @return the precioNoche
	 */
	public double getPrecioNoche();

	/**
	 * @return the precioSeguro
	 */
	public double getPrecioSeguro();

	/**
	 * @return the direccion
	 */
	public String getDireccion();

	/**
	 * @return the diasUtilizadaAñoActual
	 */
	public int getDiasUtilizadaAñoActual();
}
