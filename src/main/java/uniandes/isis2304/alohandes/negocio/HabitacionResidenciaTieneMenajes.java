package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto de relacion entre una habitacion de residencia y los menajes con los que se entrega en cada nueva reservacion en Alohandes
 *
 * @author Grupo A-09
 */
public class HabitacionResidenciaTieneMenajes implements VOHabitacionResidenciaTieneMenajes {

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del recinto que es la habitacion de residencia
	 */
	private long idHabitacion;
	
	/**
	 * id del menaje 
	 */
	private long idMenaje;
	
	/**
	 * cantidad quel item que hay en la habitacion
	 */
	private int cantidad;
	
	/**
	 * valor unidad del menaje
	 */
	private double valorUnidad;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/

	/**
     * Constructor por defecto
     */
	public HabitacionResidenciaTieneMenajes() {
		this.idHabitacion = 0;
		this.idMenaje = 0;
		this.cantidad = -1;
		this.valorUnidad = -1;
	}
	
	/**
	 * Constructor con valores
	 * @param idHabitacion
	 * @param idMenaje
	 * @param cantidad
	 * @param valorUnidad
	 */
	public HabitacionResidenciaTieneMenajes(long idHabitacion, long idMenaje, int cantidad, double valorUnidad) {
		this.idHabitacion = idHabitacion;
		this.idMenaje = idMenaje;
		this.cantidad = cantidad;
		this.valorUnidad = valorUnidad;
	}

	@Override
	public String toString() {
		return "HabitacionResidenciaTieneMenajes [idHabitacion=" + idHabitacion + ", idMenaje=" + idMenaje
				+ ", cantidad=" + cantidad + ", valorUnidad=" + valorUnidad + "]";
	}

	/**
	 * @return the idHabitacion
	 */
	public long getIdHabitacion() {
		return idHabitacion;
	}

	/**
	 * @return the idMenaje
	 */
	public long getIdMenaje() {
		return idMenaje;
	}

	/**
	 * @return the cantidad
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * @return the valorUnidad
	 */
	public double getValorUnidad() {
		return valorUnidad;
	}

	/**
	 * @param idHabitacion the idHabitacion to set
	 */
	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	/**
	 * @param idMenaje the idMenaje to set
	 */
	public void setIdMenaje(long idMenaje) {
		this.idMenaje = idMenaje;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @param valorUnidad the valorUnidad to set
	 */
	public void setValorUnidad(double valorUnidad) {
		this.valorUnidad = valorUnidad;
	}	
}
