class Bank{
    private int balance=1000;
    private void withdraw(int a){
        if(a>balance){
            System.out.println("Not enough Amount to withdraw");
        }
        else{
            balance=balance-a;
            System.out.println(a+" Transcation done");
        }
    }
    private void showAmount(){
        System.out.println("Total Amount in the Account is "+balance);
    }
    public void getwithdraw(int k){
        withdraw(k);
    }
    public void getAmount(){
        showAmount();
    }
}