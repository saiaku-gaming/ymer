package com.valhallagame.ymer.message.currency;

import com.valhallagame.common.validation.CheckLowercase;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class LockCurrencyParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotEmpty
  List<com.valhallagame.currencyserviceclient.message.LockCurrencyParameter.Currency> currencies;
}
