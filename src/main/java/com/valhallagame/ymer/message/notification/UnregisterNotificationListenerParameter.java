package com.valhallagame.ymer.message.notification;

import java.lang.String;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class UnregisterNotificationListenerParameter {
  String gameSessionId;
}
