package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de habitacion visitante ofrece servicio.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionVisitanteOfreceServicio {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idServicioPrivado
	 */
	public long getIdServicioPrivado();

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion();

	/**
	 * @return the precio
	 */
	public double getPrecio();

	/**
	 * @return the incluido
	 */
	public boolean isIncluido();
}