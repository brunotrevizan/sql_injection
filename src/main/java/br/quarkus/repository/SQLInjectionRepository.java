package br.quarkus.repository;

import br.quarkus.model.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

@Singleton
public class SQLInjectionRepository {

    @Inject
    private EntityManager entityManager;

    public boolean byPassAuth(User user) {
        try {
            Object userFromDatabase = entityManager.
                    createNativeQuery("SELECT * from geral.user where username = '" + user.user() + "' and password = '" + user.password()+"';")
                    .getSingleResult();
            return userFromDatabase != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<Object[]> getNomePessoa(String parameter) {
        return (List<Object[]>) entityManager.
                createNativeQuery("SELECT nome, nome_pai from senai.pessoa WHERE nome like '" + parameter + "'")
                .getResultList();
    }
}
