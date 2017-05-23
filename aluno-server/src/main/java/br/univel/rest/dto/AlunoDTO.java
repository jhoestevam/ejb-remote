package br.univel.rest.dto;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.xml.bind.annotation.XmlRootElement;

import br.univel.model.Aluno;

@XmlRootElement
public class AlunoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private int version;
	private String nome;
	private String telefone;

	public AlunoDTO() {
	}

	public AlunoDTO(final Aluno entity) {
		if (entity != null) {
			this.id = entity.getId();
			this.version = entity.getVersion();
			this.nome = entity.getNome();
			this.telefone = entity.getTelefone();
		}
	}

	public Aluno fromDTO(Aluno entity, EntityManager em) {
		if (entity == null) {
			entity = new Aluno();
		}
		entity.setVersion(this.version);
		entity.setNome(this.nome);
		entity.setTelefone(this.telefone);
		entity = em.merge(entity);
		return entity;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(final String telefone) {
		this.telefone = telefone;
	}
}