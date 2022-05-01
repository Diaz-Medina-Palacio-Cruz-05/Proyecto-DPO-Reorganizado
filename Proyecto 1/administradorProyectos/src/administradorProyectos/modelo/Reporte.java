package administradorProyectos.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;

public class Reporte implements Serializable
{
	//Atributos
	private String elReporte;
	private String nombreParticipante;
	private String correoParticipante;
	
	public Reporte(String nombre, String correo) 
	{
		elReporte = null;
		nombreParticipante = nombre;
		correoParticipante = correo;
	}
	
	//Getter
	
	public String getReporte()
	{
		return elReporte;
	}
	
	//Metodos
	
	private String calcularTiempoTotalInvertido(ArrayList<Actividad> actividades) 
	{
		Long tiempoTInvertido = 0L;
		String paraImprimir = "El tiempo total invertido en el trabajo fue: ";
		for (Actividad a: actividades) 
		{
			if (a.getTerminado() == true)
			{
				tiempoTInvertido += a.getCronometro().getTiempoCronometrado();
			}
		}
		
		paraImprimir += deMilisegundosATiempoCompleto(tiempoTInvertido);
		return paraImprimir;
	}
	
	private String calcularTiempoPromedio(ArrayList<Actividad> actividades) 
	{
		String aImprimir = "";
		HashMap<String, HashMap<String, Long>> tiemposActividades = new HashMap<String, HashMap<String, Long>>();
		HashMap<String, Long> tiempoCantidad = new HashMap<String, Long>();
		
		for (Actividad a: actividades)
		{
			String llave = a.getTipo();
			Long tiempoCronometro  = a.getCronometro().getTiempoCronometrado();
			if (a.getTerminado() == true)
			{
				if (tiemposActividades.get(llave) != null) 
				{
					Long tiempoNuevo = tiemposActividades.get(llave).get("tiempo");
					Long cantidadNueva = tiemposActividades.get(llave).get("cantidad");
					tiempoNuevo += tiempoCronometro;
					cantidadNueva += 1L;
					tiemposActividades.get(llave).replace("tiempo", tiempoNuevo);
					tiemposActividades.get(llave).replace("cantidad", cantidadNueva);
					
				}
				else
				{
					tiempoCantidad.put("tiempo", tiempoCronometro);
					tiempoCantidad.put("cantidad", 1L);
					tiemposActividades.put(llave, tiempoCantidad);
				}
			}
		}
		
		for (String i: tiemposActividades.keySet())
		{
			Long sumatoriaTiempos = tiemposActividades.get(i).get("tiempo");
			Long sumatoriaTotal = tiemposActividades.get(i).get("cantidad");
			Long promedio = sumatoriaTiempos / sumatoriaTotal;
			String datos = "\nTiempo Promedio por la actividad  " + i + " es: "  + deMilisegundosATiempoCompleto(promedio);
			aImprimir += datos;
		}
		
		return aImprimir;
	}
	
	private String calcularTiempoPorDia(ArrayList<Actividad> actividades) 
	{
		Integer totalCanti = 0;
		Long totalTiempo = 0L;
		Long promedio = 0L;
		String aImprimir = "";
		for(Actividad a : actividades)
		{
			if (a.getTerminado() == true)
			{
				totalCanti += a.getCronometro().getCantPausado();
				totalTiempo += a.getCronometro().getTiempoTotalPausado();
			}
		}
		
		if (totalTiempo != 0)
		{
			promedio = totalTiempo/totalCanti;
		}
		
			
		Long difference_In_Seconds
	    = TimeUnit.MILLISECONDS
	         .toSeconds(promedio)
	         % 60;
	
		Long difference_In_Minutes
		        = TimeUnit.MILLISECONDS.toMinutes(promedio)
		          % 60;
		    
		Long difference_In_Hours
	       = TimeUnit
	             .MILLISECONDS
	             .toHours(promedio)
	         % 24;
			
		aImprimir = ("El tiempo promedio por dia es: "
			    + difference_In_Hours
			    + " horas, "
			    + difference_In_Minutes
			    + " minutos, "
			    + difference_In_Seconds
			    + " segundos");
		
		return aImprimir;
	}
		
	public void generarTextoReporte(ArrayList<Actividad> actividades) 
	{
		String textoReporte = "\n===========================================";
		textoReporte += "\n==== Reporte de Actividades de " + nombreParticipante + "====";
		textoReporte += "\nCorreo de Contacto: " +  correoParticipante;
		Integer x = 0;
		if (actividades.isEmpty() == false)
		{
			textoReporte += "\n===== Actividades del Usuario =====\n";
			for (Actividad v: actividades)
			{
				x += 1;
				textoReporte += (x + ". " + v.getTitulo() + " (" + v.getTipo() + ") ");
			}
			textoReporte += "\n===========================================";
			textoReporte += "\n" + calcularTiempoTotalInvertido(actividades);
			textoReporte += "\n===========================================";
			textoReporte += "\n" + calcularTiempoPromedio(actividades);
			textoReporte += "\n===========================================";
			textoReporte += "\n" + calcularTiempoPorDia(actividades);
			textoReporte += "\n===========================================";
		}
		else 
		{
			textoReporte += "\nEste participante no tiene actividades asignadas.";
			textoReporte += "\n===========================================";
		}
		elReporte = textoReporte;
	}
	
	private String deMilisegundosATiempoCompleto(Long milisegundos) 
	{
		String tiempoPapel = null;
		long difference_In_Seconds
    	= TimeUnit.MILLISECONDS
          .toSeconds(milisegundos)
          % 60;

	    long difference_In_Minutes
	        = TimeUnit
	              .MILLISECONDS
	              .toMinutes(milisegundos)
	          % 60;
	
	    long difference_In_Hours
	        = TimeUnit
	              .MILLISECONDS
	              .toHours(milisegundos)
	          % 24;
	
	    long difference_In_Days
	        = TimeUnit
	              .MILLISECONDS
	              .toDays(milisegundos)
	          % 365;
	
	    long difference_In_Years
	        = TimeUnit
	              .MILLISECONDS
	              .toDays(milisegundos)
	          / 365l;
	    tiempoPapel = (
	        difference_In_Years
	        + " años, "
	        + difference_In_Days
	        + " dias, "
	        + difference_In_Hours
	        + " horas, "
	        + difference_In_Minutes
	        + " minutos, "
	        + difference_In_Seconds
	        + " segundos");
	    
	    return tiempoPapel;
	}	
	
}
