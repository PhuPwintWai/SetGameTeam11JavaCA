package com.Team11.JavaCA.Controller;

import com.Team11.JavaCA.Model.Card;
import com.Team11.JavaCA.Model.CardOnDeck;
import com.Team11.JavaCA.Model.Game;
import com.Team11.JavaCA.Model.User;
import com.Team11.JavaCA.Service.GameService;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/game")
@Singleton
public class GameServlet extends HttpServlet {

    @Inject
    private GameService gameService;
     
    @POST
    @Path("/login")
    //@Consumes({"application/xml", "application/json"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public String login(User user) {
        //@PathParam("username") String userName
        String userName = user.getUserName();
        String password = user.getUserPassword();
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (userName != null && password != null) {
            if(gameService.isValid(user)){
                json.add("success", true);
            }else{
                json.add("success", false);
                json.add("error", "User name was not found");
            }
        }
        return json.build().toString();
    }
    
    @POST
    @Path("/register")
    //@Consumes({"application/xml", "application/json"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public String register(User user) {
        //@PathParam("username") String userName
        String userName = user.getUserName();
        String password = user.getUserPassword();
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (userName != null && password != null) {
            json.add("userName", userName);
            json.add("password", password);
            if(gameService.addUser(user)){
                json.add("success", true);
                
            }else{
                json.add("success", false);
                json.add("error", "Duplicate user name found");
            }
        }
        return json.build().toString();
    }
    
    @POST
    @Path("/createGame")
    //@Consumes({"application/xml", "application/json"})
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public String createGame(Game game) {
        //@PathParam("username") String userName
        String description = game.getDescription();
        int maximumPlayer = game.getMaximumPlayer();
        JsonObjectBuilder json = Json.createObjectBuilder();
        if (description != null && maximumPlayer >=0) {
            Game newGame = gameService.createGame(new User(game.getCreator()));
            newGame.setDescription(description);
            newGame.setMaximumPlayer(maximumPlayer);
            newGame.toJson().toString();
            json.add("success", true);
        }
        return json.build().toString();
    }

    @GET
    @Produces("application/json")
    @Path("/getExistingGames")
    public String  getExistingGames() {
        return gameService.toJson().toString();
    }

//    @GET
//    @Produces("application/json")
//    @Path("/createNewGame")
//    public String createNewGame() {
//        Game game = gameService.createGame(new User("demo"));
//        return game.toJson().toString();
//    }

    @GET
    @Produces("application/json")
    @Path("/getAllCards")
    public String showAllCards() {
        CardOnDeck cardOnDeck = new CardOnDeck();
        cardOnDeck.getCards();
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        int i = 0;
        for (Card card : cardOnDeck.gameCards) {
            cards.add(card.toJson());
            i++;
        }
        results.add("cards", cards.build());
        return results.build().toString();
    }

    @GET
    @Produces("application/json")
    @Path("/getShuffleCards")
    public String showShuffleCards() {
        CardOnDeck cardOnDeck = new CardOnDeck();
        cardOnDeck.getCards().shuffleCards();
        JsonObjectBuilder results = Json.createObjectBuilder();
        JsonArrayBuilder cards = Json.createArrayBuilder();
        int i = 0;
        for (Card card : cardOnDeck.gameCards) {
            cards.add(card.toJson());
            i++;
        }

        results.add("cards", cards.build());
        return results.build().toString();
    }

//    @GET
//    @Produces("application/json")
//    @Path("/getAllCards")
//    public JsonObject getCards() {
//        //Note: this println is checking input data type (string or invalid int)
//        System.out.println(">>> cid = " + 1);
//        Card card = new Card("a", "b", "c", 1);
//        card.setImageUrl("d");
//        if (null == card) {
//            throw new WebApplicationException(Response.Status.NOT_FOUND);
//        }
//        return (card.toJson());
//
//    }
    @GET
    @Produces(value = "application/json")
    @Path(value = "/openExistingGame")
    public String  doOpenExistingGame(@Context UriInfo info) {
        String id = info.getQueryParameters().getFirst("id");
        if (id != null) {
            gameService.getGame(id).toJson().toString();
        }
        return null;
    }
}
