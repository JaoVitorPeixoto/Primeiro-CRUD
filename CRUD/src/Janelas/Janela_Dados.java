package Janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JTextField;

import DAO.Pessoa_DAO;
import cadastro.com.Pessoa;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Janela_Dados {

	
	private JTextField txtNOME;
	private JTextField txtIDADE;
	private JTextField txtNACIONALIDADE;
	JFrame frame;
	private Pessoa pessoa;
	Pessoa_DAO dao = new Pessoa_DAO();
	Janela_Principal janela1 = new Janela_Principal();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Dados window = new Janela_Dados(null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	

	
	public Janela_Dados(Pessoa pessoa) {
		initialize();
		
		this.pessoa = pessoa;
		if (pessoa != null) {
			txtNOME.setText(pessoa.getNome());
			txtIDADE.setText(Integer.toString(pessoa.getIdade()));
			txtNACIONALIDADE.setText(pessoa.getNacionalidade());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBackground(Color.BLACK);
		frame.setBounds(100, 100, 300, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtNOME = new JTextField();
		txtNOME.setBounds(56, 25, 160, 20);
		frame.getContentPane().add(txtNOME);
		txtNOME.setColumns(10);
		
		JLabel lblNOME = new JLabel("Nome");
		lblNOME.setBounds(56, 11, 46, 14);
		frame.getContentPane().add(lblNOME);
		
		JLabel lblIDADE = new JLabel("Idade");
		lblIDADE.setBounds(56, 56, 46, 14);
		frame.getContentPane().add(lblIDADE);
		
		txtIDADE = new JTextField();
		txtIDADE.setBounds(56, 72, 160, 20);
		frame.getContentPane().add(txtIDADE);
		txtIDADE.setColumns(10);
		
		JLabel lblNACIONALIDADE = new JLabel("Nacionalidade");
		lblNACIONALIDADE.setBounds(56, 103, 79, 14);
		frame.getContentPane().add(lblNACIONALIDADE);
		
		txtNACIONALIDADE = new JTextField();
		txtNACIONALIDADE.setBounds(56, 118, 160, 20);
		frame.getContentPane().add(txtNACIONALIDADE);
		txtNACIONALIDADE.setColumns(10);
		
		JButton btnSALVAR = new JButton("Salvar");
		btnSALVAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		Pessoa p = new Pessoa();
		int c = 0;
			
		if (pessoa == null)	{
			try {
				p.setNome(txtNOME.getText());
				p.setIdade(Integer.parseInt(txtIDADE.getText()));
				p.setNacionalidade(txtNACIONALIDADE.getText());
				
				dao.InserirPessoa(p);
				JOptionPane.showMessageDialog(null, "Nova pessoa inserida com sucesso!!", "Sistema", 1);
				
				
			}catch(NumberFormatException r) {
				c += 1;
				JOptionPane.showMessageDialog(null, "Informe a idade corretamente, um número inteiro!!", "Alerta", 2);
				
			}catch(Exception r) {
				c += 1;
				r.printStackTrace();
			}finally {
				
		if(c == 0) {
			frame.dispose();
			janela1.CarregaTabela();
			janela1.frame.setVisible(true);
			}
		}	
			
		}else {
			try {
				p.setNome(txtNOME.getText());
				p.setIdade(Integer.parseInt(txtIDADE.getText()));
				p.setNacionalidade(txtNACIONALIDADE.getText());
				p.setId(pessoa.getId());
				
				dao.AtualizarPessoa(p);
				JOptionPane.showMessageDialog(null, "Pessoa editada com sucesso!!", "Sistema", 1);
				
				
				
			}catch(NumberFormatException r) {
				JOptionPane.showMessageDialog(null, "Informe a idade corretamente, um número inteiro!!", "Alerta", 2);
				c += 1;
			}catch(Exception r) {
				r.printStackTrace();
				c += 1;
			}finally {
				
		if(c == 0) {
		frame.dispose();
		janela1.CarregaTabela();
		janela1.frame.setVisible(true);
		
		}
			}
			
		}
		
	
	}
		});
		btnSALVAR.setBounds(10, 170, 100, 41);
		frame.getContentPane().add(btnSALVAR);
		
		JButton btnCANCELAR = new JButton("Cancelar");
		btnCANCELAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				janela1.CarregaTabela();
				janela1.frame.setVisible(true);
			}
		});
		btnCANCELAR.setBounds(174, 170, 100, 41);
		frame.getContentPane().add(btnCANCELAR);
	}

}
