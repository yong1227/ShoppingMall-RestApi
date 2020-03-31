package com.simple.shop.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Transient;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Getter
@Setter
@ToString(exclude = {"tokens","baskets"})
@NoArgsConstructor
@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String name;
	private String birth;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@JsonProperty(access = Access.WRITE_ONLY)
	@Transient
	private String passwordConfirm;
	private String gender;
	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	private Date createdAt;
	@JsonInclude(Include.NON_EMPTY)
	@Transient
	private Boolean isBasket;
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Token> tokens = new ArrayList<Token>();
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Basket> baskets = new ArrayList<Basket>();
	
//	@Transient
//	@OneToOne(mappedBy = "user")
//	@JsonIgnore
//	private Product product;
	
}
