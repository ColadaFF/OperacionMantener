/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.optmant.services.rest;

import com.optmant.entity.Absence;
import com.optmant.entity.Faculty;
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
@Path("/absence")
public interface AbsenceServices {

    @GET
    @Path("/list")
    @Produces("application/json")
    ArrayList<Absence> getLists(@QueryParam("id") String id, @QueryParam("witch") String witch);

    @POST
    @Path("/save")
    @Consumes("application/json")
    public void setAbsence(Absence absence);

}
