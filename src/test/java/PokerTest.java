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
    @Test
    public void testFivePoker_tree_of_kind_compare_two_pair() {
        //given
//        palyer1 3H 3D 9S 9C KD
//        palyer2 3H 3D 5S 9C 3D
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "9"), new Poker("C", "9"), new Poker("D", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "3")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_straight_compare_three_of_kind() {

//        palyer1 3H 3D 5S 9C 3D
//        palyer2 3H 4D 5S 6C 7D
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "3")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "4"),
                new Poker("S", "5"), new Poker("C", "6"), new Poker("D", "7")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_flush_compare_straight() {
//    palyer1 3H 4D 5S 6C 7D
//    palyer2 2H 3H 5H 9H KH
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "4"),
                new Poker("S", "5"), new Poker("C", "6"), new Poker("D", "7")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("H", "3"),
                new Poker("H", "5"), new Poker("H", "9"), new Poker("H", "K")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_same_three_of_kind_compare() {
        //given
//        palyer1 4H 4D 4S 9C KD
//        palyer2 3H 3D 5S 9C 3D
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "4"), new Poker("D", "4"),
                new Poker("S", "4"), new Poker("C", "9"), new Poker("D", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "9"), new Poker("D", "3")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer1", winner);
    }
    @Test
    public void testFivePoker_same_straight() {
//        palyer1 3H 4D 5S 6C 7D
//        palyer2 4H 5D 6S 7H 8C
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "4"),
                new Poker("S", "5"), new Poker("C", "6"), new Poker("D", "7")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "4"), new Poker("D", "5"),
                new Poker("S", "6"), new Poker("H", "7"), new Poker("C", "8")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_same_straight_and_diff_color() {
//        palyer1 3H 4D 5S 6C 7D
//        palyer2 3S 4H 5D 6H 7C
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "4"),
                new Poker("S", "5"), new Poker("C", "6"), new Poker("D", "7")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("S", "3"), new Poker("H", "4"),
                new Poker("D", "5"), new Poker("H", "6"), new Poker("C", "7")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("Equal value", winner);
    }
    @Test
    public void testFivePoker_both_flush_compare() {
//        palyer1 2H 3H 5H 9H KH
//        palyer2 2D 3D 5D 9D AD
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("H", "3"),
                new Poker("H", "5"), new Poker("H", "9"), new Poker("H", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("D", "2"), new Poker("D", "3"),
                new Poker("D", "5"), new Poker("D", "9"), new Poker("D", "A")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_flush_compare_full_hose() {
//        palyer1 2H 3H 5H 9H KH
//        palyer2 3H 3D 5S 5C 3Sｕ
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "2"), new Poker("H", "3"),
                new Poker("H", "5"), new Poker("H", "9"), new Poker("H", "K")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "5"), new Poker("C", "5"), new Poker("S", "3")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }

    @Test
    public void testFivePoker_both_full_house() {
//        palyer1 4H 4D 5S 5C 4D
//        palyer2 7H 7D 3S 7C 3D
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "4"), new Poker("D", "4"),
                new Poker("S", "5"), new Poker("C", "5"), new Poker("D", "4")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "7"), new Poker("D", "7"),
                new Poker("S", "3"), new Poker("C", "7"), new Poker("D", "3")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_full_house_compare_four_of_a_kind() {
//        palyer1 4H 4D 5S 5C 4D
//        palyer2 3H 3D 3S 6C 3S
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "4"), new Poker("D", "4"),
                new Poker("S", "5"), new Poker("C", "5"), new Poker("D", "4")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "3"), new Poker("C", "6"), new Poker("S", "3")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }
    @Test
    public void testFivePoker_both_four_of_a_kind() {
//        palyer1 3H 3D 3S 5C 3C
//        palyer2 4H 4D 4S 2C 4C
        //given
        Player player1 = new Player("palyer1");
        Player player2 = new Player("palyer2");
        player1.setCardGroup(Arrays.asList(new Poker("H", "3"), new Poker("D", "3"),
                new Poker("S", "3"), new Poker("C", "5"), new Poker("C", "3")));
        player1.computeCardLevel();
        player2.setCardGroup(Arrays.asList(new Poker("H", "4"), new Poker("D", "4"),
                new Poker("S", "4"), new Poker("C", "2"), new Poker("C", "4")));
        player2.computeCardLevel();

        //when
        String winner = CheckCard.getWinner(player1, player2);

        //then
        Assert.assertEquals("palyer2", winner);
    }

}