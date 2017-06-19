package br.univel.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.ejb.EJB;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import br.univel.dao.PetDao;
import br.univel.model.Pet;
import br.univel.model.PetModel;

public class Principal extends PrincipalBase{

	private static final long serialVersionUID = 115499095209949169L;

	private Pet petSelecionado;
	
	@EJB
	private PetDao dao;
	
	private PetModel modelo;
	
	public Principal() {
		super();
		limparCampos();
		configurarBotoes();
		configuraTabela();
	}

	
	public void carregarLinha(int idx){
		Pet p = this.modelo.getPet(idx);
		this.petSelecionado = p;
		this.labelAlerta.setText(CARREGADO_PARA_ALTERACAO);
		
		this.txfId.setText(String.valueOf(p.getId()));
		this.txfNome.setText(String.valueOf(p.getNome()));
		this.txfEspecie.setText(String.valueOf(p.getEspecie()));
		
		super.btnExcluir.setEnabled(true);
	}
	
	private void configuraTabela() {
		List<Pet> lista = dao.getTodosAnimais();
		
		this.modelo = new PetModel(lista);
		
		super.table.setModel(modelo);
		
		super.table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					int idx = table.getSelectedRow();
					if (idx < 0) {
						System.out.println("Não há linha selecionada");
					}else{
						System.out.println("Linha" + idx);
						
					}
				}
				super.mouseClicked(e);
			}
		});
	}

	private void configurarBotoes() {
		
		super.btnNovo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click Novo !");
				novo();
			}
			
		});
		
		super.btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click Excluir !");
				excluir();
			}
			
		});
		
		super.btnSalvar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Click Salvar");
				salvar();
			}
		});
	}
	
	protected void salvar() {
		if (petSelecionado == null) {
			Pet p = new Pet();
			
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strEspecie = txfEspecie.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			p.setId(intId);
			p.setNome(strNome);
			p.setEspecie(strEspecie);
			
			this.modelo.adicionar(p);
			
			limparCampos();
		}else{
			
			String strId = txfId.getText().trim();
			String strNome = txfNome.getText().trim();
			String strEspecie = txfEspecie.getText().trim();
			
			int intId = Integer.parseInt(strId);
			
			this.petSelecionado.setId(intId);
			this.petSelecionado.setNome(strNome);
			this.petSelecionado.setEspecie(strEspecie);
			
			limparCampos();
			
			this.modelo.fireTableDataChanged();
		}
	}

	protected void excluir() {
		
		this.modelo.remover(petSelecionado);
		
		limparCampos();
	}
	
	protected void novo() {
		Pet p = new Pet();
		
		String strId = txfId.getText().trim();
		String strNome = txfNome.getText().trim();
		String strEspecie = txfEspecie.getText().trim();
		
		int intId = Integer.parseInt(strId);
		
		p.setId(intId);
		p.setNome(strNome);
		p.setEspecie(strEspecie);
		
		this.modelo.adicionar(p);
		
		this.petSelecionado = null;
		
		limparCampos();
	}

	private void limparCampos() {
		
		super.labelAlerta.setText("");
		super.txfId.setText("");
		super.txfNome.setText("");
		super.txfEspecie.setText("");
		
		super.btnExcluir.setEnabled(false);
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					
					Principal frame = new Principal();
					frame.setVisible(true);
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SwingUtilities.updateComponentTreeUI(frame);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		});
	}
}
