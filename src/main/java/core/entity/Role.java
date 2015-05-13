package core.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role",
        uniqueConstraints = @UniqueConstraint(
                columnNames = { "role", "account_id" }))
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public Role(String role, Account acc) {
        this.role = role;
        account = acc;
    }

    public Role() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}