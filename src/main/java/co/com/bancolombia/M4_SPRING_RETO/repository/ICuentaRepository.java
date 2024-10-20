package co.com.bancolombia.M4_SPRING_RETO.repository;

import co.com.bancolombia.M4_SPRING_RETO.model.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    Optional<Cuenta> findByNroCuenta(String nroCuenta);
 }
