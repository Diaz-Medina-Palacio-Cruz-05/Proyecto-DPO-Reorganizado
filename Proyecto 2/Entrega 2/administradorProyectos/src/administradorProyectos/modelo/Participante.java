package administradorProyectos.modelo;

import java.io.Serializable;
import java.util.ArrayList;

public class Participante implements Serializable 
{
	//Atributos
	private String nombre;
	private String correo;
	private Boolean due�o;
	private Reporte miReporte;
	private ArrayList<Actividad> misActividades;
	
	//Constructor
	public Participante(String elNombre, String email, Boolean lider) 
	{
		nombre = elNombre;
		correo = email;
		due�o = lider;
		misActividades = new ArrayList<Actividad>();
		miReporte = new Reporte(nombre, correo);
	}
	
	//Getters
	
	public String getNombre()
	{
		return nombre; 
	}
	
	public String getCorreo()
	{
		return correo;
		
	}
	
	public Boolean isDue�o()
	{
		return due�o;
	}
	
	public Reporte getReporte()
	{
		return miReporte;
	}
	
	public ArrayList<Actividad> getActividades()
	{
		return misActividades;
	}
}
