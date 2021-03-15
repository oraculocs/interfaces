package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import model.services.BrasilTaxaServico;
import model.services.ServicoAluguel;

public class Program {

	public static void main(String[] args) throws ParseException{
		
		/*
		 * Uma locadora brasileira de carros cobra um valor por hora para loca��es de at�
		 * 12 horas. Por�m, se a dura��o da loca��o ultrapassar 12 horas, a loca��o ser�
		 * cobrada com base em um valor di�rio. Al�m do valor da loca��o, � acrescido no
		 * pre�o o valor do imposto conforme regras do pa�s que, no caso do Brasil, � 20%
		 * para valores at� 100.00, ou 15% para valores acima de 100.00. Fazer um
		 * programa que l� os dados da loca��o (modelo do carro, instante inicial e final da
		 * loca��o), bem como o valor por hora e o valor di�rio de loca��o. O programa
		 * deve ent�o gerar a nota de pagamento (contendo valor da loca��o, valor do
		 * imposto e valor total do pagamento) e informar os dados na tela. Veja os
		 * exemplos.
		 * 
		 * Enter rental data
		 * Car model: Civic
		 * Pickup (dd/MM/yyyy hh:mm): 25/06/2018 10:30
		 * Return (dd/MM/yyyy hh:mm): 25/06/2018 14:40
		 * Enter price per hour: 10.00
		 * Enter price per day: 130.00
		 * INVOICE:
		 * Basic payment: 50.00
		 * Tax: 10.00
		 * Total payment: 60.00
		 * Calculations:
		 * Duration = (25/06/2018 14:40) - (25/06/2018 10:30) = 4:10 = 5 hours
		 * Basic payment = 5 * 10 = 50
		 * Tax = 50 * 20% = 50 * 0.2 = 10
		 */

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do Carro: ");
		String modelo = sc.nextLine();
		System.out.print("Retirado (dd/MM/yyyy hh:ss): ");
		Date inicio = sdf.parse(sc.nextLine());
		System.out.print("Retorno (dd/MM/yyyy hh:ss): ");
		Date fim = sdf.parse(sc.nextLine());
		
		AluguelCarro cr = new AluguelCarro(inicio, fim, new Veiculo(modelo));
		
		System.out.print("Entre com o pre�o por hora: ");
		double precoHora = sc.nextDouble();
		System.out.print("Entre com o pre�o por dia: ");
		double precoDia = sc.nextDouble();
		
		ServicoAluguel servicoAluguel = new ServicoAluguel(precoDia, precoHora, new BrasilTaxaServico());
		
		servicoAluguel.gerarNotaPagamento(cr);
		
		System.out.println("Fatura: ");
		System.out.println("Pagamento B�sico: " + String.format("%.2f", cr.getNotaPagamento().getPagamentoBasico()));
		System.out.println("Taxa: " + String.format("%.2f", cr.getNotaPagamento().getTaxa()));
		System.out.println("Total de Pagamento: " + String.format("%.2f", cr.getNotaPagamento().getTotalPagamento()));
		
		
		sc.close();
	}

}
