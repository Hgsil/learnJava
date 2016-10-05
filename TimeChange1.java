/**
 * Created by Administrator on 2016/9/22 0022.
 */
import  java.util.*;
public class TimeChange {
    private double month,day,hour,minute,second,sumday;
    private int year;

    double[] evetymonth = {31,28,31,30,31,30,31,31,30,31,30,31};
    double leapyeartimes=0;
    int monthnumber=0;
    long l;


    TimeChange(long l){
        this.l=l;
        setSecond();
        setMinute();
        setHour();
        setDay();
        setMonth();
        setYear();

}
    public void setYear(){
        year = (int)(sumday/365);
        year +=1970;
    }

    public void setMonth(){
        if ((year+1969)%4==0&&(year+1969)%400!=0)
            evetymonth[1]= 29;

        month = monthnumber;
        evetymonth[1]=28;
    }


    public void setDay() {
        sumday = (long)(l/60/60/24);
        year = (int)(sumday/365);
        day = sumday % 365;
        for (double i = 0;i<=year;i++) {
            if ((i+1970)%4==0&&(i+1970)%400!=0)
                leapyeartimes++;
        }

        if (day - leapyeartimes <=0) {
            year -= 1;
            if ((year + 1969) % 4 == 0 && (year + 1969) % 400 != 0) {
                day += 366;
                day -= leapyeartimes;
            } else {
                day += 365;
                day -= leapyeartimes;
            }
        }

        for(int i=0; day>evetymonth[i] ; ++i,monthnumber=i+1){
            day -= evetymonth[i];

        }
        day -= leapyeartimes;
    }

    public void setHour() {
        hour = (long)(l/60/60)%24;
        l -= hour;
        hour+=8;
    }

    public void setMinute() {
        minute =  (long)(l/60) % 60 ;
        l -= minute;
    }

    public void setSecond() {
        second = l % 60 ;
        l -= second;
    }

    public int getyear(){return year;}
    public double getmonth(){return month;}
    public double getday(){return day;}
    public double gethour(){
        return hour;
    }
    public double getminute(){
        return minute;
    }
    public double getsecond(){
        return second;
    }

    public static void main(String[] args) {
        TimeChange timeChange = new TimeChange(1474289974);
        System.out.printf("%d年",timeChange.getyear());
        System.out.printf("%.0f月",timeChange.getmonth());
        System.out.printf("%.0f日    ",timeChange.getday());
        System.out.printf("%.0f：",timeChange.gethour());
        System.out.printf("%.0f：",timeChange.getminute());
        System.out.printf("%.0f",timeChange.getsecond());
    }
}
