package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ResidenciaOfreceServicio;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Residencia Ofrece Servicio de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLResidenciaOfreceServicio {
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
	public SQLResidenciaOfreceServicio(PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 *  Crea y ejecuta la sentencia SQL para adicionar una conexion entre una residencia y un servicio que ofrece
	 * @param pm - El manejador de persistencia
	 * @param idResidencia id del operario que es la residencia
	 * @param idServicio
	 * @return numero de tuplas insertadas
	 */
	public long adicionarResidenciaOfreceServicio(PersistenceManager pm, long idResidencia, long idServicio){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaGustan () + "(idResidencia, idServicio) values (?, ?)");
		// TODO cambiar dar tabla por ResidenciaOfreceServicio 
		q.setParameters(idResidencia, idServicio);
        return (long) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN Residencia ofrece Servicio de la base de datos de alohandes, por su pk
	 * @param pm - El manejador de persistencia
	 * @param idResidencia - El identificador del bebedor
	 * @param idServicio - El identificador de la bebida
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarResidenciaOfreceServicio (PersistenceManager pm, long idResidencia, long idServicio)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaGustan () + " WHERE idResidencia = ? AND idServicio = ?");
     // TODO cambiar dar tabla por ResidenciaOfreceServicio 
        q.setParameters(idResidencia, idServicio);
        return (long) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos LOS ResidenciaOfreceServicio de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos los ResidenciaOfreceServicio
	 */
	public List<ResidenciaOfreceServicio> darResidenciaOfreceServicio(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar ());
		// TODO cambiar dar tabla por ResidenciaOfreceServicio 
		q.setResultClass(ResidenciaOfreceServicio.class);
		return (List<ResidenciaOfreceServicio>) q.executeList();
	}
	
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el identificador y el número de servicio que ofrecen las residencias de la 
	 * base de datos de alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de una Residencia,
	 * 	el segundo elemento representa el número de servicios que ofrece
	 */
	public List<Object []> darResidenciasYCantidadServicios (PersistenceManager pm)
	{
		String sql = "SELECT idResidencia, count (*) as numServicios";
		sql += " FROM " + pp.darTablaSirven ();
		// TODO cambiar dar tabla sirven por ResidenciaOfreceServicio 
		sql	+= " GROUP BY idResidencia";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}

	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar el identificador y el número de residencias en los que se ofrece un servicio 
	 * base de datos de alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de parejas de objetos, el primer elemento de cada pareja representa el identificador de un Servicio,
	 * 	el segundo elemento representa el número de residencias en los que se ofrece
	 */
	public List<Object []> darServiciosYCantidadHoteles (PersistenceManager pm)
	{
		String sql = "SELECT idServicio, count (*) as numResidencias";
		sql += " FROM " + pp.darTablaSirven ();
		// TODO cambiar dar tabla sirven por ResidenciaOfreceServicio 
		sql	+= " GROUP BY idServicio";
		Query q = pm.newQuery(SQL, sql);
		return q.executeList();
	}	
}
