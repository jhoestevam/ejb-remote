package br.univel.model;

public class Pet {

	private int id;
	
	private String nome;
	
	private String especie;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((especie == null) ? 0 : especie.hashCode());
		result = prime * result + id;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pet other = (Pet) obj;
		if (especie == null) {
			if (other.especie != null)
				return false;
		} else if (!especie.equals(other.especie))
			return false;
		if (id != other.id)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + "";
		
		if (nome != null && !nome.trim().isEmpty()) {
			result += "nome: " + nome;
		}
		
		result += ", especie: " + especie;
		
		return result;
	}
	
	public String toJson(){
		StringBuilder string = new StringBuilder();
		
		string.append("{\n");
		string.append("\tid:").append((this.getId())).append(",\n");
		string.append("\tnome:").append((entreAspas(this.getNome()))).append(",\n");
		string.append("\tespecie").append(entreAspas(this.getEspecie())).append(",\n");
		string.append("}\n");
		
		return string.toString();
	}
	
	private String entreAspas(String string){
		return "\"" + string + "\"";
	}

	public int getId() {
		return this.id;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(final String especie) {
		this.especie = especie;
	}
	
}
