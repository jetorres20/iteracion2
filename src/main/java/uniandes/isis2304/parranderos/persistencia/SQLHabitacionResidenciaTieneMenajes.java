package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.HabitacionResidenciaTieneMenajes;

 class SQLHabitacionResidenciaTieneMenajes {
	
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
	public SQLHabitacionResidenciaTieneMenajes(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una HabitacionResidenciaTieneMenajes a la base de datos de Alohande
	 * @param pm
	 * @param idHabitacionResidencia
	 * @param idMenaje
	 * @param cantidad
	 * @param valorUnidad
	 * @return numero de tuplas insertadas
	 */
	public long adicionarHabitacionResidenciaTieneMenajes(PersistenceManager pm, long idHabitacion, long idMenaje, int cantidad, double valorUnidad){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacionresidenciaTieneMenajes () + "(idHabitacion, idMenaje, cantidad,  valorUnidad) values (?, ?, ?, ?)");
		// TODO cambiar dar tabla por ViviendaTieneMenajes 
		q.setParameters(idHabitacion, idMenaje, cantidad,  valorUnidad);
		return (long)q.executeUnique();
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ViviendaTieneMenajes de la base de datos de Alohandes, por sus identificadores
	 * @param idVivienda
	 * @param idMenaje
	 * @return numero tuplas eliminadas
	 */
	public long eliminarViviendaTieneMenajes(PersistenceManager pm, long idHabitacion, long idMenaje){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionresidenciaTieneMenajes () + " WHERE idHabitacion = ? AND idMenaje = ?");
		// TODO cambiar dar tabla sirven por ViviendaTieneMenajes 
		q.setParameters(idHabitacion, idMenaje);
		return (long) q.executeUnique();   
	}
	
	// TODO revisar orden y nombre de parametros sentencia SQL
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los ViviendaTieneMenajes de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ViviendaTieneMenajes
	 */
	public List<HabitacionResidenciaTieneMenajes> darViviendasTieneMenajes(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacionresidenciaTieneMenajes());
		// TODO cambiar dar tabla sirven por ViviendaTieneMenajes 
		q.setResultClass(HabitacionResidenciaTieneMenajes.class);
		return (List<HabitacionResidenciaTieneMenajes>) q.execute();
	}

}
