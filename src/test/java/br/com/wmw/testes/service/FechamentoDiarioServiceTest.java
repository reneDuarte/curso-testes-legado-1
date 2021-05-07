package br.com.wmw.testes.service;

import br.com.wmw.test.method.MethodTestUtils;
import br.com.wmw.testes.ValidationException;
import javafx.util.converter.NumberStringConverter;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.Extensions;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FechamentoDiarioServiceTest {

	private FechamentoDiarioService fechamentoDiarioService;

	@BeforeEach
	public void configure() {
		fechamentoDiarioService = new FechamentoDiarioService();
	}

	@ParameterizedTest
	@CsvSource({"0,1", "1,0", "-1,1", "1,-1", "2,1", "2,2"})
	void deveFalharTesteTestarKm(double kmIni, double kmFinal , TestInfo info, TestReporter testReporter) {
		Assertions.assertThrows(ValidationException.class, () -> {
			MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validarKM", kmIni, kmFinal);
		});

	}

	@ParameterizedTest(name="{index}")
	@CsvSource({ "1,2" , "2,3"})
	void deveTesteTestarKm(double kmIni, double kmFinal , TestInfo info, TestReporter testReporter) {
		MethodTestUtils.invokePrivateMethod(fechamentoDiarioService, "validarKM", kmIni, kmFinal);
	}
}