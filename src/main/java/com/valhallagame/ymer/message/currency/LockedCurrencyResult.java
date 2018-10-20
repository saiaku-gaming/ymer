package com.valhallagame.ymer.message.currency;

import com.valhallagame.currencyserviceclient.model.CurrencyType;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LockedCurrencyResult {
  Long id;

  String characterName;

  CurrencyType type;

  int amount;

  String lockingId;

  Instant created;
}
