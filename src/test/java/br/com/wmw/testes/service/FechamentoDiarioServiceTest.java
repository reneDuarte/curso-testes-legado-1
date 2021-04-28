package br.com.wmw.testes.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import br.com.wmw.test.method.MethodTestUtils;
import br.com.wmw.testes.Messages;
import br.com.wmw.testes.ValidationException;

class FechamentoDiarioServiceTest {

	@Test
	void naoDevePermitirKilometragemInicialZero() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		final ValidationException validationException = assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", 0, 1);
		});
		assertEquals(Messages.FECHAMENTO_DIARIO_MSG_KM_INICIAL_VEICULO_MAIOR_ZERO, validationException.getMessage());
	}

	@Test
	void naoDevePermitirKilometragemFinalZero() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		final ValidationException validationException = assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", 1, 0);
		});
		assertEquals(Messages.FECHAMENTO_DIARIO_MSG_KM_FINAL_VEICULO_MAIOR_ZERO, validationException.getMessage());
	}

	@Test
	void naoDevePermitirKilometragemInicialNegativa() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		final ValidationException validationException = assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", -1, 1);
		});
		assertEquals(Messages.FECHAMENTO_DIARIO_MSG_KM_INICIAL_VEICULO_MAIOR_ZERO, validationException.getMessage());
	}

	@Test
	void naoDevePermitirKilometragemFinalNegativa() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		final ValidationException validationException = assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", 1, -1);
		});
		assertEquals(Messages.FECHAMENTO_DIARIO_MSG_KM_FINAL_VEICULO_MAIOR_ZERO, validationException.getMessage());
	}

	@Test
	void naoDevePermitirKilometragemFinalMenorQueInicial() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		final ValidationException validationException = assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", 2, 1);
		});
		assertEquals(Messages.FECHAMENTO_DIARIO_MSG_KM_FINAL_DEVE_SER_MAIOR_INICIAL, validationException.getMessage());
	}

	@Test
	void naoDevePermitirKilometragemFinalIgualAInicial() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		final ValidationException validationException = assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", 2, 2);
		});
		assertEquals(Messages.FECHAMENTO_DIARIO_MSG_KM_FINAL_DEVE_SER_MAIOR_INICIAL, validationException.getMessage());
	}

	@Test
	void devePermitirKilometragemFinalMaiorQueInicial() {
		final FechamentoDiarioService fechamentoDiarioService = new FechamentoDiarioService();
		assertDoesNotThrow(() -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validaKilometragemVeiculo", 1, 2);
		});
	}

}
