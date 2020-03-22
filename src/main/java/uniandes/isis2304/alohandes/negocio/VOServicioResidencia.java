package uniandes.isis2304.alohandes.negocio;

public interface VOServicioResidencia {

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

	

	/**
	 * @return the costoExtraNoche
	 */
	public double getCostoExtraNoche();

	

	/**
	 * @return the costoExtraMes
	 */
	public double getCostoExtraMes();

	

	/**
	 * @return the costoExtraSemestre
	 */
	public double getCostoExtraSemestre();

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una habitacion de residencia estudiantil
	 */
	public String toString();
}
