package br.univel.model;

import java.io.Serializable;


public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private int version;

	private String nome;

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