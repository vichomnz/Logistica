package PerfulandiaSPA.Logistica.service;

   import PerfulandiaSPA.Logistica.repository.PedidoRepository; 
   import lombok.AllArgsConstructor;
   import org.springframework.stereotype.Service;
   import PerfulandiaSPA.Logistica.model.PedidoModel;



@Service
@AllArgsConstructor
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    public PedidoModel registrarPedido(String nombreTransportista) {
        PedidoModel pedido = new PedidoModel();
        pedido.setNombreTransportista(nombreTransportista);
        pedido.setEstado("PENDIENTE");
        return pedidoRepository.save(pedido);
    }

    public PedidoModel actualizarEnvio(Long id, String estado) {
        PedidoModel pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el pedido con ID: " + id));
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }
}