package jaxrs;

import impl.RepositoryManagerImpl;
import interfacedef.RepositoryManager;
import model.Album;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


@Path("album")
public class AlbumRest {

    private static final RepositoryManager manager = RepositoryManagerImpl.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Album> getAlbums() {
        return manager.getAlbums();
    }


    @GET
    @Path("search")
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Album> getAlbum(@QueryParam("albumID") int albumID, @QueryParam("isrc") String isrc,
                                     @QueryParam("title") String title, @QueryParam("year") int year,
                                     @QueryParam("artist") String artist) {
        ArrayList<Album> albumArrayList = new ArrayList<>();
        Album filterAlbum = new Album();
        if (albumID != 0) {
            filterAlbum.setAlbumID(albumID);
        }
        if (isrc != null && !isrc.isEmpty()) {
            filterAlbum.setISRC(isrc);
        }
        if (title != null && !title.isEmpty()) {
            filterAlbum.setTitle(title);
        }
        if (year != 0) {
            filterAlbum.setYear(year);
        }
        if (artist != null && !artist.isEmpty()) {
            filterAlbum.setArtist(artist);
        }
        return manager.filterAlbum(filterAlbum);
    }


    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addAlbum(Album album) {
        JSONObject response = new JSONObject();
        try {
            int albumId = manager.addAlbum(album);
            if (albumId == -1) {
                response.put("status", 500);
            } else {
                response.put("status", 200);
                response.put("albumId", albumId);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                response.put("status", 500);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            return response.toString();
        }
        return response.toString();
    }


    @PUT
    @Path("update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateAlbum(Album album) {
        JSONObject response = new JSONObject();
        try {
            boolean flag = manager.updateAlbum(album);
            if (flag) {
                response.put("status", 200);
            } else {
                response.put("status", 500);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                response.put("status", 500);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            return response.toString();
        }
        return response.toString();
    }


    @DELETE
    @Path("delete")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeAlbum(@QueryParam("albumID") int albumID) {
        JSONObject response = new JSONObject();
        try {
            boolean flag = manager.removeAlbum(albumID);
            if (flag) {
                response.put("status", 200);
            } else {
                response.put("status", 500);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                response.put("status", 500);
            } catch (JSONException jsonException) {
                jsonException.printStackTrace();
            }
            return response.toString();
        }
        return response.toString();
    }
}
