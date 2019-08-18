public class CheckCard {
    public static String getWinner(Player player1, Player player2) {

        int palyer1Value = Integer.valueOf(player1.getCardGroup().get(0).getCardValue());
        int palyer2Value = Integer.valueOf(player2.getCardGroup().get(0).getCardValue());
        if(palyer1Value>palyer2Value){
            return player1.getName();
        }
        return player2.getName();
    }
}
