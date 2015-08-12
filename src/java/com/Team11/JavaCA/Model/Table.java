package com.Team11.JavaCA.Model;

public class Table {

    public Card[] tableCard = new Card[12];
    public Card[] setGameCard= new Card[81];
    
    int setCount=0;

    int deckArray;

    Desk deck = new Desk();

    public Table() {
        deck.getCards().shuffleCards();
        for (deckArray = 0; deckArray < tableCard.length; deckArray++) {
            tableCard[deckArray] = deck.gameCards[deckArray];
            deck.gameCards[deckArray] = null;
        }
    }

    public Table showOnTable(boolean shuffle) {
        if (shuffle) {
            deck.getCards().shuffleCards();
            for (deckArray = 0; deckArray < tableCard.length; deckArray++) {
                tableCard[deckArray] = deck.gameCards[deckArray];
                deck.gameCards[deckArray] = null;
            }
        }
        return this;
    }
    
    public Card getCardOnTable(int id){
        for(Card card : tableCard){
            if(card.getId()==id){
                return card;
            }
        }
        return null;
    }
    
    public void setNewCardOnTable(Card card){
        for (int index = 0; index  < tableCard.length; index++) {
            if(tableCard[index].getId()==card.getId()){
                Card replacedCard = tableCard[index];
                int replacedIndex=  index;
                tableCard[index]= deck.gameCards[deckArray];
                deckArray++;
                //Show the Set Game Card
                setGameCard[setCount]=replacedCard;
                setCount++;
                return;
            }
        }
    }
    

}
