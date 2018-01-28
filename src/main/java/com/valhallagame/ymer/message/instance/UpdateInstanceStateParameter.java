package com.valhallagame.ymer.message.instance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UpdateInstanceStateParameter {
  String gameSessionId;

  String state;
}
