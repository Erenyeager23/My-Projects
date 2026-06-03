#include <iostream>
#include<vector>
using namespace std;
int main()
{
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    int pid[n], at[n], bt[n], ct[n], tat[n], wt[n], pr[n],r[n];
    int count = 0, cur = 0;

    cout << "Enter AT, BT and Priority:\n";
    for (int i = 0; i < n; i++) 
    {
        pid[i] = i + 1;
        cin >> at[i] >> bt[i] >> pr[i];
        r[i]=bt[i];
    }
    vector<int> gpid;
    vector<int> gtime;
    while(count<n)
    {
        int idx=-1;
        int mp=999;
        for(int i=0;i<n;i++)
        {
            if(at[i]<=cur && r[i]>0 && mp>pr[i])
            {
                idx=i;
                mp=pr[i];
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
        if(gpid.empty()|| gpid.back()!=pid[idx])
        {
            gpid.push_back(pid[idx]);
            gtime.push_back(cur);
        }
        r[idx]--;
        cur++;
        if(r[idx]==0)
        {
            ct[idx]=cur;
            tat[idx]=ct[idx]-at[idx];
            wt[idx]=tat[idx]-bt[idx];
            count++;
        }
        
    }
    gtime.push_back(cur);
    cout<<"gantt chart\n";
    cout<<"|";
    for(int i=0;i<gpid.size();i++)
    {
        if(gpid[i]==-1)
        {
            cout<<"Idle|";
        }
        else
        {
            cout<<"p"<<gpid[i]<<"  |";
        }
    }
    cout<<"\n";
    for(int i=0;i<gtime.size();i++)
    {
        cout<<gtime[i]<<"    ";
    }
    return 0;
}