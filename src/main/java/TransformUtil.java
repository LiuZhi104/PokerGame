import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransformUtil {
    public static int transformValue(String cardVlue) {
        if (cardVlue.equals("T")) {
            return 10;
        }
        if (cardVlue.equals("J")) {
            return 11;
        }
        if (cardVlue.equals("Q")) {
            return 12;
        }
        if (cardVlue.equals("K")) {
            return 13;
        }
        if (cardVlue.equals("A")) {
            return 14;
        }
        return Integer.valueOf(cardVlue);
    }

    public static Integer[] getSortValues(Player player) {
        List<Integer> cardGroupValues = new ArrayList<>();
        for (int i = 0; i < player.getCardGroup().size(); i++) {
            cardGroupValues.add(transformValue(player.getCardGroup().get(i).getCardValue()));
        }
        Integer[] cardValues = cardGroupValues.toArray(new Integer[cardGroupValues.size()]);
        Arrays.sort(cardValues);
        return cardValues;
    }

    public static String compareCardValues(Integer[] palyer1Values, Integer[] palyer2Values) {
        for (int i = palyer1Values.length - 1; i >= 0; i--) {
            if (palyer1Values[i] > palyer2Values[i]) {
                return "palyer1";
            }
            if (palyer1Values[i] < palyer2Values[i]) {
                return "palyer2";
            }
        }
        return "Equal value";
    }

    public static Integer findPairValue(Player player) {
        Integer[] palyer1Values = getSortValues(player);
        for (int i = 0; i < palyer1Values.length; i++) {
            for (int j = i + 1; j < palyer1Values.length; j++) {
                if (palyer1Values[i].equals(palyer1Values[j])) {
                    return palyer1Values[i];
                }
            }
        }
        return null;
    }

    public static Integer findPairValueMax(List<Integer> palyer1Values) {
        for (int i = 0; i < palyer1Values.size(); i++) {
            for (int j = i + 1; j < palyer1Values.size(); j++) {
                if (palyer1Values.get(i).equals(palyer1Values.get(j))) {
                    return palyer1Values.get(i);
                }
            }
        }
        return null;
    }

    public static List<String> getAllCardColor(Player player) {
        List<Poker> cardGroup = player.getCardGroup();
        List<String> cardColors = new ArrayList<>();
        for (Poker p : cardGroup) {
            cardColors.add(p.getColor());
        }
        return cardColors;
    }
}
