package br.quarkus;

import br.quarkus.model.User;
import br.quarkus.service.SQLInjectionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/sql-injection")
public class SQLInjection {

    @Inject
    private SQLInjectionService sqlInjectionService;

    @POST
    @Path("bypass-auth")
    @Produces(MediaType.TEXT_PLAIN)
    public Response byPassAuth(User user) {
        return sqlInjectionService.byPassAuth(user) ? Response.ok("Usuário autenticado").build() : Response.status(401).build();
    }


    @GET
    @Path("get-nome-pessoa/{nome}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNomePessoa(@PathParam("nome") String nome) {
        return Response.ok(sqlInjectionService.getNomePessoa(nome)).build();
    }
}
