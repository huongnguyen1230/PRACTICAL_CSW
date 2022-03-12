package com.example.serverjaxws.resource;

import com.example.serverjaxws.entity.Employee;
import com.example.serverjaxws.model.EmployeeModel;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.ArrayList;

@Path("/employees")
public class EmployeeResource {

    private EmployeeModel employeeModel;

    public EmployeeResource() {
        this.employeeModel = new EmployeeModel();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        try {
            return Response.status(Response.Status.OK).entity(employeeModel.findAll()).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.OK).entity(new ArrayList<>()).build();
        }
    }

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") int id) {
        try {
            return Response.status(Response.Status.OK).entity(employeeModel.findById(id)).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST()
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Employee employee) {
        try {
            Employee savedEmployee = employeeModel.save(employee);
            return Response.status(Response.Status.CREATED).entity(savedEmployee).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, Employee employee) {
        try {
            Employee foundEmployee = employeeModel.findById(id);
            if (foundEmployee == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            Employee updatedEmployee = employeeModel.update(id, employee);
            return Response.status(Response.Status.OK).entity(updatedEmployee).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
