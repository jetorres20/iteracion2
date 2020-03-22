package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Menaje.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOMenaje {
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return the id
	 */
	public long getId();

	/**
	 * @return the nombre
	 */
	public String getNombre();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Menaje
	 */
	public String toString();
}