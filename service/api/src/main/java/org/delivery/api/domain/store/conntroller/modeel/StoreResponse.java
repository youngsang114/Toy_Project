package org.delivery.api.domain.store.conntroller.modeel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreResponse {
    private Long id;
    private String name;
    private String address;
    private StoreStatus status;
    private StoreCategory category;
    private double star;
    private String thumbnailUrl;
    private BigDecimal minimumAmount;
    private BigDecimal minimumDeliveryAmount;
    private String phoneNumber;

}
