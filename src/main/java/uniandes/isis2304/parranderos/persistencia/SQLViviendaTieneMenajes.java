package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.alohandes.negocio.ViviendaTieneMenajes;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto ViviendaTieneMenajes de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Equipo A-09
 */
class SQLViviendaTieneMenajes {
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
	public SQLViviendaTieneMenajes(PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar una ViviendaTieneMenajes a la base de datos de Alohande
	 * @param pm
	 * @param idVivienda
	 * @param idMenaje
	 * @param cantidad
	 * @param valorUnidad
	 * @return numero de tuplas insertadas
	 */
	public long adicionarViviendaTieneMenajes(PersistenceManager pm, long idVivienda, long idMenaje, int cantidad, double valorUnidad){
		Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaViviendaTieneMenajes () + "(idVivienda, idMenaje, cantidad,  valorUnidad) values (?, ?, ?, ?)");
		q.setParameters();
		return (long)q.executeUnique();
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar un ViviendaTieneMenajes de la base de datos de Alohandes, por sus identificadores
	 * @param idVivienda
	 * @param idMenaje
	 * @return numero tuplas eliminadas
	 */
	public long eliminarViviendaTieneMenajes(PersistenceManager pm, long idVivienda, long idMenaje){
		Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaTieneMenajes () + " WHERE idVivienda = ? AND idMenaje = ?");
		q.setParameters(idVivienda, idMenaje);
		return (long) q.executeUnique();   
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de los ViviendaTieneMenajes de la 
	 * base de datos de Alohandes
	 * @param pm - El manejador de persistencia
	 * @return Una lista de objetos ViviendaTieneMenajes
	 */
	public List<ViviendaTieneMenajes> darViviendasTieneMenajes(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendaTieneMenajes ());
		q.setResultClass(ViviendaTieneMenajes.class);
		return (List<ViviendaTieneMenajes>) q.execute();
	}

	
}
