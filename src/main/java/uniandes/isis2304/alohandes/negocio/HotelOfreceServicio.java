package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto de una relacion entre un hotel y los servicios que ofrece en AlohAndes
 *
 * @author Grupo A-09
 */
public class HotelOfreceServicio implements VOHotelOfreceServicio{

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * id unico del operador que es el hotel
	 */
	private long idHotel;
	
	/**
	 * id del servicio ofrecido
	 */
	private long idServicio;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * Constructor por defecto
	 */
	public HotelOfreceServicio(){
		this.idHotel = 0;
		this.idServicio = 0;
	}
	
	/**
	 * Constructor con valores
	 * @param idHotel
	 * @param idServicio
	 */
	public HotelOfreceServicio(long idHotel, long idServicio) {
		this.idHotel = idHotel;
		this.idServicio = idServicio;
	}

	@Override
	public String toString() {
		return "HotelOfreceServicio [idHotel=" + idHotel + ", idServicio=" + idServicio + "]";
	}

	/**
	 * @return the idHotel
	 */
	public long getIdHotel() {
		return idHotel;
	}

	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idHotel the idHotel to set
	 */
	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	
}
