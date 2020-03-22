package uniandes.isis2304.alohandes.negocio;

/**
 * Clase para modelar el concepto PERSONA del negocio de AlohAndes
 *
 * @author Grupo A-09
 */
public class Persona implements VOPersona{

	/**
	 * id de operario para la persona
	 */
	private long idOperario;
	
	/**
	 * numero de cedula unico para la persona
	 */
	private int cedula;
	
	/**
	 * primer y/o segundo nombre de la persona
	 * 15-25 caracteres 
	 */
	private String nombre;
	
	/**
	 * apellidos de la persona
	 * 15-25 caracteres
	 */
	private String apellido;
	
	/**
	 * telefono de la persona con indicador pais 
	 * +(areaCode)(numero)
	 * 10-14 caracteres
	 */
	private String telefono;
	
	/**
	 * correo de la persona
	 * incluye @
	 * 25-35 caracteres
	 */
	private String correo;
	
	/**
	 * tipo de vinculo con uniandes
	 * 0 = ESTUDIANTE
	 * 1 = EGRESADO
	 * 2 = PROFESOR
	 * 3 = EMPLEADO
	 * 4 = VECINO FENICIA
	 * 5 = PADRE ESTUDIANTE
	 * 6 = VISITANTE EVENTO 
	 */
	private int vinculo;
	
	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
	/**
     * Constructor por defecto
     */
	public Persona(){
		this.idOperario = 0;
		this.cedula = 0;
		this.nombre = "";
		this.apellido = "";
		this.telefono = "";
		this.correo = "";
		this.vinculo = -1;
	}
	
	/**
	 * Constructor con valores
	 * @param idOperario id del operario para esta persona
	 * @param cedula de la persona
	 * @param nombres de la persona
	 * @param apellidos de la persona
	 * @param telefono de la persona
	 * @param correo de la persona
	 * @param vinculo con uniandes
	 */
	public Persona(long idOperario, int cedula, String nombre, String apellido, String telefono, String correo, int vinculo){
		this.idOperario = idOperario;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.correo = correo;
		this.vinculo = vinculo;
	}

	/**
	 * @return el id del operario
	 */
	public long getIdOperario() {
		return idOperario;
	}

	/**
	 * @param id del operario
	 */
	public void setIdOperario(long idOperario) {
		this.idOperario = idOperario;
	}

	/**
	 * @return la cedula de la persona
	 */
	public int getCedula() {
		return cedula;
	}

	/**
	 * @param cedula de la persona
	 */
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	/**
	 * @return el nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre de la persona
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return el apellido de la persona
	 */
	public String getApellido() {
		return apellido;
	}

	/**
	 * @param apellido de la persona
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return el telefono de la persona
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono de la persona
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return el correo de la persona
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo de la persona
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return el vinculo de la persona con uniandes
	 */
	public int getVinculo() {
		return vinculo;
	}

	/**
	 * @param vinculo de la persona con uniandes
	 */
	public void setVinculo(int vinculo) {
		this.vinculo = vinculo;
	}

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la persona
	 */
	public String toString() {
		return "Persona [idOperario=" + idOperario + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido="
				+ apellido + ", telefono=" + telefono + ", correo=" + correo + ", vinculo=" + vinculo + "]";
	}	
}
