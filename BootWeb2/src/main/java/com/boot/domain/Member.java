package com.boot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Member {
	
	@Id
	private String id;
	
	private String password;
	private String name;
	private String role;
	
	@Column(insertable=false, updatable=false, 
			columnDefinition = "timestamp default current_timestamp")
	private Date regDate;
}
