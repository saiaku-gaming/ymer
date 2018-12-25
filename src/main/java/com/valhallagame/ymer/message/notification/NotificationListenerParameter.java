package com.valhallagame.ymer.message.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class NotificationListenerParameter {
  @NotBlank
  String gameSessionId;

  @NotBlank
  String address;

  @NotBlank
  int port;
}
