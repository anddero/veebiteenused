/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.music;

import ee.ttu.idu0075._2018.ws._164718.music.AddDiskRequest;
import ee.ttu.idu0075._2018.ws._164718.music.AddDiskSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.DiskSongListType;
import ee.ttu.idu0075._2018.ws._164718.music.DiskSongType;
import ee.ttu.idu0075._2018.ws._164718.music.DiskType;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskListRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskListResponse;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskSongListRequest;
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
@Path("disks")
public class DisksResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DisksResource
     */
    public DisksResource() {
    }
    
    /**
     * Retrieves representation of an instance of invoice.InvoicesResource
     * @param token
     * @param hasRelatedProducts
     * @param startDateString
     * @param endDateString
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public GetDiskListResponse getDiskList(@QueryParam("token") String token,
            @QueryParam("minLengthSeconds") String minLengthSeconds,
            @QueryParam("maxLengthSeconds") String maxLengthSeconds,
            @QueryParam("minSongCount") String minSongCount,
            @QueryParam("maxSongCount") String maxSongCount) {
        MusicWebService ws = new MusicWebService();
        GetDiskListRequest request = new GetDiskListRequest();
        request.setToken(token);
        System.out.println(minLengthSeconds + " " + maxLengthSeconds + " " + minSongCount + " " + maxSongCount);
        if (minLengthSeconds != null) {
            request.setMinLengthSeconds(BigInteger.valueOf(Integer.parseInt(minLengthSeconds)));
        }
        if (maxLengthSeconds != null) {
            request.setMaxLengthSeconds(BigInteger.valueOf(Integer.parseInt(maxLengthSeconds)));
        }
        if (minSongCount != null) {
            request.setMinSongCount(BigInteger.valueOf(Integer.parseInt(minSongCount)));
        }
        if (maxSongCount != null) {
            request.setMaxSongCount(BigInteger.valueOf(Integer.parseInt(maxSongCount)));
        }

        return ws.getDiskList(request);
    }

    @GET
    @Path("{id : \\d+}") //support digit only
    @Produces("application/json")
    public DiskType getDisk(@PathParam("id") String id,
            @QueryParam("token") String token) {
        MusicWebService ws = new MusicWebService();
        GetDiskRequest request = new GetDiskRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getDisk(request);
    }
    
    /**
     *
     * @param content
     * @param token
     * @return 
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public DiskType addDisk(DiskType content, 
                                @QueryParam("token") String token,
                                @QueryParam("requestCode") String code) {
        MusicWebService ws = new MusicWebService();
        AddDiskRequest request = new AddDiskRequest();
        request.setToken(token);
        request.setRequestCode(BigInteger.valueOf(Integer.parseInt(code)));
        request.setName(content.getName());
        request.setAuthor(content.getAuthor());
        
        return ws.addDisk(request);
    }
    
    @GET
    @Path("{id : \\d+}/songs") //support digit only
    @Produces("application/json")
    public DiskSongListType getDiskSongList(@PathParam("id") String id,
            @QueryParam("token") String token) {
        MusicWebService ws = new MusicWebService();
        GetDiskSongListRequest request = new GetDiskSongListRequest();
        request.setDiskId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getDiskSongList(request);
    }
    
    /**
     *
     * @param content
     * @param token
     * @param id
     * @return 
     */
    @POST
    @Path("{id : \\d+}/songs") //support digit only
    @Consumes("application/json")
    @Produces("application/json")
    public DiskSongType addDiskSong(AddDiskSongRequest content, 
                                @QueryParam("token") String token,
                                @QueryParam("requestCode") String code,
                                @PathParam("id") String id) {
        MusicWebService ws = new MusicWebService();
        AddDiskSongRequest request = content;
        request.setRequestCode(BigInteger.valueOf(Integer.parseInt(code)));
        request.setToken(token);
        request.setDiskId(BigInteger.valueOf(Integer.parseInt(id)));
        
        return ws.addDiskSong(request);    
    }

    /**
     * Retrieves representation of an instance of ee.ttu.idu0075.music.DisksResource
     * @return an instance of ee.ttu.idu0075._2018.ws._164718.music.DiskType
     */
//    @GET
//    @Produces(MediaType.APPLICATION_XML)
//    public DiskType getXml() {
//        //TODO return proper representation object
//        throw new UnsupportedOperationException();
//    }

    /**
     * PUT method for updating or creating an instance of DisksResource
     * @param content representation for the resource
     */
//    @PUT
//    @Consumes(MediaType.APPLICATION_XML)
//    public void putXml(DiskType content) {
//    }
}
