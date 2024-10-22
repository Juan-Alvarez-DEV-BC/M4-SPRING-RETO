package co.com.bancolombia.M4_SPRING_RETO.controller;

import co.com.bancolombia.M4_SPRING_RETO.model.dto.CrearCuentaDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.dto.DepositoRetiroDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.dto.NroCuentaDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.Cuenta;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.Transaccion;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.OrigenTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.TipoTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController()
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @GetMapping("/saldo")
    public ResponseEntity<String> obtenerSaldoPorCuenta(@Valid @RequestBody NroCuentaDTO numeroCuentaDTO) {
        System.out.println(numeroCuentaDTO.toString());
        BigDecimal saldo = cuentaService.obtenerSaldoCuenta(numeroCuentaDTO);
        String     mensaje = "El saldo actual es: "+ saldo;
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<String> registrarCuenta(@Valid @RequestBody CrearCuentaDTO crearCuentaDTO){
        Cuenta cuentaCreada = cuentaService.crearCuenta(crearCuentaDTO);
        String mensaje =  "REGISTRO EXITOSO!!!\n\n"  ;
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/deposito")
    public ResponseEntity<String> depositoCuenta(@Valid @RequestBody DepositoRetiroDTO depositoRetiroDTO) throws Exception {
        Cuenta depositoCreada = cuentaService.deposito(depositoRetiroDTO , TipoTrxENUM.DEPOSITO, depositoRetiroDTO.getOrigenTrxEnum());
        String mensaje =  "DEPOSITO EXITOSO!!!\n\n"  ;
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
    @PostMapping("/retiro")
    public ResponseEntity<String> retiroCuenta(@Valid @RequestBody DepositoRetiroDTO depositoRetiroDTO) throws Exception {
        Cuenta retiroCreada = cuentaService.retiro(depositoRetiroDTO, TipoTrxENUM.RETIRO, depositoRetiroDTO.getOrigenTrxEnum());
        String mensaje =  "RETIRO EXITOSO!!!\n\n"  ;
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/compra")
    public ResponseEntity<String> compraCuenta(@Valid @RequestBody DepositoRetiroDTO depositoRetiroDTO) throws Exception {
        Cuenta compraCreada = cuentaService.retiro(depositoRetiroDTO, TipoTrxENUM.COMPRA, depositoRetiroDTO.getOrigenTrxEnum());
        String mensaje =  "COMPRA EXITOSO!!!\n\n"  ;
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }

    @PostMapping("/listar-transacciones")
    public ResponseEntity<List> listar5Transacciones(@Valid @RequestBody NroCuentaDTO numeroCuentaDTO , @RequestParam(defaultValue = "5") int cantidad) {
        System.out.println(numeroCuentaDTO.toString());
        List<Transaccion> transacciones = cuentaService.obtenerUltimasTransacciones(numeroCuentaDTO , cantidad);
        return new ResponseEntity<>(transacciones, HttpStatus.OK);
    }
}
