interface delivery{
    public void food();
}
interface pay{
    public void payment();
}
class zomoto implements delivery{
    public void food(){
        System.out.println("Zomoto ordered food is biryani");
    }
}

class zepto implements delivery,pay{
    public void food(){
        System.out.println("zepto ordered food is dairymilk");
    }
    public void payment(){
        System.out.println("zepto payment is done in gpay");
    }
}

