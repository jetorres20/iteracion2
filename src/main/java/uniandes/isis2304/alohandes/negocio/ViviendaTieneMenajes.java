package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Menaje del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class ViviendaTieneMenajes implements VOViviendaTieneMenajes{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del recinto que es la vivienda
	 */
	private long idVivienda;
	
	/**
	 * id del menaje que esta en la vivienda
	 */
	private long idMenaje;
	
	/**
	 * cantidad de unidades del manje que estan en la vivienda cada vez que se alquila
	 */
	private int cantidad;
	
	/**
	 * valor unidad del menaje que esta en una casa especifica
	 */
	private double valorUnidad;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public ViviendaTieneMenajes() {
		this.idVivienda = 0;
		this.idMenaje = 0;
		this.cantidad = 0;
		this.valorUnidad = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param idVivienda
	 * @param idMenaje
	 * @param cantidad
	 * @param valorUnidad
	 */
	public ViviendaTieneMenajes(long idVivienda, long idMenaje, int cantidad, double valorUnidad) {
		this.idVivienda = idVivienda;
		this.idMenaje = idMenaje;
		this.cantidad = cantidad;
		this.valorUnidad = valorUnidad;
	}

	@Override
	public String toString() {
		return "ViviendaTieneMenajes [idVivienda=" + idVivienda + ", idMenaje=" + idMenaje + ", cantidad=" + cantidad
				+ ", valorUnidad=" + valorUnidad + "]";
	}

	/**
	 * @return the idVivienda
	 */
	public long getIdVivienda() {
		return idVivienda;
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
	 * @param idVivienda the idVivienda to set
	 */
	public void setIdVivienda(long idVivienda) {
		this.idVivienda = idVivienda;
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