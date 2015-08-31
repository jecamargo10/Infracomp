package Solucion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Buffer
{

	private int capacidad;
	
	private List<Mensaje> mensajes;
	
	private static int numClientes;
	
	private static int numServidores;

	public Buffer(){
		try 
		{
			Properties propiedades = new Properties();
			
			propiedades
			.load(new FileInputStream(
					"./docs/datos.properties"));

			capacidad = Integer.valueOf(propiedades.getProperty("capacidad"));
			numClientes = Integer.valueOf(propiedades.getProperty("clientes"));
			numServidores= Integer.valueOf(propiedades.getProperty("servidores"));
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("Error, El archivo no exite");
		}
		catch (IOException e) 
		{
			System.out.println("Error, No se puede leer el archivo");
		}
		mensajes = new ArrayList<Mensaje>();
	}
	
	/**
	 * Almacena si hay espacio para hacerlo.
	 * @param mensaje El mensaje que se desea almacenar.
	 * @throws InterruptedException
	 */
	public void enviarMensaje(Mensaje mensaje) throws InterruptedException
	{
		synchronized(mensajes)
		{
			//Si no hay espacio para guardar más mensajes. Se duerme en el buffer
			while(mensajes.size()==capacidad)
				this.wait();
		}
		synchronized (this)
		{
			//Agrega un mensaje después de haber verificado la capacidad.
			mensajes.add(mensaje);		
		}	
		synchronized(mensaje)
		{
			//Se queda dormido en el mensaje esperando la respuesta.
			mensaje.wait();
		}
	}
	
	public Mensaje retirarMensaje()
	{
		while(mensajes.size()==0){
			//TODO yield();
		}
		Mensaje resp;
		synchronized(this)
		{
			resp = mensajes.remove(0);		
		}
		synchronized(resp)
		{
			resp.notify();	
		}
		return resp;
	}
	
	public static void main(String[] arg)
	{
		Buffer b= new Buffer();
		for(int i=0;i<numClientes;i++)
		{
			Cliente c =new Cliente(i,b);
			c.start();
		}
		for(int j=0;j<numServidores;j++)
		{
			Servidor s=new Servidor();
			s.start();
		}
	}

	public void finalize() throws Throwable {

	}
}