package com.nisum.exam.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class User extends BaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
    @Id
	private String id;
	private String name;
	private String email;
	private String password;
	private String token;
	
	@UpdateTimestamp
	private LocalDateTime lastLogin;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Phone> phones;
}
