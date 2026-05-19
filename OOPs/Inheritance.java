class vehicle{
    String name="BMW";
    public void display(){
        System.out.println("This is Vehicle Class");
    }
}

class car extends vehicle{
    public void show(){
        System.out.println("The Car type is "+name);
    }
}