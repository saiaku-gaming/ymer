package com.valhallagame.ymer.message.character;

import com.valhallagame.common.validation.CheckLowercase;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class CharacterNameAndOwnerUsernameParameter {
  @NotNull
  @CheckLowercase
  String characterName;

  @NotNull
  @CheckLowercase
  String ownerUsername;
}
