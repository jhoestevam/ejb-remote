package br.univel.view;

import java.util.List;

import br.univel.dao.PetDao;
import br.univel.model.Pet;
import br.univel.model.PetModel;

public class Principal extends PrincipalBase{

	private static final long serialVersionUID = 115499095209949169L;

	private Pet petSelecionado;
	
	private PetModel modelo;
	
	public Principal() {
		super();
		limparCampos();
		configurarBotoes();
		configuraTabela();
	}

	private void configuraTabela() {
		
		
	}

	private void configurarBotoes() {
		// TODO Auto-generated method stub
		
	}

	private void limparCampos() {
		// TODO Auto-generated method stub
		
	}

}
