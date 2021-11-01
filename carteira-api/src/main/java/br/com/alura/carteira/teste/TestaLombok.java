package br.com.alura.carteira.teste;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.alura.carteira.modelo.Transacao;

import br.com.alura.carteira.modelo.TipoTransacao;

public class TestaLombok {

	public static void main(String[] args) {
		
		Transacao t = new Transacao();
		
		t.setTicker("sergio");
		System.out.println(t.getTicker());
		
		System.out.println(t);
		
// teste do construtor

// foi comentado com a inclusão do JPA		
	//	Transacao t1 = new Transacao("xpto",new BigDecimal("123.45"),23,LocalDate.now(),TipoTransacao.COMPRA);
		
	//	System.out.println(t1);
		

	}

}
