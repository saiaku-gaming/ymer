package com.valhallagame.ymer.message.currency;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.currencyserviceclient.model.CurrencyType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SubtractCurrencyParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  CurrencyType currencyType;

  @Min(0)
  int amount;
}
