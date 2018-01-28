package com.valhallagame.ymer.message.friend;

import com.valhallagame.common.validation.CheckLowercase;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AcceptCharacterParameter {
  @NotNull
  @CheckLowercase
  String accepterUsername;

  @NotNull
  @CheckLowercase
  String accepteeCharacterName;
}
