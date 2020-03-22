package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Habitacion de residencia.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionResidenciaIncluyeServicio {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion();
	

	/**
	 * @return the idServicio
	 */
	public long getIdServicio();
	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una relacion entre una habitacion de residencia y un servicio
	 */
	public String toString();
}
