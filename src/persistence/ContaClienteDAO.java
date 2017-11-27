package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.ContaCliente;

public class ContaClienteDAO {
	
 private Connection con = DBUtil.getConnection();

 
 public void adicionar(List<ContaCliente> clientes) {
		
	try {
		
		Statement s = con.createStatement();
		
		/*
		 * cria uma query de insercao para cada registro na lista
		 * e adiciona no batch
		 */
		for (ContaCliente cliente: clientes) {
			String query = "insert into tb_customer_account"
							+ " (id_customer, cpf_cnpj, nm_customer, "
							+ "is_active, vl_total) values('"
							+ cliente.getId() + "','" 
							+ cliente.getCpfCnpj() + "','" 
							+ cliente.getNome() + "','" 
							+ (cliente.isAtivo() ? 1 : 0) + "','" 
							+ cliente.getSaldo() + "')";
			
			s.addBatch(query);
		}
		
		System.out.println("Registros inseridos com sucesso!");
		
		//executa todos os inserts que foram armazenados no batch
		s.executeBatch();
		
		s.clearBatch();
		s.close();
		
	} catch (SQLException e) {
		System.out.println("Erro ao inserir registros!");
		e.printStackTrace();
	}
	
 }
 
 public List<ContaCliente> buscar (){
	 List<ContaCliente> clientes = new ArrayList<ContaCliente>();
	 
	 String query = "select id_customer, cpf_cnpj, nm_customer, "
	 		+ "is_active, vl_total from tb_customer_account";
	 try {
		 
		 PreparedStatement pst = con.prepareStatement(query);
		 ResultSet rs = pst.executeQuery();
		 
		 /*
		  * adiciona cada registro do resultset na lista
		  * por meio de um objeto do tipo ContaCliente
		  */
		 while( rs.next() ) {
			 ContaCliente cliente = new ContaCliente();
			 cliente.setId( rs.getInt( "id_customer" ));
			 cliente.setCpfCnpj( rs.getString( "cpf_cnpj" ));
			 cliente.setNome( rs.getString( "nm_customer" ));
			 cliente.setAtivo( rs.getBoolean("is_active"));
			 cliente.setSaldo( rs.getDouble( "vl_total" ));
			 clientes.add(cliente);
		 }
		 
	} catch (SQLException e) {
		System.out.println("Erro ao buscar registros!");
		e.printStackTrace();
	}
	 
	 return clientes;
 }
 
}
