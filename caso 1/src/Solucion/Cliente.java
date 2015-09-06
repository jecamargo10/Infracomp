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
		m_Mensaje=new Mensaje("Hola");
		try 
		{
			m_Buffer.enviarMensaje(m_Mensaje);
			System.out.println("Desperte");
			m_Buffer.meRetiro(this);
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void finalize() throws Throwable {

	}
}//end Cliente