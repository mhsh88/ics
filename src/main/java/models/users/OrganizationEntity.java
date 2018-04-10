package models.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.users.OrganizationConstants;
import dtos.users.OrganizationView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name="organization")
@EntityListeners({AuditingEntityListener.class})
public class OrganizationEntity extends BaseEntity implements OrganizationConstants {
	private static final long serialVersionUID = 1L;

	@JsonView(OrganizationView.class)
	@NotNull(message = ERROR_NAME_REQUIRED)
	@Size(max = 1000)
	public String name;

	@JsonView(OrganizationView.class)
	@ManyToOne
	@JoinColumn(name="parent_organization_id")
	public OrganizationEntity parent;

	@JsonView
	@OneToMany(mappedBy = "parent")
	public List<OrganizationEntity> children;

	@JsonView
	//bi-directional many-to-one association to RoleEntity
	@OneToMany(mappedBy="organization")
	public List<UserEntity> users;

}