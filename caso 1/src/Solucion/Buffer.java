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
	
	/**
	 * Lista con los mensajes almacenados
	 */
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
		synchronized(this)
		{
			int i = 0;
			//Si no hay espacio para guardar más mensajes. Se duerme en el buffer
			if(capacidad == 0)
			{
				System.out.println("Espero");
				wait();			
			}
			//Agrega un mensaje después de haber verificado la capacidad.
			mensajes.add(mensaje);
			capacidad--;
			System.out.println("Guarde un mensaje");
		}	
		synchronized(mensaje)
		{
			//Se queda dormido en el mensaje esperando la respuesta.
			mensaje.wait();
		}
	}
	
	public boolean retirarMensaje()
	{
		synchronized(this)
		{
			if(mensajes.size()==0)
			{
				return false;
			}
			else
			{
				Mensaje resp;
				resp = mensajes.remove(0);
				capacidad++;
				this.notify();
				synchronized(resp)
				{
					resp.notify();
					System.out.println("Retire 1 mensaje");
					System.out.println(mensajes.size());
				}
				return true;
			}
		}
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
			Servidor s=new Servidor(j,b);
			s.start();
		}
	}

	public void finalize() throws Throwable {

	}

	public void meRetiro(Cliente cliente) throws Throwable 
	{
		synchronized(cliente)
		{
			numClientes--;
			System.out.println("Chao");
			cliente.finalize();
		}
	}

	public boolean hayGente()
	{
		return numClientes!= 0;
	}

}