package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.entity.Apartment;
import org.acme.entity.Person;
import org.jboss.logging.Logger;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/person")
public class PersonController {
    private static final Logger logger = Logger.getLogger(ApartmentController.class);

    @GET
    @Produces("application/json")
    public Response get() {
        List<Person> people = Person.listAll();
        return Response.ok(people).build();

    }

    @GET
    @Path("{query}")
    @Produces("application/json")
    public Response get(@PathParam("query") String query) {
        List<Person> people = Person.findByQuery(query);

        return Response.ok(people).build();
    }


    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response get(@PathParam("id") long id) {
        PanacheEntityBase person = Person.findById(id);

        if (person != null) {
            return Response.ok(person).build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @POST
    @Transactional
    @Produces("application/json")
    @Consumes("application/json")
    public Response create(Person person) {
        if (person == null) {
            return Response.noContent().build();
        }

        logger.info(person);
        Apartment apartmentEntity = Apartment.findById(person.apartment.id);

        if (apartmentEntity == null) {
            return Response.noContent().build();
        }

        person.persist();
        return Response.created(URI.create("/person/" + person.id))
                .build();
    }

    @PATCH
    @Path("{id}")
    @Transactional
    @Produces("application/json")
    public Response update(@PathParam("id") long id, Person person) {
        logger.info(person.toString());

        Person personEntity = Person.findById(id);
        if (person != null && personEntity != null) {
            personEntity.name = person.name;
            personEntity.lastName = person.lastName;
            personEntity.genre = person.genre;
            personEntity.apartment = person.apartment;

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

        PanacheEntityBase personEntity = Person.findById(id);

        if (personEntity != null) {
            logger.info(personEntity.toString());

            personEntity.delete();
            return Response.ok().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
