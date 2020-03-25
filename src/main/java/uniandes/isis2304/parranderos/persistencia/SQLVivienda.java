package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Vivienda;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto Vivienda de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLVivienda {
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
	public SQLVivienda(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Vivienda a la base de datos de Alohandes
	 * @param pm
	 * @param idVivienda
	 * @param idOperario
	 * @param numeroHabitaciones
	 * @param precioNoche
	 * @param precioSeguro
	 * @param direccion
	 * @param diasUtilizadaAñoActual
	 * @return numero de tuplas insertadas
	 */
	public long adicionarVivienda(PersistenceManager pm, long idRecinto, long idOperario, int numeroHabitaciones, double precioNoche, double precioSeguro, String direccion, int diasUsada){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaViviendas () + "(idRecinto, idOperario, numeroHabitaciones, precioNoche, precioSeguro, direccion, diasUsada) values (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idRecinto, idOperario, numeroHabitaciones, precioNoche, precioSeguro, direccion, diasUsada);
		return (long)q.executeUnique();
	}
	
	

	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una vivienda de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la vivienda
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarViviendaPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendas () + " WHERE idRecinto = ?");
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNa vivienda de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la vivienda
	 * @return el objeto Vivienda que tiene el identificador dado
	 */
	public Vivienda darViviendaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendas () + " WHERE idRecinto = ?");
		q.setResultClass(Vivienda.class);
		q.setParameters(id);
		return (Vivienda) q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Las viviendas de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos las viviendas en alohandes
	 */
	public List<Vivienda> darViviendas(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendas ());
		q.setResultClass(Vivienda.class);
		return (List<Vivienda>) q.executeList();
	}
}