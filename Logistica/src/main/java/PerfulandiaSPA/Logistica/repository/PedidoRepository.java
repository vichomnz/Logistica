package PerfulandiaSPA.Logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import PerfulandiaSPA.Logistica.model.LogisticaModel;

public interface PedidoRepository extends JpaRepository<LogisticaModel, Long> {
    boolean existsByIdPedido(String idPedido);
}
