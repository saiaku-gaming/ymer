package com.valhallagame.ymer.message.currency;

import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

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
