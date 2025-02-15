package br.quarkus.repository;

import br.quarkus.model.User;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

@Singleton
public class AntiSQLInjectionRepository {

    @Inject
    private EntityManager entityManager;

    /**
     * Separa a lógica da consulta SQL (o "código") dos dados fornecidos pelo usuário.
     * Os dados fornecidos (como valores dos parâmetros) são tratados como dados, não como parte do código SQL
     * Se o atacante mandar um ' OR 1=1 -- por exemplo, será tratado como String dentro de aspas
     * O Banco vai saber que são valores e não parte da consulta/comando SQL
     */
    public boolean byPassAuth(User user) {
        try {
            Object userFromDatabase = entityManager.
                    createNativeQuery("SELECT * from geral.user where username = :username and password = :password ;")
                    .setParameter("username", user.user())
                    .setParameter("password", user.password())
                    .getSingleResult();
            return userFromDatabase != null;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<Object[]> getNomePessoa(String parameter) {
        return (List<Object[]>) entityManager.
                createNativeQuery("SELECT nome, nome_pai from senai.pessoa WHERE nome like :nome")
                .setParameter("nome", parameter)
                .getResultList();
    }
}
