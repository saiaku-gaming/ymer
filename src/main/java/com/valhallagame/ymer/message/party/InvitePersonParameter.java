package com.valhallagame.ymer.message.party;

import com.valhallagame.common.validation.CheckLowercase;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class InvitePersonParameter {
  @NotNull
  @CheckLowercase
  String targetUsername;
}
