/**
 * Created by Administrator on 2016/10/8 0008.
 */
public class ShufflePoker {
    public static  Integer[] numbers = {2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,
                            12,12,12,12,13,13,13,13,14,14,14,14};

    public static String[] colours = {"Diamond","Club","Heart","Spade"};

    public static PokerOne[] firstKind(PokerOne[] pokerOnes){        ///第一种排序方法 对半分之后插插插插插

        PokerOne[] q =new PokerOne[52];

        for (int i = 0; i <q.length ; i++) {
            q[i]=new PokerOne();
        }

        for (int i = 0,j =0; j <q.length ; i++,j+=2) {
            pokerOnes[i].getPokers(q[j]);
        }
        for (int i = 26, j = 1; i < pokerOnes.length ; i++,j+=2) {
            pokerOnes[i].getPokers(q[j]);

        }

        for (int i = 0; i <q.length ; i++) {
            q[i].getPokers(pokerOnes[i]);

        }
        return pokerOnes;
    }

    public static PokerOne[] secondKind(PokerOne[] pokerOnes){


        int random =0;

        for (int i = pokerOnes.length-1; i>0 ; i--) {
            random=(int)(0+Math.random()*(i-0+1));          //抽出一个0到i的位置
            pokerOnes[i].changePokers(pokerOnes[random]);  //换换换换
        }

        return pokerOnes;

    }

    public static PokerOne[] thirdKind(PokerOne[] pokerOnes){        //算法思想 ：让最后一张与前面任意一张牌进行交换，交换52*100次


        int random =0;
        for (int i = 0, max = pokerOnes.length-1; i <(pokerOnes.length)*100 ; i++) {
            random=(int)(0+Math.random()*(max-0+1));          //抽出一个0到i的位置
            pokerOnes[max].changePokers(pokerOnes[random]);
        }
        return pokerOnes;

    }
}
