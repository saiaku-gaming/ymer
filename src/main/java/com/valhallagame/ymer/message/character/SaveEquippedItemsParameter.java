package com.valhallagame.ymer.message.character;

import com.valhallagame.characterserviceclient.message.EquippedItemParameter;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SaveEquippedItemsParameter {
  @NotNull
  String characterName;

  @NotNull
  List<EquippedItemParameter> equippedItems;
}
