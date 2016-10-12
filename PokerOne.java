/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class PokerOne {
    private Integer number ;
    private String colour;

    PokerOne(){

    }

    public void setNumber(Integer number) {

        this.number = number;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public Integer getNumber() {
        return number;
    }

    public String getColour() {
        return colour;
    }

    public void changePockers(PokerOne pokerOne){
        PokerOne pokerOne1 = new PokerOne();

        pokerOne1.setNumber(this.getNumber());
        pokerOne1.setColour(this.getColour());
        this.setNumber(pokerOne.getNumber());
        this.setColour(pokerOne.getColour());
        pokerOne.setNumber(pokerOne1.getNumber());
        pokerOne.setColour(pokerOne1.getColour());
    }

    public void getPockers(PokerOne pokerOne){
        pokerOne.setNumber(this.getNumber());
        pokerOne.setColour(this.getColour());
    }

}
