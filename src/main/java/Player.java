import java.util.*;

public class Player {
    private final int HIGH_CARD =0;
    private final int PAIR =1;
    private final int TWO_PAIR =2;
    private final int THREE_OF_A_KIND =3;
    private final int THREE_C =4;
    private final int STRAIGHT =5;
    private final int FLUSH =6;
    private final int FULL_HOUSE =7;
    private final int FOUR_OF_A_KIND =8;
    private final int STARIGHT_FLUSH =9;
    private String name;
    private List<Poker> cardGroup = new ArrayList<>();
    private int cardLevel = HIGH_CARD;

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
        if(noRepetValues.size() == 4){
            this.cardLevel = PAIR;
        }else if(noRepetValues.size() == 3){
            Integer pairValue1 = TransformUtil.findPairValue(this);
            List<Integer> play1List = Arrays.asList(TransformUtil.getSortValues(this));
            if(play1List.size() == 2){
                this.cardLevel = THREE_OF_A_KIND;
            }
            this.cardLevel = TWO_PAIR;
        }else if (noRepetValues.size() == 2){

        }
    }
}
