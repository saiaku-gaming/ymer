package com.valhallagame.ymer.message.character;

import com.valhallagame.characterserviceclient.message.EquippedItemParameter;
import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class EquipItemParameter {
    @NotBlank
    @CheckLowercase
    String characterName;

    @NotNull
    EquippedItemParameter itemToEquip;
}
