package administradorProyectos.interfazgrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class panelIzquierdaVP extends JPanel implements ActionListener
{
	private JLabel lblDue�o;
	private JLabel lblUsuario;
	private JLabel lblLogo;
	private JTextField txtDue�o;
	private JPanel panelDue�o;
	private JPanel panelCargar;
	private JPanel panelImagen;
	private ImageIcon iconLogo;
	private ImageIcon iconUsuario;
	private JButton btnAbrir;
	private JButton btnCrear;
	private VentanaPrincipal principal;
	
	private static final String CREAR = "CREAR";
	private static final String ABRIR = "ABRIR";
	
	public panelIzquierdaVP(VentanaPrincipal principal) 
	{
		this.principal = principal;
		
		lblDue�o = new JLabel("Due�o:");
		lblUsuario = new JLabel("");
		lblLogo = new JLabel("");
		panelDue�o = new JPanel();
		panelCargar = new JPanel();
		panelImagen = new JPanel();
		iconLogo = new ImageIcon("./data/andes.png");
		iconUsuario = new ImageIcon("./data/usuario.png");
		txtDue�o = new JTextField();
		btnAbrir = new JButton("Abrir Proyecto");
		btnCrear = new JButton("Crear Proyecto");
		
		//panelDue�o
		panelDue�o.setLayout(new GridLayout(2,3));
		panelDue�o.setBorder( new TitledBorder( "Usuario" ) );;
		txtDue�o.setEditable(false);
		lblUsuario.setIcon(iconUsuario);
		panelDue�o.add(lblDue�o);
		panelDue�o.add(new JLabel());
		panelDue�o.add(new JLabel());
		panelDue�o.add(lblUsuario);
		panelDue�o.add(txtDue�o);
		panelDue�o.add(new JLabel());
		
		//panelCargar
		panelCargar.setLayout(new GridLayout(2, 1));
		panelCargar.setBorder(new TitledBorder("Cargar Proyecto"));
		btnCrear.addActionListener(this);
		btnCrear.setActionCommand(CREAR);
		btnAbrir.addActionListener(this);
		btnAbrir.setActionCommand(ABRIR);
		panelCargar.add(btnCrear);
		panelCargar.add(btnAbrir);
		
		//panelImagen
		panelImagen.setLayout(new GridLayout(1, 1));
		lblLogo.setIcon(iconLogo);
		panelImagen.add(lblLogo);
		
		//PanelIzquiersaVP
		setLayout(new GridLayout(5, 1));
		add(panelDue�o);
		add(panelCargar);
		add(new JLabel());
		add(panelImagen);
		add(new JLabel());
	}
	
	public void actualizar()
	{
		txtDue�o.setText(principal.proyecto.getParticipantes().get(0).getNombre());
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	 {
		String comando = e.getActionCommand();
		if (comando.equals(ABRIR)) 
		{
			dialogoCargarProyecto cargar = new dialogoCargarProyecto(principal, 1);
			cargar.setVisible(true);
		}
		else if (comando.equals(CREAR))
		{
			dialogoCargarProyecto cargar = new dialogoCargarProyecto(principal, 0);
			cargar.setVisible(true);
		}
		
	}

}
