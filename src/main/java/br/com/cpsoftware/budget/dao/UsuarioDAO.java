package br.com.cpsoftware.budget.dao;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

import br.com.cpsoftware.budget.model.Usuario;

public class UsuarioDAO {

	
	private DatastoreService datastore;
	private static final String USUARIO_KIND = "Usuario";
	
	public UsuarioDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	public Long create(Usuario usuario) {
		Entity usuarioEntity = new Entity(USUARIO_KIND);
		usuarioEntity.setProperty(Usuario.NOME, usuario.getNome());
		usuarioEntity.setProperty(Usuario.EMAIL, usuario.getEmail());
		usuarioEntity.setProperty(Usuario.LOGIN, usuario.getLogin());
		usuarioEntity.setProperty(Usuario.SENHA, usuario.getSenha());
		
		Key usuarioKey = datastore.put(usuarioEntity);
		
		return usuarioKey.getId();
	}

	public Usuario read(Long usuarioId) {
		try {
		    Entity usuarioEntity = datastore.get(KeyFactory.createKey(USUARIO_KIND, usuarioId));
		    return entityToUsuario(usuarioEntity);
		} catch (EntityNotFoundException e) {
		    return null;
		}
	}

	public void update(Usuario usuario) {
		Key key = KeyFactory.createKey(USUARIO_KIND, usuario.getId());  // From a book, create a Key
		Entity usuarioEntity = new Entity(key);         // Convert Book to an Entity
		
		usuarioEntity.setProperty(Usuario.NOME, usuario.getNome());
		usuarioEntity.setProperty(Usuario.EMAIL, usuario.getEmail());
		usuarioEntity.setProperty(Usuario.LOGIN, usuario.getLogin());
		usuarioEntity.setProperty(Usuario.SENHA, usuario.getSenha());
		usuarioEntity.setProperty(Usuario.ORCAMENTOS, usuario.getListaOrcamentos());
		
		datastore.put(usuarioEntity);
		
	}

	public void delete(Long usuarioId) {
		Key key = KeyFactory.createKey(USUARIO_KIND, usuarioId);        // Create the Key
		datastore.delete(key);                      // Delete the Entity
	}
	
	private Usuario entityToUsuario(Entity usuarioEntity) {
		return new Usuario((Long)usuarioEntity.getProperty(Usuario.ID),
				 (String)usuarioEntity.getProperty(Usuario.NOME),
				 (String)usuarioEntity.getProperty(Usuario.EMAIL),
				 (String)usuarioEntity.getProperty(Usuario.LOGIN),
				 (String)usuarioEntity.getProperty(Usuario.SENHA));
	}
	
	private List<Usuario> entitiesToUsuario(List<Entity> entities) {
		List<Usuario> resultUsuarios = new ArrayList<>();
		
		for (Entity entidade : entities) {
			resultUsuarios.add(entityToUsuario(entidade));
		}
		
		return resultUsuarios;
	}
	
	//TODO getUsuarios() ??
	
	public Usuario entrar(String login, String senha) {
		
		Query query = new Query("Usuario");
		Filter propertyFilter = new FilterPredicate("login", FilterOperator.EQUAL, login);
		query.setFilter(propertyFilter);
		PreparedQuery pq = datastore.prepare(query);
		
		Entity entityUsuario = pq.asSingleEntity();
		if (entityUsuario != null) {
			
			Usuario usuario = new Usuario((String) entityUsuario.getProperty("nome"),
											(String) entityUsuario.getProperty("email"),
											(String) entityUsuario.getProperty("login")); 
			
			//TODO ATENÇÃO AQUI!!
			
			/*if (UsuarioDAO.encryptPassword(senha.getBytes()).equals((String) entityUsuario.getProperty("senha"))) {
				return usuario;
			}*/
			
			if(senha.equals(entityUsuario.getProperty("senha"))) {
				System.out.println("Senhas iguais");
				return usuario;
			}else {
				System.out.println("Senhas diferentes");
			}
		} 
		return null;
	}
	
	public static String encryptPassword(byte[] password){
		String key = "thebestsecretkey";
		byte[] keyBytes = key.getBytes();
		
		try {
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyBytes, "AES")); 
			return new String(cipher.doFinal(password), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
