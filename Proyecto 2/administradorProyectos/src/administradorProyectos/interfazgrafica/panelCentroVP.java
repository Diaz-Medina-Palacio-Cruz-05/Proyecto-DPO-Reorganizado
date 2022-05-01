package administradorProyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import administradorProyectos.modelo.Proyecto;

public class panelCentroVP extends JPanel implements ActionListener
{
	private JLabel lblProyecto;
	private JLabel lblDescripcion;
	private JLabel lblFechaInicial;
	private JLabel lblFechaFinal;
	private JTextField txtProyecto;
	private JTextArea txtDescripcion;
	private JTextField txtFechaInicial;
	private JTextField txtFechaFinal;
	private JButton btnParticipante;
	private JButton btnActividad;
	private JPanel panelInfo;
	private VentanaPrincipal principal;
	private static final String PARTICPANTE = "PARTICIPANTE";
	private static final String ACTIVIDAD = "ACTIVIDAD";
	private DateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	
	public panelCentroVP(VentanaPrincipal principal)
	{
		this.principal = principal;
		
		setLayout(new GridLayout(3, 1));
		setBorder(new TitledBorder("Información del proyecto"));
		
		lblProyecto = new JLabel("Título: ");
		lblDescripcion = new JLabel("Descripción: ");
		lblFechaInicial = new JLabel("Fecha Inicial: ");
		lblFechaFinal = new JLabel("Fecha Final: ");
		
		txtProyecto = new JTextField();
		txtProyecto.setEditable(false);
		txtDescripcion = new JTextArea(6, 12);
		txtDescripcion.setEditable(false);
		txtFechaInicial = new JTextField();
		txtFechaInicial.setEditable(false);
		txtFechaFinal = new JTextField();
		txtFechaFinal.setEditable(false);
		
		btnActividad = new JButton("Crear Actividad");
		btnActividad.addActionListener(this);
		btnActividad.setActionCommand(ACTIVIDAD);
		
		btnParticipante = new JButton("Añadir Participante");
		btnParticipante.addActionListener(this);
		btnParticipante.setActionCommand(PARTICPANTE);
		
		panelInfo = new JPanel();
		
		
		
		panelInfo.setLayout(new GridLayout(4, 2));
		panelInfo.add(lblProyecto);
		panelInfo.add(txtProyecto);
		panelInfo.add(lblFechaInicial);
		panelInfo.add(txtFechaInicial);
		panelInfo.add(lblFechaFinal);
		panelInfo.add(txtFechaFinal);
		panelInfo.add(lblDescripcion);
		panelInfo.add(txtDescripcion);
		
		
		add(panelInfo);
		add(btnActividad);
		add(btnParticipante);
		
		
		
	}
	
	public void actualizar()
	{
		txtProyecto.setText(principal.proyecto.getTitulo());
		txtDescripcion.setText(principal.proyecto.getDescripcion());
		txtFechaInicial.setText(sdf.format(principal.proyecto.getFechaInicial()));
		txtFechaFinal.setText(sdf.format(principal.proyecto.getFechaFinal()));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if (comando.equals(PARTICPANTE))
		{
			if (principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				dialogoNuevoParticipante dialParticipante = new dialogoNuevoParticipante(principal);
				dialParticipante.setVisible(true);
			}
			
		}
		else if(comando.equals(ACTIVIDAD))
		{
			if(principal.proyecto == null)
			{
				dialWarning error = new dialWarning("NO SE HA CARGADO O ABIERTO NINGUN PROYECTO");
				error.setVisible(true);
			}
			else
			{
				dialNuevaActividad dialActividades = new dialNuevaActividad(principal);
				dialActividades.setVisible(true);
			}
			
		}
		
	}

}
