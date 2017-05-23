package br.univel.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

@Entity
@Table(name = "cad_aluno")
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = "version")
	private int version;

	@Column(name = "nome")
	private String nome;

	@Column(name = "telefone")
	private String telefone;

	public Long getId() {
		return this.id;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Aluno)) {
			return false;
		}
		Aluno other = (Aluno) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		result += ", telefone: " + telefone;
		return result;
	}
	
	public String toJson() {
		StringBuilder string = new StringBuilder();
		
		string.append("{\n");
		string.append("\tid:")
			.append((this.getId()))
			.append(",\n");
		string.append("\tnome:")
			.append(entreAspas(this.getNome()))
			.append(",\n");
		string.append("\ttelefone:")
			.append(entreAspas(this.getTelefone()))
			.append(",\n");
		string.append("}\n");
		
		return string.toString();
	}

	private String entreAspas(String string) {
		return "\"" + string + "\"";
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
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

}