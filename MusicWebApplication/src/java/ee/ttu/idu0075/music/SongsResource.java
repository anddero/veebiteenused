/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.music;

import ee.ttu.idu0075._2018.ws._164718.music.SongType;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Karl-Andero Mere
 */
@Path("songs")
public class SongsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SongsResource
     */
    public SongsResource() {
    }

    /**
     * Retrieves representation of an instance of ee.ttu.idu0075.music.SongsResource
     * @return an instance of ee.ttu.idu0075._2018.ws._164718.music.SongType
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SongType getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SongsResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(SongType content) {
    }
}
