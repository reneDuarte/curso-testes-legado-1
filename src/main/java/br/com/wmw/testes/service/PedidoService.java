package br.com.wmw.testes.service;

import br.com.wmw.testes.AppConfig;

public class PedidoService {

	private static PedidoService instance = null;

	public static PedidoService getInstance() {
		if (PedidoService.instance == null) {
			PedidoService.instance = new PedidoService();
		}
		return PedidoService.instance;
	}

	public int countPedidosEmAberto() {
		return 0;
	}

	public int countPedidosNaoTransmitidosFechamentoDiario() {
		return 0;
	}

	private double calcularDescontoPorEstoque(final long estoque) {
		if (estoque >= 1000) {
			return 20;
		} else if (estoque >= 500) {
			return 10;
		} else if (estoque >= 100) {
			return 5;
		}
		return 0;
	}

	public double calcularDesconto(final double valor, final long quantidade, final long estoque) {
		double desconto = 0;

		if (AppConfig.isUsaDescontoPorQuantidade()) {
			if (quantidade >= 100) {
				desconto = 20D;
			} else if (quantidade >= 50) {
				desconto = 10D;
			} else if (quantidade >= 10) {
				desconto = 5D;
			}
		}

		desconto += calcularDescontoPorEstoque(estoque);

		return valor * (1 - (desconto / 100));
	}

}
