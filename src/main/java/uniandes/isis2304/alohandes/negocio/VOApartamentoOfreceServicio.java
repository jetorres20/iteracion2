package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de Apartamento ofrece servicio.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOApartamentoOfreceServicio {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	@Override
	public String toString();

	/**
	 * @return the idServicioPrivado
	 */
	public long getIdServicioPrivado();

	/**
	 * @return the idApartamento
	 */
	public long getIdApartamento();

	/**
	 * @return the precio
	 */
	public double getPrecio();

	/**
	 * @return the incluido
	 */
	public boolean isIncluido();
}