import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckCard {

    public static String getWinner(Player player1, Player player2) {
        if(player1.getCardLevel()==player2.getCardLevel()){
            return checkSameLevel(player1,player2);
        }
        if(player1.getCardLevel()>player2.getCardLevel()){
            return "palyer1";
        }
        return "palyer2";
    }

    public static  String checkSameLevel(Player player1, Player player2) {
        Integer[] palyer1Values = TransformUtil.getSortValues(player1);
        Integer[] palyer2Values = TransformUtil.getSortValues(player2);
        return TransformUtil.compareCardValues(palyer1Values, palyer2Values);
    }
}
