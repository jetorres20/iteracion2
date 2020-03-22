package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de RECINTO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VORecinto {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return el id del recinto
	 */
	public long getId();

	

	/**
	 * @return el capacidad total del recinto
	 */
	public int getCapacidadTotal();

	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del recinto
	 */
	public String toString();
}
