package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto de que  apartamento ofrece servicios 
 *
 * @author Grupo A-09
 */
public class ApartamentoOfreceServicio implements VOApartamentoOfreceServicio {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id del servicio ofrecido por un apartamento
	 */
	private long idServicioPrivado;
	
	/**
	 * id del recinto que es el apartamento
	 */
	private long idApartamento;
	
	/**
	 * precio del servicio que ofrece el apartamento
	 */
	private double precio;
	
	/**
	 * true si el servicio esta incluido, false si se suma a el total
	 */
	private int incluido;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public ApartamentoOfreceServicio() {
		this.idServicioPrivado = 0;
		this.idApartamento = 0;
		this.precio = 0;
		this.incluido = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param idServicioPrivado
	 * @param idApartamento
	 * @param precio
	 * @param incluido
	 */
	public ApartamentoOfreceServicio( long idApartamento,long idServicioPrivado, double precio, int incluido) {
		this.idServicioPrivado = idServicioPrivado;
		this.idApartamento = idApartamento;
		this.precio = precio;
		this.incluido = incluido;
	}

	@Override
	public String toString() {
		return "ApartamentoOfreceServicio [idServicioPrivado=" + idServicioPrivado + ", idApartamento=" + idApartamento
				+ ", precio=" + precio + ", incluido=" + incluido + "]";
	}

	/**
	 * @return the idServicioPrivado
	 */
	public long getIdServicio() {
		return idServicioPrivado;
	}

	/**
	 * @return the idApartamento
	 */
	public long getIdApto() {
		return idApartamento;
	}

	/**
	 * @return the precio
	 */
	public double getPrecio() {
		return precio;
	}

	/**
	 * @return the incluido
	 */
	public int getIncluido() {
		return incluido;
	}

	/**
	 * @param idServicioPrivado the idServicioPrivado to set
	 */
	public void setIdServicio(long idServicioPrivado) {
		this.idServicioPrivado = idServicioPrivado;
	}

	/**
	 * @param idApartamento the idApartamento to set
	 */
	public void setIdApto(long idApartamento) {
		this.idApartamento = idApartamento;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @param incluido the incluido to set
	 */
	public void setIncluido(int incluido) {
		this.incluido = incluido;
	}	
}