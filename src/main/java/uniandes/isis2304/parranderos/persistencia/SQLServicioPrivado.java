package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;


import uniandes.isis2304.alohandes.negocio.ServicioPrivado;


/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ServicioPrivado de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
public class SQLServicioPrivado {
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
	public SQLServicioPrivado(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ServicioPrivado a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - el identificador del ServicioPrivado
	 * @param nombre - nombre del ServicioPrivado
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarServicioPrivado(PersistenceManager pm, long id, String nombre){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(id, nombre) values (?, ?)");
		// TODO cambiar dar tabla por ServicioPrivado 
		q.setParameters(id, nombre);
		return (long)q.executeUnique();   
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar ServicioPrivado de la base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del ServicioPrivado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPrivadoPorNombre (PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE nombre = ?");
		// TODO cambiar dar tabla por ServicioPrivado 
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar ServicioPrivado de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id del ServicioPrivado
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioPrivadoPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por ServicioPrivado 
		q.setParameters(id);
		return (long) q.executeUnique();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServicioPrivado de la 
	 * base de datos de alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del ServicioPrivado
	 * @return una lista de ServicioPrivado que tienen el nombre dado
	 */
	public List<ServicioPrivado> darServiciosPrivadoPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE nombre = ?");
		// TODO cambiar dar tabla por ServicioPrivado 
		q.setResultClass(ServicioPrivado.class);
		q.setParameters(nombre);
		return (List<ServicioPrivado>) q.executeList();		
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Un ServicioPrivado de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id del ServicioPrivado
	 * @return el objeto ServicioPrivado que tiene el identificador dado
	 */
	public ServicioPrivado darMenajePorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por ServicioPrivado 
		q.setResultClass(ServicioPrivado.class);
		q.setParameters(id);
		return (ServicioPrivado) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServiciosPrivado de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos los Servicios Privados en la tabla Menajes
	 */
	public List<ServicioPrivado> darMenajes(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		// TODO cambiar dar tabla por ServicioPrivado 
		q.setResultClass(ServicioPrivado.class);
		return (List<ServicioPrivado>) q.executeList();
	}
}
