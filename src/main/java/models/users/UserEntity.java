package models.users;

//import be.objectify.deadbolt.java.models.Permission;
//import be.objectify.deadbolt.java.models.Role;
//import be.objectify.deadbolt.java.models.Subject;
import com.fasterxml.jackson.annotation.JsonView;
//import com.feth.play.module.pa.providers.password.UsernamePasswordAuthUser;
import com.payAm.core.model.BaseEntity;
import constants.users.UserConstants;
import dtos.users.UserView;
import models.assessments.OrganizationAssessmentEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import play.data.validation.Constraints;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="security_user")
@EntityListeners({AuditingEntityListener.class})
public class UserEntity extends BaseEntity implements UserConstants/*, Subject*/ {
	private static final long serialVersionUID = 1L;

	@JsonView(UserView.class)
	@ManyToOne
	@JoinColumn(name="organization_id")
	public OrganizationEntity organization;

	@JsonView(UserView.class)
	@NotNull(message = ERROR_ACTIVE_REQUIRED)
	public Boolean active;

	@JsonView(UserView.class)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date", columnDefinition = "datetime")
	@NotNull(message = ERROR_CREATED_DATE_REQUIRED)
	public Date createdDate;

	@JsonView(UserView.class)
	@Size(max = 45)
	private String province;

	@JsonView(UserView.class)
	@Size(max = 45)
	private String city;

	@JsonView(UserView.class)
	@Size(max = 250)
	private String address;

	@JsonView(UserView.class)
	@Size(max=128, message = ERROR_USERNAME_MAX_LENGTH)
	@NotNull(message = ERROR_USERNAME_REQUIRED)
	public String username;

	@JsonView(UserView.class)
	@Size(max=128, message = ERROR_EMAIL_MAX_LENGTH)
//	@Constraints.Email
	public String email;

	@JsonView(UserView.class)
	@Column(name="email_activated")
	@NotNull(message = ERROR_EMAIL_ACTIVATED_REQUIRED)
	public Boolean emailActivated;

	@JsonView(UserView.class)
	@Column(name="email_activation_code")
	@Size(max=36, message = ERROR_EMAIL_ACTIVATION_CODE_MAX_LENGTH)
	public String emailActivationCode;

	@JsonView(UserView.class)
	@Column(name="first_name")
	@NotNull(message = ERROR_FIRST_NAME_REQUIRED)
	@Size(max = 45)
	public String firstName;

	@JsonView(UserView.class)
	@Column(name="last_name")
	@NotNull(message = ERROR_LAST_NAME_REQUIRED)
	@Size(max = 45)
	public String lastName;

	@JsonView(UserView.class)
	@Column(name="mobile_activated")
	@NotNull(message = ERROR_MOBILE_ACTIVATED_REQUIRED)
	public Boolean mobileActivated;

	@JsonView(UserView.class)
	@Column(name="mobile_activation_code")
	@Size(max=6, message = ERROR_MOBILE_ACTIVATION_CODE_MAX_LENGTH)
	public String mobileActivationCode;

	@JsonView(UserView.class)
	@NotNull(message = ERROR_PASSWORD_REQUIRED)
	@Size(max=45, message = ERROR_PASSWORD_MAX_LENGTH)
	public String password;

	@JsonView(UserView.class)
	@Column(name="phone_number")
	@Size(max=45, message = ERROR_PHONE_NUMBER_MAX_LENGTH)
	public String phoneNumber;

	@JsonView
	//bi-directional many-to-many association to RoleEntity
	@ManyToMany
	@JoinTable(
		name="user_role"
		, joinColumns={
			@JoinColumn(name="user_id", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="role_id", nullable=false)
			}
		)
	public List<RoleEntity> roles;

	@JsonView
    @OneToMany(mappedBy = "user")
    public List<LinkedAccountEntity> linkedAccounts;

	@JsonView
    @OneToMany(mappedBy = "user")
    public List<OrganizationAssessmentEntity> organizationAssessments;

//	public String getEmail() {
//		return this.email;
//	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

//	public List<? extends Role> getRoles() {
//		return this.roles;
//	}

//    @Override
//	public String getIdentifier() {
//		return email != null && !email.isEmpty() ? email :
//				(phoneNumber != null && !phoneNumber.isEmpty() ? phoneNumber : id.toString());
//	}
//
//	@Override
//	public List<? extends Permission> getPermissions() {
//		List<PermissionEntity> permissions = new ArrayList<>();
//		getRoles().parallelStream().forEach(r -> {
//			((RoleEntity)r).permissions.parallelStream().forEach(p -> {
//				if (!permissions.contains(p))
//					permissions.add(p);
//			});
//		});
//		return permissions;
//	}

    public List<String> getProviders() {
        final List<String> providerKeys = new ArrayList<>();
        for (final LinkedAccountEntity acc : this.linkedAccounts) {
            providerKeys.add(acc.providerKey);
        }
        return providerKeys;
    }

    public String getFullName() {
		return firstName + " " + lastName;
	}

//	public static UserEntity toAuthUser(UsernamePasswordAuthUser authUser) {
//		UserEntity user = new UserEntity();
//		user.username = authUser.getEmail();
//		user.password = authUser.getPassword();
//		return null;
//	}
}