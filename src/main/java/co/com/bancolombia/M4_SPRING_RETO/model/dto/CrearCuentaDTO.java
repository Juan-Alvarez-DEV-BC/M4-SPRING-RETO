package co.com.bancolombia.M4_SPRING_RETO.model.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CrearCuentaDTO {

    @NotEmpty(message ="El número de cuenta es Obligatorio")
    @Size(max = 10, message = "El número de cuenta debe tener un máximo de 10 caracteres!")
    @Size(min = 4, message = "El número de cuenta debe tener un mínimo de 4 caracteres!")
    @Pattern(regexp="^\\d+$", message = "El número de cuenta debe ser un valor numérico")
    private String nroCuenta;

    @NotEmpty(message ="El titular es Obligatorio")
    @Size(max = 255, message = "El titular debe tener un máximo de 255 caracteres!")
    @Size(min = 5, message = "El titular debe tener un mínimo de 5 caracteres!")
    private String titular;

    @NotNull(message = "El monto es obligatorio!")
    @Positive(message = "El monto debe ser mayor a cero!")
    private BigDecimal monto;

    @NotEmpty(message ="El Tipo de Cuenta es Obligatorio")
    @Size(max = 10, message = "El Tipo de Cuenta debe tener un máximo de 10 caracteres!")
    @Size(min = 5, message = "El titular debe tener un mínimo de 6 caracteres!")
    private String tipoCuenta;

   public CrearCuentaDTO() {
    }

    public CrearCuentaDTO(String nroCuenta, String titular, BigDecimal monto, String tipoCuenta) {
        this.nroCuenta = nroCuenta;
        this.titular = titular;
        this.monto = monto;
        this.tipoCuenta = tipoCuenta;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }
}