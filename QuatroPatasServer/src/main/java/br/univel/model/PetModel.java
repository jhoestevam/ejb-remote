package br.univel.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class PetModel extends AbstractTableModel{

	private static final long serialVersionUID = 8416091988290506638L;
	
	private List<Pet> lista;
	
	public PetModel() {
		
		this((List<Pet>)null);
		
		for (int i = 0; i < 10; i++) {
			Pet p = new Pet();
			p.setId(i + 1);
			p.setNome("Pet " + (i + 1));
			p.setEspecie("Cachorro");
			this.lista.add(p);
		}
		
	}
	
	public PetModel(List<Pet> _lista){
		
		if (_lista == null) {
			this.lista = new ArrayList<>();
		}else {
			this.lista = _lista;
		}
		
	}
	
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return lista.size();
	}

	@Override
	public String getColumnName(int column) {
		
		switch (column) {
		case 0:
			return "Id";

		case 1:
			return "Nome";
			
		case 2:
			return "Especie";
		}
		
		return super.getColumnName(column);
	}
	
	@Override
	public Object getValueAt(int row, int column) {
		
		Pet p = this.lista.get(row);
		
		switch (column) {
		case 0:
			return p.getId();

		case 1:
			return p.getNome();
			
		case 2:
			return p.getEspecie();
			
		}
		
		throw new RuntimeException("Coluna " + column + " solicitada, não existe");
	}
	
	public void adicionar(Pet p){
		
		this.lista.add(p);
		
		super.fireTableDataChanged();
	}
	
	public Pet getPet(int idx){
		return lista.get(idx);
	}
	
	public void remover(Pet petSelecionado){
		
		this.lista.remove(petSelecionado);
		
		super.fireTableDataChanged();
	}
}
