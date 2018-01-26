package com.valhallagame.ymer.message;

import javax.validation.constraints.NotNull;

import com.valhallagame.common.validation.CheckLowercase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsernamePasswordParameter {
	@NotNull
	@CheckLowercase
	private String username;

	@NotNull
	private String password;
}
