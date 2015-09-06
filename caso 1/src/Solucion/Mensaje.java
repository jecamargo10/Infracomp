package Solucion;



/**
 * @version 1.0
 * @created 30-Aug-2015 17:25:22
 */
public class Mensaje {

	private String respuesta;
	private String pregunta;
	private int estado;

	public Mensaje(String pPregunta)
	{
		setRespuesta(" ");
		setPregunta(pPregunta);
		setEstado(0);
		
				
	}

	public void finalize() throws Throwable 
	{

	}

	public String getPregunta() {
		return pregunta;
	}

	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}//end Mensaje