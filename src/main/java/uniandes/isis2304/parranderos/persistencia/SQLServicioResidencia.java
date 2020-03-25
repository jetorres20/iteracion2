package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ServicioResidencia;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ServicioResidencia de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */

class SQLServicioResidencia {
	/* ****************************************************************
	 * 			Constantes 
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
	public SQLServicioResidencia(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}

	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un ServicioResidencia a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - el identificador del ServicioResidencia
	 * @param nombre - nombre del ServicioResidencia
	 * @return EL número de tuplas insertadas
	 */
	public long adicionarServicioResidencia(PersistenceManager pm, long id, String nombre, double costoExtraNoche , double costoExtraMes, double costoExtraSemestre){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServiciosResidencia () + "(id, nombre, costoExtraNoche, costoExtraMes, costoExtraSemestre) values (?, ?, ?, ?, ?)");
		q.setParameters(id, nombre, costoExtraNoche , costoExtraMes, costoExtraSemestre);
		return (long)q.executeUnique();   
	}


	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ServicioResidencia de la base de datos de Alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del ServicioResidencia
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioResidenciaPorNombre (PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosResidencia () + " WHERE nombre = ?");
		q.setParameters(nombre);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ServicioResidencia de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id del ServicioResidencia
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarServicioResidenciaPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosResidencia () + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServicioResidencia de la 
	 * base de datos de alohandes, por su nombre
	 * @param pm - El manejador de persistencia
	 * @param nombre - El nombre del ServicioResidencia
	 * @return una lista de ServicioResidencia que tienen el nombre dado
	 */
	public List<ServicioResidencia> darServicioResidenciaPorNombre(PersistenceManager pm, String nombre)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServiciosResidencia () + " WHERE nombre = ?");
		q.setResultClass(ServicioResidencia.class);
		q.setParameters(nombre);
		return (List<ServicioResidencia>) q.executeList();		
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN ServicioResidencia de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id del ServicioResidencia
	 * @return el objeto ServicioResidencia que tiene el identificador dado
	 */
	public ServicioResidencia darServicioResidenciaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServiciosResidencia () + " WHERE id = ?");
		q.setResultClass(ServicioResidencia.class);
		q.setParameters(id);
		return (ServicioResidencia) q.executeUnique();
	}


	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS ServicioResidencia de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos los ServicioResidencia en la base de datos
	 */
	public List<ServicioResidencia> darServiciosResidencias(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServiciosResidencia ());
		q.setResultClass(ServicioResidencia.class);
		return (List<ServicioResidencia>) q.executeList();
	}
}
