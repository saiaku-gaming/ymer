package com.valhallagame.ymer.message.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
