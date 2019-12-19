package com.example.library.model;


import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr")
@Builder
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private UserRole userRole;

	@Column(unique = true)
	private String login;

	private String password;

	private String firstName;

	private String lastName;

	@Column(unique = true)
	private String email;
}
