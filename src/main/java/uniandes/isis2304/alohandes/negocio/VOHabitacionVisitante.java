package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto de habitacion visitante del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public interface VOHabitacionVisitante {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion();

	/**
	 * @return the idOperario
	 */
	public long getIdOperario();

	/**
	 * @return the direccion
	 */
	public String getDireccion();

	/**
	 * @return the compartido
	 */
	public boolean isCompartido();

	/**
	 * @return the banioCompartido
	 */
	public boolean isBanioCompartido();

	/**
	 * @return the precioMes
	 */
	public double getPrecioMes();

	/**
	 * @return the capacidadDisponible
	 */
	public int getCapacidadDisponible();
}