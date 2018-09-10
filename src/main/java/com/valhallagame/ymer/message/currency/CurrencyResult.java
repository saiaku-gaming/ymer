package com.valhallagame.ymer.message.currency;

import com.valhallagame.currencyserviceclient.model.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CurrencyResult {
    Long id;

    String characterName;

    CurrencyType type;

    int amount;
}
