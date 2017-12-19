package testes;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import lab05.Cenario;
import lab05.Estado;
import lab05.Previsao;

public class CenarioTest {
	
	private Cenario cenario;
	@Before
	public void setUp() {
		cenario = new Cenario("Todos os alunos vao ser aprovados");
	}

	@Test(expected = NullPointerException.class)
	public void cenarioComDescricaoNulaTest() {
		cenario = new Cenario(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cenarioComDescricaoVaziaTest() {
		cenario = new Cenario("        ");
	}
	
	@Test(expected = NullPointerException.class)
	public void cadastraApostaComNomeNullTest() {
		cenario.cadastrarApostas(null, 200, Previsao.VAI_ACONTECER);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void cadastraApostaComNomeVazioTest() {
		cenario.cadastrarApostas("       ", 2000, Previsao.NAO_VAI_ACONTECER);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void fecharCenarioFinalizadoTest() {
		cenario.fecharCenario(Estado.FINALIZADO_OCORREU);
		cenario.fecharCenario(Estado.FINALIZADO_NAO_OCORREU);
	}
	
	@Test
	public void getApostasPerdedorasTest() {
		cenario.cadastrarApostas("Jose", 200, Previsao.VAI_ACONTECER);
		cenario.cadastrarApostas("Joao", 500, Previsao.NAO_VAI_ACONTECER);
		int expected = 500;
		cenario.fecharCenario(Estado.FINALIZADO_OCORREU);
		assertEquals(expected, (int)cenario.getSomaPerdedoras());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void getApostasPerdedorasCenarioNaoFinalizadoTest() {
		cenario.getSomaPerdedoras();
	}
	
	@Test
	public void listarApostasTest() {
		cenario.cadastrarApostas("Jose", 200, Previsao.VAI_ACONTECER);
		cenario.cadastrarApostas("Joao", 500, Previsao.NAO_VAI_ACONTECER);
		String expected = "Jose - R$200.0 - Vai acontecer\n" +
						"Joao - R$500.0 - Nao vai acontecer\n";
		assertEquals(expected, cenario.listarApostas());
	}
	
	@Test
	public void numeroApostasTest() {
		cenario.cadastrarApostas("Jose", 200, Previsao.VAI_ACONTECER);
		cenario.cadastrarApostas("Joao", 500, Previsao.NAO_VAI_ACONTECER);
		assertEquals(2, cenario.numeroApostas());
	}
	
	@Test
	public void valorApostasCenarioTest() {
		cenario.cadastrarApostas("Jose", 200, Previsao.VAI_ACONTECER);
		cenario.cadastrarApostas("Joao", 500, Previsao.NAO_VAI_ACONTECER);
		assertEquals(700, cenario.valorApostas());
	}
	
	@Test
	public void getDescricaoTest() {
		String expected = "Todos os alunos vao ser aprovados";
		assertEquals(expected, cenario.getDescricao());
	}
	
	@Test
	public void toStringCenarioNaoFinalizadoTest() {
		String expected = "Todos os alunos vao ser aprovados - Nao finalizado";
		assertEquals(expected, cenario.toString());
	}
	
	@Test
	public void toStringCenarioFinalizadoOcorreuTest() {
		cenario.fecharCenario(Estado.FINALIZADO_OCORREU);
		String expected = "Todos os alunos vao ser aprovados - Finalizado (ocorreu)";
		assertEquals(expected, cenario.toString());
	}
	
	public void toStringCenarioFinalizadoNaoOcorreuTest() {
		cenario.fecharCenario(Estado.FINALIZADO_NAO_OCORREU);
		String expected = "Todos os alunos vao ser aprovados - Finalizado ( n ocorreu)";
		assertEquals(expected, cenario.toString());
	}
	
}