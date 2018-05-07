/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.music;

import javax.jws.WebService;

/**
 *
 * @author Karl-Andero Mere
 */
@WebService(serviceName = "MusicService", portName = "MusicPort", endpointInterface = "ee.ttu.idu0075._2018.ws._164718.music.MusicPortType", targetNamespace = "http://www.ttu.ee/idu0075/2018/ws/164718/music", wsdlLocation = "WEB-INF/wsdl/MusicWebService/MusicService.wsdl")
public class MusicWebService {

    public ee.ttu.idu0075._2018.ws._164718.music.SongType getSong(ee.ttu.idu0075._2018.ws._164718.music.GetSongRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.SongType addSong(ee.ttu.idu0075._2018.ws._164718.music.AddSongRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.GetSongListResponse getSongList(ee.ttu.idu0075._2018.ws._164718.music.GetSongListRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.DiskType getDisk(ee.ttu.idu0075._2018.ws._164718.music.GetDiskRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.DiskType addDisk(ee.ttu.idu0075._2018.ws._164718.music.AddDiskRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.GetDiskListResponse getDiskList(ee.ttu.idu0075._2018.ws._164718.music.GetDiskListRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.DiskSongListType getDiskSongList(ee.ttu.idu0075._2018.ws._164718.music.GetDiskSongListRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public ee.ttu.idu0075._2018.ws._164718.music.DiskSongType addDiskSong(ee.ttu.idu0075._2018.ws._164718.music.AddDiskSongRequest parameter) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
