package com.valhallagame.ymer.message.currency;

import com.valhallagame.currencyserviceclient.model.CurrencyType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Currency {
  @NotNull
  CurrencyType currencyType;

  @Min(0)
  int amount;
}
