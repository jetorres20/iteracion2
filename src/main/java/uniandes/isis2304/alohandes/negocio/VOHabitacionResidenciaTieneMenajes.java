package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Habitacion residencia tiene menajes .
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionResidenciaTieneMenajes {

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
	 * @return the idMenaje
	 */
	public long getIdMenaje();

	/**
	 * @return the cantidad
	 */
	public int getCantidad();

	/**
	 * @return the valorUnidad
	 */
	public double getValorUnidad();
}
