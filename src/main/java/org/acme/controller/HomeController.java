package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.entity.Apartment;
import org.acme.entity.Home;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/home")
public class HomeController {

    private static final Logger logger = Logger.getLogger(HomeController.class);

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") long id) {
        PanacheEntityBase home = Home.findById(id);

        if (home != null) {
            return Response.ok(home).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(Home home) {
        logger.info(home.toString());

        if (home != null) {
            home.persist();

            return Response.created(URI.create("/home/" + home.id))
                    .build();
        }

        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    @Produces("application/json")
    public Response update(@PathParam("id") long id, Home home) {
        logger.info(home.toString());

        Home homeEntity = Home.findById(id);
        if (home != null && homeEntity != null) {

            homeEntity.year = home.year;
            homeEntity.address = home.address;
            homeEntity.persist();

            logger.info(home.toString());

            return Response.status(201).build();
        }

        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    @Produces("application/json")
    public Response delete(@PathParam("id") long id) {
        PanacheEntityBase home = Home.findById(id);

        if (home != null) {
            ((Home) home).apartments.forEach(ap -> ap.locatari.forEach(l -> {
                logger.info(l);
                l.delete();
            }));
            home.delete();

            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
