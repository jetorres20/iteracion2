package uniandes.isis2304.alohandes.negocio;
import java.sql.Timestamp;

/**
 * Interfaz para los métodos get de Hostal.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOHostal {
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return the idOperario
	 */
	public long getIdOperario();
	
	/**
	 * @return the nit
	 */
	public int getNit();
	
	/**
	 * @return the nombre
	 */
	public String getNombre();	

	/**
	 * @return the horaAbre
	 */
	public Timestamp getHoraAbre();
	
	/**
	 * @return the horaCierra
	 */
	public Timestamp getHoraCierra();
	
	/**
	 * @return the direccion
	 */
	public String getDireccion();
	

	/**
	 * @return the telefono
	 */
	public String getTelefono();
		
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del Hostal
	 */
	public String toString();
}
