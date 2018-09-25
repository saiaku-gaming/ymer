package com.valhallagame.ymer.message.friend;

import com.valhallagame.common.ExposedNameInYmer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class DeclineCharacterParameter {
  @NotNull
  @ExposedNameInYmer("characterName")
  String characterName;
}
