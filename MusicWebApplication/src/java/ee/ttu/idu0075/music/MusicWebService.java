/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ee.ttu.idu0075.music;

import ee.ttu.idu0075._2018.ws._164718.music.AddDiskRequest;
import ee.ttu.idu0075._2018.ws._164718.music.AddDiskSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.AddSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.DiskSongListType;
import ee.ttu.idu0075._2018.ws._164718.music.DiskSongType;
import ee.ttu.idu0075._2018.ws._164718.music.DiskType;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskListRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskListResponse;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetDiskSongListRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongListRequest;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongListResponse;
import ee.ttu.idu0075._2018.ws._164718.music.GetSongRequest;
import ee.ttu.idu0075._2018.ws._164718.music.SongType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.jws.WebService;

/**
 *
 * @author Karl-Andero Mere
 */
@WebService(serviceName = "MusicService",
        portName = "MusicPort",
        endpointInterface = "ee.ttu.idu0075._2018.ws._164718.music.MusicPortType",
        targetNamespace = "http://www.ttu.ee/idu0075/2018/ws/164718/music",
        wsdlLocation = "WEB-INF/wsdl/MusicWebService/MusicService.wsdl")
public class MusicWebService {
    private static final String PASSWORD = "salajane";
    private static final List<SongType> SONGS = new ArrayList<>();
    private static final List<DiskType> DISKS = new ArrayList<>();
    private static final HashMap<BigInteger, SongType> ADD_SONG_REQUESTS = new HashMap<>();
    private static final HashMap<BigInteger, DiskType> ADD_DISK_REQUESTS = new HashMap<>();
    private static final HashMap<BigInteger, DiskSongType> ADD_DISK_SONG_REQUESTS = new HashMap<>();
    private static BigInteger autoId = BigInteger.ZERO;

    public SongType getSong(GetSongRequest getSongRequest) {
        if (!PASSWORD.equalsIgnoreCase(getSongRequest.getToken())) {
            throw new RuntimeException("Invalid token"); // token null or invalid
        }
        
        Optional<SongType> optionalSong =  SONGS.stream()
                .filter(s -> getSongRequest.getId().equals(s.getId()))
                .findAny();

        if (!optionalSong.isPresent()) {
            throw new RuntimeException("Invalid song id"); // id null or invalid
        }
        
        return optionalSong.get();
    }

    public SongType addSong(AddSongRequest addSongRequest) {
        if (!PASSWORD.equalsIgnoreCase(addSongRequest.getToken())) {
            throw new RuntimeException("Invalid token"); // token null or invalid
        }
        
        if (addSongRequest.getRequestCode() == null) {
            throw new RuntimeException("Missing requestCode"); // requestCode null
        }
        
        if (addSongRequest.getName() == null) {
            throw new RuntimeException("Missing name"); // name null
        }
        
        if (addSongRequest.getAuthor() == null) {
            throw new RuntimeException("Missing author"); // author null
        }
        
        if (addSongRequest.getLengthSeconds() == null) {
            throw new RuntimeException("Missing lengthSeconds"); // lengthSeconds null
        }
        
        if (!ADD_SONG_REQUESTS.containsKey(addSongRequest.getRequestCode())) {
            SongType newSong = new SongType();
            newSong.setId(autoId);
            newSong.setName(addSongRequest.getName());
            newSong.setAuthor(addSongRequest.getAuthor());
            newSong.setLengthSeconds(addSongRequest.getLengthSeconds());
            SONGS.add(newSong);
            ADD_SONG_REQUESTS.put(addSongRequest.getRequestCode(), newSong);
            autoId = autoId.add(BigInteger.ONE);
        }
        
        return ADD_SONG_REQUESTS.get(addSongRequest.getRequestCode());
    }

    public GetSongListResponse getSongList(GetSongListRequest getSongListRequest) {
        if (!PASSWORD.equalsIgnoreCase(getSongListRequest.getToken())) {
            throw new RuntimeException("Invalid or missing token"); // token null or invalid
        }
        
        GetSongListResponse response = new GetSongListResponse();
        response.getSong().addAll(SONGS);
        return response;
    }

    public DiskType getDisk(GetDiskRequest getDiskRequest) {
        if (!PASSWORD.equalsIgnoreCase(getDiskRequest.getToken())) {
            throw new RuntimeException("Invalid or missing token"); // token null or invalid
        }
        
        Optional<DiskType> optionalDisk = DISKS.stream()
                .filter(disk -> getDiskRequest.getId().equals(disk.getId()))
                .findAny();
        
        if (!optionalDisk.isPresent()) {
            throw new RuntimeException("Invalid or missing id"); // id null or invalid
        }
        
        return optionalDisk.get();
    }

    public DiskType addDisk(AddDiskRequest addDiskRequest) {
        if (!PASSWORD.equalsIgnoreCase(addDiskRequest.getToken())) {
            throw new RuntimeException("Missing or invalid token"); // token null or invalid
        }
        
        if (addDiskRequest.getRequestCode() == null) {
            throw new RuntimeException("Missing requestCode"); // requestCode null
        }
        
        if (addDiskRequest.getName() == null) {
            throw new RuntimeException("Missing name"); // name null
        }
        
        if (addDiskRequest.getAuthor() == null) {
            throw new RuntimeException("Missing author"); // author null
        }
        
        if (!ADD_DISK_REQUESTS.containsKey(addDiskRequest.getRequestCode())) {
            DiskType newDisk = new DiskType();
            newDisk.setId(autoId);
            newDisk.setName(addDiskRequest.getName());
            newDisk.setAuthor(addDiskRequest.getAuthor());
            newDisk.setLengthSeconds(BigInteger.ZERO);
            newDisk.setDiskSongList(new DiskSongListType());
            DISKS.add(newDisk);
            ADD_DISK_REQUESTS.put(addDiskRequest.getRequestCode(), newDisk);
            autoId = autoId.add(BigInteger.ONE);
        }
        
        return ADD_DISK_REQUESTS.get(addDiskRequest.getRequestCode());
    }

    public GetDiskListResponse getDiskList(GetDiskListRequest request) {
        if (!PASSWORD.equalsIgnoreCase(request.getToken())) {
            throw new RuntimeException("Missing or invalid token"); // token null or missing
        }
        
        GetDiskListResponse response = new GetDiskListResponse();
        response.getDisk().addAll(DISKS.stream()
                .filter(disk -> request.getMaxLengthSeconds() == null
                        || request.getMaxLengthSeconds().compareTo(disk.getLengthSeconds()) >= 0)
                .filter(disk -> request.getMinLengthSeconds() == null
                        || request.getMinLengthSeconds().compareTo(disk.getLengthSeconds()) <= 0)
                .filter(disk -> request.getMaxSongCount() == null
                        || request.getMaxSongCount().compareTo(BigInteger.valueOf(disk.getDiskSongList().getDiskSong().size())) >= 0)
                .filter(disk -> request.getMinSongCount() == null
                        || request.getMinSongCount().compareTo(BigInteger.valueOf(disk.getDiskSongList().getDiskSong().size())) <= 0)
                .collect(Collectors.toList()));
        return response;
    }

    public DiskSongListType getDiskSongList(GetDiskSongListRequest getDiskSongListRequest) {
        if (!PASSWORD.equalsIgnoreCase(getDiskSongListRequest.getToken())) {
            throw new RuntimeException("Missing or invalid token"); // token null or invalid
        }
        
        Optional<DiskType> optionalDisk =  DISKS.stream()
                .filter(disk -> getDiskSongListRequest.getDiskId().equals(disk.getId()))
                .findAny();
        
        if (!optionalDisk.isPresent()) {
            throw new RuntimeException("Missing or invalid diskId"); // diskId null or invalid
        }
        
        return optionalDisk.get().getDiskSongList();
    }

    public DiskSongType addDiskSong(AddDiskSongRequest addDiskSongRequest) {
        if (!PASSWORD.equalsIgnoreCase(addDiskSongRequest.getToken())) {
            throw new RuntimeException("Invalid token"); // token null or invalid
        }
        
        if (addDiskSongRequest.getRequestCode() == null) {
            throw new RuntimeException("Missing requestCode"); // requestCode null
        }
        
        if (addDiskSongRequest.getStartTimeSeconds() == null) {
            throw new RuntimeException("Missing startTimeSeconds"); // startTimeSeconds null
        }
        
        if (addDiskSongRequest.getFormat() == null) {
            throw new RuntimeException("Missing format"); // format null
        }
        
        if (!ADD_DISK_SONG_REQUESTS.containsKey(addDiskSongRequest.getRequestCode())) {
            Optional<DiskType> optionalDisk = DISKS.stream()
                .filter(disk -> addDiskSongRequest.getDiskId().equals(disk.getId()))
                .findAny();
        
            if (!optionalDisk.isPresent()) {
                throw new RuntimeException("Missing or invalid diskId"); // diskId null or invalid
            }
            
            Optional<SongType> optionalSong = SONGS.stream()
                    .filter(song -> addDiskSongRequest.getSongId().equals(song.getId()))
                    .findAny();
            
            if (!optionalSong.isPresent()) {
                throw new RuntimeException("Missing or invalid songId"); // songId null or invalid
            }
            
            if (!"mp3".equalsIgnoreCase(addDiskSongRequest.getFormat())
                    && !"wav".equalsIgnoreCase(addDiskSongRequest.getFormat())
                    && !"ogg".equalsIgnoreCase(addDiskSongRequest.getFormat())) {
                throw new RuntimeException("Invalid format"); // format invalid
            }
            
            DiskSongType newDiskSong = new DiskSongType();
            newDiskSong.setSong(optionalSong.get());
            newDiskSong.setFormat(addDiskSongRequest.getFormat());
            newDiskSong.setStartTimeSeconds(addDiskSongRequest.getStartTimeSeconds());
            optionalDisk.get().getDiskSongList().getDiskSong().add(newDiskSong);
            ADD_DISK_SONG_REQUESTS.put(addDiskSongRequest.getRequestCode(), newDiskSong);
            autoId = autoId.add(BigInteger.ONE);
            
            optionalDisk.get().setLengthSeconds(BigInteger.ZERO);
            optionalDisk.get().getDiskSongList().getDiskSong().forEach(song -> {
                BigInteger endTime = song.getStartTimeSeconds().add(song.getSong().getLengthSeconds());
                if (endTime.compareTo(optionalDisk.get().getLengthSeconds()) > 0) {
                    optionalDisk.get().setLengthSeconds(endTime);
                }
            });
        }
        
        return ADD_DISK_SONG_REQUESTS.get(addDiskSongRequest.getRequestCode());
    }
}
