package PerfulandiaSPA.Logistica.service;

   import PerfulandiaSPA.Logistica.repository.PedidoRepository; 
   import lombok.AllArgsConstructor;
   import org.springframework.stereotype.Service;
   import PerfulandiaSPA.Logistica.model.LogisticaModel;

   import java.util.List;



@Service
@AllArgsConstructor
public class LogisticaService {
    private final PedidoRepository pedidoRepository;

    public LogisticaModel registrarPedido(String idPedido, String nombreTransportista) {
        if (pedidoRepository.existsByIdPedido(idPedido)){
            throw new IllegalArgumentException("El idPedido " + idPedido + " ya está registrado.");
        }
        LogisticaModel pedido = new LogisticaModel();
        pedido.setIdPedido(idPedido);
        pedido.setNombreTransportista(nombreTransportista);
        pedido.setEstado("PENDIENTE");
        return pedidoRepository.save(pedido);
    }

    public LogisticaModel actualizarEnvio(Long id, String estado) {
        LogisticaModel pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró el pedido con ID: " + id));
        pedido.setEstado(estado);
        return pedidoRepository.save(pedido);
    }

    public List<LogisticaModel> listarPedidos() {
        return pedidoRepository.findAll();
    }
}