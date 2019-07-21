package com.valhallagame.ymer.message.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class GetDungeonConnectionParameter {
  @NotNull
  String instanceId;

  @NotNull
  String version;
}
