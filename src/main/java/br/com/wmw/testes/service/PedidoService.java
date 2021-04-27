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

	//TODO: Adicionar o desconto por estoque
	//TODO: Se tiver desconto por estoque habilitado e estoque for > 1000, conceder desconto adicional de 5%
	public Double calcularDesconto(final Double valor, final Integer quantidade, final Integer estoque) {
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

		return valor * (1 - (desconto / 100));
	}

}
