package com.example.library.model;


import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
@Builder
@EqualsAndHashCode(exclude = "borrowedSet")
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String login;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Builder.Default
	@OneToMany(targetEntity = Borrowed.class, mappedBy = "customer", orphanRemoval = true)
	private Set<Borrowed> borrowedSet = new HashSet<>();

	@Column(unique = true)
	private String email;
}
