package uniandes.isis2304.alohandes.negocio;

import java.sql.Timestamp;

/**
 * Interfaz para los métodos get de OPERARIO.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOOperario {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return el id del operario
	 */
	public long getId();

	
	/**
	 * @return fecha de registro del operario
	 */
	public Timestamp getFechaRegistro();

	
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del operario generico
	 */
	public String toString();
	
}
