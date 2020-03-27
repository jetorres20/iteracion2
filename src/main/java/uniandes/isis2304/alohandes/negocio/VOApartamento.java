package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Apartamento.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOApartamento {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idApartamento
	 */
	public long getIdApartamento();

	/**
	 * @return the idOperario
	 */
	public long getIdOperario();

	/**
	 * @return the numeroHabitaciones
	 */
	public int getNumeroHabitaciones();

	/**
	 * @return the precioMes
	 */
	public double getPrecioMes();

	/**
	 * @return the direccion
	 */
	public String getDireccion();

	/**
	 * @return the amoblado
	 */
	public boolean isAmoblado();


}