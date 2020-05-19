package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto Habitacion de hotel del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class HabitacionHotel implements VOHabitacionHotel{
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del recinto correspondiente a esta habitacion
	 */
	private long idHabitacion;
	
	/**
	 * id unico del operador que es el hotel
	 */
	private long idHotel;
	
	/**
	 * numero de la habitacion dentro del hotel
	 */
	private int numero;
	
	/**
	 * tipo de la habitacion
	 * i.e 
	 * sencilla
	 * semi suit
	 * suit
	 * suit de lujo
	 * 
	 */
	private String tipo;
	
	/**
	 * precio por noche de la habitacion
	 */
	private double precioNoche;

		
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public HabitacionHotel() {
		this.idHabitacion = 0;
		this.idHotel = 0;
		this.numero = -1;
		this.tipo = "";
		this.precioNoche = -1;
	}
	
	/** Constructor con valores
	 * @param idHabitacion
	 * @param idHotel
	 * @param numero
	 * @param tipo
	 * @param precioNoche
	 */
	public HabitacionHotel(long idHabitacion, long idHotel, int numero, String tipo, double precioNoche) {
		this.idHabitacion = idHabitacion;
		this.idHotel = idHotel;
		this.numero = numero;
		this.tipo = tipo;
		this.precioNoche = precioNoche;
	}

	@Override
	public String toString() {
		return "HabitacionHotel [idHabitacion=" + idHabitacion + ", idHotel=" + idHotel + ", numero=" + numero
				+ ", tipo=" + tipo + ", precioNoche=" + precioNoche + "]";
	}
	
	
	/**
	 * @return the idHabitacion
	 */
	public long getIdRecinto() {
		return idHabitacion;
	}

	/**
	 * @return the idHotel
	 */
	public long getIdHotel() {
		return idHotel;
	}

	/**
	 * @return the numero
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return the precioNoche
	 */
	public double getPrecioNoche() {
		return precioNoche;
	}

	/**
	 * @param idHabitacion the idHabitacion to set
	 */
	public void setIdRecinto(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	/**
	 * @param idHotel the idHotel to set
	 */
	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @param precioNoche the precioNoche to set
	 */
	public void setPrecioNoche(double precioNoche) {
		this.precioNoche = precioNoche;
	}
}
