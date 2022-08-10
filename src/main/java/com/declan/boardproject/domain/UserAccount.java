package com.declan.boardproject.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "userId"),
        @Index(columnList = "email", unique = true),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class UserAccount extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 50)
    private String userId;

    @Setter
    @Column(nullable = false)
    private String userPassword;

    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String nickname;

    protected UserAccount() {}

    private UserAccount(Long id, String userId, String userPassword, String email, String nickname) {
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.email = email;
        this.nickname = nickname;
    }

    public static UserAccount of(Long id, String userId, String userPassword, String email, String nickname) {
        return new UserAccount(id, userId, userPassword, email, nickname);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount that = (UserAccount) o;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
