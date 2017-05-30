package br.univel.dao;

import java.util.List;

import br.univel.model.Pet;

public interface PetDao {
	
	Pet salvar(Pet pet);
	
	List<Pet> getTodosAnimais();
}
