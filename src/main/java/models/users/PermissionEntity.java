package models.users;

//import be.objectify.deadbolt.java.models.Permission;
import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.users.PermissionConstants;
import dtos.users.PermissionView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="permission")
@EntityListeners({AuditingEntityListener.class})
public class PermissionEntity extends BaseEntity implements PermissionConstants /*, Permission*/ {
	private static final long serialVersionUID = 1L;

	@JsonView(PermissionView.class)
	@NotNull(message = ERROR_NAME_REQUIRED)
	@Size(max=45, message = ERROR_NAME_MAX_LENGTH)
	public String name;

	@JsonView
	//bi-directional many-to-many association to RoleEntity
	@ManyToMany(mappedBy="permissions")
	public List<RoleEntity> roles;
//
//	@Override
//	public String getValue() {
//		return name;
//	}

	@Override
	public boolean equals(Object object) {
		PermissionEntity other = (PermissionEntity) object;
		return super.equals(object) &&
				id.equals(((PermissionEntity) object).id) &&
				name.equals(((PermissionEntity) object).name);
	}
}