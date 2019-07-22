package com.valhallagame.ymer.message.character;

import com.valhallagame.characterserviceclient.message.EquippedItemParameter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SaveEquippedItemsParameter {
    @NotNull
  String characterName;

  @NotNull
  List<EquippedItemParameter> equippedItems;
}
