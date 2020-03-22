package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Hotel ofrece servicio.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHotelOfreceServicio {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idHotel
	 */
	public long getIdHotel();

	/**
	 * @return the idServicio
	 */
	public long getIdServicio();
}
