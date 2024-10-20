package co.com.bancolombia.M4_SPRING_RETO.repository;

import co.com.bancolombia.M4_SPRING_RETO.model.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransaccionRepository extends JpaRepository<Transaccion, Long> {
}
