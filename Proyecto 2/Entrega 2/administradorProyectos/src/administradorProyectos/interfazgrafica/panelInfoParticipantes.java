package administradorProyectos.interfazgrafica;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.Actividad;
import administradorProyectos.modelo.Participante;

public class panelInfoParticipantes extends JPanel
{
	private JPanel infoParticpantePrincipal;
	private JPanel infoParticpanteSecundario;
	private JPanel reporteParticipante;
	
	private JLabel lblNombre;
	private JLabel lblCorreo;
	private JLabel lblDue�o;
	
	private JTextField txtNombre;
	private JTextField txtCorreo;
	
	private JRadioButton radioDue�o;
	
	private JTextArea txtReporte;
	
	private DefaultListModel<String> model;
	private JList<String> lista;
	
	private JScrollPane listaActividades;
	
	
	public panelInfoParticipantes()
	{
		setLayout(new GridLayout(1,2));
		setBorder(new TitledBorder("Informaci�n del Participante"));
		
		infoParticpantePrincipal = new JPanel();
		infoParticpanteSecundario =new JPanel();
		reporteParticipante = new JPanel();
		
		lblNombre = new JLabel("Nombre");
		lblCorreo = new JLabel("Correo");
		lblDue�o = new JLabel("Due�o");
		
		txtNombre = new JTextField();
		txtCorreo = new JTextField();
		txtReporte = new JTextArea(5, 20);
		
		radioDue�o = new JRadioButton();
		
		model = new DefaultListModel<String>();
		lista = new JList<String>(model);
		
		listaActividades = new JScrollPane();
		
		txtNombre.setEditable(false);
		txtCorreo.setEditable(false);
		txtReporte.setEditable(false);
		radioDue�o.setEnabled(false);
		
		listaActividades.setViewportView(lista);
		
		infoParticpanteSecundario.setLayout(new GridLayout(4,2));
		infoParticpanteSecundario.add(lblNombre);
		infoParticpanteSecundario.add(txtNombre);
		infoParticpanteSecundario.add(lblCorreo);
		infoParticpanteSecundario.add(txtCorreo);
		infoParticpanteSecundario.add(lblDue�o);
		infoParticpanteSecundario.add(radioDue�o);
		infoParticpanteSecundario.add(new JLabel("Actividades"));
		
		infoParticpantePrincipal.setLayout(new GridLayout(2,1));
		infoParticpantePrincipal.add(infoParticpanteSecundario);
		infoParticpantePrincipal.add(listaActividades);
		
		reporteParticipante.setLayout(new BorderLayout());
		reporteParticipante.add(new JLabel("Reporte"), BorderLayout.NORTH);
		reporteParticipante.add(txtReporte, BorderLayout.CENTER);
		
		add(infoParticpantePrincipal);
		add(reporteParticipante);
		
		
	}
	
	public void setDueno(Participante dueno)
	{
		txtNombre.setText(dueno.getNombre());
		txtCorreo.setText(dueno.getCorreo());
		dueno.getReporte().generarTextoReporte(dueno.getActividades());
		txtReporte.setText(dueno.getReporte().getReporte());
		radioDue�o.setSelected(dueno.isDue�o());
		model.removeAllElements();
		for (Actividad actividad: dueno.getActividades())
		{
			model.addElement(actividad.getTitulo());
		}
	}
	
	public void actualizar(Participante participante)
	{
		txtNombre.setText(participante.getNombre());
		txtCorreo.setText(participante.getCorreo());
		participante.getReporte().generarTextoReporte(participante.getActividades());
		txtReporte.setText(participante.getReporte().getReporte());
		radioDue�o.setSelected(participante.isDue�o());
		model.removeAllElements();
		for (Actividad actividad: participante.getActividades())
		{
			model.addElement(actividad.getTitulo());
		}
	}
	
	
}
