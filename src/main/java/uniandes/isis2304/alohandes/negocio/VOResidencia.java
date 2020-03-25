package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de RESIDENCIA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOResidencia {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return el id de operario
	 */
	public long getIdOperario();

	
	/**
	 * @return el nit de la residencia
	 */
	public int getNit();

	

	/**
	 * @return nombre de la residencia
	 */
	public String getNombre();

	

	/**
	 * @return direccion de la residencia
	 */
	public String getDireccion();

	
	/**
	 * @return telefono de la residencia
	 */
	public int getTelefono();

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la residencia
	 */
	public String toString();
}
