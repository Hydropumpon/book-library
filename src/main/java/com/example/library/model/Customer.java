package com.example.library.model;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
@Builder
@EqualsAndHashCode
public class Customer
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String login;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;
}
