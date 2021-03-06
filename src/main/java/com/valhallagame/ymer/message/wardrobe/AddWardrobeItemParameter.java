package com.valhallagame.ymer.message.wardrobe;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.wardrobeserviceclient.message.WardrobeItem;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AddWardrobeItemParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  WardrobeItem name;
}
