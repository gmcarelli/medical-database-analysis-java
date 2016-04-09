package br.edu.ifsp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgreConnection implements IConnection {   
    
    private Connection connection = null;
	private Statement statement;
      
    /**
     * Método que  cria uma conexão com o banco de dados
     * @return uma conexão com o banco de dados
     * @throws java.sql.SQLException
     */
    public Connection connect() throws SQLException {
        
    	try {
    		
            Class.forName("org.postgresql.Driver");
            
            this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "1qaz2wsx");
            
            this.statement = this.connection.createStatement();
            
        } catch (ClassNotFoundException ex) {
        	
            throw new SQLException(ex.getMessage());
            
        }
    	
    	return this.connection;
    }

    /**
     * Método que encerra uma conexão com o banco de dados
     * @throws java.sql.SQLException
     */
    public boolean disconnect() throws SQLException {
    	
    	boolean result = true;
    	
    	try {
    		
    		this.connection.close();
    		
    	} catch(Exception e) {
    
    		result = false;
    	}
    	
    	return result;
    }

	@Override
	public ResultSet executeQuery(String query) throws SQLException {
		
		if(!this.connection.isClosed() && this.connection != null) {
			return this.statement.executeQuery(query);
		}
		
		return null;
		
	}

	@Override
	public void startTransaction() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean executeUpdate(String query) throws SQLException {		
		
		if(!this.connection.isClosed() && this.connection != null) {
			return this.statement.executeUpdate(query) > 0;
		}
		
		return false;
	}
    
	
}