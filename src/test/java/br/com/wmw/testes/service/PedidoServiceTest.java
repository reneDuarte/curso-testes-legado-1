package br.com.wmw.testes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.wmw.test.method.MethodTestUtils;

class PedidoServiceTest {

	private static final int VINTE_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_MIL = 20;
	private static final int DEZ_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_QUINHENTOS = 10;
	private static final int CINCO_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_CEM = 5;
	private static final int NENHUM_DESCONTO_PARA_ESTOQUE_MENOR_CEM = 0;

	@Test
	void deveConcederVintePorcentoDeDescontoQuandoEstoqueForMaiorQueMil() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 1001);
		assertEquals(PedidoServiceTest.VINTE_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_MIL, desconto);
	}

	@Test
	void deveConcederVintePorcentoDeDescontoQuandoEstoqueForIgualAMil() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 1000);
		assertEquals(PedidoServiceTest.VINTE_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_MIL, desconto);
	}

	@Test
	void deveConcederDezPorcentoDeDescontoQuandoEstoqueForMaiorQueQuinhentos() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 501);
		assertEquals(PedidoServiceTest.DEZ_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_QUINHENTOS, desconto);
	}

	@Test
	void deveConcederDezPorcentoDeDescontoQuandoEstoqueForIgualAQuinhentos() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 500);
		assertEquals(PedidoServiceTest.DEZ_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_QUINHENTOS, desconto);
	}

	@Test
	void deveConcederCincoPorcentoDeDescontoQuandoEstoqueForMaiorQueCem() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 101);
		assertEquals(PedidoServiceTest.CINCO_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_CEM, desconto);
	}

	@Test
	void deveConcederCincoPorcentoDeDescontoQuandoEstoqueForIgualACem() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 100);
		assertEquals(PedidoServiceTest.CINCO_PORCENTO_DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_CEM, desconto);
	}

	@Test
	void naoDeveConcederDescontoQuandoEstoqueForMenorQueCem() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 99);
		assertEquals(PedidoServiceTest.NENHUM_DESCONTO_PARA_ESTOQUE_MENOR_CEM, desconto);
	}

}
