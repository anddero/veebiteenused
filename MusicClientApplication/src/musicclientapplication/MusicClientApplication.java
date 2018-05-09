/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicclientapplication;

import ee.ttu.idu0075._2018.ws._164718.music.AddSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.MusicPortType;
import ee.ttu.idu0075._2018.ws._164718.music.MusicService;
import ee.ttu.idu0075._2018.ws._164718.music.SongType;
import java.math.BigInteger;

/**
 *
 * @author Karl-Andero Mere
 */
public class MusicClientApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AddSongRequest addRequest = new AddSongRequest();
        addRequest.setToken("salajane");
        addRequest.setRequestCode(BigInteger.ONE);
        addRequest.setName("Something Original");
        addRequest.setAuthor("Someone Special");
        addRequest.setLengthSeconds(BigInteger.valueOf(1234));
        SongType song = addSong(addRequest);
        
        GetSongRequest getRequest = new GetSongRequest();
        getRequest.setId(song.getId());
        getRequest.setToken("salajane");
        
        song = getSong(getRequest);
        
        System.out.println("Song id: " + song.getId());
        System.out.println("Song name: " + song.getName());
        System.out.println("Song author: " + song.getAuthor());
        System.out.println("Song length in seconds: " + song.getLengthSeconds());
    }

    private static SongType addSong(AddSongRequest parameter) {
        MusicService service = new MusicService();
        MusicPortType port = service.getMusicPort();
        return port.addSong(parameter);
    }

    private static SongType getSong(GetSongRequest parameter) {
        MusicService service = new MusicService();
        MusicPortType port = service.getMusicPort();
        return port.getSong(parameter);
    }
    
}
