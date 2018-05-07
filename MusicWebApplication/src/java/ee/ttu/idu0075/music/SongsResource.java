/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.music;

import ee.ttu.idu0075._2018.ws._164718.music.AddSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongListRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongListResponse;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.SongType;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
    
    @GET
    @Produces("application/json")
    public GetSongListResponse getSongList(@QueryParam("token") String token) {
        MusicWebService ws = new MusicWebService();
        GetSongListRequest request = new GetSongListRequest();
        request.setToken(token);
        return ws.getSongList(request);
    }
    
    @GET
    @Path("{id : \\d+}") //supports digits only
    @Produces("application/json")
    public SongType getSong(@PathParam("id") String id,
            @QueryParam("token") String token) {
        MusicWebService ws = new MusicWebService();
        GetSongRequest request = new GetSongRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getSong(request);
    }
    
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public SongType addSong(SongType content, 
                                @QueryParam("token") String token,
                                @QueryParam("requestCode") String code) {
        MusicWebService ws = new MusicWebService();
        AddSongRequest request = new AddSongRequest();
        request.setRequestCode(BigInteger.valueOf(Integer.parseInt(code)));
        request.setName(content.getName());
        request.setToken(token);
        request.setAuthor(content.getAuthor());
        request.setLengthSeconds(content.getLengthSeconds());
        return ws.addSong(request);
    }

    /**
     * Retrieves representation of an instance of ee.ttu.idu0075.music.SongsResource
     * @return an instance of ee.ttu.idu0075._2018.ws._164718.music.SongType
     */
//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public SongType getJson() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }

    /**
     * PUT method for updating or creating an instance of SongsResource
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_JSON)
//    public void putJson(SongType content) {
//    }
}
