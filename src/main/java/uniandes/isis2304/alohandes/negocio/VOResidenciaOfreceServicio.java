package uniandes.isis2304.alohandes.negocio;

public interface VOResidenciaOfreceServicio {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return the idResidencia
	 */
	public long getIdResidencia();

	
	/**
	 * @return the idServicio
	 */
	public long getIdServicio();

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una relacion entre una residencia y un servicio
	 */
	public String toString();
}