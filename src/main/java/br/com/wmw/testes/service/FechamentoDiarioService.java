package br.com.wmw.testes.service;

import java.util.Date;

import br.com.wmw.testes.AppConfig;
import br.com.wmw.testes.Messages;
import br.com.wmw.testes.ValidationException;
import br.com.wmw.testes.domain.FechamentoDiario;

public class FechamentoDiarioService {

	private void validaDataFechamentoDiario(final Date dtFechamento) {
		if (dtFechamento == null) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_SEM_DATA_FECHAMENTO);
		}
	}

	private void validaKilometragemVeiculo(final double kmInicialVeiculo, final double kmFinalVeiculo) {
		if (kmInicialVeiculo <= 0) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_KM_INICIAL_VEICULO_MAIOR_ZERO);
		}
		if (kmFinalVeiculo <= 0) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_KM_FINAL_VEICULO_MAIOR_ZERO);
		}
		if (kmFinalVeiculo <= kmInicialVeiculo) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_KM_FINAL_DEVE_SER_MAIOR_INICIAL);
		}
	}

	public void validate(final FechamentoDiario fechamentoDiario) {
		validaDataFechamentoDiario(fechamentoDiario.getDtFechamento());

		validaKilometragemVeiculo(fechamentoDiario.getKmInicialVeiculo(), fechamentoDiario.getKmFinalVeiculo());

		final int countAbertos = PedidoService.getInstance().countPedidosEmAberto();
		final int countNaoTransmitidos = AppConfig.isBloqueiaFechamentoDiarioPedidosNaoTransmitidos() ? PedidoService.getInstance().countPedidosNaoTransmitidosFechamentoDiario() : 0;
		if ((countAbertos > 0) && (countNaoTransmitidos > 0)) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_PEDIDOS_ABERTOS_E_NAO_TRANSMITIDOS);
		} else if (countAbertos > 0) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_PEDIDO_ABERTO);
		} else if (countNaoTransmitidos > 0) {
			throw new ValidationException(Messages.FECHAMENTO_DIARIO_MSG_PEDIDO_NAO_TRANSMITIDO);
		}
	}

}
