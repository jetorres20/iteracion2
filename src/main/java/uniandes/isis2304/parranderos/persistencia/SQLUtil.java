/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Alohandes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Alohandes
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
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
	public SQLUtil (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqAlohandes () + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public void limpiarAlohandes (PersistenceManager pm)
	{
        Query qApartamentoOfreceServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAptoOfreceServicios());          
        Query qHabitacionHotelIncluyeServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabHotelIncluyeServicio());
        Query qHabitacionVisitanteOfreceServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionVisitanteOfreceServicio());
        Query qHabitacionResidenciaIncluyeServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionresidenciaIncluye());
        Query qHotelOfreceServicios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotelOfreceServicios() );
        Query qHabitacionResidenciaTieneMenajes = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionresidenciaTieneMenajes());
        Query qResidenciaOfreceServicios = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaResidenciaOfreceServicios());
        Query qViviendaTieneMenajes = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaTieneMenajes());
        
        Query qApartamentos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaApartamentos());
        Query qHabitacionesHostal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablahabitacionesHostal());
        Query qHabitacionesHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionesHotel());
        Query qHabitacionesResidencia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionesResidencia());
        Query qHabitacionesVisitantes = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacionesVisitante());
        Query qViviendas = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendas());
        
        Query qHostales = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostales());
        Query qHoteles = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHoteles());
        Query qMenajes = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaMenajes());
        Query qResidencias = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaResidencias());
        Query qServicioResidencia = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosResidencia());
        Query qServiciosHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosHotel());
        Query qServicioPrivados = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServiciosPrivados());
        
        Query qPersonas = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonas());
        
        Query qReservas = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReservas());
        Query qRecintos = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaRecintos());
        Query qOperario = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOperarios());   
        
        
        
        

//        long gustanEliminados = (long) qGustan.executeUnique ();
//        long sirvenEliminados = (long) qSirven.executeUnique ();
//        long visitanEliminadas = (long) qVisitan.executeUnique ();
//        long bebidasEliminadas = (long) qBebida.executeUnique ();
//        long tiposBebidaEliminados = (long) qTipoBebida.executeUnique ();
//        long bebedoresEliminados = (long) qBebedor.executeUnique ();
//        long baresEliminados = (long) qBar.executeUnique ();
//        return new long[] {gustanEliminados, sirvenEliminados, visitanEliminadas, bebidasEliminadas, 
//        		tiposBebidaEliminados, bebedoresEliminados, baresEliminados};
	}

}
