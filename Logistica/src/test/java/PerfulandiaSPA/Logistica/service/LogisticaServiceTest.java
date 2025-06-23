package PerfulandiaSPA.Logistica.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

import PerfulandiaSPA.Logistica.model.LogisticaModel;
import PerfulandiaSPA.Logistica.repository.LogisticaRepository;

public class LogisticaServiceTest {

    @Mock
    private LogisticaRepository pedidoRepository;

    @InjectMocks
    private LogisticaService logisticaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarPedido() {
    // Crear el pedido como lo hace LogisticaService
    LogisticaModel pedido = new LogisticaModel();
    pedido.setIdPedido("PED-001");
    pedido.setNombreTransportista("Starken");
    pedido.setEstado("PENDIENTE");
    LogisticaModel pedidoGuardado = new LogisticaModel(1L, "PED-001", "Starken", "PENDIENTE", LocalDateTime.now(), LocalDateTime.now());
    when(pedidoRepository.existsByIdPedido("PED-001")).thenReturn(false);
    when(pedidoRepository.save(pedido)).thenReturn(pedidoGuardado); // Usar el mismo pedido creado

    LogisticaModel resultado = logisticaService.registrarPedido("PED-001", "Starken");

    assertThat(resultado).isNotNull(); // Línea 42
    assertThat(resultado.getIdPedido()).isEqualTo("PED-001");
    assertThat(resultado.getNombreTransportista()).isEqualTo("Starken");
    assertThat(resultado.getEstado()).isEqualTo("PENDIENTE");
    verify(pedidoRepository).save(pedido);
}

    @Test
    void testListarPedidos() {
        LogisticaModel pedido1 = new LogisticaModel(null, "PED-001", "Starken", "PENDIENTE", null, null);
        LogisticaModel pedido2 = new LogisticaModel(null, "PED-002", "Chilexpress", "EN_TRANSITO", null, null);
        when(pedidoRepository.findAll()).thenReturn(Arrays.asList(pedido1, pedido2));

        List<LogisticaModel> resultado = logisticaService.listarPedidos();

        assertThat(resultado).hasSize(2).contains(pedido1, pedido2);
        verify(pedidoRepository).findAll();
    }

    @Test
    void testActualizarEnvio() {
        LogisticaModel pedidoExistente = new LogisticaModel(1L, "PED-001", "Starken", "PENDIENTE", LocalDateTime.now(), LocalDateTime.now());
        LogisticaModel pedidoActualizado = new LogisticaModel(1L, "PED-001", "Starken", "EN_TRANSITO", LocalDateTime.now(), LocalDateTime.now());

        when(pedidoRepository.findById(1L)).thenReturn(Optional.of(pedidoExistente));
        when(pedidoRepository.save(pedidoExistente)).thenReturn(pedidoActualizado);

        LogisticaModel resultado = logisticaService.actualizarEnvio(1L, "EN_TRANSITO");

        assertThat(resultado.getEstado()).isEqualTo("EN_TRANSITO");
        verify(pedidoRepository).findById(1L);
        verify(pedidoRepository).save(pedidoExistente);
    }
}