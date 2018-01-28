package com.valhallagame.ymer.message.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class AddLocalInstanceParameter {
  String gameSessionId;

  String address;

  Integer port;

  String mapName;

  String state;

  String version;
}
