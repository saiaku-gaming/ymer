package com.valhallagame.ymer.message.trait;

import com.valhallagame.traitserviceclient.message.AttributeType;
import com.valhallagame.traitserviceclient.message.TraitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class SkilledTraitData {
  TraitType trait;

  AttributeType selectedAttribute;

  Integer position;

    Boolean purchased;

  Integer specialization;

  Integer specializationPosition;
}
