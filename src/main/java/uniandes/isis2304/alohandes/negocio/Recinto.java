package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto RECINTO del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Recinto implements VORecinto{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los recintos
	 */
	private long id;
	
	/**
	 * capacidad Total del recinto
	 */
	private int capacidadTotal;
	
	/**
	 * fecha en la que se retiro o se va a retirar el recinto de alohandes
	 */
	private Timestamp fechaRetiroOferta;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Recinto(){
		this.id = 0;
		this.capacidadTotal = 0;
		this.fechaRetiroOferta =  null;
	}
	
	/**
	 * Constructor con valores
	 * @param id - El id del recinto
	 * @param capacidadTotal - capacidad del recinto
	 */
	public Recinto(long id, int capacidadTotal){
		this.id = id;
		this.capacidadTotal = capacidadTotal;
		this.fechaRetiroOferta = null;
	}

	/**
	 * @return el id del recinto
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id del recinto
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return el capacidad total del recinto
	 */
	public int getCapacidadTotal() {
		return capacidadTotal;
	}

	/**
	 * @param capacidadTotal del recinto
	 */
	public void setCapacidadTotal(int capacidadTotal) {
		this.capacidadTotal = capacidadTotal;
	}
	
	/**
	 * @return the fechaRetiroOferta
	 */
	public Timestamp getFechaRetiroOferta() {
		return fechaRetiroOferta;
	}

	/**
	 * @param fechaRetiroOferta the fechaRetiroOferta to set
	 */
	public void setFechaRetiroOferta(Timestamp fechaRetiroOferta) {
		this.fechaRetiroOferta = fechaRetiroOferta;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del recinto
	 */
	public String toString() {
		return "Recinto [id=" + id + ", capacidadTotal=" + capacidadTotal + "]";
	}	
}
