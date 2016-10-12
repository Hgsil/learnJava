/**
 * Created by Administrator on 2016/10/9 0009.
 */
public class ComparePockers {

    public static void main(String[] args) {
        PokerOne[] allPocker = new PokerOne[52];
        PokerOne[] firstPockerman = new PokerOne[7];
        PokerOne[] secondPockerman = new PokerOne[7];
        //赋初值
        for (int i = 0, j = 0; j < ShufflePoker.numbers.length; i++, j++) {
            allPocker[j] = new PokerOne();
            allPocker[j].setNumber(ShufflePoker.numbers[j]);
            allPocker[j].setColour(ShufflePoker.colours[i]);
            if (i == 3) i = -1;

        }
        //洗牌
        for (int i = 0; i < 3; i++) {
            ShufflePoker.firstKind(allPocker);
        }

        ShufflePoker.secondKind(allPocker);
        ShufflePoker.thirdKind(allPocker);
        //发2张底牌
        for (int i = 0; i < firstPockerman.length - 5; i++) {
            firstPockerman[i] = new PokerOne();

            allPocker[i].getPockers(firstPockerman[i]);
        }

        for (int i = 0, j = firstPockerman.length - 5;
             i < secondPockerman.length - 5; i++, j++) {
            secondPockerman[i] = new PokerOne();
            allPocker[j].getPockers(secondPockerman[i]);


        }
        //发额外的5张牌
        for (int i = firstPockerman.length - 5, j = 2 * (firstPockerman.length - 5);
             i < firstPockerman.length; i++, j++) {
            firstPockerman[i] = new PokerOne();
            secondPockerman[i] = new PokerOne();
            allPocker[j].getPockers(firstPockerman[i]);
            j += 1;
            allPocker[j].getPockers(secondPockerman[i]);
        }

        //分别测出两份牌的等级
        ComparePockers.sortPokers(firstPockerman);    //排序
        ComparePockers.sortPokers(secondPockerman);   //排序

        ComparePockers.getbest(firstPockerman);

    }


    public static PokerOne[] sortPokers(PokerOne[] p) {     //排序
        for (int i = p.length; i > 0; i--) {
            for (int j = p.length; j > 0; j--) {
                if (p[j].getNumber() > p[j - 1].getNumber())
                    p[j].changePockers(p[j - 1]);
            }

        }
        return p;
    }



    public static void getbest(PokerOne[] pokerOnes){
        PokerOne[][] allkindPocker =new PokerOne[21][5];
        int[] flag =new int[21];
        Integer[] level =new Integer[21];
        long[] finalValue = new long[21];
        String finalNumber[] =new String[6];
        GiveValue.stringGet(finalNumber);
        GiveValue.intGet(flag);
        GiveValue.intergerGet(level);
        GiveValue.longGet(finalValue);

        for (int i = 0; i <21 ; i++) {
            for (int j = 0; j <5 ; j++) {
                allkindPocker[i][j] =new PokerOne();
            }
        }
        //把7张牌所有可能拿出来
        for (int a=0 ;a<3 ; a++)
            for (int b = a+1; b <4 ; b++)
                for (int c = b+1; c <5 ; c++)
                    for (int d = c+1; d <6 ; d++)
                        for (int e = d+1,m =0,n =0; e <7 ; e++,m++) {
                            pokerOnes[a].getPockers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[b].getPockers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[c].getPockers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[d].getPockers(allkindPocker[m][n]);
                            n++;
                            pokerOnes[e].getPockers(allkindPocker[m][n]);
                            n=0;
                        }

        for (int i = 0; i <21 ; i++) {
            for (int j = 0; j <5; j++){
                for (int k = j+1; k <5 ; k++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[j][k].getNumber())
                        flag[i]++;
                }
            }
            switch (flag[i]){
                case 6:
                    level[i] = 7;
                case 4:
                    level[i] = 6;
                case 3:
                    level[i] = 3;
                case 2:
                    level[i] = 2;
                case 1:
                    level[i] = 1;
                case 0:
                    level[i] = 0;

            }
            flag[i] =0;
            for (int j = 0; j <4 ; j++) {
                if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()+1)
                    flag[i]++;
            }
            if (flag[i]==4){
                level[i] = 4 ;

            }
            flag[i] = 0 ;
            for (int j = 0; j <4 ; j++) {
                if (allkindPocker[i][j].getColour().equals(allkindPocker[i][j+1].getColour()))
                    flag[i]++;
            }


            if (flag[i]==4){
                if (level[i]==4){
                    level[i] = 8;

                    if (allkindPocker[i][0].getNumber()==14)
                        level[i] = 9;
                }
                else{
                    level[i] = 5;
                }

            }
            //将一对重新排序
            if (level[i]==1){
                for (int j = 0; j < 4 ; j++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()) {
                        allkindPocker[i][j].changePockers(allkindPocker[i][0]);
                        allkindPocker[i][j+1].changePockers(allkindPocker[i][1]);
                    }
                }
            }
            //将两对重新排序
            if (level[i]==2){
                int[] showLocation =new int[2];
                int l =0;
                GiveValue.intGet(showLocation);
                for (int j = 0; j < 4 ; j++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()) {
                        showLocation[l] =j;
                        l++;
                    }
                }
                allkindPocker[i][showLocation[0]].changePockers(allkindPocker[i][0]);
                allkindPocker[i][showLocation[0]+1].changePockers(allkindPocker[i][1]);
                allkindPocker[i][showLocation[1]].changePockers(allkindPocker[i][2]);
                allkindPocker[i][showLocation[1]+1].changePockers(allkindPocker[i][3]);

            }

            //将三条重新排序
            if(level[i]==3){
                int location =0;
                for (int j = 0; j <4 ; j++) {
                    if (allkindPocker[i][j].getNumber()==allkindPocker[i][j+1].getNumber()) {
                        location = j;
                        break;
                    }
                }
                allkindPocker[i][location].changePockers(allkindPocker[i][0]);
                allkindPocker[i][location+1].changePockers(allkindPocker[i][1]);
                allkindPocker[i][location+2].changePockers(allkindPocker[i][2]);
            }
            //将葫芦重新排序
            if (level[i]==6){
                if (allkindPocker[i][0].getNumber()!=allkindPocker[i][2].getNumber()){
                    allkindPocker[i][0].changePockers(allkindPocker[i][3]);
                    allkindPocker[i][1].changePockers(allkindPocker[i][4]);
                }
            }
            //将四条重新排序
            if (level[i]==7){
                if (allkindPocker[i][0].getNumber()!=allkindPocker[i][1].getNumber()){
                    allkindPocker[i][0].changePockers(allkindPocker[i][4]);
                }
            }
            finalNumber[0] =  level[i].toString();
            for (int j = 1,k =0; j <finalNumber.length ; j++,k++) {
                if (allkindPocker[i][k].getNumber()>9)
                finalNumber[j] = allkindPocker[i][k].getNumber().toString();
                else
                    finalNumber[j] = "0"+allkindPocker[i][k].getNumber().toString();
            }


        }
        //测试
        for (int i = 0; i <finalNumber.length ; i++) {
            System.out.print(finalNumber+" ");
        }


    }
}