package com.valhallagame.ymer.message.instance;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ActivateInstanceParameter {
  @NotNull
  String gameSessionId;

  @NotNull
  String address;

  @NotNull
  int port;
}
