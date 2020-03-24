package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Residencia;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Residencia de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLResidencia {
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
	public SQLResidencia(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una Residencia a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param idOperario
	 * @param nit
	 * @param nombre
	 * @param direccion
	 * @param telefono
	 * @return el numero de tuplas insertadas
	 */
	public long adicionarResidencia(PersistenceManager pm, long idOperario, int nit, String nombre, String direccion, String telefono ){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(idOperario, nit, nombre, direccion, telefono ) values (?, ?, ?, ?, ?)");
		// TODO cambiar dar tabla por Residencias 
		q.setParameters( idOperario,  nit,  nombre,  direccion,  telefono );
		return (long)q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una Residencia de la base de datos de Alohandes, por su id de operario
	 * @param pm - El manejador de persistencia
	 * @param idOperario
	 * @return numero de tuplas eliminadas
	 */
	public long eliminarResidenciaPorId(PersistenceManager pm, long idOperario){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE idOperario = ?");
		// TODO cambiar dar tabla por Residencia 
		q.setParameters(idOperario);
		return (long) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Una residencia de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - - El manejador de persistencia
	 * @param idOperario - id del operario que es la residencia
	 * @return un objeto residencia con el id dado
	 */
	public Residencia darResidenciaPorId(PersistenceManager pm, long idOperario){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE idOperario = ?");
		// TODO cambiar dar tabla por Residencia 
		q.setResultClass(Residencia.class);
		q.setParameters(idOperario);
		return (Residencia) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Una residencia de la 
	 * base de datos de Alohandes, por su nit
	 * @param pm - - El manejador de persistencia
	 * @param nit - nit de la residencia
	 * @return un objeto residencia con el id dado
	 */
	public Residencia darResidenciaPorNit(PersistenceManager pm, int nit){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE nit = ?");
		// TODO cambiar dar tabla por Residencia 
		q.setResultClass(Residencia.class);
		q.setParameters(nit);
		return (Residencia) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Una residencia de la 
	 * base de datos de Alohandes, por su nombre
	 * @param pm - - El manejador de persistencia
	 * @param nombre - nombre de la residencia
	 * @return un objeto residencia con el nombre dado
	 */
	public Residencia darResidenciaPorNombre(PersistenceManager pm, String nombre){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE nombre = ?");
		// TODO cambiar dar tabla por Residencia 
		q.setResultClass(Residencia.class);
		q.setParameters(nombre);
		return (Residencia) q.executeUnique();
	}
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos Las residencias de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return lista de Residencias de la base de datos
	 */
	public List<Residencia> darResidencias(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		// TODO cambiar dar tabla por Residencias 
		q.setResultClass(Residencia.class);
		return (List<Residencia>) q.executeList();
	}	
}
