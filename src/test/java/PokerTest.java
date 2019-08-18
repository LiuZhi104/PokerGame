import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PokerTest {
    @Test
    public void testSinglePoker() {
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.getCardGroup().add(new Poker("H", "6"));
        player2.getCardGroup().add(new Poker("H", "9"));

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);


    }

    @Test
    public void testTwoPoker() {
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "6"), new Poker("H", "A")));
        player2.setCardGroup(Arrays.asList(new Poker("H", "9"), new Poker("C", "8")));

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer1", winner);


    }

    @Test
    public void testFivePoker_and_no_pair_is_easiest() {
//        palyer1 2H 3D 5S 9C KD
//        palyer2 2H 3D 5S 9C QD
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("s", "5"), new Poker("C", "9"), new Poker("D", "K")));
        player2.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("s", "5"), new Poker("C", "9"), new Poker("D", "Q")));

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer1", winner);

    }

    @Test
    public void testFivePoker_and_no_pair_one_by_one_compare() {
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "K")));
        player2.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "8"), new Poker("D", "K")));

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer1", winner);

    }

    @Test
    public void testFivePoker_and_no_pair_all_same() {
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "K")));
        player2.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "K")));

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("Equal value", winner);

    }

    @Test
    public void testFivePoker_and_pair() {
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "A")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer1", winner);
    }

    @Test
    public void testFivePoker_and_same_pair() {
        //given
        //palyer1 3H 3D 5S 9D KD
        //palyer2 3H 3D 5S 9C AD
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "A")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }

    @Test
    public void testFivePoker_two_pair_compare_pair(){
        //given
        //palyer1 3H 3D 5S 5D KD
        //palyer2 3H 3D 5S 9C AD
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "5"), new Poker("D", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "A")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer1", winner);
    }

    @Test
    public void testFivePoker_two_pair_compare(){
        //given
        //palyer1 3H 3D 5S 5D KD
        //palyer2 3H 3D 5S 5C AD
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "5"), new Poker("D", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "5"), new Poker("D", "A")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }

}