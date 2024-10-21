package co.com.bancolombia.M4_SPRING_RETO.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class NroCuentaDTO {
    @NotEmpty(message ="El ID de la cuenta es Obligatorio")
    private String nroCuenta;

    public NroCuentaDTO(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }
    public NroCuentaDTO() {}

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    @Override
    public String toString() {
        return "NumeroCuentaDTO{" +
                "nroCuenta=" + nroCuenta +
                '}';
    }
}
