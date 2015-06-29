package core.repository.impl.jpa;

import core.entity.PasswordResetToken;
import core.repository.PasswordResetTokenRepo;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Adrian on 29/06/2015.
 */
@Repository
@Transactional
public class PasswordResetTokenRepoImpl implements PasswordResetTokenRepo {

    @PersistenceContext
    private EntityManager emgr;

    @Override
    public PasswordResetToken createPasswordResetToken(PasswordResetToken token) {
        emgr.persist(token);
        emgr.flush();
        return token;
    }

    @Override
    public PasswordResetToken findPasswordResetToken(String token) {
        Query query = emgr.createQuery("SELECT v FROM password_reset_token v WHERE v.token = :token");
        query.setParameter("token", token);
        if (!query.getResultList().isEmpty())
            return (PasswordResetToken) query.getResultList().get(0);
        else
            return null;
    }
}
