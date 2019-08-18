import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class PokerTest {
    @Test
    public void testSinglePoker(){
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.getCardGroup().add(new Poker("H","6"));
        player2.getCardGroup().add(new Poker("H","9"));

        //when
        String winner =  CheckCard.getWinner(player1,player2);

        //then
        Assert.assertEquals("palyer2",winner);
    }

    @Test
    public void testTwoPoker(){
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H","6"),new Poker("H","A")));
        player2.setCardGroup(Arrays.asList(new Poker("H","9"),new Poker("C","8")));

        //when
        String winner =  CheckCard.getWinner(player1,player2);

        //then
        Assert.assertEquals("palyer1",winner);


    }
}
