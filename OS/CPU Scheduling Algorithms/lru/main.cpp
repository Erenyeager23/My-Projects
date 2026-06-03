/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
#include<stdio.h>
#include <iostream>
using namespace std;
int lru(int times[],int n)
{
    int temp=times[0],pos;
    for(int i=0;i<n;i++)
    {
        if(temp>times[i])
        {
            temp=times[i];
            pos=i;
        }
    }
    return pos;
}
int main()
{
    int pages[50], frame[10], time[10];
    int n, f, i, j, pos, count=0, flag1, flag2;

    printf("Enter number of pages: ");
    scanf("%d",&n);
    printf("Enter page reference string:\n");
    for(i=0; i<n; i++) scanf("%d",&pages[i]);

    printf("Enter number of frames: ");
    scanf("%d",&f);

    for(i=0; i<f; i++) frame[i] = -1;

    printf("\nLRU Page Replacement\n");
    printf("Ref\tFrames\t\tStatus\n");
    
    for(i=0;i<n;i++)
    {
        flag1=0;
        flag2=0;
        for(j=0;j<f;j++)
        {
            if(frame[j]==pages[i])
            {
                flag2=1;
                flag1=1;
                times[j]=i+1;
                break;
            }
        }
        if(flag1==0)
        {
            for(j=0;j<f;j++)
            {
                if(frame[j]==-1)
                {
                    flag2=1;
                    frame[j]=pages[i];
                    count++;
                    times[j]=i+1;
                    break;
                }
            }
        }
        if(flag2==0)
        {
            pos=lru(times,f);
            frame[pos]=pages[i];
            count++;
            times[pos]=i+1;
        }
    }

    return 0;
}