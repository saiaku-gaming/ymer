package com.valhallagame.ymer.message.party;

import com.valhallagame.common.validation.CheckLowercase;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class PromotePersonToLeaderParameter {
  @NotNull
  @CheckLowercase
  String promoterUsername;

  @NotNull
  @CheckLowercase
  String promoteeUsername;
}
