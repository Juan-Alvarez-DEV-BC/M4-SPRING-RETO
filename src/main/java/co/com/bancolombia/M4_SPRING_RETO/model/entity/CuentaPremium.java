package co.com.bancolombia.M4_SPRING_RETO.model.entity;

import co.com.bancolombia.M4_SPRING_RETO.model.enums.OrigenTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.TipoTrxENUM;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("PREMIUM")
public class CuentaPremium extends Cuenta{

    @Override
    public BigDecimal costoTransaccion(TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx) {
        switch (tipoTrx) {
            case DEPOSITO:
                if (origenTrx == OrigenTrxENUM.OTRA_CUENTA) {
                    return BigDecimal.valueOf(1.5); // Depósito desde otra cuenta
                }
                return BigDecimal.ZERO; // Depósito en cajero o sucursal sin costo
            case COMPRA:
                if (origenTrx == OrigenTrxENUM.WEB) {
                    return BigDecimal.valueOf(5); // Compra en web con seguro
                }
                return BigDecimal.ZERO; // Compra en establecimiento físico
        }
        return BigDecimal.ZERO;
    }
}
