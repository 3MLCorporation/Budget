package br.com.cpsoftware.budget.dao;

import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

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
	
}
