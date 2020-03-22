package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Habitacion de hotel.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHabitacionHotel {
	
	@Override
	public String toString();

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion();

	/**
	 * @return the idHotel
	 */
	public long getIdHotel();

	/**
	 * @return the numero
	 */
	public int getNumero();

	/**
	 * @return the tipo
	 */
	public String getTipo();

	/**
	 * @return the precioNoche
	 */
	public double getPrecioNoche();
}
