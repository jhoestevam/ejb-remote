package br.univel.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import br.univel.model.Aluno;
import br.univel.rest.dto.AlunoDTO;

/**
 * 
 */
@Stateless
@Path("/alunos")
public class AlunoEndpoint {
	@PersistenceContext(unitName = "persistence-unit")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(AlunoDTO dto) {
		Aluno entity = dto.fromDTO(null, em);
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(AlunoEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		Aluno entity = em.find(Aluno.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<Aluno> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT a FROM Aluno a WHERE a.id = :entityId ORDER BY a.id",
						Aluno.class);
		findByIdQuery.setParameter("entityId", id);
		Aluno entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		AlunoDTO dto = new AlunoDTO(entity);
		return Response.ok(dto).build();
	}

	@GET
	@Produces("application/json")
	public List<AlunoDTO> listAll(@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<Aluno> findAllQuery = em.createQuery(
				"SELECT DISTINCT a FROM Aluno a ORDER BY a.id", Aluno.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<Aluno> searchResults = findAllQuery.getResultList();
		final List<AlunoDTO> results = new ArrayList<AlunoDTO>();
		for (Aluno searchResult : searchResults) {
			AlunoDTO dto = new AlunoDTO(searchResult);
			results.add(dto);
		}
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, AlunoDTO dto) {
		if (dto == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(dto.getId())) {
			return Response.status(Status.CONFLICT).entity(dto).build();
		}
		Aluno entity = em.find(Aluno.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		entity = dto.fromDTO(entity, em);
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Status.CONFLICT).entity(e.getEntity())
					.build();
		}
		return Response.noContent().build();
	}
}
