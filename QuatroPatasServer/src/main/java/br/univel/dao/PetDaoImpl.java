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
		Query query = entity.createQuery("from Pet");
		return query.getResultList();
	}

//	@Override
//	public void update(Pet pet) {
//		Query query = entity.createQuery("udpate Pet set nome = :nome");
//		
//		
//	}

	@Override
	public void delete(Pet pet) {
		entity.remove(pet);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Pet> select(Pet pet) {
		Query query = entity.createQuery("select name from Pet where name = name");
		List<Pet> list = query.getResultList();
		list.forEach(e -> {
			System.out.println(e);
		});
		
		return list;
	}

	@Override
	public Pet insert(Pet pet) {
//		Query query = entity.createQuery("insert into Pet(id, nome, especie) ");
		
		return null;
	}
	
}
