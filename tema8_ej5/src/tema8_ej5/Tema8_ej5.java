package tema8_ej5;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;


class ConnectionSingleton {
	private static Connection con;

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://127.0.0.1:3307/Tema8_ej5";
		String user = "alumno";
		String password = "alumno";
		if (con == null || con.isClosed()) {
			con = DriverManager.getConnection(url, user, password);
		}
		return con;
	}
}


public class Tema8_ej5 {

	private JFrame frmTienda;
	private JTable table;
	private JTable table_1;
	private JTextField codTextField;
	private JTextField precioTextField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tema8_ej5 window = new Tema8_ej5();
					window.frmTienda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tema8_ej5() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTienda = new JFrame();
		frmTienda.setTitle("Tienda");
		frmTienda.setBounds(100, 100, 1076, 692);
		frmTienda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTienda.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Mostrar");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Código");
				model.addColumn("Nombre");
				model.addColumn("Precio");
				model.addColumn("Unidades");
				try {
					Connection con=ConnectionSingleton.getConnection();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM producto");
					while (rs.next()) {
						Object[] row = new Object[4];
						row[0] = rs.getInt("cod");
						row[1] = rs.getString("nombre");
						row[2] = rs.getInt("precio");
						row[3] = rs.getInt("unidades");
						model.addRow(row);
					}
					
					table = new JTable(model);
					table.setBounds(62, 103, 372, 127);
					frmTienda.getContentPane().add(table);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					
					JScrollPane scrollPane = new JScrollPane (table);
					scrollPane.setBounds(62, 103, 372, 127);
					frmTienda.getContentPane().add(scrollPane);
				}catch (SQLException e1) {
					System.err.println(e1.getMessage());
					 e1.getErrorCode();
					 e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(175, 66, 117, 25);
		frmTienda.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nuevo producto:");
		lblNewLabel.setBounds(49, 338, 127, 15);
		frmTienda.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Código:");
		lblNewLabel_1.setBounds(93, 377, 70, 15);
		frmTienda.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Precio:");
		lblNewLabel_2.setBounds(93, 413, 70, 15);
		frmTienda.getContentPane().add(lblNewLabel_2);
		
		codTextField = new JTextField();
		codTextField.setBounds(175, 375, 55, 19);
		frmTienda.getContentPane().add(codTextField);
		codTextField.setColumns(10);
		
		precioTextField = new JTextField();
		precioTextField.setBounds(175, 411, 55, 19);
		frmTienda.getContentPane().add(precioTextField);
		precioTextField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Nombre:");
		lblNewLabel_3.setBounds(291, 377, 70, 15);
		frmTienda.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Unidades:");
		lblNewLabel_4.setBounds(291, 413, 82, 15);
		frmTienda.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(395, 411, 55, 19);
		frmTienda.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(395, 375, 98, 19);
		frmTienda.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Borrar producto:");
		lblNewLabel_5.setBounds(49, 516, 127, 15);
		frmTienda.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Elige producto:");
		lblNewLabel_6.setBounds(93, 556, 117, 15);
		frmTienda.getContentPane().add(lblNewLabel_6);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(228, 551, 64, 24);
		frmTienda.getContentPane().add(comboBox);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(333, 551, 117, 25);
		frmTienda.getContentPane().add(btnEliminar);
		
		JButton btnAadir = new JButton("Añadir");
		btnAadir.setBounds(228, 452, 117, 25);
		frmTienda.getContentPane().add(btnAadir);
		
		JLabel lblActualizarPrecio = new JLabel("Actualizar precio:");
		lblActualizarPrecio.setBounds(618, 133, 138, 15);
		frmTienda.getContentPane().add(lblActualizarPrecio);
		
		
		
		
		
		
	}
}
