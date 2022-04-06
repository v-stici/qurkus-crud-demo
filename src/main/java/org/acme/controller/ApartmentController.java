package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.entity.Apartment;
import org.acme.entity.Home;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/apartment")
public class ApartmentController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class);

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") long id) {
        PanacheEntityBase apartment = Apartment.findById(id);

        if (apartment != null) {
            return Response.ok(apartment).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(Apartment apartment) {
        logger.info(apartment);
        PanacheEntityBase home = Home.findById(apartment.home.id);


        if (apartment != null && home != null) {
            apartment.persist();
            logger.info(apartment);
            return Response.created(URI.create("/apartament/" + apartment.id))
                    .build();
        }

        return Response.noContent().build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    @Produces("application/json")
    public Response update(@PathParam("id") long id, Apartment apartment) {
        logger.info(apartment.toString());

        Apartment apartmentEntity = Apartment.findById(id);
        if (apartment != null && apartmentEntity != null) {
            apartmentEntity.size = apartment.size;
            apartmentEntity.rooms = apartment.rooms;
            apartmentEntity.phone = apartment.phone;
            apartmentEntity.haveInternet = apartment.haveInternet;
            apartmentEntity.home = apartment.home;

            return Response.status(201).build();
        }

        return Response.status(204).build();
    }


    @DELETE
    @Path("{id}")
    @Transactional
    @Produces("application/json")
    public Response delete(@PathParam("id") long id) {
        logger.info(id);

        PanacheEntityBase apartment = Apartment.findById(id);

        if (apartment != null) {
            logger.info(apartment.toString());

            apartment.delete();
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


}
