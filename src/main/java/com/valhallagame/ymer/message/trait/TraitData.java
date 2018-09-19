package com.valhallagame.ymer.message.trait;

import com.valhallagame.traitserviceclient.message.SkilledTraitData;
import com.valhallagame.traitserviceclient.message.TraitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class TraitData {
  @NotNull
  List<TraitType> unlockedTraits;

  @NotNull
  List<SkilledTraitData> skilledTraits;
}
