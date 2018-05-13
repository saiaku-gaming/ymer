package com.valhallagame.ymer.message.party;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class KickCharacterFromPartyParameter {
  @NotNull
  String displayCharacterName;
}
