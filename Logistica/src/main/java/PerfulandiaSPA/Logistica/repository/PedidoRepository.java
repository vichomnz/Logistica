package PerfulandiaSPA.Logistica.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import PerfulandiaSPA.Logistica.model.PedidoModel;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {
}
