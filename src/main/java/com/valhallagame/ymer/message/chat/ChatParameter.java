package com.valhallagame.ymer.message.chat;

import com.valhallagame.common.validation.CheckLowercase;
import java.lang.String;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ChatParameter {
  @NotNull
  @CheckLowercase
  String senderUsername;

  @NotNull
  String message;
}
