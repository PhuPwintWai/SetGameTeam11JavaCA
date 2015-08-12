
package com.Team11.JavaCA.Service;

import com.Team11.JavaCA.Model.Game;
import com.Team11.JavaCA.Model.User;
import java.util.Collection;
import java.util.HashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;


@ApplicationScoped
public class GameServiceImpl implements GameService{
    private final HashMap<String, Game> games = new HashMap<>();
    private final HashMap<String, User> users= new HashMap<>();
    
    public GameServiceImpl(){
        
    }
    @Override
    public boolean addUser(User user) {
        if(!users.containsKey(user.getUserName())){
            users.put(user.getUserName(), user);
            return true;
        }        
        return false;
    }

    @Override
    public boolean isValid(User user) {
        if(users.containsKey(user.getUserName())){
            return users.get(user.getUserName()).getUserPassword().equalsIgnoreCase(user.getUserPassword());
        }
        return false;
    }
 

    @Override
    public Collection<User> getAllUsers() {
        return users.values();
    }
    
    @Override
    public Game createGame(User user) {
        Game game = new Game(user.getUserName());
        games.put(game.getId(), game);
        return game;
    }

   
    @Override
    public Game getGame(String id) {
        return games.get(id);
    }
    
    @Override
    public JsonObject toJson(){
        JsonObjectBuilder gamesObject = Json.createObjectBuilder();
        JsonArrayBuilder gameList = Json.createArrayBuilder();
        JsonArrayBuilder onlineUserList = Json.createArrayBuilder();
        for(Game game : games.values()){
            gameList.add(game.toJson());
        }
        for(User user : users.values()){
            JsonObjectBuilder userObject = Json.createObjectBuilder();
            userObject.add("username", user.getUserName());
            onlineUserList.add(userObject.build());
        }
        gamesObject.add("games", gameList);
        gamesObject.add("users", onlineUserList);
        
        return gamesObject.build();
    }
    
}
