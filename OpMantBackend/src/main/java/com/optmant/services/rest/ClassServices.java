/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.services.rest;

import com.optmant.entity.Class;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * @author Cristian
 */
@Path("/class")
public interface ClassServices {

    @GET
    @Path("/list")
    @Produces("application/json")
    ArrayList<Class> getLists(@QueryParam("id") String id);

    @POST
    @Path("/save")
    @Consumes("application/json")
    public void setClass(Class classes);

}
