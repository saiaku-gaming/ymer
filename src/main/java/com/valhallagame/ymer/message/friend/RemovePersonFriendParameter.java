package com.valhallagame.ymer.message.friend;

import com.valhallagame.common.ExposedNameInYmer;
import com.valhallagame.common.validation.CheckLowercase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class RemovePersonFriendParameter {
  @NotNull
  @CheckLowercase
  @ExposedNameInYmer("username")
  String username;
}
