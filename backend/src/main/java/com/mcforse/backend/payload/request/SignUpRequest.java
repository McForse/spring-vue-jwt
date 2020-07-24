package com.mcforse.backend.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SignUpRequest implements Serializable {

	@JsonProperty("username")
	String username;

	@JsonProperty("password")
	String password;

}
