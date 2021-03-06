package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Habitacion de residencia.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionResidencia {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return the idRecinto
	 */
	public long getIdRecinto();
	

	/**
	 * @return the idResidencia
	 */
	public long getIdResidencia();

	

	/**
	 * @return the compartido
	 */
	public int getCompartido();

	
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

	
	/**
	 * @return the precioMes
	 */
	public double getPrecioMes();

	

	/**
	 * @return the precioSemestre
	 */
	public double getPrecioSemestre();

	

	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una habitacion de residencia estudiantil
	 */
	public String toString();
}
