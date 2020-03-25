package uniandes.isis2304.alohandes.negocio;

/**
 * Interfaz para los métodos get de PERSONA.
 * Sirve para proteger la información del negocio de posibles manipulaciones desde la interfaz 
 * 
 * @author Grupo A-09
 */
public interface VOPersona {

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
	 * @return el id del operario
	 */
	public long getIdOperario();

	

	/**
	 * @return la cedula de la persona
	 */
	public int getCedula();

	
	
	/**
	 * @return el nombre de la persona
	 */
	public String getNombre();

	
	/**
	 * @return el apellido de la persona
	 */
	public String getApellido();

	

	/**
	 * @return el telefono de la persona
	 */
	public int getTelefono();

	

	/**
	 * @return el correo de la persona
	 */
	public String getCorreo();

	

	/**
	 * @return el vinculo de la persona con uniandes
	 */
	public int getVinculo();

	

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la persona
	 */
	public String toString();
	
}
