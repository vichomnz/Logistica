package PerfulandiaSPA.Logistica.controller;

import PerfulandiaSPA.Logistica.service.LogisticaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import PerfulandiaSPA.Logistica.model.LogisticaModel;

import java.util.List;

@RestController
@RequestMapping("/api/logistica")
@AllArgsConstructor
public class PedidoController {
    private final LogisticaService LogisticaService;

    @PostMapping("/registrar")
    public ResponseEntity<LogisticaModel> registrarPedido(@RequestBody LogisticaModel logisticaModel) {
        LogisticaModel pedido = LogisticaService.registrarPedido(logisticaModel.getIdPedido(), logisticaModel.getNombreTransportista());
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<LogisticaModel> actualizarEnvio(@PathVariable Long id, @RequestParam String estado) {
        LogisticaModel pedidoActualizado = LogisticaService.actualizarEnvio(id, estado);
        return ResponseEntity.ok(pedidoActualizado);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<LogisticaModel>> listarPedidos() {
        List<LogisticaModel> pedidos = LogisticaService.listarPedidos();
        return ResponseEntity.ok(pedidos);
    }
}