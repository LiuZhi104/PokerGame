import org.junit.Assert;
import org.junit.Test;


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
}
