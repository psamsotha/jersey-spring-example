package com.underdog.jersey.spring.example.resource;

import com.underdog.jersey.spring.example.domain.Customer;
import com.underdog.jersey.spring.example.service.CustomerService;
import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Paul Samsotha
 */
@Path("customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @GET
    public Response getAllCustomers(@QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName) {

        List<Customer> customers;
        if (firstName != null && lastName != null) {
            customers = customerService.findByFirstAndLastName(firstName, lastName);
        } else {
            customers = customerService.findAll();
        }
        return Response.ok(new GenericEntity<List<Customer>>(customers) {
        }).build();
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") long id) {
        Customer customer = customerService.findOne(id);
        if (customer == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        return Response.ok(customer).build();
    }

    @POST
    public Response createCustomer(Customer customer, @Context UriInfo uriInfo) {
        customer = customerService.save(customer);
        long id = customer.getId();

        URI createdUri = uriInfo.getAbsolutePathBuilder().path(Long.toString(id)).build();
        return Response.created(createdUri).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") long id, Customer customer) {
        Customer inDb = customerService.findOne(id);
        if (inDb == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        inDb.setFirstName(customer.getFirstName());
        inDb.setLastName(customer.getLastName());
        customerService.update(inDb);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") long id) {
        Customer inDb = customerService.findOne(id);
        if (inDb == null) {
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        customerService.delete(inDb);
        return Response.ok().build();
    }
}
