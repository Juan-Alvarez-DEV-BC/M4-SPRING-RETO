package co.com.bancolombia.M4_SPRING_RETO.controller;

import co.com.bancolombia.M4_SPRING_RETO.model.dto.CrearCuentaDTO;
import co.com.bancolombia.M4_SPRING_RETO.model.entity.Cuenta;
import co.com.bancolombia.M4_SPRING_RETO.service.CuentaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/cuenta")
public class CuentaController {

    private final CuentaService cuentaService;

    public CuentaController(CuentaService cuentaService){
        this.cuentaService = cuentaService;
    }

    @PostMapping("/crear-cuenta")
    public ResponseEntity<String> registrarCuenta(@Valid @RequestBody CrearCuentaDTO crearCuentaDTO){
        Cuenta cuentaCreada = cuentaService.crearCuenta(crearCuentaDTO);
        String mensaje =  "REGISTRO EXITOSO!!!\n\n"  ;
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
    }
}
