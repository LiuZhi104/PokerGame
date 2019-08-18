import java.util.*;

public class Player {
    private String name;
    private List<Poker> cardGroup = new ArrayList<>();
    private int cardLevel = 0;

    public int getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(int cardLevel) {
        this.cardLevel = cardLevel;
    }

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Poker> getCardGroup() {
        return cardGroup;
    }

    public void setCardGroup(List<Poker> cardGroup) {
        this.cardGroup = cardGroup;
    }
    public void computeCardLevel(){
        Integer[] palyerValues = TransformUtil.playerGetSortValues(this.cardGroup);
        Set<Integer> noRepetValues = new HashSet<>(Arrays.asList(palyerValues));
        if(noRepetValues.size() < palyerValues.length){
            this.cardLevel = 2;
        }
    }
}
