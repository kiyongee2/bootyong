package com.boot.dto;

import java.time.LocalDateTime;

import com.boot.domain.Role;

import lombok.Data;

@Data
public class MemberInput {
	
	private String userid;
	private String password;
	private String name;
	private LocalDateTime regDate;
	private Role role;
	private boolean enabled;
	
	private String newPassword;
}
