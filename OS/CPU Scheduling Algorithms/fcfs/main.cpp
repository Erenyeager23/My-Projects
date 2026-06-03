#include <iostream>
using namespace std;

void swap1(int a,int b)
{
    int temp=a;
    a=b;
    b=temp;
}
int main()
{
    int n;
    cout<<"enter n\n";
    cin>>n;
    int pid[n],at[n],bt[n],ct[n],wt[n],tat[n];
    cout<<"enter at and bt\n";
    for(int i=0;i<n;i++)
    {
        pid[i]=i+1;
        cin>>at[i]>>bt[i];
    }
    for(int i=0;i<n;i++)
    {
        for(int j=i;j<n;j++)
        {
            if(at[i]>at[j])
            {
                swap(at[i],at[j]);
                swap(bt[i],bt[j]);
                swap(pid[i],pid[j]);
            }
        }
    }
    int curr=0;
    
    for(int i=0;i<n;i++)
    {
        if(curr<at[i])
        {
            cout<<"|IDLE";
            curr=at[i];
        }
        ct[i]=curr+bt[i];
        tat[i]=ct[i]-at[i];
        wt[i]=tat[i]-bt[i];
        curr=ct[i];
        cout<<"| p"<<pid[i]<<" ";
    }
    int cur1=0;
    cout<<"\n0";
    for(int i=0;i<n;i++)
    {
        if(cur1<at[i])
        {
            cout<<"    "<<at[i];
            cur1=at[i];
        }
        cout<<"    "<<ct[i];
        cur1=ct[i];
    }
    return 0;
}