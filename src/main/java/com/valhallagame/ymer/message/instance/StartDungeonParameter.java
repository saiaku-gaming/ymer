package com.valhallagame.ymer.message.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class StartDungeonParameter {
  @NotBlank
  String map;

  @NotBlank
  String version;
}
