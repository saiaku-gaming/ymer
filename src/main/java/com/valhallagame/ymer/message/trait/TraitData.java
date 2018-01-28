package com.valhallagame.ymer.message.trait;

import com.valhallagame.traitserviceclient.message.TraitBarItem;
import com.valhallagame.traitserviceclient.message.TraitType;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TraitData {
  @NotNull
  List<TraitType> traits;

  @NotNull
  List<TraitBarItem> traitBarItems;
}
