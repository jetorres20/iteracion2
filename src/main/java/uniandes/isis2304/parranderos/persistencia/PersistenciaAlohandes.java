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

import uniandes.isis2304.alohandes.negocio.Apartamento;
import uniandes.isis2304.alohandes.negocio.ApartamentoOfreceServicio;
import uniandes.isis2304.alohandes.negocio.HabitacionHostal;
import uniandes.isis2304.alohandes.negocio.HabitacionHotel;
import uniandes.isis2304.alohandes.negocio.HabitacionHotelIncluyeServicio;
import uniandes.isis2304.alohandes.negocio.HabitacionResidencia;
import uniandes.isis2304.alohandes.negocio.HabitacionResidenciaTieneMenajes;
import uniandes.isis2304.alohandes.negocio.HabitacionVisitante;
import uniandes.isis2304.alohandes.negocio.HabitacionVisitanteOfreceServicio;
import uniandes.isis2304.alohandes.negocio.Hostal;
import uniandes.isis2304.alohandes.negocio.Hotel;
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
import uniandes.isis2304.alohandes.negocio.Vivienda;
import uniandes.isis2304.alohandes.negocio.ViviendaTieneMenajes;


/**
 * Clase para el manejador de persistencia del proyecto Alohandes
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaAlohandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
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
	private static PersistenciaAlohandes instance;
	
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
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLApartamento sqlApartamento;
	
	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLApartamentoOfreceServicio sqlApartamentoOfreceServicio;
	
	
	private SQLHabitacionHostal sqlHabitacionHostal;
	
	
	private SQLHabitacionHotel sqlHabitacionHotel;
	
	private SQLHabitacionHotelIncluyeServicio sqlHabitacionHotelIncluyeServicio;
	
	
	private SQLHabitacionResidencia sqlHabitacionResidencia;
	
	private SQLHabitacionResidenciaTieneMenajes sqlHabitacionResidenciaTieneMenajes;
	
	private SQLHabitacionVisitante sqlHabitacionVisitante;
	
	private SQLHabitacionVisitanteOfreceServicio sqlHabitacionVisitanteOfreceServicio;
	
	private SQLHostal sqlHostal;
	
	private SQLHotel sqlHotel;
	
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
	private PersistenciaAlohandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");
		tablas.add ("APARTAMENTO_OFRECE_SERVICIO");
		tablas.add ("APARTAMENTOS");
		tablas.add ("HAB_HOTEL_INCLUYE_SERVICIO");
		tablas.add ("HAB_VIS_OFRECE_SERVICIO");
		tablas.add ("HABI_RESIDENCIA_TIENE_MENAJES");
		tablas.add ("HABITACION_RESIDENCIA_INCLUYE");
		tablas.add ("HABITACIONES_HOSTAL");
		tablas.add ("HABITACIONES_HOTEL");
		tablas.add ("HABITACIONES_RESIDENCIA");
		tablas.add ("HABITACIONES_VISITANTE");
		tablas.add ("HOSTALES");
		tablas.add ("HOTEL_OFRECE_SERVICIO");
		tablas.add ("HOTELES");
		tablas.add ("MENAJES");
		tablas.add ("PERSONAS");
		tablas.add ("RECINTOS");
		tablas.add ("RESERVAS");
		tablas.add ("RESIDENCIA_OFRECE_SERVICIOS");
		tablas.add ("RESIDENCIAS");
		tablas.add ("SERVICIO_RESIDENCIA");
		tablas.add ("SERVICIOS_HOTEL");
		tablas.add ("SERVICIOS_PRIVADOS");
		tablas.add ("VIVIENDA_TIENE_MENAJES");
		tablas.add ("VIVIENDAS");
		tablas.add ("OPERARIOS");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes (JsonObject tableConfig)
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
	public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaAlohandes existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
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
		sqlApartamento = new SQLApartamento(this);
		sqlApartamentoOfreceServicio = new SQLApartamentoOfreceServicio(this);
		sqlHabitacionHostal = new SQLHabitacionHostal(this);
		sqlHabitacionHotel = new SQLHabitacionHotel(this);
		sqlHabitacionHotelIncluyeServicio= new SQLHabitacionHotelIncluyeServicio(this);
		sqlHabitacionResidencia = new SQLHabitacionResidencia(this);
		sqlHabitacionResidenciaTieneMenajes = new SQLHabitacionResidenciaTieneMenajes(this);
		sqlHabitacionVisitante = new SQLHabitacionVisitante(this);
		sqlHabitacionVisitanteOfreceServicio=new SQLHabitacionVisitanteOfreceServicio(this);
		sqlHostal = new SQLHostal(this);
		sqlHotel = new SQLHotel(this);		
		sqlUtil = new SQLUtil(this);
		sqlHotelOfreceServicio=new SQLHotelOfreceServicio(this);
		sqlServicioHotel = new SQLServicioHotel(this);
		sqlServicioPrivado = new SQLServicioPrivado(this);
		sqlServicioResidencia = new SQLServicioResidencia(this);
		sqlMenaje = new SQLMenaje(this);
		sqlOperario= new SQLOperario(this);
		sqlPersona = new SQLPersona(this);
		sqlRecinto = new SQLRecinto(this);
		sqlReserva= new SQLReserva(this);
		sqlResidencia= new SQLResidencia(this);
		sqlResidenciaOfreceServicio= new SQLResidenciaOfreceServicio(this);
		sqlVivienda= new SQLVivienda(this);
		sqlViviendaTieneMenajes = new SQLViviendaTieneMenajes(this);
		
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
	
	
	

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public Apartamento adicionarApartamento(long idOperario, int numHabitaciones, int precioMes, String direccion, int amoblado , int capacidadTotal, Timestamp fechaRetiroOferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasApartamento = sqlApartamento.adicionarApartamento(pm, idRecinto, idOperario, numHabitaciones, precioMes, direccion, amoblado);
            tx.commit();
            
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasApartamento + " tuplas insertadas");
            boolean bAmoblado = amoblado==1;
         
            return new Apartamento(idRecinto, idOperario, numHabitaciones, precioMes, direccion, bAmoblado);
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
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarApartamentoPorId (long idApto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamento.eliminarApartamentoPorId(pm, idApto);
            sqlRecinto.eliminarRecintoPorId(pm,idApto);
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
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public Apartamento darApartamentoPorId (long idApto) 
	{
		return (Apartamento) sqlApartamento.darApartamentoPorId(pmf.getPersistenceManager(), idApto) ;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<Apartamento> darApartamentos ()
	{
		return sqlApartamento.darApartamentos (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación APARTAMENTOOFRECESERVICIOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla GUSTAN
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor - Debe haber un bebedor con ese identificador
	 * @param idBebida - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto GUSTAN con la información dada. Null si ocurre alguna Excepción
	 */
	public ApartamentoOfreceServicio adicionaAdicionarServicioaApartamento (long idApto,long idServicio, int precio, int incluido)  
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlApartamentoOfreceServicio.adicionarServicioaApartamento(pm, idApto, idServicio, precio, incluido);
            tx.commit();

            log.trace ("Inserción de ApartamentoOfreceServicio: [" + idApto + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

            if(incluido==1)
            return new ApartamentoOfreceServicio(idApto,idServicio, precio, true);
            else
            return new ApartamentoOfreceServicio(idApto,idServicio, precio, false);	
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

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeApartamento(long idApto, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlApartamentoOfreceServicio.eliminarApartamentoPorId(pm, idApto, idServicio);           
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
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<ApartamentoOfreceServicio> darApartamentosOfrecenServicios ()
	{
		return sqlApartamentoOfreceServicio.darApartamentoOfreceServicios (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACIONHOSTAL
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHostal adicionarHabitacionHostal(long idHostal, int compartida, int baniocompartido, int numero, int precioNoche, int capacidadDisponible, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionHostal.adicionarHabitacionHostal(pm, idRecinto, idHostal, numero, compartida, baniocompartido, precioNoche, capacidadDisponible);
            tx.commit();
            
            boolean bCompartido= compartida==1;
            boolean bBanio = baniocompartido==1;
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionHostal(idRecinto, idHostal, bBanio, bCompartido, numero, precioNoche, capacidadDisponible);
            	
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
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHostalporId (long idHostal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.eliminarHabitacionPorId(pm, idHostal);
            sqlRecinto.eliminarRecintoPorId(pm,idHostal);
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
	 * Método que actualiza, de manera transaccional, la capacidad disponible
	 * @param idBebedor - El identificador del bebedor
	 * @param ciudad - La nueva ciudad del bebedor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarCapacidadDisponibleHabitacionHostal (long idHabitacion, int capacidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHostal.cambiarCapacidadDisponibleHabitacion(pm, idHabitacion, capacidad);
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
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionHostal darHabitacionHostalPorId (long habitacionHostal) 
	{
		return (HabitacionHostal) sqlHabitacionHostal.darHabitacionHostalPorId(pmf.getPersistenceManager(), habitacionHostal) ;
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionHostal> darHabitacionesHostal ()
	{
		return sqlHabitacionHostal.darHabitacionesHostal (pmf.getPersistenceManager());
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACIONHOTEL
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionHotel adicionarHabitacionHotel(long idHotel, int numero, String tipo, int precioNoche, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionHotel.adicionarHabitacionHotel(pm, idRecinto, idHotel, numero, tipo, precioNoche);
            tx.commit();
         
            log.trace ("Inserción de habitacion hotel : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionHotel(idRecinto, idHotel, numero, tipo, precioNoche);
            	
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
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionHotelporId (long idHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotel.eliminarHabitacionPorId(pm, idHotel);
            sqlRecinto.eliminarRecintoPorId(pm,idHotel);
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
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionHotel darHabitacionHotelPorId (long idHabitacion) 
	{
		return (HabitacionHotel) sqlHabitacionHotel.darHabitacionHotelPorId(pmf.getPersistenceManager(), idHabitacion) ;
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionHotel> darHabitacionesHotel ()
	{
		return sqlHabitacionHotel.darHabitacionesHotel (pmf.getPersistenceManager());
	}
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION HOTEL OFRECE SERVICIOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla GUSTAN
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor - Debe haber un bebedor con ese identificador
	 * @param idBebida - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto GUSTAN con la información dada. Null si ocurre alguna Excepción
	 */
	public HabitacionHotelIncluyeServicio adicionaAdicionarServicioAHabitacionHotel(long idHabitacion,long idServicio )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionHotelIncluyeServicio.adicionarServicioahabitacion(pm, idHabitacion, idServicio);
            tx.commit();

            log.trace ("Inserción de ApartamentoOfreceServicio: [" + idHabitacion + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

           return new HabitacionHotelIncluyeServicio(idHabitacion, idServicio)	;
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
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeHabitacionHotel(long idHabitacion, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionHotelIncluyeServicio.eliminarServicioDeHabitacionPorId(pm, idHabitacion, idServicio);           
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
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionHotelIncluyeServicio> darHabitacionHotelIncluyeServicios ()
	{
		return sqlHabitacionHotelIncluyeServicio.darHabitacionIncluyeServicios (pmf.getPersistenceManager());
	}
	
	
	/**
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionHotelIncluyeServicio> darServiciosDeUnaHabitacionHotel (long idHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		
		return sqlHabitacionHotelIncluyeServicio.darServiciosDeUnaHabitacion(pm, idHabitacion);
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION RESIDENCIA
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionResidencia adicionarHabitacionResidencia(long idResidencia, int compartido, int baniocompartido, int numero, int precioNoche, int precioMes, int precioSemestre, int capacidadDisponible, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionResidencia.adicionarHabitacionResidencia(pm, idRecinto, idResidencia, compartido, baniocompartido, numero, precioNoche, precioMes, precioSemestre, capacidadDisponible);
            tx.commit();
            
            boolean bCompartido= compartido==1;
            boolean bBanio = baniocompartido==1;
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionResidencia(idRecinto, idResidencia, bCompartido, bBanio, numero, precioNoche, precioMes, precioSemestre, capacidadDisponible);
            	
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
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionResidenciaporId (long idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionResidencia.eliminarHabitacionPorId(pm, idHabitacion);
            sqlRecinto.eliminarRecintoPorId(pm,idHabitacion);
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
	 * Método que actualiza, de manera transaccional, la capacidad disponible
	 * @param idBebedor - El identificador del bebedor
	 * @param ciudad - La nueva ciudad del bebedor
	 * @return El número de tuplas modificadas. -1 si ocurre alguna Excepción
	 */
	public long cambiarCapacidadDisponibleHabitacionResidencia (long idHabitacion, int capacidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionResidencia.cambiarCapacidadDisponibleHabitacion(pm, idHabitacion, capacidad);
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
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionResidencia darHabitacionResidenciaPorId (long idHabitacion) 
	{
		return (HabitacionResidencia) sqlHabitacionResidencia.darHabitacionResidenciaPorId(pmf.getPersistenceManager(), idHabitacion);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionResidencia> darHabitacionesResidencia ()
	{
		return sqlHabitacionResidencia.darHabitacionesResidencia (pmf.getPersistenceManager());
	}
	
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION VISITANTE
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	public HabitacionVisitante adicionarHabitacionVisitante(long idOperario, String direccion, int compartido, int baniocompartido, int numero, int precioMes, int capacidadDisponible, int capacidadTotal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idRecinto = nextval ();
            long tuplasInsertadasRecinto =sqlRecinto.adicionarRecinto(pm,idRecinto,capacidadTotal,null);
            long tuplasInsertadasHabitacionhostal = sqlHabitacionVisitante.adicionarHabitacionVisitante(pm, idRecinto, idOperario, direccion, compartido, baniocompartido, precioMes, capacidadDisponible);
            tx.commit();
            
            boolean bCompartido= compartido==1;
            boolean bBanio = baniocompartido==1;
            log.trace ("Inserción de apartamento : " + idRecinto + ": " + tuplasInsertadasHabitacionhostal + " tuplas insertadas");
            
            return new HabitacionVisitante(idRecinto, idOperario, direccion, bCompartido, bCompartido, precioMes, capacidadDisponible);
            	
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
	

	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla BAR, dado el nombre del bar
	 * Adiciona entradas al log de la aplicación
	 * @param nombreBar - El nombre del bar
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarHabitacionVisitanteporId (long idHabitacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionVisitante.eliminarHabitacionPorId(pm, idHabitacion);
            sqlRecinto.eliminarRecintoPorId(pm,idHabitacion);
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
	 * Método que consulta todas las tuplas en la tabla BEBEDOR que tienen el identificador dado
	 * @param idBebedor - El identificador del bebedor
	 * @return El objeto BEBEDOR, construido con base en la tuplas de la tabla BEBEDOR, que tiene el identificador dado
	 */
	public HabitacionVisitante darHabitacionVisitantePorId (long idHabitacion) 
	{
		return (HabitacionVisitante) sqlHabitacionVisitante.darHabitacionVisitantePorId(pmf.getPersistenceManager(), idHabitacion);
	}
	
	/**
	 * Método que consulta todas las tuplas en la tabla BAR
	 * @return La lista de objetos BAR, construidos con base en las tuplas de la tabla BAR
	 */
	public List<HabitacionVisitante> darHabitacionesVisitante ()
	{
		return sqlHabitacionVisitante.darHabitacionesVisitante (pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación HABITACION VISITANTE OFRECE SERVICIOS
	 *****************************************************************/
	
	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla GUSTAN
	 * Adiciona entradas al log de la aplicación
	 * @param idBebedor - El identificador del bebedor - Debe haber un bebedor con ese identificador
	 * @param idBebida - El identificador de la bebida - Debe haber una bebida con ese identificador
	 * @return Un objeto GUSTAN con la información dada. Null si ocurre alguna Excepción
	 */
	public HabitacionVisitanteOfreceServicio adicionaAdicionarServicioAHabitacionVisitante(long idHabitacion,long idServicio, int precio, int incluido )
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlHabitacionVisitanteOfreceServicio.adicionarServicioaHabitacion(pm, idHabitacion, idServicio, precio, incluido);
            tx.commit();

            log.trace ("Inserción de ApartamentoOfreceServicio: [" + idHabitacion + ", " + idServicio + "]. " + tuplasInsertadas + " tuplas insertadas");

            boolean bincluido = incluido==1;
            
           return new HabitacionVisitanteOfreceServicio(idHabitacion, idServicio, precio, bincluido);
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
	
	
	/**
	 * Método que elimina, de manera transaccional, una tupla en la tabla GUSTAN, dados los identificadores de bebedor y bebida
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return El número de tuplas eliminadas. -1 si ocurre alguna Excepción
	 */
	public long eliminarServicioDeHabitacionVisitante(long idHabitacion, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionVisitanteOfreceServicio.eliminarServicioHabitacionPorId(pm, idHabitacion, idServicio);           
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
	 * Método que consulta todas las tuplas en la tabla GUSTAN
	 * @return La lista de objetos GUSTAN, construidos con base en las tuplas de la tabla GUSTAN
	 */
	public List<HabitacionVisitanteOfreceServicio> darHabitacioVisitanteOfreceServicios ()
	{
		return sqlHabitacionVisitanteOfreceServicio.darHabitacionVisitanteOfreceServicios(pmf.getPersistenceManager());
	}
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los Hostales
	 *****************************************************************/
	public Residencia adicionarHostal( int nit, String nombre, int horaAbre, int horaCierra, String direccion, int telefono)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //primero crear un operario
            long idOperario = nextval();
            Operario op = adicionarOperario(idOperario, new Timestamp(System.currentTimeMillis()));
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, idOperario, nit, nombre, horaAbre, horaCierra, direccion, telefono);
            tx.commit();
            
            log.trace ("Inserción de Hostal: " + idOperario + "," + nit + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	
	
	public long eliminarHostalPorID(long idOperario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorId(pm, idOperario);
            sqlOperario.eliminarOperarioPorId(pm, idOperario);
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
        
        public long eliminarHostalPorNIT(int nit) 
    	{
    		PersistenceManager pm = pmf.getPersistenceManager();
            Transaction tx=pm.currentTransaction();
            try
            {
                tx.begin();
                long resp = sqlHostal.eliminarHostalPorNit(pm, nit);                
                tx.commit();
                return resp;
            }
            catch (Exception e)
            {
//            	e.printStackTrace();
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
        
        
	public Hostal darHostalPorId(long id){
		return sqlHostal.darHostalPorId(pmf.getPersistenceManager(), id);
	}
	
	public Hostal darHostalPorNit(int nit){
		return sqlHostal.darHostalPorNit(pmf.getPersistenceManager(), nit);
	}
	
	public List<Hostal> darHostales(){
		return sqlHostal.darHostales(pmf.getPersistenceManager());
	}
	
	

	/* ****************************************************************
	 * 			Métodos para manejar los Hoteles
	 *****************************************************************/
	public Residencia adicionarHotel(int nit, String nombre, int estrellas, String direccion, int telefono)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //primero crear un operario
            long idOperario = nextval();
            Operario op = adicionarOperario(idOperario, new Timestamp(System.currentTimeMillis()));
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idOperario, nit, nombre, estrellas, direccion, telefono);
            tx.commit();
            
            log.trace ("Inserción de Hotel: " + idOperario + "," + nit + ": " + tuplasInsertadas + " tuplas insertadas");
            
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
	
	
	public long eliminarHotelPorID(long idOperario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorId(pm, idOperario);
            sqlOperario.eliminarOperarioPorId(pm, idOperario);
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
        
        public long eliminarHotelPorNIT(int nit) 
    	{
    		PersistenceManager pm = pmf.getPersistenceManager();
            Transaction tx=pm.currentTransaction();
            try
            {
                tx.begin();
                long resp = sqlHotel.eliminarHotelPorNit(pm, nit);
                tx.commit();
                return resp;
            }
            catch (Exception e)
            {
//            	e.printStackTrace();
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
        
        
	public Hotel darHotelPorId(long id){
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), id);
	}
	
	public Hotel darHotelPorNit(int nit){
		return sqlHotel.darHotelPorNit(pmf.getPersistenceManager(), nit);
	}
	
	public List<Hotel> darHoteles(){
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
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
            sqlOperario.eliminarOperarioPorId(pm, idOperario);
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

	public Reserva adicionarReserva(long recintoId, long personaId, Timestamp fechaInicio, Timestamp fechaFin, int personas)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        
        // calcular el valor de la reserva basado en el tipo de recinto y el numero de dias
        long msFin = fechaFin.getTime();
        long msIni = fechaInicio.getTime();
        long numeroDias = Math.round(((msFin - msIni)*((double)(1.0/1000.0)*(1.0/3600.0)*(1.0/24.0) ) )); //  milliSeconds*(1 segundo/ 1000 ms)*(1 h/3600 seg)(1 dia/ 24 h)
        long numeroDiasTotales = numeroDias; // copia del numero de dias que no cambia
        long numeroMeses = 0;
        long numeroSemestres = 0;
        if(numeroDias >= 30){
        	numeroMeses = numeroDias / 30;
        	numeroDias = numeroDias % 30;
        }
        if(numeroMeses >= 6){
        	numeroSemestres = numeroMeses/6;
        	numeroMeses = numeroMeses%6;
        }
        
        Recinto recintoObj = this.darRecintoPorId(recintoId);
        HabitacionHotel habHotel = this.darHabitacionHotelPorId(recintoId);
        HabitacionHostal habHostal = this.darHabitacionHostalPorId(recintoId);
        HabitacionResidencia habResidencia = this.darHabitacionResidenciaPorId(recintoId);
        HabitacionVisitante habVisitante = this.darHabitacionVisitantePorId(recintoId);
        Apartamento apto = this.darApartamentoPorId(recintoId);
        Vivienda vivienda = this.darViviendaPorId(recintoId);
        
        int capacidadDisponible = recintoObj.getCapacidadTotal();
        
        double subTotal = 0;
        
        if(habHotel != null){
        	subTotal = numeroDiasTotales*habHotel.getPrecioNoche();
        	
        }
        if(habHostal != null){
        	subTotal = numeroDiasTotales*habHostal.getPrecioNoche();
        	if(habHostal.isCompartida()){       	
        		capacidadDisponible = habHostal.getCapacidadDisponible();
        	}
        }
        if(habResidencia != null){
        	subTotal = numeroDias*habResidencia.getPrecioNoche() + numeroMeses*habResidencia.getPrecioMes() + numeroSemestres*habResidencia.getPrecioSemestre();
        	if(habResidencia.isCompartido()){
        		capacidadDisponible = habResidencia.getCapacidadDisponible();
        	}
        }
        if(habVisitante != null){
        	subTotal = numeroMeses*habVisitante.getPrecioMes();
        	if(habVisitante.isBanioCompartido()){
        		capacidadDisponible = habVisitante.getCapacidadDisponible();
        	}
        }
        if(apto != null){
        	subTotal = numeroMeses*apto.getPrecioMes();        	
        }
        if(vivienda != null){
        	subTotal = numeroDiasTotales*vivienda.getPrecioNoche() + vivienda.getPrecioSeguro();
        }
        
        
        
        try
        {
            if(capacidadDisponible >= personas ){
            	tx.begin();
                long id = nextval();
              
                long tuplasInsertadas = sqlReserva.adicionarReserva(pm, id, recintoId, personaId, new Timestamp(System.currentTimeMillis()), fechaInicio, fechaFin, personas, subTotal, new Timestamp(0), 0, 1);
                // actualizar capacidad disponible 
                tx.commit();
                log.trace ("Inserción de Reserva: " + id + "," + personaId + ","  + recintoId + ": " + tuplasInsertadas + " tuplas insertadas");
                
                return new Reserva(id, recintoId, personaId, new Timestamp(System.currentTimeMillis()), fechaInicio, fechaFin, personas, subTotal, new Timestamp(0), 0, true);
            }else{
            	return null;
            }
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
	
	
	
	public void cancelarReserva(long id){
	
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
        	Reserva r = this.darReservaPorId(id);
    		long recintoId = r.getRecintoId(); 
    		Timestamp fechaCancelacion = new Timestamp(System.currentTimeMillis());		
    		Timestamp fechaInicio = r.getFechaInicio();	
    		
    		Recinto recintoObj = this.darRecintoPorId(recintoId);
            HabitacionHotel habHotel = this.darHabitacionHotelPorId(recintoId);
            HabitacionHostal habHostal = this.darHabitacionHostalPorId(recintoId);
            HabitacionResidencia habResidencia = this.darHabitacionResidenciaPorId(recintoId);
            HabitacionVisitante habVisitante = this.darHabitacionVisitantePorId(recintoId);
            Apartamento apto = this.darApartamentoPorId(recintoId);
            Vivienda vivienda = this.darViviendaPorId(recintoId);
            
            // servicios por dia: hoteles, hostales, viviendas tipo 1
            //servicios por meses o semesttres residencia, apto, hab compartida tipo 2
         		
            int tipo = 1;        
            long limiteDias = 3;
            long difDias = fechaInicio.getTime() - fechaCancelacion.getTime();
            
            if(habHotel != null && habHostal != null && vivienda != null){        	
            	tipo = 2;
            	limiteDias = 7;
            }        
            
           double cobroAdicional = 0;
            
            if(difDias > limiteDias ){
            	cobroAdicional = 0.1 * r.getSubTotal();
            }else if(difDias>0 && difDias < limiteDias){
            	cobroAdicional = 0.3 * r.getSubTotal();
            }
            else{
            	cobroAdicional = 0.5*r.getSubTotal();
            }            
            
            sqlReserva.cancelarReservaPorId(pm, id, cobroAdicional, fechaCancelacion);
            tx.commit();
            
            log.trace ("cancelacion de Residencia: " + id + ", cobro add" + cobroAdicional + " pesos");
            
            
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	
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
            sqlOperario.eliminarOperarioPorId(pm, idOperario);
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
            sqlRecinto.eliminarRecintoPorId(pm, idRecinto);
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
	
		

	public List<HotelOfreceServicio> darHotelesOfreceServicios ()
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
	
	public List<ResidenciaOfreceServicio> darResidenciasOfrecenServicios ()
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
	
	
	public List<ViviendaTieneMenajes> darViviendaTieneMenajes()
	{
		return sqlViviendaTieneMenajes.darViviendasTieneMenajes(pmf.getPersistenceManager());
	}	
 

	/* ****************************************************************
	 * 			Métodos para manejar los HabResidenciaTieneMenajesOperar
	 *****************************************************************/
	
	public HabitacionResidenciaTieneMenajes adicionarHabitacionResidenciaTieneMenajes(long idHabitacion, 
			long idMenaje, int cantidad, double valorUnidad)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasInsertadas = sqlHabitacionResidenciaTieneMenajes.adicionarHabitacionResidenciaTieneMenajes(pm, idHabitacion, idMenaje, cantidad, valorUnidad);
            tx.commit();
            
            log.trace ("Inserción de ViviendaTieneMenajes:  res" + idHabitacion + ", men" + idMenaje + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new HabitacionResidenciaTieneMenajes(idHabitacion, idMenaje, cantidad, valorUnidad);
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
	
	
	public long eliminarHabitacionResidenciaTieneMenajes(long idHabitacion, long idMenaje) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacionResidenciaTieneMenajes.eliminarViviendaTieneMenajes(pm, idHabitacion, idMenaje);           
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
	
	
	public List<HabitacionResidenciaTieneMenajes> darHabitacionResidenciaTieneMenajes()
	{
		return sqlHabitacionResidenciaTieneMenajes.darViviendasTieneMenajes(pmf.getPersistenceManager());
	}	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los RC1 y RC2
	 *****************************************************************/
 
	/**
	 * Método que consulta los operarios y el dinero que han recibido este año y el año corrido
	 * @return La lista del id del operario junto con sus dos ganacias
	 */
	public List<Object []> darBebedoresYNumVisitasRealizadas ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlReserva.darOperariosYGanancias(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idOperario = ((BigDecimal) datos [0]).longValue ();
			int gananciaAnio = ((BigDecimal) datos [1]).intValue ();
			int gananciaAnioCorrido = ((BigDecimal) datos [2]).intValue ();
			

			Object [] resp = new Object [3];
			resp [0] = idOperario;
			resp [1] = gananciaAnio;	
			resp [2] = gananciaAnioCorrido;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	/**
	 * Método que consulta los operarios y el dinero que han recibido este año y el año corrido
	 * @return La lista del id del operario junto con sus cantidad de veches que ha sido reservada
	 */
	public List<Object []> darOfertasMasPopulares ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		List<Object> tuplas = sqlReserva.darMejoresRecintos(pmf.getPersistenceManager());
        for ( Object tupla : tuplas)
        {
			Object [] datos = (Object []) tupla;
			long idRecinto = ((BigDecimal) datos [0]).longValue ();
			int reservas = ((BigDecimal) datos [1]).intValue ();
			
			

			Object [] resp = new Object [2];
			resp [0] = idRecinto;
			resp [1] = reservas;	
			
			respuesta.add(resp);
        }

		return respuesta;
	}
	
	
	
	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Alohandes
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE
	 */
	public void limpiarAlohandes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            sqlUtil.limpiarAlohandes (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	
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
	

 }
