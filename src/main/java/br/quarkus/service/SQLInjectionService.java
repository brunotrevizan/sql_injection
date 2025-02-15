package br.quarkus.service;

import br.quarkus.model.User;
import br.quarkus.repository.SQLInjectionRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class SQLInjectionService {

    @Inject
    private SQLInjectionRepository sqLInjectionRepository;

    public boolean byPassAuth(User user) {
        return sqLInjectionRepository.byPassAuth(user);
    }

    public List<Object[]> getNomePessoa(String parameter) {
        return sqLInjectionRepository.getNomePessoa(parameter);
    }
}
