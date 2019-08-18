import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private List<Poker> cardGroup = new ArrayList<>();

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
}