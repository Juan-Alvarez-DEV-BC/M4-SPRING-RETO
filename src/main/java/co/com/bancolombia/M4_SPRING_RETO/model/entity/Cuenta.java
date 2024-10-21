package co.com.bancolombia.M4_SPRING_RETO.model.entity;

import co.com.bancolombia.M4_SPRING_RETO.model.enums.OrigenTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.TipoTrxENUM;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_cuenta")
@Table(name="cuentas")
public abstract  class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "nro_cuenta")
    private String nroCuenta;

    @Column(name = "titular")
    private  String nombreTitular;

    @Column(name = "saldo")
    private BigDecimal monto;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaccion> transacciones;

    public Cuenta() {}

    public Cuenta(Long id, String nroCuenta, String nombreTitular, BigDecimal monto, List<Transaccion> transacciones) {
        this.id = id;
        this.nroCuenta = nroCuenta;
        this.nombreTitular = nombreTitular;
        this.monto = monto;
        this.transacciones = transacciones;
    }

    public  void deposito(BigDecimal valor, TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx){
        validaValor(valor);
        BigDecimal costoTransaccion = costoTransaccion(tipoTrx, origenTrx);
        valor = valor.add(costoTransaccion);
        setMonto(getMonto().add(valor));
    }

    public  void retiro(BigDecimal valor, TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx){
        validaValor(valor);
        BigDecimal costoTransaccion = costoTransaccion(tipoTrx, origenTrx);
        valor = valor.add(costoTransaccion);
        validaSaldo(valor);
        setMonto(getMonto().subtract(valor));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

    public Transaccion asignarTransaccion(TipoTrxENUM tipoTrx, BigDecimal monto) throws Exception {
        if (!(tipoTrx == TipoTrxENUM.RETIRO) &&
            !(tipoTrx == TipoTrxENUM.DEPOSITO) &&
            !(tipoTrx == TipoTrxENUM.COMPRA) ) {
            throw new IllegalArgumentException("Tipo de transacción NO valida");
        }
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(LocalDateTime.now());
        transaccion.setTipoTrx(tipoTrx.toString());
        transaccion.setMonto(monto);
        transaccion.setCuenta(this);
        return transaccion;
    }

    public abstract BigDecimal costoTransaccion(TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx);

    public void validaValor(BigDecimal valor){
        if (valor.compareTo(BigDecimal.ZERO) <= 0 ) {
            throw new IllegalArgumentException("El monto del depósito debe ser mayor a 0");
        }
    }

    public void validaSaldo(BigDecimal valor){
        if (valor.compareTo(this.getMonto()) < 0) {
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id=" + id +
                ", nroCuenta='" + nroCuenta + '\'' +
                ", nombreTitular='" + nombreTitular + '\'' +
                ", monto=" + monto +
                ", transacciones=" + transacciones +
                '}';
    }
}
