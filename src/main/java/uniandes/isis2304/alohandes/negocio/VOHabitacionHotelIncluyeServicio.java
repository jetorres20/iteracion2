package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Habitacion hotel ofrece servicio.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionHotelIncluyeServicio {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idHabitacion
	 */
	public long getIdRecinto();

	/**
	 * @return the idServicio
	 */
	public long getIdServicio();
}