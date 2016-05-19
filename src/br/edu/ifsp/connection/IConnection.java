package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface IConnection {

	public Connection connect() throws Exception;
	
	public boolean disconnect() throws Exception;
	
	public ResultSet executeQuery(PreparedStatement preparedStatement) throws Exception;	

	public boolean executeUpdate(PreparedStatement preparedStatement) throws Exception;	
	
}
