package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Persona;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Persona de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLPersona {

	/* ****************************************************************
	 * 			Constantes // TODO cambiar PersistenciaAlohandes 
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLPersona(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una persona a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idOperario - id unico del operario que es esta pesona
	 * @param cedula - cedula de la persona
	 * @param nombre - nombre de la persona
	 * @param apellido - apellido de la persona
	 * @param telefono - telefono de la persona
	 * @param correo - correo de la persoa
	 * @param vinculo . vinculo de la persona con uniandes
	 * @return el numero de tuplas insertadas
	 */
	public long adicionarPersona(PersistenceManager pm, long idOperario, long cedula, String nombre, String apellido, int telefono, String correo, int vinculo){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonas () + "(idOperario, cedula, nombre, apellido, telefono, correo, vinculo) values (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idOperario, cedula, nombre, apellido, telefono, correo, vinculo);
		return (long)q.executeUnique();
	}
	
	
	/**
	 *  Crea y ejecuta la sentencia SQL para eliminar una persona de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param idOperario -  id unico del operario que es esta pesona
	 * @return el numero de tuplas eliminadas
	 */
	public long eliminarPersonaPorId(PersistenceManager pm, long idOperario){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonas () + " WHERE idOperario = ?");
		// TODO cambiar dar tabla por Persona 
		q.setParameters(idOperario);
		return (long) q.executeUnique();		
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Una persona de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param idOperario - id unico del operario que es esta pesona
	 * @return el objeto Persona que tiene el identificador dado
	 */
	public Persona darPersonaPorId(PersistenceManager pm, long idOperario){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonas () + " WHERE id = ?");
		q.setResultClass(Persona.class);
		q.setParameters(idOperario);
		return (Persona) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Una persona de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param cedula - cedula de la pesona
	 * @return el objeto Persona que tiene la persona dado
	 */
	public Persona darPersonaPorCedula(PersistenceManager pm, long cedula){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonas () + " WHERE cedula = ?");
		q.setResultClass(Persona.class);
		q.setParameters(cedula);
		return (Persona) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos Las personas de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todas las personas de la base de datos
	 */
	public List<Persona> darPersonas(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonas ());
		q.setResultClass(Persona.class);
		return (List<Persona>) q.executeList();		
	}	
}
