package br.unitins.topicos1.resource;

import java.util.List;

import br.unitins.topicos1.model.Oculos;
import br.unitins.topicos1.repository.OculosRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/oculos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OculosResource {

    @Inject
    OculosRepository repository;

    @POST
    @Transactional
    public Oculos insert(Oculos oculos) {
        Oculos novOculos = new Oculos();
        novOculos.setReferencia(oculos.getReferencia());
        novOculos.setTamanho(oculos.getTamanho());
        novOculos.setCor(oculos.getCor());

        repository.persist(novOculos);

        return novOculos;
    }

    @GET
    public List<Oculos> todosOculos() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Oculos findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @GET
    @Path("/search/referencia/{referencia}")
    public List<Oculos> findByReferencia(@PathParam("referencia") String referencia) {
        return repository.findByReferencia(referencia);
    }

    @DELETE
    @Transactional
    @Path("/{id}")
    public boolean delete(@PathParam("id") Long id) {
        Oculos oculos = repository.findById(id);
        repository.delete(oculos);

        return true;
    }

    @PUT
    @Transactional
    @Path("/{id}")
    public Oculos insert(Oculos oculos, @PathParam("id") Long id) {
        Oculos oculosBanco = repository.findById(id);
        oculosBanco.setReferencia(oculos.getReferencia());
        oculosBanco.setTamanho(oculos.getTamanho());
        oculosBanco.setCor(oculos.getCor());

        repository.persist(oculosBanco);

        return oculosBanco;
    }
}
