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


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.alohandes.negocio.Bar;
import uniandes.isis2304.alohandes.negocio.Bebedor;
import uniandes.isis2304.alohandes.negocio.Bebida;
import uniandes.isis2304.alohandes.negocio.Gustan;
import uniandes.isis2304.alohandes.negocio.HotelOfreceServicio;
import uniandes.isis2304.alohandes.negocio.Menaje;
import uniandes.isis2304.alohandes.negocio.Operario;
import uniandes.isis2304.alohandes.negocio.Persona;
import uniandes.isis2304.alohandes.negocio.Recinto;
import uniandes.isis2304.alohandes.negocio.Reserva;
import uniandes.isis2304.alohandes.negocio.Residencia;
import uniandes.isis2304.alohandes.negocio.ResidenciaOfreceServicio;
import uniandes.isis2304.alohandes.negocio.ServicioHotel;
import uniandes.isis2304.alohandes.negocio.ServicioPrivado;
import uniandes.isis2304.alohandes.negocio.ServicioResidencia;
import uniandes.isis2304.alohandes.negocio.Sirven;
import uniandes.isis2304.alohandes.negocio.TipoBebida;
import uniandes.isis2304.alohandes.negocio.Visitan;
import uniandes.isis2304.alohandes.negocio.Vivienda;
import uniandes.isis2304.alohandes.negocio.ViviendaTieneMenajes;

public class PersistenciaAlohandesCopiaAndres 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandesCopiaAndres.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandesCopiaAndres instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaAlohandes
	 */
	private SQLUtil sqlUtil;
	
		
	
	private SQLHotelOfreceServicio sqlHotelOfreceServicio;
	
	private SQLMenaje sqlMenaje;
	
	private SQLOperario sqlOperario;
	
	private SQLPersona sqlPersona;
	
	private SQLRecinto sqlRecinto;
	
	private SQLReserva sqlReserva;
	
	private SQLResidencia sqlResidencia;
	
	private SQLResidenciaOfreceServicio sqlResidenciaOfreceServicio;
	
	private SQLServicioHotel sqlServicioHotel;
	
	private SQLServicioPrivado sqlServicioPrivado;
	
	private SQLServicioResidencia sqlServicioResidencia;
	
	private SQLVivienda sqlVivienda; 
	
	private SQLViviendaTieneMenajes sqlViviendaTieneMenajes;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohandesCopiaAndres ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");
		tablas.add ("TIPOBEBIDA");
		tablas.add ("BEBIDA");
		tablas.add ("BAR");
		tablas.add ("BEBEDOR");
		tablas.add ("GUSTAN");
		tablas.add ("SIRVEN");
		tablas.add ("VISITAN");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandesCopiaAndres (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandesCopiaAndres getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandesCopiaAndres ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandesCopiaAndres getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandesCopiaAndres (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlTipoBebida = new SQLTipoBebida(this);
		sqlBebida = new SQLBebida(this);
		sqlBar = new SQLBar(this);
		sqlBebedor = new SQLBebedor(this);
		sqlGustan = new SQLGustan(this);
		sqlSirven = new SQLSirven (this);
		sqlVisitan = new SQLVisitan(this);		
		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaAptoOfreceServicios ()
	{
		return tablas.get (1);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoBebida de parranderos
	 */
	public String darTablaApartamentos ()
	{
		return tablas.get (2);
	}


	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaHabHotelIncluyeServicio ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaHabitacionVisitanteOfreceServicio ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaHabitacionresidenciaTieneMenajes ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaHabitacionresidenciaIncluye ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablahabitacionesHostal ()
	{
		return tablas.get (7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHabitacionesHotel ()
	{
		return tablas.get (8);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHabitacionesResidencia ()
	{
		return tablas.get (9);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHabitacionesVisitante()
	{
		return tablas.get (10);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHostales ()
	{
		return tablas.get (11);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHotelOfreceServicios ()
	{
		return tablas.get (12);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHoteles ()
	{
		return tablas.get (13);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaMenajes ()
	{
		return tablas.get (14);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPersonas()
	{
		return tablas.get (15);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaRecintos ()
	{
		return tablas.get (16);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaReservas ()
	{
		return tablas.get (17);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaResidenciaOfreceServicios ()
	{
		return tablas.get (18);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaResidencias ()
	{
		return tablas.get (19);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServiciosResidencia ()
	{
		return tablas.get (20);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServiciosHotel ()
	{
		return tablas.get (21);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServiciosPrivados()
	{
		return tablas.get (22);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaViviendaTieneMenajes ()
	{
		return tablas.get (23);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaViviendas ()
	{
		return tablas.get (24);
	}
	
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaOperarios ()
	{
		return tablas.get (25);
	}

	
	
	/**
	 * Transacción para el generador de secuencia de Alohandes
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Alohandes
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Operario
	 *****************************************************************/
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla Operario
	 * Adiciona entradas al log de la aplicación
	 * @param long id
	 * Timestamp fechaRegistro
	 * @return El objeto Operario adicionado. null si ocurre alguna Excepción
	 */
	public Operario adicionarOperario(long id, Timestamp fechaRegistro)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlOperario.adicionarOperario(pm, id, fechaRegistro);
            tx.commit();
            
            log.trace ("Inserción de Operario: " + id + "," + fechaRegistro + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Operario(id, fechaRegistro);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	
	
	public long eliminarOperarioPorId(long id){
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOperario.eliminarOperarioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla Operarios
	 * @return La lista de objetos Operario, construidos con base en las tuplas de la tabla Operarios
	 */
	public List<Operario> darOperarios ()
	{
		return sqlOperario.darOperarios(pmf.getPersistenceManager());
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla Operario con un identificador dado
	 * @param id - El identificador del Operario
	 * @return El objeto Operario, construido con base en las tuplas de la tabla Operario con el identificador dado
	 */
	public Operario darOperarioPorId (long id)
	{
		return sqlOperario.darOperarioPorId(pmf.getPersistenceManager(), id);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Persona
	 *****************************************************************/
	public Persona adicionarPersona(int cedula, String nombre, String apellido, int telefono, String correo, int vinculo)
	{
				
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
          //crear un operario para esta persona
    		long idOperario = nextval ();
    		Operario op = adicionarOperario(idOperario, new Timestamp(System.currentTimeMillis()));
            
            long tuplasInsertadas = sqlPersona.adicionarPersona(pm, idOperario, cedula, nombre, apellido, telefono, correo, vinculo);
            tx.commit();
            
            log.trace ("Inserción de persona: " + idOperario + "," + cedula + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Persona(idOperario, cedula, nombre, apellido, telefono, correo, vinculo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	public long eliminarPersonaPorId(long idOperario){
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPersona.eliminarPersonaPorId(pm, idOperario);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Persona darPersonaPorId (long idOperario)
	{
		return sqlPersona.darPersonaPorId(pmf.getPersistenceManager(), idOperario);
	}
	
	public List<Persona> darPersonas()
	{
		return sqlPersona.darPersonas(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Recinto
	 *****************************************************************/
	
	public Recinto adicionarRecinto(long id, int capacidadTotal){
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlRecinto.adicionarRecinto(pm, id, capacidadTotal, null);
            tx.commit();
            
            log.trace ("Inserción de Recinto: " + id  + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Recinto(id, capacidadTotal);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarRecinto(long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlRecinto.eliminarRecintoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Recinto darRecintoPorId(long id){
		return sqlRecinto.darRecintoPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<Recinto> darRecintos()
	{
		return sqlRecinto.darRecintos(pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar las Reservas
	 *****************************************************************/

	public Reserva adicionarReserva(long recintoId, long personaId, Timestamp fechaInicio, Timestamp fechaFin, int personas, double subTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long id = nextval();
            //TODO buscar el recinto y segun la fecha calcular el subtotal
            long tuplasInsertadas = sqlReserva.adicionarReserva(pm, id, recintoId, personaId, new Timestamp(System.currentTimeMillis()), fechaInicio, fechaFin, personas, subTotal, new Timestamp(0), 0, true);
            tx.commit();
            
            log.trace ("Inserción de Reserva: " + id + "," + personaId + ","  + recintoId + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Reserva(id, recintoId, personaId, new Timestamp(System.currentTimeMillis()), fechaInicio, fechaFin, personas, subTotal, new Timestamp(0), 0, true);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	public Reserva darReservaPorId(long id){
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), id);
	}
	
	
	//crear un metodo en SQL para cancelar update datos de una tupla en la tabla reservas
	public void cancelarReserva(long id){
		//TODO //crear un metodo en SQL para cancelar update datos de una tupla en la tabla reservas
		//calcular el cobro adicional
	}
	
	public List<Reserva> darReservas(){
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}
	
	

	/* ****************************************************************
	 * 			Métodos para manejar las Residencias
	 *****************************************************************/
	public Residencia adicionarResidencia(int nit,  String nombre, String direccion, int telefono )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //primero crear un operario
            long idOperario = nextval();
            Operario op = adicionarOperario(idOperario, new Timestamp(System.currentTimeMillis()));
            long tuplasInsertadas = sqlResidencia.adicionarResidencia(pm, idOperario, nit, nombre, direccion, telefono);
            tx.commit();
            
            log.trace ("Inserción de Residencia: " + idOperario + "," + nit + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Residencia(idOperario, nit, nombre, direccion, telefono);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	
	public long eliminarResidencia(long idOperario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlResidencia.eliminarResidenciaPorId(pm, idOperario);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Residencia darResidenciaPorId(long id){
		return sqlResidencia.darResidenciaPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<Residencia> darResidencias(){
		return sqlResidencia.darResidencias(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar las viviendas
	 *****************************************************************/
	// int capacidadToatl, long idOperario, int numeroHabitaciones, double precioNoche, double precioSeguro, String direccion, int diasUsada
	
	public Vivienda adicionarVivienda(int capacidadTotal, long idOperario, int numeroHabitaciones, double precioNoche, double precioSeguro, String direccion, int diasUsada)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //creamos el recinto
            long idRecinto = nextval();
            Recinto rec = adicionarRecinto(idRecinto, capacidadTotal);
            
            long tuplasInsertadas = sqlVivienda.adicionarVivienda(pm, idRecinto, idOperario, numeroHabitaciones, precioNoche, precioSeguro, direccion, 0);
            tx.commit();
            
            log.trace ("Inserción de Vivienda: " + idRecinto + ", persona" + idOperario + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Vivienda(idRecinto, idOperario, numeroHabitaciones, precioNoche, precioSeguro, direccion, 0) ;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarViviendaPorId (long idRecinto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlVivienda.eliminarViviendaPorId(pm, idRecinto);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public Vivienda darViviendaPorId(long id){
		return sqlVivienda.darViviendaPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<Vivienda> darViviendas(){
		return sqlVivienda.darViviendas(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los Menajes
	 *****************************************************************/
	public Menaje adicionarMenaje(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idMenaje = nextval();
            long tuplasInsertadas = sqlMenaje.adicionarMenaje(pm, idMenaje, nombre);
            tx.commit();
            
            log.trace ("Inserción de Menaje: " + idMenaje + "," + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Menaje (idMenaje, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarMenajePorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlMenaje.eliminarMenajePorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public Menaje darMenajePorId(long id){
		return sqlMenaje.darMenajePorId(pmf.getPersistenceManager(), id);
	}
	
	public List<Menaje> darMenajes(){
		return sqlMenaje.darMenajes(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los ServicioHotel
	 *****************************************************************/
	public ServicioHotel adicionarServicioHotel(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval();
            long tuplasInsertadas = sqlServicioHotel.adicionarServicio(pm, idServicio, nombre);
            tx.commit();
            
            log.trace ("Inserción de ServicioHotel: " + idServicio + "," + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ServicioHotel (idServicio, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarServicioHotelPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioHotel.eliminarServicioPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public ServicioHotel darServicioHotelPorId(long id){
		return sqlServicioHotel.darServicioHotelPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<ServicioHotel> darServiciosHotel(){
		return sqlServicioHotel.darServicios(pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los ServiciosResidencia
	 *****************************************************************/
	
	public ServicioResidencia adicionarServicioResidencia(String nombre, double costoExtraNoche , double costoExtraMes, double costoExtraSemestre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval();
            long tuplasInsertadas = sqlServicioResidencia.adicionarServicioResidencia(pm, idServicio, nombre, costoExtraNoche, costoExtraMes, costoExtraSemestre);
            tx.commit();
            
            log.trace ("Inserción de ServicioResidencia: " + idServicio + "," + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ServicioResidencia (idServicio, nombre, costoExtraNoche, costoExtraMes, costoExtraSemestre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarServicioResidenciaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioResidencia.eliminarServicioResidenciaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public ServicioResidencia darServicioResidenciaPorId(long id){
		return sqlServicioResidencia.darServicioResidenciaPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<ServicioResidencia> darServiciosResidencia(){
		return sqlServicioResidencia.darServiciosResidencias(pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ServicioPrivado
	 *****************************************************************/
	
	
	public ServicioPrivado adicionarServicioPrivado(String nombre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval();
            long tuplasInsertadas = sqlServicioPrivado.adicionarServicioPrivado(pm, idServicio, nombre);
            tx.commit();
            
            log.trace ("Inserción de ServicioPrivado: " + idServicio + "," + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ServicioPrivado (idServicio, nombre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarServicioPrivadoPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioPrivado.eliminarServicioPrivadoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public ServicioPrivado darServicioPrivadoPorId(long id){
		return sqlServicioPrivado.darServicioPorId(pmf.getPersistenceManager(), id);
	}
	
	public List<ServicioPrivado> darServiciosPrivados(){
		return sqlServicioPrivado.darServiciosPrivados(pmf.getPersistenceManager());
	}
	
	
	
	
	

	/* ****************************************************************
	 * 			Métodos para manejar los HotelOfreceServicio
	 *****************************************************************/

	
	public HotelOfreceServicio adicionarHotelOfreceServicio(long idHotel, long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlHotelOfreceServicio.adicionarHotelOfreceServicio(pm, idHotel, idServicio);
            tx.commit();
            
            log.trace ("Inserción de HotelOfreceServicio: " + idHotel + "," + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new HotelOfreceServicio (idHotel, idServicio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarHotelOfreceServicio(long idHotel, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotelOfreceServicio.eliminarHotelOfreceServicio(pm, idHotel, idServicio);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
		

	List<HotelOfreceServicio> darHotelesOfreceServicios ()
	{
		return sqlHotelOfreceServicio.darHotelesOfreceServicio(pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ResidenciaOfreceServicio
	 *****************************************************************/
	
	
	public ResidenciaOfreceServicio adicionarResidenciaOfreceServicio(long idResidencia, long idServicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlResidenciaOfreceServicio.adicionarResidenciaOfreceServicio(pm, idResidencia, idServicio);
            tx.commit();
            
            log.trace ("Inserción de ResidenciaOfreceServicio:  res" + idResidencia + ", serv" + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ResidenciaOfreceServicio(idResidencia, idServicio);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long eliminarResidenciaOfreceServicio(long idResidencia, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlResidenciaOfreceServicio.eliminarResidenciaOfreceServicio(pm, idResidencia, idServicio);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	List<ResidenciaOfreceServicio> darResidenciasOfrecenServicios ()
	{
		return sqlResidenciaOfreceServicio.darResidenciaOfreceServicio(pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los ViviendaTieneMenajesOperar
	 *****************************************************************/
	
	public ViviendaTieneMenajes adicionarViviendaTieneMenajes(long idVivienda, long idMenaje, int cantidad, double valorUnidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlViviendaTieneMenajes.adicionarViviendaTieneMenajes(pm, idVivienda, idMenaje, cantidad, valorUnidad);
            tx.commit();
            
            log.trace ("Inserción de ViviendaTieneMenajes:  res" + idVivienda + ", men" + idMenaje + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new ViviendaTieneMenajes(idVivienda, idMenaje, cantidad, valorUnidad);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	public long eliminarViviendaTieneMenajes(long idVivienda, long idMenaje) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlViviendaTieneMenajes.eliminarViviendaTieneMenajes(pm, idVivienda, idMenaje);           
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	
	List<ViviendaTieneMenajes> darViviendaTieneMenajes()
	{
		return sqlViviendaTieneMenajes.darViviendasTieneMenajes(pmf.getPersistenceManager());
	}	
 }
