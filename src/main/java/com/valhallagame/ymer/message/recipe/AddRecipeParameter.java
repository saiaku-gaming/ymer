package com.valhallagame.ymer.message.recipe;

import com.valhallagame.common.validation.CheckLowercase;
import com.valhallagame.wardrobeserviceclient.message.WardrobeItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AddRecipeParameter {
  @NotBlank
  @CheckLowercase
  String characterName;

  @NotNull
  WardrobeItem recipe;
}
