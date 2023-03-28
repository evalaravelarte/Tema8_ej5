package tema8_ej5;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import javax.swing.JOptionPane;
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
//	private JTable table_1;
	private JTextField codTextField;
	private JTextField precioTextField;
	private JTextField unidadesTextField;
	private JTextField nombreTextField;
	private JTextField nuevoPrecioTextField;
	private JTextField udStockTextField;
	private JTextField textField_2;

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
		frmTienda.setBounds(100, 100, 1196, 692);
		frmTienda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTienda.getContentPane().setLayout(null);
		
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
				row[2] = rs.getDouble("precio");
				row[3] = rs.getInt("unidades");
				model.addRow(row);
			}
			
			table = new JTable(model);
			table.setBounds(62, 103, 372, 150);
			frmTienda.getContentPane().add(table);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		
			
			JScrollPane scrollPane = new JScrollPane (table);
			scrollPane.setBounds(62, 103, 372, 150);
			frmTienda.getContentPane().add(scrollPane);
		}catch (SQLException e1) {
			System.err.println(e1.getMessage());
			 e1.getErrorCode();
			 e1.printStackTrace();
		}
		
		JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
		
				try {
					Connection con=ConnectionSingleton.getConnection();
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM producto");
					while (rs.next()) {
						Object[] row = new Object[4];
						row[0] = rs.getInt("cod");
						row[1] = rs.getString("nombre");
						row[2] = rs.getDouble("precio");
						row[3] = rs.getInt("unidades");
						model.addRow(row);
					}
					
					rs.close();
					stmt.close();
					
				}catch (SQLException e1) {
					System.err.println(e1.getMessage());
					 e1.getErrorCode();
					 e1.printStackTrace();
				}
			}
		});
		btnMostrar.setBounds(175, 66, 117, 25);
		frmTienda.getContentPane().add(btnMostrar);
		
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
		
		unidadesTextField = new JTextField();
		unidadesTextField.setBounds(395, 411, 55, 19);
		frmTienda.getContentPane().add(unidadesTextField);
		unidadesTextField.setColumns(10);
		
		nombreTextField = new JTextField();
		nombreTextField.setBounds(395, 375, 98, 19);
		frmTienda.getContentPane().add(nombreTextField);
		nombreTextField.setColumns(10);
		
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
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection con=ConnectionSingleton.getConnection();
					PreparedStatement delete_pstmt = con.prepareStatement("DELETE FROM producto WHERE id=?");
					int cod=Integer.parseInt(codTextField.getText());
					delete_pstmt.setInt(1, cod);
					int rowDeleted=delete_pstmt.executeUpdate();
					delete_pstmt.close();
					//btnMostrar.doClick();
					JOptionPane.showMessageDialog(null, "Producto añadido");
					}catch (SQLException e1) {
					System.err.println(e1.getMessage());
					 e1.getErrorCode();
					 e1.printStackTrace();
				}
			}
		});
		btnEliminar.setBounds(333, 551, 117, 25);
		frmTienda.getContentPane().add(btnEliminar);
		
		JButton btnAñadir = new JButton("Añadir");
		btnAñadir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection con=ConnectionSingleton.getConnection();
					PreparedStatement add_pstmt = con.prepareStatement("INSERT INTO producto (cod, nombre, precio, unidades) VALUES (?,?,?,?)");
					int cod=Integer.parseInt(codTextField.getText());
					add_pstmt.setInt(1, cod);
					String nombre=nombreTextField.getText();
					add_pstmt.setString(2, nombre);
					double precio=Double.parseDouble(precioTextField.getText());
					add_pstmt.setDouble(3, precio);
					int uds=Integer.parseInt(unidadesTextField.getText());
					add_pstmt.setInt(4, uds);
					int rowInserted = add_pstmt.executeUpdate();
					add_pstmt.close();
				//	btnMostrar.doClick();
					JOptionPane.showMessageDialog(null, "Producto añadido");
					}catch (SQLException e1) {
					System.err.println(e1.getMessage());
					 e1.getErrorCode();
					 e1.printStackTrace();
				}
				
				codTextField.setText("");
				nombreTextField.setText("");
				precioTextField.setText("");
				unidadesTextField.setText("");
			}
		});
		btnAñadir.setBounds(228, 452, 117, 25);
		frmTienda.getContentPane().add(btnAñadir);
		
		JLabel lblActualizarPrecio = new JLabel("Actualizar precio:");
		lblActualizarPrecio.setBounds(618, 133, 138, 15);
		frmTienda.getContentPane().add(lblActualizarPrecio);
		
		JLabel lblEligeProducto = new JLabel("Elige producto:");
		lblEligeProducto.setBounds(646, 176, 107, 15);
		frmTienda.getContentPane().add(lblEligeProducto);
		
		JComboBox actualizarPrecioComboBox = new JComboBox();
		actualizarPrecioComboBox.setBounds(771, 171, 64, 24);
		frmTienda.getContentPane().add(actualizarPrecioComboBox);
		
		JLabel lblNuevoPrecio = new JLabel("Nuevo precio:");
		lblNuevoPrecio.setBounds(646, 222, 110, 15);
		frmTienda.getContentPane().add(lblNuevoPrecio);
		
		nuevoPrecioTextField = new JTextField();
		nuevoPrecioTextField.setBounds(771, 220, 70, 19);
		frmTienda.getContentPane().add(nuevoPrecioTextField);
		nuevoPrecioTextField.setColumns(10);
		
		JLabel lblIncrementarStock = new JLabel("Incrementar stock:");
		lblIncrementarStock.setBounds(618, 338, 135, 15);
		frmTienda.getContentPane().add(lblIncrementarStock);
		
		JLabel lblEligeProducto_1 = new JLabel("Elige producto:");
		lblEligeProducto_1.setBounds(646, 375, 110, 15);
		frmTienda.getContentPane().add(lblEligeProducto_1);
		
		JComboBox incrementarStrockComboBox = new JComboBox();
		incrementarStrockComboBox.setBounds(832, 372, 64, 24);
		frmTienda.getContentPane().add(incrementarStrockComboBox);
		
		JLabel lblUnidadesAdquiridas = new JLabel("Unidades adquiridas:");
		lblUnidadesAdquiridas.setBounds(646, 413, 152, 15);
		frmTienda.getContentPane().add(lblUnidadesAdquiridas);
		
		udStockTextField = new JTextField();
		udStockTextField.setBounds(832, 411, 76, 19);
		frmTienda.getContentPane().add(udStockTextField);
		udStockTextField.setColumns(10);
		
		JLabel lblVenta = new JLabel("Venta:");
		lblVenta.setBounds(618, 480, 70, 15);
		frmTienda.getContentPane().add(lblVenta);
		
		JLabel lblProductoAVender = new JLabel("Producto a vender:");
		lblProductoAVender.setBounds(646, 516, 138, 15);
		frmTienda.getContentPane().add(lblProductoAVender);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(809, 511, 70, 24);
		frmTienda.getContentPane().add(comboBox_1);
		
		JLabel lblUnidadesAVender = new JLabel("Unidades a vender:");
		lblUnidadesAVender.setBounds(646, 556, 138, 15);
		frmTienda.getContentPane().add(lblUnidadesAVender);
		
		textField_2 = new JTextField();
		textField_2.setBounds(809, 552, 82, 19);
		frmTienda.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnActualizarPrecio = new JButton("Actualizar precio");
		btnActualizarPrecio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					Connection con=ConnectionSingleton.getConnection();
					
					Statement stmtprod = con.createStatement();
					ResultSet rsprod = stmtprod.executeQuery("SELECT cod FROM producto");
					while (rsprod.next()) {
						int cod = rsprod.getInt("cod");
						actualizarPrecioComboBox.addItem(String.valueOf(cod));
					}
					rsprod.close();
					stmtprod.close();
					
					PreparedStatement updatePrecio_pstmt = con.prepareStatement("UPDATE precio SET precio=? WHERE cod=?");
					
					double precio=Double.parseDouble(nuevoPrecioTextField.getText());
					updatePrecio_pstmt.setDouble(1, precio);
					
					int cod=Integer.parseInt(actualizarPrecioComboBox.getSelectedItem().toString());
					updatePrecio_pstmt.setInt(2, cod);
					
					int rowsUpdated = updatePrecio_pstmt.executeUpdate();
					
					//btnMostrar.doClick();
					
					JOptionPane.showMessageDialog(null, "Precio actualizado");
					}catch (SQLException e1) {
					System.err.println(e1.getMessage());
					 e1.getErrorCode();
					 e1.printStackTrace();
				}
			}
		});
		btnActualizarPrecio.setBounds(910, 176, 159, 25);
		frmTienda.getContentPane().add(btnActualizarPrecio);
		
		JButton btnActualizarStock = new JButton("Actualizar stock");
		btnActualizarStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					Connection con=ConnectionSingleton.getConnection();
					
					Statement stmtprod = con.createStatement();
					ResultSet rsprod = stmtprod.executeQuery("SELECT cod FROM producto");
					while (rsprod.next()) {
						int cod = rsprod.getInt("cod");
						actualizarPrecioComboBox.addItem(String.valueOf(cod));
					}
					rsprod.close();
					stmtprod.close();
					PreparedStatement updateStock_pstmt = con.prepareStatement("UPDATE unidades SET unidades=? WHERE cod=?");
					
					int unidades=Integer.parseInt(udStockTextField.getText());
					updateStock_pstmt.setInt(1, unidades);
					
					int cod=Integer.parseInt(incrementarStrockComboBox.getSelectedItem().toString());
					updateStock_pstmt.setInt(2, cod);
					
					int rowsUpdated = updateStock_pstmt.executeUpdate();
					
					//btnMostrar.doClick();
					JOptionPane.showMessageDialog(null, "Stock actualizado");
					}catch (SQLException e1) {
					System.err.println(e1.getMessage());
					 e1.getErrorCode();
					 e1.printStackTrace();
				}
			}
		});
		btnActualizarStock.setBounds(990, 386, 152, 25);
		frmTienda.getContentPane().add(btnActualizarStock);
		
		JButton btnVender = new JButton("Vender");
		btnVender.setBounds(952, 525, 117, 25);
		frmTienda.getContentPane().add(btnVender);
		
		
		
		
		
		
	}
}
