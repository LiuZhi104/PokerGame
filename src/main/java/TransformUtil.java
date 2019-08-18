import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransformUtil {
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

    public static Integer[] getSortValues(Player player) {
        List<Integer> cardGroupValues = new ArrayList<>();
        for (int i = 0; i < player.getCardGroup().size(); i++) {
            cardGroupValues.add(transformValue(player.getCardGroup().get(i).getCardValue()));
        }
        Integer[] cardValues = cardGroupValues.toArray(new Integer[cardGroupValues.size()]);
        Arrays.sort(cardValues);
        return cardValues;
    }

    public static Integer[] playerGetSortValues(List<Poker> cardGroup) {
        List<Integer> cardGroupValues = new ArrayList<>();
        for (int i = 0; i < cardGroup.size(); i++) {
            cardGroupValues.add(transformValue(cardGroup.get(i).getCardValue()));
        }
        Integer[] cardValues = cardGroupValues.toArray(new Integer[cardGroupValues.size()]);
        Arrays.sort(cardValues);
        return cardValues;
    }


    public static String compareCardValues( Integer[] palyer1Values, Integer[] palyer2Values){
        for(int i = palyer1Values.length-1 ;i >= 0 ;i--){
            if(palyer1Values[i]>palyer2Values[i]){
                return "palyer1";
            }else if(palyer1Values[i] < palyer2Values[i]){
                return "palyer2";
            }
        }
        return "Equal value";
    }

}
