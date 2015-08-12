
package com.Team11.JavaCA.Service;

import com.Team11.JavaCA.Model.Game;
import com.Team11.JavaCA.Model.User;
import java.util.Collection;



public interface GameService extends  BaseService {

    public boolean addUser(User user);
    public boolean isValid(User user);
    public Collection<User> getAllUsers();
    public Game createGame(User user);
    
    public Game getGame(String id);
}
