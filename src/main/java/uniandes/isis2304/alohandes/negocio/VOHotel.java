package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Hotel.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHotel {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return the idOperario
	 */
	public long getIdOperario();

	

	/**
	 * @return the nit
	 */
	public int getNit();

	
	/**
	 * @return the nombre
	 */
	public String getNombre();

	

	/**
	 * @return the estrellas
	 */
	public int getEstrellas();

	

	/**
	 * @return the direccion
	 */
	public String getDireccion();

	

	/**
	 * @return the telefono
	 */
	public String getTelefono();

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Hotel
	 */
	public String toString();

}
