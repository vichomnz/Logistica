package PerfulandiaSPA.Logistica.controller;

import PerfulandiaSPA.Logistica.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import PerfulandiaSPA.Logistica.model.PedidoModel;

@RestController
@RequestMapping("/api/logistica")
@AllArgsConstructor
public class PedidoController {
    private final PedidoService pedidoService;

    @PostMapping("/registrar")
    public ResponseEntity<PedidoModel> registrarPedido(@RequestParam String nombreTransportista) {
        PedidoModel pedido = pedidoService.registrarPedido(nombreTransportista);
        return ResponseEntity.ok(pedido);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PedidoModel> actualizarEnvio(@PathVariable Long id, @RequestParam String estado) {
        PedidoModel pedidoActualizado = pedidoService.actualizarEnvio(id, estado);
        return ResponseEntity.ok(pedidoActualizado);
    }
}