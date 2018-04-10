package models.users;

import com.fasterxml.jackson.annotation.JsonView;
import com.payAm.core.model.BaseEntity;
import constants.users.TokenActionConstants;
import dtos.users.TokenActionView;
//import enumerations.security.ActionTokenType;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Payam Mostafaei
 * Creation time: 2017/May/07 - 3:41 PM
 */

@Entity
@Table(name="token_action")
@EntityListeners({AuditingEntityListener.class})
public class TokenActionEntity extends BaseEntity implements TokenActionConstants {
    private static final long serialVersionUID = 1L;

    @JsonView(TokenActionView.class)
    @Column(unique = true)
    public String token;

    @JsonView(TokenActionView.class)
    @ManyToOne
    @JoinColumn(name="user_id")
    public UserEntity user;

//    @JsonView(TokenActionView.class)
//    @NotNull(message = ERROR_TYPE_REQUIRED)
//    public ActionTokenType type;

    @JsonView(TokenActionView.class)
    @NotNull(message = ERROR_CREATED_REQUIRED)
    @Column(columnDefinition = "datetime")
    public Date created;

    @JsonView(TokenActionView.class)
    @NotNull(message = ERROR_EXPIRES_REQUIRED)
    @Column(columnDefinition = "datetime")
    public Date expires;

    public boolean isValid() {
        return this.expires.after(new Date());
    }
}