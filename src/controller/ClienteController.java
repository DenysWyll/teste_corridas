package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import model.ContaCliente;
import persistence.ContaClienteDAO;

public class ClienteController {
	
 private static ContaClienteDAO cDao = new ContaClienteDAO();

 public static void main(String[] args) {
	insereRegistros();
	calculaMedia();
 }
 
 public static void insereRegistros() {
	 
	List<ContaCliente> clientes = new ArrayList<ContaCliente>();
	
	ContaCliente cliente;
	Boolean ativo = true;
	
	double random;
	double saldoAleatorio;
	
	//Adiciona 15 pessoas na lista
	for (int i = 0; i < 15; i++) {
		
		cliente = new ContaCliente();
		
		//cria um id > que 1000
		cliente.setId(1000 + (i * 100));
		cliente.setNome("Pessoa "+i);
		cliente.setAtivo(ativo);
		cliente.setCpfCnpj("123456789"+i);
		
		random = new Random().nextDouble();
		//gera um valor aleatório entre 3000 e 1000
		saldoAleatorio = 1000 + (random * (3000 - 1000));
		cliente.setSaldo(saldoAleatorio);
		
		//insere o objeto na lista
		clientes.add(cliente);
		
		//inverte o valor da variavel booleana
		ativo = !ativo;
		
	}
	
	cDao.adicionar(clientes);
	
 }
 
 public static void calculaMedia() {
	 	 
	 Double media = 0.0;
	 int numRegistros = 0;
	 
	 List<ContaCliente> clientes = cDao.buscar();
	 List<ContaCliente> clientesDaBusca = new ArrayList<ContaCliente>();
	 
	 for (ContaCliente contaCliente : clientes) {
		 
		if(contaCliente.getSaldo() > 560 
				&& contaCliente.getId() > 1500 
				&& contaCliente.getId() < 2700 ) {
			
					clientesDaBusca.add(contaCliente);	
					
					numRegistros ++;
					media += contaCliente.getSaldo();
					
		}
		
	 }
	 
	 //divide a soma de todos os saldo pela quantidade
	 media /= numRegistros;
	 
	 //Ordena a lista pelos saldo decrescente
	 Collections.sort(clientesDaBusca, new Comparator<ContaCliente> () {

		@Override
		public int compare(ContaCliente cliente1, ContaCliente cliente2) {
			
			return cliente2.getSaldo().compareTo(cliente1.getSaldo());
		}
		 
	 });
	 
	 //imprime media com duas casas decimais
	 System.out.printf("A media do valor total e:  %.2f %n",media);
	 
	 //imprime saldo de cada cliente do criterio com duas casas decimais
	 for (ContaCliente contaCliente : clientesDaBusca) {
		 
		 	System.out.printf("\n Cliente:"+contaCliente.getNome()
							 +"\n Saldo: %.2f %n", contaCliente.getSaldo());
		 	
	 }
	 
 }
 
}
