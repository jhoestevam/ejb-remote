package br.univel.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.univel.model.Aluno;

@Stateless
@Remote(AlunoDao.class)
public class AlunoDaoImpl implements AlunoDao {
	
	@PersistenceContext(name="persistence-unit")
	EntityManager entity;
	
	/* (non-Javadoc)
	 * @see org.aluno.server.dao.AlunoDaoIF#salvar(org.aluno.server.model.Aluno)
	 */
	@Override
	public Aluno salvar(Aluno aluno){
		entity.persist(aluno);
		return aluno;
	}

	
	/* (non-Javadoc)
	 * @see org.aluno.server.dao.AlunoDaoIF#getTodosAlunos()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<Aluno> getTodosAlunos() {
	
		Query query = entity.createQuery("from Aluno");
		
		return query.getResultList();
	}
	
}
