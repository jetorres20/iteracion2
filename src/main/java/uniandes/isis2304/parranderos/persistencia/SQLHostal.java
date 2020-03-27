package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.Hostal;

 class SQLHostal {
	
	/* ****************************************************************
	 * 			Constantes // 
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
	public SQLHostal(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	// 
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un Hostal a la base de datos de Alohandes
	 * @param pm
	 * @param idHostal
	 * @param idOperario
	 * @param numeroHabitaciones
	 * @param precioNoche
	 * @param precioSeguro
	 * @param direccion
	 * @param diasUtilizadaAñoActual
	 * @return numero de tuplas insertadas
	 */
	public long adicionarHostal(PersistenceManager pm, long idOperario, int nit, String nombre, int horaAbre, int horaCierra,String direccion, int telefono){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostales () + "( idOperario, nit, nombre, horaAbre, horaCierra, direccion, telefono) values (?, ?, ?, ?, ?, ?, ?)");
	
		q.setParameters(idOperario, nit, nombre, horaAbre, horaCierra, direccion, telefono);
		return (long)q.executeUnique();
	}
	
	

	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una Hostal de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hostal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHostalPorId (PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostales() + " WHERE idOperario = ?");
		
		q.setParameters(id);
		return (long) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar una Hostal de la base de datos de Alohandes, por su id
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hostal
	 * @return EL número de tuplas eliminadas
	 */
	public long eliminarHostalPorNit (PersistenceManager pm, int nit)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostales() + " WHERE nit = ?");
		
		q.setParameters(nit);
		return (long) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNa Hostal de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hostal
	 * @return el objeto Hostal que tiene el identificador dado
	 */
	public Hostal darHostalPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostales () + " WHERE idOperario = ?");
		
		q.setResultClass(Hostal.class);
		q.setParameters(id);
		return (Hostal) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UNa Hostal de la 
	 * base de datos de Alohandes, por su identificador
	 * @param pm - El manejador de persistencia
	 * @param id - El id de la Hostal
	 * @return el objeto Hostal que tiene el identificador dado
	 */
	public Hostal darHostalPorNit(PersistenceManager pm, int nit){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostales () + " WHERE nit = ?");
		
		q.setResultClass(Hostal.class);
		q.setParameters(nit);
		return (Hostal) q.executeUnique();
	}
	
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de Las Hostals de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return todos las Hostals en alohandes
	 */
	public List<Hostal> darHostales(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostales ());

		q.setResultClass(Hostal.class);
		return (List<Hostal>) q.executeList();
	}
	

}
