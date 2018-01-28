package com.valhallagame.ymer.message.instance;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetDungeonConnectionParameter {
  @NotNull
  String gameSessionId;

  @NotNull
  String version;
}
