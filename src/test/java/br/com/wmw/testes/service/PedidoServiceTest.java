package br.com.wmw.testes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import br.com.wmw.test.method.MethodTestUtils;

class PedidoServiceTest {

	private static final int DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_MIL = 20;
	private static final int DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_QUINHENTOS = 10;
	private static final int DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_CEM = 5;
	private static final int DESCONTO_PARA_ESTOQUE_MENOR_CEM = 0;

	@Test
	void deveConcederDescontoQuandoEstoqueForIgualAMil() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 1000);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_MIL, desconto);
	}

	@Test
	void deveConcederDescontoQuandoEstoqueForMaiorQueMil() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 1001);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_MIL, desconto);
	}

	@Test
	void deveConcederDescontoQuandoEstoqueForIgualAQuinhentos() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 500);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_QUINHENTOS, desconto);
	}

	@Test
	void deveConcederDescontoQuandoEstoqueForMaiorQueQuinhentos() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 501);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_QUINHENTOS, desconto);
	}

	@Test
	void deveConcederDescontoQuandoEstoqueForIgualACem() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 100);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_CEM, desconto);
	}

	@Test
	void deveConcederDescontoQuandoEstoqueForMaiorQueCem() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 101);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MAIOR_IGUAL_CEM, desconto);
	}

	@Test
	void naoDeveConcederDescontoQuandoEstoqueForMenorQueCem() {
		final PedidoService pedidoService = PedidoService.getInstance();
		final double desconto = MethodTestUtils.invokePrivateMethod(pedidoService, "calcularDescontoPorEstoque", 50);
		assertEquals(PedidoServiceTest.DESCONTO_PARA_ESTOQUE_MENOR_CEM, desconto);
	}

}
