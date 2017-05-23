package br.com.dog.model.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.dog.model.entity.Cachorro;
import br.com.dog.model.entity.Match;
import br.com.dog.model.entity.Usuario;

public interface MatchDAO {

	// Create
	public void create(Cachorro cachorro) throws SQLException;

	// ReadAll
	public List<Cachorro> readAll() throws SQLException;

	// DeleteAll
	public void deleteAll() throws SQLException;

	// Update
	public void update(Cachorro cachorro) throws SQLException;

	// Read especifico
	public Usuario read(Cachorro cachorro) throws SQLException;

	// Delete per id
	public void delete(Cachorro cachorro) throws SQLException;
}