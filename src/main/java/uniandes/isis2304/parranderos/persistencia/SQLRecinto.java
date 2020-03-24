package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Recinto;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Recinto de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLRecinto {
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
	public SQLRecinto(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Rwcinto a la base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @param id - id unico del recinto
	 * @param capacidadTotal - capacidad total del recinto
	 * @param fechaRetiroOferta - fecha en la que se 
	 * @return numero de tuplas insertadas
	 */
	public long adicionarRecinto(PersistenceManager pm, long id, int capacidadTotal, Timestamp fechaRetiroOferta){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSirven () + "(id, capacidadTotal, fechaRetiroOferta) values (?, ?, ?)");
		// TODO cambiar dar tabla por Recintos 
		q.setParameters(id, capacidadTotal, fechaRetiroOferta);
		return (long)q.executeUnique(); 
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un recinto de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - id unico del recinto
	 * @return numero de tuplas eliminadas
	 */
	public long eliminarRecintoPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Recintos 
		q.setParameters(id);
		return (long) q.executeUnique();
		
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información parcial de UN Recinto de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - - id unico del recinto
	 * @return el objeto recinto que tiene el id dado
	 */
	public Recinto darRecintoPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar () + " WHERE id = ?");
		// TODO cambiar dar tabla por Recintos 
		q.setResultClass(Recinto.class);
		q.setParameters(id);
		return (Recinto) q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de todos LOS Recintos de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia- 
	 * @return todos los recintos de la base de datos
	 */
	public List<Recinto> darRecintos(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBar());
		// TODO cambiar dar tabla por Recintos
		q.setResultClass(Recinto.class);
		return (List<Recinto>) q.executeList();
	}	
}