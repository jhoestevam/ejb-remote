package br.univel;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.univel.dao.PetDao;
import br.univel.model.Pet;

public class RemoteEJBCleint {

	public static void main(String[] args) throws Exception{
		invokeStatelessBean();
	}
	
	private static void invokeStatelessBean() throws NamingException{
		
		final PetDao dao = lookupRemoteStatelessCalculator();
		
		dao.getTodosAnimais();
		List<Pet> lista = dao.getTodosAnimais();
		
		for (Pet p: lista) {
			System.out.println(p.toString());
		}
	}
	
	private static PetDao lookupRemoteStatelessCalculator() throws NamingException{
		
		final Hashtable<String, String> jndiProperties = new Hashtable<>();
		jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		return (PetDao) context.lookup("ejb:/QuatroPatasServer/PetDaoImpl!");
	}
}
