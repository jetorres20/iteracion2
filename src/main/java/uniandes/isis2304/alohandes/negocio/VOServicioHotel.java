package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Servcio de Hotel.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOServicioHotel {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @return the nombre
	 */
	public String getNombre();
}