package Solucion;



/**
 * @author javie_000
 * @version 1.0
 * @created 30-Aug-2015 17:25:23
 */
public class Servidor extends Thread 
{

	private int id;
	public Buffer buff;
	public Mensaje m_Mensaje;

	public Servidor(int i, Buffer bBuff)
	{
		buff = bBuff;
		id =i;
	}

	public void run()
	{
		// implementacion de la espera activa, constantemente estoy preguntandole al buffer si existe algun mensaje
		while (true)
		{
			if(!buff.hayMensaje())
			{
				
				
// Cada vez que no hay ningun mensaje, el servidor cede memoria
				yield();
			}
			
			

			
		}
		
	}
	
	public void finalize() throws Throwable {

	}
}//end Servidor