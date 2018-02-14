package com.valhallagame.ymer.message.friend;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AcceptCharacterInviteParameter {
  @NotNull
  String displayCharacterName;
}
