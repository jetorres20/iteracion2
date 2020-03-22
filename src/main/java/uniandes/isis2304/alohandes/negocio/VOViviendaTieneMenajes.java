package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Vivienda tiene menajes.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOViviendaTieneMenajes {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idVivienda
	 */
	public long getIdVivienda();

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
