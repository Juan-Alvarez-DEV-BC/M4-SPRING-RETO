package co.com.bancolombia.M4_SPRING_RETO.service;

import co.com.bancolombia.M4_SPRING_RETO.model.dto.CrearCuentaDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.dto.DepositoRetiroDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.dto.NroCuentaDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.Cuenta;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.CuentaBasica;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.CuentaPremium;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.Transaccion;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.OrigenTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.TipoTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.repository.ICuentaRepository;
import co.com.bancolombia.M4_SPRING_RETO.repository.ITransaccionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CuentaService {
    private final ICuentaRepository cuentaRepository;
    private final ITransaccionRepository transaccionRepository;

    public CuentaService(ICuentaRepository cuentaRepository, ITransaccionRepository transaccionRepository) {
        this.cuentaRepository = cuentaRepository;
        this.transaccionRepository = transaccionRepository;
    }

    @Transactional
    public BigDecimal obtenerSaldoCuenta(NroCuentaDTO nroCuentaDTO){
        Cuenta cuenta = obtenerCuenta(nroCuentaDTO.getNroCuenta());
        return  cuenta.getMonto();
    }

    @Transactional
    public List<Transaccion> obtenerUltimasTransacciones(NroCuentaDTO nroCuentaDTO , int cantidad){
        Cuenta cuenta = obtenerCuenta(nroCuentaDTO.getNroCuenta());
        List<Transaccion> ultimasTransacciones = cuenta.getTransacciones().stream()
                .sorted(Comparator.comparing(Transaccion::getFecha).reversed())  // Ordenar por fecha descendente
                .limit(cantidad)                                                 // Limitar a las últimas transacciones
                .collect(Collectors.toList());
        return  ultimasTransacciones;
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

    @Transactional
    public Cuenta deposito(DepositoRetiroDTO depositoRetiroDTO, TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx) throws Exception {
        Cuenta cuenta = this.obtenerCuenta(depositoRetiroDTO.getNroCuenta());
        cuenta.deposito(depositoRetiroDTO.getMonto(), tipoTrx,origenTrx);
        transaccionRepository.save(cuenta.asignarTransaccion(tipoTrx, depositoRetiroDTO.getMonto()));
        return cuentaRepository.save(cuenta);
    }

    @Transactional
    public Cuenta retiro(DepositoRetiroDTO depositoRetiroDTO, TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx) throws Exception {
        Cuenta cuenta = this.obtenerCuenta(depositoRetiroDTO.getNroCuenta());
        cuenta.retiro(depositoRetiroDTO.getMonto(), tipoTrx,origenTrx);
        transaccionRepository.save(cuenta.asignarTransaccion(tipoTrx, depositoRetiroDTO.getMonto()));
        return cuentaRepository.save(cuenta);
    }

}
