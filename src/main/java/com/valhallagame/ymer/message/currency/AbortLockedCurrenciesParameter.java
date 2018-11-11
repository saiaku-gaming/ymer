package com.valhallagame.ymer.message.currency;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AbortLockedCurrenciesParameter {
  @NotBlank
  String lockingId;
}
