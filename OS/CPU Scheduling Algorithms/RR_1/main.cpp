#include <iostream>
#include <queue>
#include <iomanip>
using namespace std;

void roundRobin(int pid[], int at[], int bt[], int n, int quantum) {
    int rt[100], ct[100], tat[100], wt[100];
    bool visited[100] = {false};

    int gantt_pids[200];
    int gantt_times[200];
    int gantt_count = 0;

    for (int i = 0; i < n; i++)
        rt[i] = bt[i];

    queue<int> q;
    int current_time = 0;
    int completed = 0;
    float total_tat = 0, total_wt = 0;

    // Find first process to start
    int earliest = 1e9;
    for (int i = 0; i < n; i++)
        if (at[i] < earliest)
            earliest = at[i];

    current_time = earliest;

    // Push first arrived processes
    for (int i = 0; i < n; i++) {
        if (at[i] <= current_time && !visited[i]) {
            q.push(i);
            visited[i] = true;
        }
    }

    while (completed < n) {
        if (q.empty()) {
            // No process ready → IDLE time
            gantt_pids[gantt_count] = -1; // IDLE
            gantt_times[gantt_count++] = current_time;
            current_time++;
            for (int i = 0; i < n; i++) {
                if (at[i] <= current_time && !visited[i]) {
                    q.push(i);
                    visited[i] = true;
                }
            }
            continue;
        }

        int idx = q.front();
        q.pop();

        gantt_pids[gantt_count] = pid[idx];
        gantt_times[gantt_count++] = current_time;

        int run_time = (rt[idx] > quantum) ? quantum : rt[idx];
        rt[idx] -= run_time;
        current_time += run_time;

        // Add newly arrived processes
        for (int i = 0; i < n; i++) {
            if (at[i] <= current_time && !visited[i]) {
                q.push(i);
                visited[i] = true;
            }
        }

        if (rt[idx] > 0) {
            q.push(idx); // process not finished → push back
        } else {
            ct[idx] = current_time;
            tat[idx] = ct[idx] - at[idx];
            wt[idx] = tat[idx] - bt[idx];
            total_tat += tat[idx];
            total_wt += wt[idx];
            completed++;
        }
    }

    gantt_times[gantt_count] = current_time;

    cout << "\nProcess\tArrival Time\tBurst Time\tCompletion Time\tTurnaround Time\tWaiting Time\n";
    cout << "--------------------------------------------------------------------------------------\n";
    for (int i = 0; i < n; i++) {
        cout << "P" << pid[i] << "\t" << at[i] << "\t\t" << bt[i]
             << "\t\t" << ct[i] << "\t\t" << tat[i]
             << "\t\t" << wt[i] << "\n";
    }

    cout << fixed << setprecision(2);
    cout << "\nAverage Waiting Time: " << total_wt / n;
    cout << "\nAverage Turnaround Time: " << total_tat / n << "\n";

    cout << "\n--- Gantt Chart ---\n";
    cout << "--------------------------------------------------------\n";
    cout << "|";
    for (int i = 0; i < gantt_count; i++) {
        if (gantt_pids[i] == -1)
            cout << "  IDLE\t|";
        else
            cout << "   P" << gantt_pids[i] << "\t|";
    }
    cout << "\n--------------------------------------------------------\n";
    for (int i = 0; i <= gantt_count; i++) {
        cout << gantt_times[i] << "\t";
    }
    cout << "\n";
}

int main() {
    int n, quantum;
    cout << "Enter the number of processes: ";
    cin >> n;

    int pid[100], at[100], bt[100];

    for (int i = 0; i < n; i++) {
        pid[i] = i + 1;
        cout << "Enter arrival time and burst time for Process P" << i + 1 << ": ";
        cin >> at[i] >> bt[i];
    }

    cout << "Enter time quantum: ";
    cin >> quantum;

    roundRobin(pid, at, bt, n, quantum);
    return 0;
}
