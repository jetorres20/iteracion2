/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.alohandes.negocio.Alohandes;
import uniandes.isis2304.alohandes.negocio.VOPersona;
import uniandes.isis2304.alohandes.negocio.VOReserva;

/**
 * Clase principal de la interfaz
 * 
 */
@SuppressWarnings("serial")

public class InterfazParranderosApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazParranderosApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private Alohandes alohandes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazParranderosApp( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		alohandes = new Alohandes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			CRUD de Reserva
	 *****************************************************************/

	/**
	 * Adiciona una nueva reserva con la información dada por el usuario
	 * Se crea una nueva tupla de Reserva en la base de datos
	 */
	public void adicionarReserva( )
	{
		try 
		{
			String recintId = JOptionPane.showInputDialog (this, "El identificador de la oferta?", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
			String personaId = JOptionPane.showInputDialog (this, "Su identificador de cliente?", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
			String fechaIni = JOptionPane.showInputDialog (this, "La fecha de inicio? En formato dia/mes/año ", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
			String fechafin = JOptionPane.showInputDialog (this, "La fecha de finalizacion? En formato dia/mes/año ", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);



			if (recintId != null && personaId!= null && fechaIni != null && fechafin!= null)
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				Date date2=null;
				date2= dateFormat.parse(fechaIni);
				Timestamp fechaInicio = new Timestamp( date2.getTime());
				Date date3=null;
				date3= dateFormat.parse(fechafin);
				Timestamp fechaFin = new Timestamp( date3.getTime());
				String cant = JOptionPane.showInputDialog (this, "Cuantas personas?", "Adicionar reserva", JOptionPane.QUESTION_MESSAGE);
				int personas = Integer.parseInt(cant);
				long idRecinto = Integer.parseInt(recintId);
				long idPersona = Integer.parseInt(personaId);   

				VOReserva tb = alohandes.adicionarReserva(idRecinto, idPersona, fechaInicio, fechaFin, personas);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un tipo de bebida con id: " + idRecinto);
				}

			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarReservas(){
		try 
		{
			List <VOReserva> lista = alohandes.darVOReservas();
			System.out.println("hice la lista en la interfaz");
			String resultado = "En listar Reservas";
			resultado +=  "\n" + mostrarReservas (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	private String mostrarReservas(List<VOReserva> lista) 
	{
		String resp = "Las reservas existentes son:\n";
		int i = 1;
		for (VOReserva tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}





	public void cancelarReserva(){
		try 
		{
			String idTipoStr = JOptionPane.showInputDialog (this, "Id de la reserva?", "Cancelar una Reserva", JOptionPane.QUESTION_MESSAGE);
			if (idTipoStr != null)
			{
				long id = Long.valueOf (idTipoStr);
				alohandes.cancelarReserva(id);


				String resultado = "\n Se cancelo la reserva con id" + id;
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}


	/**
	 * Borra de la base de datos el tipo de bebida con el identificador dado po el usuario
	 * Cuando dicho tipo de bebida no existe, se indica que se borraron 0 registros de la base de datos
	 */
	public void eliminarOfertaAlohamiento( )
	{
		try 
		{
			String idTipoStr = JOptionPane.showInputDialog (this, "Id del recinto", "Borrar recinto/oferta por Id", JOptionPane.QUESTION_MESSAGE);
			if (idTipoStr != null)
			{
				long id = Long.valueOf (idTipoStr);
				long tbEliminados = alohandes.eliminarRecintoPorID(id);

				String resultado = "En eliminar recinto\n\n";
				resultado += tbEliminados + " recintos/ ofertas eliminados\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void consultarUsuariosSinReserva(){

		try{
			int adminResp = JOptionPane.showConfirmDialog(this, "Es usted el administrador?");
			boolean admin;
			System.out.println(adminResp);
			if(adminResp==0){
				admin=true;
				String stringidRecinto = JOptionPane.showInputDialog (this, "Inserte el Id del recinto que quiere consultar ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechaIni = JOptionPane.showInputDialog (this, "La fecha de inicio? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechafin = JOptionPane.showInputDialog (this, "La fecha de finalizacion? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);


				if( stringidRecinto!= null && fechaIni != null && fechafin != null  ){
					long idRecinto = Long.valueOf(stringidRecinto);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date2=null;
					date2= dateFormat.parse(fechaIni);
					Timestamp fechaInicio = new Timestamp( date2.getTime());
					Date date3=null;
					date3= dateFormat.parse(fechafin);
					Timestamp fechaFin = new Timestamp( date3.getTime());
					int ordenRespAsc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre ascendentemente?");
					if(ordenRespAsc==0){

						List<VOPersona> personas = alohandes.darClientesSinReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, 0, 1);
						mostrarPersonas(personas);
					}	
					if(ordenRespAsc==1){
						int ordenRespDesc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre descendentemente?");
						if(ordenRespDesc==0){
							List<VOPersona> personas = alohandes.darClientesSinReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, 0, 1);
							mostrarPersonas(personas);
						}
					}
				}
			}
			else if (adminResp==1){
				admin=false;
				String stringidOperario =JOptionPane.showInputDialog (this, "Inserte su Id de operario", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String stringidRecinto = JOptionPane.showInputDialog (this, "Inserte el Id del recinto que quiere consultar ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechaIni = JOptionPane.showInputDialog (this, "La fecha de inicio? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechafin = JOptionPane.showInputDialog (this, "La fecha de finalizacion? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);


				if(stringidOperario!= null && stringidRecinto!= null && fechaIni != null && fechafin != null  ){
					long idOperario = Long.valueOf(stringidOperario);
					long idRecinto = Long.valueOf(stringidRecinto);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date2=null;
					date2= dateFormat.parse(fechaIni);
					Timestamp fechaInicio = new Timestamp( date2.getTime());
					Date date3=null;
					date3= dateFormat.parse(fechafin);
					Timestamp fechaFin = new Timestamp( date3.getTime());
					int ordenRespAsc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre ascendentemente?");
					if(ordenRespAsc==0){

						List<VOPersona> personas = alohandes.darClientesSinReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, idOperario, 1);
						mostrarPersonas(personas);
					}	
					if(ordenRespAsc==1){
						int ordenRespDesc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre descendentemente?");
						if(ordenRespDesc==0){
							List<VOPersona> personas = alohandes.darClientesSinReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, idOperario, 1);
							mostrarPersonas(personas);
						}

					}





				}


			}

		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}



	}

	public void consultarUsuariosConReserva(){

		try{
			int adminResp = JOptionPane.showConfirmDialog(this, "Es usted el administrador?");
			boolean admin;
			System.out.println(adminResp);
			if(adminResp==0){
				admin=true;
				String stringidRecinto = JOptionPane.showInputDialog (this, "Inserte el Id del recinto que quiere consultar ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechaIni = JOptionPane.showInputDialog (this, "La fecha de inicio? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechafin = JOptionPane.showInputDialog (this, "La fecha de finalizacion? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);


				if( stringidRecinto!= null && fechaIni != null && fechafin != null  ){
					long idRecinto = Long.valueOf(stringidRecinto);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date2=null;
					date2= dateFormat.parse(fechaIni);
					Timestamp fechaInicio = new Timestamp( date2.getTime());
					Date date3=null;
					date3= dateFormat.parse(fechafin);
					Timestamp fechaFin = new Timestamp( date3.getTime());
					int ordenRespAsc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre ascendentemente?");
					if(ordenRespAsc==0){

						List<VOPersona> personas = alohandes.darClientesConReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, 0, 1);
						mostrarPersonas(personas);
					}	
					if(ordenRespAsc==1){
						int ordenRespDesc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre descendentemente?");
						if(ordenRespDesc==0){
							List<VOPersona> personas = alohandes.darClientesConReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, 0, 1);
							mostrarPersonas(personas);
						}
					}
				}
			}
			else if (adminResp==1){
				admin=false;
				String stringidOperario =JOptionPane.showInputDialog (this, "Inserte su Id de operario", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String stringidRecinto = JOptionPane.showInputDialog (this, "Inserte el Id del recinto que quiere consultar ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechaIni = JOptionPane.showInputDialog (this, "La fecha de inicio? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);
				String fechafin = JOptionPane.showInputDialog (this, "La fecha de finalizacion? En formato dia/mes/año ", "Consultar usuarios", JOptionPane.QUESTION_MESSAGE);


				if(stringidOperario!= null && stringidRecinto!= null && fechaIni != null && fechafin != null  ){
					long idOperario = Long.valueOf(stringidOperario);
					long idRecinto = Long.valueOf(stringidRecinto);
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date date2=null;
					date2= dateFormat.parse(fechaIni);
					Timestamp fechaInicio = new Timestamp( date2.getTime());
					Date date3=null;
					date3= dateFormat.parse(fechafin);
					Timestamp fechaFin = new Timestamp( date3.getTime());
					int ordenRespAsc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre ascendentemente?");
					if(ordenRespAsc==0){

						List<VOPersona> personas = alohandes.darClientesConReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, idOperario, 1);
						mostrarPersonas(personas);
					}	
					if(ordenRespAsc==1){
						int ordenRespDesc = JOptionPane.showConfirmDialog(this, "Quiere ordenar el resultado por nombre descendentemente?");
						if(ordenRespDesc==0){
							List<VOPersona> personas = alohandes.darClientesConReservasEnRecintoEnRangoDeFechas(idRecinto, fechaInicio, fechaFin, admin, idOperario, 1);
							mostrarPersonas(personas);
						}

					}





				}


			}

		}
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}



	}

	private String mostrarPersonas(List<VOPersona> lista) 
	{
		String resp = "Las clientes son:\n";
		int i = 1;
		for (VOPersona tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}




	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}



	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}

	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}

	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
	}




	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazParranderosApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazParranderosApp interfaz = new InterfazParranderosApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
