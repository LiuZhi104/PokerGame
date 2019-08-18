import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckCard {

    public static String getWinner(Player player1, Player player2) {
        int palyer1Value = getMaxValue(player1);
        int palyer2Value = getMaxValue(player2);
        if (palyer1Value > palyer2Value) {
            return player1.getName();
        }
        return player2.getName();
    }

    public  static  int transformValue(String cardVlue) {
        if (cardVlue == "T") {
            return 10;
        }
        if (cardVlue == "J") {
            return 11;
        }
        if (cardVlue == "Q") {
            return 12;
        }
        if (cardVlue == "K") {
            return 13;
        }
        if (cardVlue == "A") {
            return 14;
        }
        return Integer.valueOf(cardVlue);
    }

    public static int getMaxValue(Player player) {
        List<Integer> cardGroupValues = new ArrayList<>();
        for (int i = 0; i < player.getCardGroup().size(); i++) {
            cardGroupValues.add(transformValue(player.getCardGroup().get(i).getCardValue()));
        }
        Integer[] cardValues = cardGroupValues.toArray(new Integer[cardGroupValues.size()]);
        Arrays.sort(cardValues);
        return cardValues[cardValues.length - 1];

    }
}
