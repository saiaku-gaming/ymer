package com.valhallagame.ymer.message.currency;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.currencyserviceclient.model.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetCurrencyParameter {
    @NotBlank
    @CheckLowercase
    String characterName;

    @NotNull
    CurrencyType currencyType;
}
