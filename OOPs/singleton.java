public class singleton{
    private static singleton s=new singleton();
    private singleton(){}

    public static singleton getlogger(){
        return s;
    }
}