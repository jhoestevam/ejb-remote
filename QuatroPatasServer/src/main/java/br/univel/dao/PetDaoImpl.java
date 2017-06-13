package br.univel.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.univel.model.Pet;

@Stateless
@Remote(PetDao.class)
public class PetDaoImpl implements PetDao{

	@PersistenceContext(name="persiste-unit")
	EntityManager entity;
	

	@Override
	public Pet salvar(Pet pet) {
		entity.persist(pet);
		
		return pet;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pet> getTodosAnimais(){
		Query query = entity.createQuery("select id, nome, especie from cad_pet");
		
		return query.getResultList();
	}
	
}
