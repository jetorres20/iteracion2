package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar la relacion entre las residencias y los servicios que ofrecen
 *
 *el mismo servicio puede ser ofrecido por muchas residencias
 *
 *y una residencia puede ofrecer muchos servicios
 *
 * @author Grupo A-09
 */
public class ResidenciaOfreceServicio implements VOResidenciaOfreceServicio{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	
	private long idResidencia;
	
	
	private long idServicio;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public ResidenciaOfreceServicio() {		
		this.idResidencia = 0;
		this.idServicio = 0;
	}
	
	/**Constructor con valores
	 * @param idResidencia
	 * @param idServicio
	 */
	public ResidenciaOfreceServicio(long idResidencia, long idServicio) {
		super();
		this.idResidencia = idResidencia;
		this.idServicio = idServicio;
	}

	/**
	 * @return the idResidencia
	 */
	public long getIdResidencia() {
		return idResidencia;
	}

	/**
	 * @param idResidencia the idResidencia to set
	 */
	public void setIdResidencia(long idResidencia) {
		this.idResidencia = idResidencia;
	}

	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de una relacion entre una residencia y un servicio
	 */
	public String toString() {
		return "ResidenciaOfreceServicio [idResidencia=" + idResidencia + ", idServicio=" + idServicio + "]";
	}	
}