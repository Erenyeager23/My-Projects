class addItems{
    public void add(int a,int b){
        System.out.println("Addition of 2 integers is "+(a+b));
    }
    public void add(double a,double b){
        System.out.println("Addition of 2 double values are "+(a+b));
    }
}

class overriding extends addItems{
    @Override
    public void add(int a ,int b){
        System.out.println("Addition of 2 integers is with help of Overriding the method "+(a+b));
    }
}