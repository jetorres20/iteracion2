package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import uniandes.isis2304.alohandes.negocio.Operario;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Operario de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLOperario {
	/* ****************************************************************
	 * 			Constantes // TODO cambiar PersistenciaParranderos 
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaParranderos.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaParranderos pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLOperario(PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Operario a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - el identificador del Operario
	 * @param fechaRegistro - fecha de registro del operario
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarOperario(PersistenceManager pm, long id, Timestamp fechaRegistro){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(id, fechaRegistro) values (?, ?)");
		// TODO cambiar dar tabla por Operario 
		q.setParameters(id, fechaRegistro);
		return (long)q.executeUnique();   
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un operario de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id del Operario
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarOperarioPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Operario 
		q.setParameters(id);
		return (long) q.executeUnique();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN Operario de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id del Operario
	 * @return el objeto Operario que tiene el identificador dado
	 */
	public Operario darOperarioPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Operario 
		q.setResultClass(Operario.class);
		q.setParameters(id);
		return (Operario) q.executeUnique();
	}
	
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS Operarios de la 
	 * base de datos de Alohandes que fueron registrados en un fecha especifica
	 * @param pm - El manejador de persistencia
	 * @param fechaRegistro - fecha de registro de los operarios
	 * @return lista de operarios registrados en la fecha pasada por parametro
	 */
	public List<Operario> darOperariosFechaRegistro(PersistenceManager pm, Timestamp fechaRegistro){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + "WHERE fechaRegistro = ?");
		// TODO cambiar dar tabla por Operario 
		q.setResultClass(Operario.class);
		q.setParameters(fechaRegistro);
		return (List<Operario>) q.executeList();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos LOS Operarios de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos los Operarios de la tabla operarios 
	 */
	public List<Operario> darOperarios(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		// TODO cambiar dar tabla por Operario 
		q.setResultClass(Operario.class);
		return (List<Operario>) q.executeList();
	}	
}