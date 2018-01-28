package com.valhallagame.ymer.message.wardrobe;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.wardrobeserviceclient.message.WardrobeItem;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AddWardrobeItemParameter {
  @NotNull
  @CheckLowercase
  String characterName;

  @NotNull
  WardrobeItem name;
}
