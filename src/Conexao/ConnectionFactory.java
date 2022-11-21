package Conexao;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	public DataSource dataSource;
	
	public ConnectionFactory() { 
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();	
	}
}
