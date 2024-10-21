package co.com.bancolombia.M4_SPRING_RETO.service;

import co.com.bancolombia.M4_SPRING_RETO.model.dto.CrearCuentaDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.Cuenta;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.CuentaBasica;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.CuentaPremium;
import co.com.bancolombia.M4_SPRING_RETO.repository.ICuentaRepository;
import co.com.bancolombia.M4_SPRING_RETO.repository.ITransaccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CuentaService {
    private final ICuentaRepository cuentaRepository;
    private final ITransaccionRepository transaccionRepository;

    public CuentaService(ICuentaRepository cuentaRepository, ITransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Transactional
    public Cuenta obtenerCuenta(String nroCuenta){
        Optional<Cuenta> cuentaEncontrada = cuentaRepository.findByNroCuenta(nroCuenta);
        if (cuentaEncontrada.isEmpty()){
            throw new NoSuchElementException("La Cuenta " + nroCuenta + " No fue encontrada");
        }
        return  cuentaEncontrada.get();
    }

    @Transactional
    public Cuenta crearCuenta(CrearCuentaDTO crearCuentaDTO){
        Cuenta cuenta = null;
        switch (crearCuentaDTO.getTipoCuenta()) {
            case "BASICA" -> cuenta = new CuentaBasica();
            case "PREMIUM" -> cuenta = new CuentaPremium();
        }

        cuenta.setNroCuenta(crearCuentaDTO.getNroCuenta());
        cuenta.setNombreTitular(crearCuentaDTO.getTitular());
        cuenta.setMonto(crearCuentaDTO.getMonto());
        return cuentaRepository.save(cuenta);
    }

}
