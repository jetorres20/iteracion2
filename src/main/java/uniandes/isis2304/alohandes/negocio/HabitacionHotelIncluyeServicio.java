package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto habitacion hotel ofrece servicio 
 *
 * @author Grupo A-09
 */
public class HabitacionHotelIncluyeServicio implements VOHabitacionHotelIncluyeServicio {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del recinto que corresponde a la habitacion de hotel que ofrece el servicio
	 */
	private long idHabitacion;
	
	/**
	 * id del servicio de hotel ofrecido
	 */
	private long idServicio;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	/**
	 * Constructor por defecto
	 */
	public HabitacionHotelIncluyeServicio() {
		this.idHabitacion = 0;
		this.idServicio = 0;
	}
	
	/** Constructor con valores
	 * @param idHabitacion
	 * @param idServicio
	 */
	public HabitacionHotelIncluyeServicio(long idHabitacion, long idServicio) {
		this.idHabitacion = idHabitacion;
		this.idServicio = idServicio;
	}

	@Override
	public String toString() {
		return "HabitacionHotelIncluyeServicio [idHabitacion=" + idHabitacion + ", idServicio=" + idServicio + "]";
	}

	/**
	 * @return the idHabitacion
	 */
	public long getIdRecinto() {
		return idHabitacion;
	}

	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idHabitacion the idHabitacion to set
	 */
	public void setIdRecinto(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}	
}
