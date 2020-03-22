package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

/**
 * Clase para modelar el concepto OPERARIO del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Operario implements VOOperario{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los operarios
	 */
	private long id;
	
	/**
	 * La fecha del registro del operario
	 */
	private Timestamp fechaRegistro;
	
	/**
     * Constructor por defecto
     */
	public Operario(){
		this.id = 0;
		this.fechaRegistro = new Timestamp (0);
	}
	

	/**
	 * Constructor con valores
	 * @param id - El id del operario
	 * @param fechaRegistro - fecha de registro del operario
	 */
	public Operario(long id, Timestamp fechaRegistro){
		this.id = id;
		this.fechaRegistro = fechaRegistro;
	}


	/**
	 * @return el id del operario
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id del operario
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return fecha de registro del operario
	 */
	public Timestamp getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro del operario
	 */
	public void setFechaRegistro(Timestamp fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del operario generico
	 */
	public String toString() {
		return "Operario [id=" + id + ", fechaRegistro=" + fechaRegistro + "]";
	}	
}
