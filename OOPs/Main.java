
public class Main{
    public static void main(String args[]){
        car c=new car();
        c.show();

        Bank b=new Bank();
        b.getwithdraw(500);
        b.getAmount();

        addItems a=new addItems();
        a.add(1,2);
        a.add(2.3,5.6);

        overriding o=new overriding();
        o.add(6.4,7.34);

        zomoto z=new zomoto();
        z.food();
        zepto z1=new zepto();
        z1.food();
        z1.payment();

    }
}