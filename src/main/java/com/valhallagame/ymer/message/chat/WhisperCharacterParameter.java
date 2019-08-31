package com.valhallagame.ymer.message.chat;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class WhisperCharacterParameter {
  @NotNull
  String message;

  @NotNull
  String targetDisplayCharacterName;
}
