#include <iostream>
#include<vector>
#include<queue>
using namespace std;

int main()
{
    int n;
    cout<<"enter n:";
    cin>>n;
    int at[n],bt[n],ct[n],tat[n],wt[n],rt[n],pid[n],inqueue[n]={0};
    int count=0,cur=0;
    cout<<"enter at,bt\n";
    
    for(int i=0;i<n;i++)
    {
        pid[i]=i+1;
        cin>>at[i]>>bt[i];
        rt[i]=bt[i];
    }
    int q;
    cout<<"\nenter q:";
    cin>>q;
    queue<int> q1;
    vector<int> gpid;
    vector<int> gtime;
    while(count<n)
    {
        for(int i=0;i<n;i++)
        {
            if(at[i]<=cur && rt[i]>0 && inqueue[i]==0)
            {
                q1.push(i);
                inqueue[i]=1;
            }
        }
        if(q1.empty())
        {
            if(gpid.empty() || gpid.back()!=-1)
            {
                gpid.push_back(-1);
                gtime.push_back(cur);
            }
            cur++;
            for(int i=0;i<n;i++) {
        if(at[i]<=cur && rt[i]>0 && inqueue[i]==0) {
            q1.push(i);
            inqueue[i]=1;
        }
    }
            continue;
        }
        int p=q1.front();
        q1.pop();
        inqueue[p]=0;
        if(gpid.empty() || gpid.back()!=pid[p])
        {
            gpid.push_back(pid[p]);
            gtime.push_back(cur);
        }
        if(rt[p]>q)
        {
            rt[p]=rt[p]-q;
            cur=cur+q;
        }
        else
        {
            cur=cur+rt[p];
            rt[p]=0;
        }
        for(int i=0;i<n;i++)
        {
            if(at[i]<=cur && rt[i]>0 && inqueue[i]==0)
            {
                q1.push(i);
                inqueue[i]=1;
            }
        }
        if(rt[p]==0)
        {
            count++;
            ct[p]=cur;
            tat[p]=ct[p]-at[p];
            wt[p]=tat[p]-bt[p];
        }
        else
        {
            q1.push(p);
            inqueue[p]=1;
        }
    }
    gtime.push_back(cur);
    cout << "\nGantt Chart:\n|";
    for (int i = 0; i < gpid.size(); i++) {
        if (gpid[i] == -1)
            cout << " IDLE |";
        else
            cout << " P" << gpid[i] << " |";
    }
    cout << "\n";
    for (int i = 0; i < gtime.size(); i++)
        cout << gtime[i] << "   ";
    cout << "\n";

    return 0;
}