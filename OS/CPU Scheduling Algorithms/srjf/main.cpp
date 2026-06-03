
#include <iostream>
#include<vector>
using namespace std;
int main()
{
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    int pid[n], at[n], bt[n], ct[n], tat[n], wt[n], com[n], st[n],rn[n];
    int count = 0, idx = -1, m = 1000, cur = 0;

    cout << "Enter Arrival Time and Burst Time:\n";
    for (int i = 0; i < n; i++) {
        pid[i] = i + 1;
        cin >> at[i] >> bt[i];
        rn[i]=bt[i];
        com[i] = 0;
    }


    vector<int> gpid;   // store process IDs for timeline
    vector<int> gtime;  // store start times
    while(count<n)
    {
        int mn=10000;
        int idx=-1;
        for(int i=0;i<n;i++)
        {
            if(at[i]<=cur && com[i]==0 && rn[i]<mn)
            {
                idx=i;
                mn=rn[i];
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
        
        if(gpid.empty() || gpid.back()!=pid[idx])
        {
            gpid.push_back(pid[idx]);
            gtime.push_back(cur);
        }
        rn[idx]--;
        cur++;
        if(rn[idx]==0)
        {
            com[idx]=1;
            ct[idx]=cur;
            tat[idx]=ct[idx]-at[idx];
            wt[idx]=tat[idx]-bt[idx];
            count++;
        }
        
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