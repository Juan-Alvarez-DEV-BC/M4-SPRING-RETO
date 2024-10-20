package co.com.bancolombia.M4_SPRING_RETO.model.entity;

import co.com.bancolombia.M4_SPRING_RETO.model.enums.OrigenTrxENUM;
import co.com.bancolombia.M4_SPRING_RETO.model.enums.TipoTrxENUM;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("BASICA")
public class CuentaBasica extends Cuenta{

    @Override
    public BigDecimal costoTransaccion(TipoTrxENUM tipoTrx, OrigenTrxENUM origenTrx) {
        switch (tipoTrx) {
            case DEPOSITO:
                if (origenTrx == OrigenTrxENUM.CAJERO) {
                    return BigDecimal.valueOf(2); // Depósito en cajero automático
                } else if (origenTrx == OrigenTrxENUM.OTRA_CUENTA) {
                    return BigDecimal.valueOf(1.5); // Depósito desde otra cuenta
                }
                return BigDecimal.ZERO; // Depósito en sucursal
            case RETIRO:
                if (origenTrx == OrigenTrxENUM.CAJERO) {
                    return BigDecimal.valueOf(1); // Retiro en cajero automático
                }
                break;
            case COMPRA:
                if (origenTrx == OrigenTrxENUM.WEB) {
                    return BigDecimal.valueOf(5); // Compra en web con seguro
                }
                return BigDecimal.ZERO; // Compra en establecimiento físico
        }
        return BigDecimal.ZERO;
    }

}
