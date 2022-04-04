package Janelas;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import DAO.Pessoa_DAO;
import cadastro.com.Pessoa;

public class Janela_Principal {
	
	JFrame frame;
	private final JScrollPane scrollPane = new JScrollPane();
	private JTable tblPESSOAS;
	Pessoa_DAO dao = new Pessoa_DAO();

	
	
	/**
	 * Launch the application.
	 */
	
	
	//--------------------------------------------
	
	 void CarregaTabela() {
		
		DefaultTableModel modelo = (DefaultTableModel) tblPESSOAS.getModel();
		modelo.getDataVector().clear();
		
		
		try {
		for(Pessoa p : dao.MostrarPessoas()) {
			
			modelo.addRow(new Object[] {p.getId(), p.getNome(), p.getIdade(), p.getNacionalidade()});
		
		}
		}catch(Exception e) {
		 throw e;
		}
		
	}
	
	//-------------------------------------------
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Janela_Principal window = new Janela_Principal();
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
	public Janela_Principal() {
		initialize();
		CarregaTabela();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 490, 316);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		scrollPane.setBounds(-1, 0, 475, 240);
		frame.getContentPane().add(scrollPane);
		
		tblPESSOAS = new JTable();
		tblPESSOAS.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Nome", "Idade", "Nacionalidade"
			}
		));
		tblPESSOAS.getColumnModel().getColumn(2).setPreferredWidth(69);
		tblPESSOAS.getColumnModel().getColumn(3).setPreferredWidth(92);
		scrollPane.setViewportView(tblPESSOAS);
		
		JButton btnINSERIR = new JButton("Inserir");
		btnINSERIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Janela_Dados janela2 = new Janela_Dados(null);
				janela2.frame.setVisible(true);
				
				frame.dispose();
		
			}
		});
		btnINSERIR.setBounds(-1, 239, 120, 41);
		frame.getContentPane().add(btnINSERIR);
		
		JButton btnEDITAR = new JButton("Editar");
		btnEDITAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DefaultTableModel modelo = (DefaultTableModel) tblPESSOAS.getModel();
				Pessoa p = new Pessoa();
				
				try {
					int id = (int) modelo.getValueAt(tblPESSOAS.getSelectedRow(), 0);
					String nome = (String) modelo.getValueAt(tblPESSOAS.getSelectedRow(), 1);
					int idade = (int) modelo.getValueAt(tblPESSOAS.getSelectedRow(), 2);
					String nacionalidade = (String) modelo.getValueAt(tblPESSOAS.getSelectedRow(), 3);
					
					p.setId(id);
					p.setIdade(idade);
					p.setNome(nome);
					p.setNacionalidade(nacionalidade);
					
					frame.dispose();
					Janela_Dados janela2 = new Janela_Dados(p);
					janela2.frame.setVisible(true);
				
				}catch (ArrayIndexOutOfBoundsException r) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela.", "Alerta", 2);
				}catch(Exception r) {
					JOptionPane.showMessageDialog(null, "ERRO!!" + r.getClass());
				}
			}
		});
		btnEDITAR.setBounds(118, 239, 119, 41);
		frame.getContentPane().add(btnEDITAR);
		
		JButton btnDELETAR = new JButton("Deletar");
		btnDELETAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					DefaultTableModel modelo = (DefaultTableModel) tblPESSOAS.getModel();
					
					int id = (int) modelo.getValueAt(tblPESSOAS.getSelectedRow(), 0);
					
					int opt = JOptionPane.showConfirmDialog(null, "Tem certeza de que quer apagar essa linha?", "Importante", 0, 3);
					if (opt == 0) {
						dao.DeletarPessoa_porID(id);
					}
					
					CarregaTabela();
					
				}catch (ArrayIndexOutOfBoundsException r) {
					JOptionPane.showMessageDialog(null, "Por favor, selecione uma linha da tabela.", "Alerta", 2);
				}catch(Exception r) {
					JOptionPane.showMessageDialog(null, "ERRO!!" + r.getClass());
				}
				
			}
		});
		btnDELETAR.setBounds(236, 239, 119, 41);
		frame.getContentPane().add(btnDELETAR);
		
		JButton btnSAIR = new JButton("Sair");
		btnSAIR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSAIR.setBounds(354, 239, 120, 41);
		frame.getContentPane().add(btnSAIR);
	}
}
