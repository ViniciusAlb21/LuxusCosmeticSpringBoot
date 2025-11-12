package com.web.LuxusCosmetic.domain.ids;

import java.io.Serializable;
import lombok.*;
import jakarta.persistence.*;

@Embeddable @Getter @Setter @NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long productId;
}
