#include <iostream>
#include<vector>
using namespace std;
int main()
{
    int n;
    cout<<"enter n:";
    cin>>n;
    int pid[n],at[n],bt[n],ct[n],tat[n],com[n],wt[n],pr[n];
    int count=0,cur=0;
    cout<<"enter at and bt and priority\n";
    for(int i=0;i<n;i++)
    {
        pid[i]=i+1;
        cin>>at[i]>>bt[i]>>pr[i];
        com[i]=0;
    }
    vector<int> gpid;
    vector<int> gtime;
    while(count<n)
    {
        int idx=-1;
        int mp=999;
        for(int i=0;i<n;i++)
        {
            if(at[i]<=cur && com[i]==0 && mp>pr[i])
            {
                mp=pr[i];
                idx=i;
            }
        }
        if(idx==-1)
        {
            if(gpid.empty() || gpid.back()!=-1)
            {
                gpid.push_back(-1);
                gtime.push_back(cur);
            }
            cur++;
            continue;
        }
        ct[idx]=cur+bt[idx];
        tat[idx]=ct[idx]-at[idx];
        wt[idx]=tat[idx]-bt[idx];
        gpid.push_back(pid[idx]);
        gtime.push_back(cur);
        cur=cur+bt[idx];
        com[idx]=1;
        count++;
    }
    
    gtime.push_back(cur);
    cout<<"gantt Chart\n";
    cout<<"|";
    for(int i=0;i<gpid.size();i++)
    {
        if(gpid[i]==-1)
        {
            cout<<"IDLE|";
        }
        else
        {
            cout<<" p"<<gpid[i]<<" |";
        }
    }
    cout<<"\n";
    for(int i=0;i<gtime.size();i++)
    {
        cout<<gtime[i]<<"    ";
    }
    return 0;
}