//
//  main.c
//  project1_a
//
//  Created by 范雨霈 on 2022/9/18.
//


#include <stdio.h>
#include <stdlib.h>

void Mergesort(int a[], int n, int m, int s);
void merge(int a[], int n, int mid, int m);
void InsertionSort(int a[], int n, int m);
void printArray(int a[], int size);

int main() {
    int arr[] = {19,3,4,11,8,32,2,9,10};
    int arrSize = sizeof(arr) / sizeof(arr[0]);
    
    int s;
    printf("input a threshold:\n");
    scanf("%d",&s);
    
    printf("array before sorting:\n");
    printArray(arr, arrSize);
    
    Mergesort(arr, 0, arrSize-1,s);
    //InsertionSort(arr, arrSize);
    
    printf("array after sorting:\n");
    printArray(arr, arrSize);
    
    return 0;
}

void printArray(int a[], int size){
    for(int i = 0; i < size; i++) printf("%d\t",a[i]);
    printf("\n");
}

void Mergesort(int arr[], int n, int m, int s){
    if(m - n <= s) InsertionSort(arr, n, m);
    if (n < m){
        int mid = (m+n) / 2;
        Mergesort(arr, n, mid, s);
        Mergesort(arr, mid + 1, m, s);

        merge(arr, n, mid, m);
    }return;
}

void InsertionSort(int a[], int n, int m){
    for (int i = n+1; i < m; i++)
        for (int j = i; j > 0; j--){
            if (a[j] < a[j - 1]){
                int tmp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = tmp;
            }
        }
}


void merge(int a[], int n, int mid, int m){
    int l1 = mid - n +1, l2 = m - mid;
    int N[l1], M[l2];
    
    for(int i = 0; i < l1; i++) N[i] = a[n + i];
    for(int j = 0; j < l2; j++) M[j] = a[mid + 1 + j];
      
    int i = 0, j = 0, k = n;
    
    while (i < l1 && j < l2){
        if(N[i] <= M[j]) { a[k] = N[i]; i++; }
        else { a[k] = M[j]; j++; }
        k++;
    }
    
    while(i < l1){
        a[k] = N[i];
        i++; k++;
    }
    
    while(j < l2){
        a[k] = M[j];
        j++; k++;
    }
}
