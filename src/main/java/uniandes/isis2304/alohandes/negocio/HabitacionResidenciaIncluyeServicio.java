package uniandes.isis2304.alohandes.negocio;
/**
 * Clase para modelar el concepto de relacion entre una habitacion de residencia y los servicios que puede tener
 *
 * @author Grupo A-09
 */
public class HabitacionResidenciaIncluyeServicio implements VOHabitacionResidenciaIncluyeServicio {
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/	
	/**
	 * id unico del recinto de la habitacion de residencia en alohandes 
	 */
	private long idHabitacion;
	
	/**
	 * id unico del servicio que ofrece la residencia
	 */
	private long idServicio;
		
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public HabitacionResidenciaIncluyeServicio() {
		this.idHabitacion = 0;
		this.idServicio = 0;
	}
	
	/** Constructor con valores
	 * @param idHabitacion
	 * @param idServicio
	 */
	public HabitacionResidenciaIncluyeServicio(long idHabitacion, long idServicio) {
		this.idHabitacion = idHabitacion;
		this.idServicio = idServicio;
	}

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion() {
		return idHabitacion;
	}

	/**
	 * @param idHabitacion the idHabitacion to set
	 */
	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
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
	 * @return Una cadena de caracteres con todos los atributos de una relacion entre una habitacion de residencia y un servicio
	 */
	public String toString() {
		return "HabitacionResidenciaIncluye [idHabitacion=" + idHabitacion + ", idServicio=" + idServicio + "]";
	}	
}