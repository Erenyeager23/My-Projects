#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cout << "Enter number of processes: ";
    cin >> n;

    int pid[n], at[n], bt[n], ct[n], tat[n], wt[n], com[n], st[n];
    int count = 0, idx = -1, m = 1000, cur = 0;

    cout << "Enter Arrival Time and Burst Time:\n";
    for (int i = 0; i < n; i++) {
        pid[i] = i + 1;
        cin >> at[i] >> bt[i];
        com[i] = 0;
    }

    cout << "\nGantt Chart:\n";

    vector<int> gantt_pid;   // store process IDs for timeline
    vector<int> gantt_time;  // store start times

    while (count < n) {
        m = 1000;
        idx = -1;

        // Find process with minimum BT among arrived and incomplete
        for (int i = 0; i < n; i++) {
            if (at[i] <= cur && com[i] == 0) {
                if (bt[i] < m) {
                    m = bt[i];
                    idx = i;
                }
            }
        }

        if (idx == -1) {
            // CPU is idle
            gantt_pid.push_back(-1);      // -1 represents idle
            gantt_time.push_back(cur);    // start time of idle
            cur++;
            continue;
        }

        st[idx] = cur;
        ct[idx] = cur + bt[idx];
        tat[idx] = ct[idx] - at[idx];
        wt[idx] = tat[idx] - bt[idx];
        com[idx] = 1;
        count++;
        gantt_pid.push_back(pid[idx]);
        gantt_time.push_back(st[idx]);
        cur = ct[idx];
    }

    gantt_time.push_back(cur); // final end time

    // Print Gantt chart
    cout << "|";
    for (int i = 0; i < gantt_pid.size(); i++) {
        if (gantt_pid[i] == -1)
            cout << " IDLE |";
        else
            cout << " P" << gantt_pid[i] << " |";
    }

    // Print timeline below chart
    for (int i = 0; i < gantt_time.size(); i++) {
        cout << gantt_time[i] << "\t";
    }
    cout << "\n\n";

    // Print process table
    cout << "Process\tAT\tBT\tCT\tTAT\tWT\n";
    float avg_tat = 0, avg_wt = 0;
    for (int i = 0; i < n; i++) {
        cout << "P" << pid[i] << "\t" << at[i] << "\t" << bt[i] << "\t"
             << ct[i] << "\t" << tat[i] << "\t" << wt[i] << endl;
        avg_tat += tat[i];
        avg_wt += wt[i];
    }

    cout << "\nAverage TAT: " << avg_tat / n;
    cout << "\nAverage WT: " << avg_wt / n << endl;

    return 0;
}
