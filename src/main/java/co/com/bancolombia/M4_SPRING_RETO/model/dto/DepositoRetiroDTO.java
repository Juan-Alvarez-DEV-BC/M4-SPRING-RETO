package co.com.bancolombia.M4_SPRING_RETO.model.dto;

import co.com.bancolombia.M4_SPRING_RETO.model.enums.OrigenTrxENUM;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Locale;

public class DepositoRetiroDTO {

    @NotEmpty(message ="El número de cuenta es Obligatorio")
    @Size(max = 10, message = "El número de cuenta debe tener un máximo de 10 caracteres!")
    @Size(min = 4, message = "El número de cuenta debe tener un mínimo de 4 caracteres!")
    @Pattern(regexp="^\\d+$", message = "El número de cuenta debe ser un valor numérico")
    private String nroCuenta;

    @NotNull(message = "El monto es obligatorio!")
    @Positive(message = "El monto debe ser mayor a cero!")
    private BigDecimal monto;

    @NotEmpty(message ="El Origen de la transacción es Obligatorio")
    @Size(max = 11, message = "El Origen de la transacción debe tener un máximo de 11 caracteres!")
    @Size(min = 4, message = "El Origen de la transacción debe tener un mínimo de 4 caracteres!")
    @Pattern(regexp = "^(FISICO|WEB|OTRA_CUENTA|CAJERO|SUCURSAL)$", message = "El Origen de la transacción solo puede ser 'FISICO', 'SUCURSAL', 'WEB', 'OTRA_CUENTA' o 'CAJERO'")
    private String origenTRX;

    public DepositoRetiroDTO(String nroCuenta, BigDecimal monto,String origenTRX) {
        this.nroCuenta = nroCuenta;
        this.monto = monto;
        this.origenTRX = origenTRX;
    }
    public DepositoRetiroDTO() { }

    public String getOrigenTRX() {
        return origenTRX;
    }
    public OrigenTrxENUM getOrigenTrxEnum() {
        return OrigenTrxENUM.valueOf(origenTRX.toUpperCase()) ;
    }

    public void setOrigenTRX(String origenTRX) {
        this.origenTRX = origenTRX;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}

