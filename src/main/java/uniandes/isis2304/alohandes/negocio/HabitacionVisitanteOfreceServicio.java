package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Habitacion visitante ofrece servicio privado
 *
 * @author Grupo A-09 */

public class HabitacionVisitanteOfreceServicio implements VOHabitacionVisitanteOfreceServicio{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id del servicio ofrecido por una habitacion visitante
	 */
	private long idServicio;
	
	/**
	 * id del recinto que es la habitacion visitante
	 */
	private long idHabitacion;
	
	/**
	 * precio del servicio que ofrece la habitacion visitante
	 */
	private double precio;
	
	/**
	 * true si el servicio esta incluido, false si se suma a el total
	 */
	private boolean incluido;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public HabitacionVisitanteOfreceServicio() {
		this.idServicio = 0;
		this.idHabitacion = 0;
		this.precio = 0;
		this.incluido = false;
	}
	
	/**
	 * Constructor con valores
	 * @param idServicio
	 * @param idHabitacion
	 * @param precio
	 * @param incluido
	 */
	public HabitacionVisitanteOfreceServicio( long idHabitacion, long idServicio, double precio,
			boolean incluido) {
		this.idServicio = idServicio;
		this.idHabitacion = idHabitacion;
		this.precio = precio;
		this.incluido = incluido;
	}

	@Override
	public String toString() {
		return "HabitacionVisitanteOfreceServicio [idServicio=" + idServicio + ", idHabitacion="
				+ idHabitacion + ", precio=" + precio + ", incluido=" + incluido + "]";
	}

	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion() {
		return idHabitacion;
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
	public boolean isIncluido() {
		return incluido;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @param idHabitacion the idHabitacion to set
	 */
	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
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
	public void setIncluido(boolean incluido) {
		this.incluido = incluido;
	}	
}
