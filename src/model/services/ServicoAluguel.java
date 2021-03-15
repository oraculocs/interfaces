package model.services;

import model.entities.AluguelCarro;
import model.entities.NotaPagamento;

public class ServicoAluguel {
	
	private Double precoDia;
	private Double precoHora;
	
	private TaxaServico taxaServico;

	public ServicoAluguel() {
	}

	public ServicoAluguel(Double precoDia, Double precoHora, TaxaServico taxaServico) {
		this.precoDia = precoDia;
		this.precoHora = precoHora;
		this.taxaServico = taxaServico;
	}
	
	public void gerarNotaPagamento(AluguelCarro aluguelCarro) {
		//Pega o valor em milisegundos da data
		long t1 = aluguelCarro.getInicio().getTime();
		long t2 = aluguelCarro.getFim().getTime();
		
		//t2 - t1 -> vai achar a diferença em milisegundos -- / 1000 -> converter para segundos -- / 60 -> converter para minutos -- / 60 -> converter para horas
		double horas = (double) (t2 - t1) / 1000 / 60 / 60;
		
		double pagamentoBasico;
		if(horas <= 12.0) {
			pagamentoBasico = Math.ceil(horas) * precoHora;
		}else {
			pagamentoBasico = Math.ceil(horas / 24) * precoDia;
		}
		
		double taxa = taxaServico.taxa(pagamentoBasico);
		
		aluguelCarro.setNotaPagamento(new NotaPagamento(pagamentoBasico, taxa));
		
	}
	

}
