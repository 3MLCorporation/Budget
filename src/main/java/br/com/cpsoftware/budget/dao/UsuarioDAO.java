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
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

import br.com.cpsoftware.budget.model.Item;
import br.com.cpsoftware.budget.model.Orcamento;
import br.com.cpsoftware.budget.model.Rubrica;
import br.com.cpsoftware.budget.model.Usuario;

public class UsuarioDAO {

	
	private DatastoreService datastore;
	private static final String USUARIO_KIND = "Usuario";
	
	public UsuarioDAO() {
		datastore = DatastoreServiceFactory.getDatastoreService();
	}

	public Long create(Usuario usuario) {
		Entity usuarioEntity = new Entity(USUARIO_KIND);
		usuarioEntity.setProperty(Usuario.PERFIL, usuario.getPerfil());
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
		Key key = KeyFactory.createKey(USUARIO_KIND, usuario.getId());
		Entity usuarioEntity = new Entity(key);
		
		usuarioEntity.setProperty(Usuario.PERFIL, usuario.getPerfil());
		usuarioEntity.setProperty(Usuario.NOME, usuario.getNome());
		usuarioEntity.setProperty(Usuario.EMAIL, usuario.getEmail());
		usuarioEntity.setProperty(Usuario.LOGIN, usuario.getLogin());
		usuarioEntity.setProperty(Usuario.SENHA, usuario.getSenha());
		
		datastore.put(usuarioEntity);
		
	}

	public void delete(Long usuarioId) {
		Key key = KeyFactory.createKey(USUARIO_KIND, usuarioId);
		datastore.delete(key);
	}
	
	private Usuario entityToUsuario(Entity usuarioEntity) {
		return new Usuario(usuarioEntity.getKey().getId(),
						 ((Long) usuarioEntity.getProperty(Usuario.PERFIL)).intValue(),
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
	
	public List<Usuario> getUsuarios(){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(USUARIO_KIND).addSort(Usuario.NOME, SortDirection.ASCENDING);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 List<Entity> usuarioEntities = preparedQuery.asList(FetchOptions.Builder.withDefaults());
		 return entitiesToUsuario(usuarioEntities);
		
	}
	
	public Usuario getUsuarioByEmail(String email){
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query = new Query(USUARIO_KIND).addSort(Usuario.NOME, SortDirection.ASCENDING);
		
		Filter emailFilter = new FilterPredicate(Usuario.EMAIL, FilterOperator.EQUAL, email);
		query.setFilter(emailFilter);
		
		PreparedQuery preparedQuery = datastore.prepare(query);
		
		 Entity usuarioEntity = preparedQuery.asSingleEntity();
		 if(usuarioEntity != null) {
			 return entityToUsuario(usuarioEntity);
		 }else {
			 return null;
		 }
		 
		
	}
	
	public Usuario entrar(String login, String senha) {
		
		Query query = new Query("Usuario");
		Filter propertyFilter = new FilterPredicate("login", FilterOperator.EQUAL, login);
		query.setFilter(propertyFilter);
		PreparedQuery pq = datastore.prepare(query);
		
		Entity entityUsuario = pq.asSingleEntity();
		if (entityUsuario != null) {
			
			//System.out.println("entity ID: " + entityUsuario.getKey().getId());
			
			Usuario usuario = new Usuario(	entityUsuario.getKey().getId(),
											((Long) entityUsuario.getProperty(Usuario.PERFIL)).intValue(),
											(String) entityUsuario.getProperty(Usuario.NOME),
											(String) entityUsuario.getProperty(Usuario.EMAIL),
											(String) entityUsuario.getProperty(Usuario.LOGIN),
											(String) entityUsuario.getProperty(Usuario.SENHA)); 
			
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
