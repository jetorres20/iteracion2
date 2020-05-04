package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Habitacion de hostal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionHostal {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la habitacion de hostal
	 */
	public String toString();

	/**
	 * @return the idHabitacion
	 */
	public long getIdRecinto();

	/**
	 * @return the idHostal
	 */
	public long getIdHostal();

	/**
	 * @return the compartida
	 */
	public int getCompartida();

	/**
	 * @return the banioCompartido
	 */
	public int getBanioCompartido();

	/**
	 * @return the numero
	 */
	public int getNumero();

	/**
	 * @return the precioNoche
	 */
	public double getPrecioNoche();

	
}
