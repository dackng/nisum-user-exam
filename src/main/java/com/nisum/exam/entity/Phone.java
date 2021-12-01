package com.nisum.exam.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class Phone extends BaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
    
	@Id
	private String id;
	
    @ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
    private String number;
	private String cityCode;
	private String countryCode;
}
