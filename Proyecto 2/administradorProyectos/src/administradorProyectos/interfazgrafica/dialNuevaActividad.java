package administradorProyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import administradorProyectos.modelo.Cronometro;
import administradorProyectos.modelo.Participante;

public class dialNuevaActividad extends JDialog implements ActionListener
{
	private VentanaPrincipal principal;
	
	private JLabel lblTitulo;
	private JLabel lblEncargado;
	private JLabel lblDecripcion;
	private JLabel lblTipo;
	
	private JTextField txtTitulo;
	private JTextField txtDecripcion;
	
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboParticipantes;
	
	private JButton btnCrear;
	
	private static final String ANADIR = "ANADIR";
	
	
	public dialNuevaActividad(VentanaPrincipal principal)
	{
		this.principal = principal;
		
		setSize(520, 510);
		setLocationRelativeTo(null);
		setTitle("Nuevo Participante");
		setLayout(new GridLayout(6, 2));
		
		lblTitulo = new JLabel("Título");
		lblEncargado = new JLabel("Participante Encargado");
		lblDecripcion = new JLabel("Descripción");
		lblTipo = new JLabel("Tipo");
		
		txtTitulo = new JTextField();
		txtDecripcion = new JTextField();
		
		comboParticipantes = new JComboBox<String>();
		comboTipo = new JComboBox<String>();
		
		btnCrear = new JButton("Crear Actividad");
		
		ArrayList<Participante> participantes = principal.proyecto.getParticipantes();
		ArrayList<String> tipos = principal.proyecto.getTipos(); 
		
		for(Participante p: participantes)
		{
			comboParticipantes.addItem(p.getNombre());
		}
			
		for(String t: tipos)
		{
				comboTipo.addItem(t);
		}
		
		add(new JLabel("Ingrese los datos de la nueva actividad"));
		add(new JLabel());
		add(lblTitulo);
		add(txtTitulo);
		add(lblEncargado);
		add(comboParticipantes);
		add(lblTipo);
		add(comboTipo);
		add(lblDecripcion);
		add(txtDecripcion);
		add(new JLabel());
		add(btnCrear);
		
		btnCrear.addActionListener(this);
		btnCrear.setActionCommand(ANADIR);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String comando = e.getActionCommand();
		if(comando.equals(ANADIR))
		{
			String titulo = txtTitulo.getText();
			String descripcion = txtDecripcion.getText();
			Integer tipo = comboTipo.getSelectedIndex();
			Integer encargado = comboParticipantes.getSelectedIndex();
			principal.nuevaActividad(titulo, descripcion, tipo, encargado);
			dispose();
		}
		
	}

}
