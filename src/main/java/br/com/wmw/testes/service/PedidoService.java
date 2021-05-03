package br.com.wmw.testes.service;

import br.com.wmw.testes.AppConfig;

public class PedidoService {

	private static double DESCONTO_ESTOQUE_QUANTIDADE_FAIXA_UM = 1000;
	private static double DESCONTO_ESTOQUE_PERCENTUAL_FAIXA_UM = 20;
	private static double DESCONTO_ESTOQUE_QUANTIDADE_FAIXA_DOIS = 500;
	private static double DESCONTO_ESTOQUE_PERCENTUAL_FAIXA_DOIS = 10;
	private static double DESCONTO_ESTOQUE_QUANTIDADE_FAIXA_TRES = 100;
	private static double DESCONTO_ESTOQUE_PERCENTUAL_FAIXA_TRES = 5;
	private static double DESCONTO_ESTOQUE_NENHUM_DESCONTO = 0;

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
		if (estoque >= PedidoService.DESCONTO_ESTOQUE_QUANTIDADE_FAIXA_UM) {
			return PedidoService.DESCONTO_ESTOQUE_PERCENTUAL_FAIXA_UM;
		} else if (estoque >= PedidoService.DESCONTO_ESTOQUE_QUANTIDADE_FAIXA_DOIS) {
			return PedidoService.DESCONTO_ESTOQUE_PERCENTUAL_FAIXA_DOIS;
		} else if (estoque >= PedidoService.DESCONTO_ESTOQUE_QUANTIDADE_FAIXA_TRES) {
			return PedidoService.DESCONTO_ESTOQUE_PERCENTUAL_FAIXA_TRES;
		}
		return PedidoService.DESCONTO_ESTOQUE_NENHUM_DESCONTO;
	}

	public double calcularDesconto(final double valor, final long quantidade, final long estoque) {
		double desconto = 0;

		if (AppConfig.isUsaDescontoPorQuantidade()) {
			if (quantidade >= 100) {
				desconto = 20;
			} else if (quantidade >= 50) {
				desconto = 10;
			} else if (quantidade >= 10) {
				desconto = 5;
			}
		}

		desconto += calcularDescontoPorEstoque(estoque);

		return valor * (1 - (desconto / 100));
	}

}
