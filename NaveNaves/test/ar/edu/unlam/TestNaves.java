package ar.edu.unlam;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlam.dominio.GestionNaves;
import ar.edu.unlam.dominio.Nave;
import ar.edu.unlam.dominio.NaveCarguera;
import ar.edu.unlam.dominio.NaveExploradora;
import ar.edu.unlam.dominio.NaveSonda;
import ar.edu.unlam.dominio.SelecionarAlcance;
import ar.edu.unlam.exception.NaveCarguregeraSobrePesoException;
import ar.edu.unlam.exception.NaveDuplicadaException;
import ar.edu.unlam.exception.NaveSuperaConsumoException;

public class TestNaves {
	private GestionNaves adminitradorNaves;
	private Nave naveCarguera;
	private Nave naveCargueraMasPeso;
	private Nave naveExpLargo;
	private Nave naveExpCorto;
	private Nave naveSonda1;
	private Nave naveSonda2;
	private Nave naveSonda3;
	private Nave naveSondaRepetida;

	@BeforeEach
	public void init() {
		this.adminitradorNaves = new GestionNaves();
		this.naveCarguera = new NaveCarguera("C-123", "carguera", 100.0, 25, 10000);
		this.naveCargueraMasPeso = new NaveCarguera("C-456", "carguera con mas peso", 100.0, 35, 10000);
		this.naveExpLargo = new NaveExploradora("E-123", "largo", 90.0, SelecionarAlcance.LARGO_ALCANCE);
		this.naveExpCorto = new NaveExploradora("E-456", "corto", 80.0, SelecionarAlcance.CORTO_ALCANCE);
		this.naveSonda1 = new NaveSonda("S-123", "Sonda1", 90.0);
		this.naveSonda2 = new NaveSonda("S-456", "Sonda2", 80.0);
		this.naveSonda3 = new NaveSonda("S-789", "Sonda3", 70.0);
		this.naveSondaRepetida = new NaveSonda("S-123", "SondaRepetida", 90.0);

	}

	@Test
	public void debopodresAgregarUnaNaveDeLargoAlcanceAUnaFlotaDeNaves()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpLargo));
	}
	
	@Test 
	public void dadoQueExisteUnaFlotaDeNavesYDadoQueExisteUnaExceptionDeberiaRegstarUnaNaverCargueraEexcedidaDeTonelada()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException {
		assertThrows(NaveCarguregeraSobrePesoException.class, () -> this.adminitradorNaves.registrarNaves(this.naveCargueraMasPeso));
	}
	
	@Test
	public void dadoQueExisteUnaFlotaDeNavesYDadoQueExisteUnaExceptionDeberiaPermitirseElRegistroDeUnaNaveSondaSiElIdentificadorYaExiste()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda1));
		assertThrows(NaveDuplicadaException.class, () -> this.adminitradorNaves.registrarNaves(this.naveSondaRepetida));
	}
	
	@Test
	public void deberiaPoderRegistrarseUnaMisionDe4HorasParaUnaNaveDeCortoAlcanceExistente() 
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException, NaveSuperaConsumoException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpCorto));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveExpCorto,4));
	}
	
	@Test
	public void deberiaPoderCalcularseElConsumoDeCombustibleDeUnaNaveCargueraExistenteParaUnaMisionDe8Horas()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException, NaveSuperaConsumoException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveCarguera));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveCarguera,8));
		assertEquals(9.0, adminitradorNaves.obtenerConsumo(this.naveCarguera,8));
	}
	
	@Test
	public void deberiaPoderCalcularseElConsumoDeCombustibleDeUnaNaveDeLargoAlcanceExistenteEnUnaMisionDe6Horas() 
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException, NaveSuperaConsumoException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpLargo));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveExpLargo,6));
		assertEquals(2.7, adminitradorNaves.obtenerConsumo(this.naveExpLargo,6));
	}
	
	@Test
	public void deberiaPoderObtenerseUnaColeccionDeTodasLasNavesOrdenadasPorIdentificadorAscendente()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException, NaveSuperaConsumoException {
		
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpLargo));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda1));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpCorto));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda2));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveCarguera));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda3));
		
		Iterator<Nave> it = this.adminitradorNaves.ObtenerTodasLasNaves().iterator();
		assertEquals(this.naveCarguera.getCodigo(), it.next().getCodigo());
		assertEquals(this.naveExpLargo.getCodigo(), it.next().getCodigo());
		assertEquals(this.naveExpCorto.getCodigo(), it.next().getCodigo());
		assertEquals(this.naveSonda1.getCodigo(), it.next().getCodigo());
		assertEquals(this.naveSonda2.getCodigo(), it.next().getCodigo());
		assertEquals(this.naveSonda3.getCodigo(), it.next().getCodigo());
		
	}
	
	@Test
	public void deberiaPoderObtenerseUnaColeccionDeLasNavesSondaOrdenadasPorNombreDescendente()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException, NaveSuperaConsumoException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpLargo));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda1));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpCorto));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda2));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveCarguera));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda3));
		
		Iterator<Nave> it = this.adminitradorNaves.obteneerNaveSondaPorNombreDesc().iterator();
		assertEquals(3, this.adminitradorNaves.obtenerSoloNavesSondas().size());
		assertEquals(this.naveSonda3, it.next());
		assertEquals(this.naveSonda2, it.next());
		assertEquals(this.naveSonda1, it.next());
		
	}
	
	@Test
	public void deberiaPoderObtenerseUnReporteDeTodasLasMisionesRealizadasPorCadaNaveDeTodosLosGrupos()
			throws NaveCarguregeraSobrePesoException, NaveDuplicadaException, NaveSuperaConsumoException {
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpLargo));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda1));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveExpCorto));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda2));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveCarguera));
		assertTrue(this.adminitradorNaves.registrarNaves(this.naveSonda3));
		
		assertTrue(this.adminitradorNaves.registrarMision(this.naveSonda1,2));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveSonda1,6));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveSonda1,4));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveSonda2,2));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveSonda2,6));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveSonda3,4));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveExpLargo,6));
		assertTrue(this.adminitradorNaves.registrarMision(this.naveExpCorto,8));
		
		assertEquals(3, this.adminitradorNaves.obtenerRegistroDeNavesPorMision().get(naveSonda1).size());
		assertEquals(2, this.adminitradorNaves.obtenerRegistroDeNavesPorMision().get(naveSonda2).size());
		assertEquals(1, this.adminitradorNaves.obtenerRegistroDeNavesPorMision().get(naveSonda3).size());
		assertEquals(1, this.adminitradorNaves.obtenerRegistroDeNavesPorMision().get(naveExpCorto).size());
		
	}

}
