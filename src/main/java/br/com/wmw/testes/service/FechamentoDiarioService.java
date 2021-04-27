package br.com.wmw.testes.service;

import java.sql.SQLException;
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

	//TODO: Adicionar uma validação para KM inicial e final do fechamento;
	//TODO: KM inicial deve ser maior que zero
	//TODO: KM final deve ser maior que zero
	//TODO: KM final deve ser maior que a KM inicial
	//TODO: Fazer os testes da nova regra de negócio
	public void validate(final FechamentoDiario fechamentoDiario) throws SQLException {
		validaDataFechamentoDiario(fechamentoDiario.getDtFechamento());

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
