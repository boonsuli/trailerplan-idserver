package com.trailerplan.idserver.model.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="P_ROLE")
@Data
@NoArgsConstructor
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_RUNNER = "ROLE_RUNNER";
	public static final String ROLE_ADMIN = "ROLE_ADMIN";
	public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "name")
	private String name;

	// bi-directional many-to-many association to User
	@ManyToMany(mappedBy = "roles")
	private Set<UserEntity> users;

	public RoleEntity(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final RoleEntity role = (RoleEntity) obj;
		if (!role.equals(role.name)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("Role [name=").append(name).append("]").append("[id=").append(roleId).append("]");
		return builder.toString();
	}
}