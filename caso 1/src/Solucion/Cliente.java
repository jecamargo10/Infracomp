package Solucion;



/**
 * @version 1.0
 * @created 30-Aug-2015 17:25:21
 */
public class Cliente extends Thread  {

	private int id;
	public Mensaje m_Mensaje;
	public static Buffer m_Buffer;

	public Cliente(int i, Buffer b){
		id=i;
		m_Buffer=b;
	}

	public void run()
	{
		m_Mensaje=new Mensaje();
		try 
		{
			m_Buffer.enviarMensaje(m_Mensaje);
		} 
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void finalize() throws Throwable {

	}
}//end Cliente