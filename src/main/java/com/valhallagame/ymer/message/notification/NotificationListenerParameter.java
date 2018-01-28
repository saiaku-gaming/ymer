package com.valhallagame.ymer.message.notification;

import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class NotificationListenerParameter {
  @NotNull
  String gameSessionId;

  @NotNull
  String address;

  @NotNull
  int port;
}
