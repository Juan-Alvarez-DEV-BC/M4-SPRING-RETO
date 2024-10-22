package co.com.bancolombia.M4_SPRING_RETO.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="transacciones")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private String tipoTrx;       // "RETIRO" - "DEPOSITO" o "COMPRA"

    @Column(name = "valor")
    private BigDecimal monto;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "id_cuenta", nullable = false)
    @JsonIgnore
    private Cuenta cuenta;

    public Transaccion(String tipoTrx, BigDecimal monto, LocalDateTime fecha, Cuenta cuenta) {
        this.tipoTrx = tipoTrx;
        this.monto = monto;
        this.fecha = fecha;
        this.cuenta = cuenta;
    }

    public Transaccion() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoTrx() {
        return tipoTrx;
    }

    public void setTipoTrx(String tipoTrx) {
        this.tipoTrx = tipoTrx;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "id=" + id +
                ", tipoTrx='" + tipoTrx + '\'' +
                ", monto=" + monto +
                ", fecha=" + fecha +
                '}';
    }
}