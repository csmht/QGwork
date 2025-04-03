#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <string.h>

// ��������
void InsertSort(int arr[], int n) {
    int i, j, key;
    for (i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}

// �ϲ�����������
void merge(int arr[], int l, int m, int r) {
    int n1 = m - l + 1;
    int n2 = r - m;

    int L[n1], R[n2];

    for (int i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[m + 1 + j];

    int i = 0, j = 0, k = l;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

// �鲢����
void MergeSort(int arr[], int l, int r) {
    if (l < r) {
        int m = l + (r - l) / 2;

        MergeSort(arr, l, m);
        MergeSort(arr, m + 1, r);

        merge(arr, l, m, r);
    }
}

// ��������ķ�������
int partition(int arr[], int low, int high) {
    int pivot = arr[high];
    int i = (low - 1);

    for (int j = low; j <= high - 1; j++) {
        if (arr[j] < pivot) {
            i++;
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    int temp = arr[i + 1];
    arr[i + 1] = arr[high];
    arr[high] = temp;
    return (i + 1);
}

// �������򣨵ݹ�棩
void QuickSort(int arr[], int low, int high) {
    if (low < high) {
        int pi = partition(arr, low, high);

        QuickSort(arr, low, pi - 1);
        QuickSort(arr, pi + 1, high);
    }
}

// ��������
void CountSort(int arr[], int n) {
    int max = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i] > max) {
            max = arr[i];
        }
    }
    int *count = (int *)calloc(max + 1, sizeof(int));
    for (int i = 0; i < n; i++) {
        count[arr[i]]++;
    }
    int index = 0;
    for (int i = 0; i <= max; i++) {
        while (count[i] > 0) {
            arr[index++] = i;
            count[i]--;
        }
    }
    free(count);
}

// ���ɲ������ݲ����浽�ļ�
void generateAndSaveData(const char *filename, int num) {
    FILE *fp = fopen(filename, "w");
    if (fp == NULL) {
        perror("Failed to open file");
        return;
    }
    srand(time(NULL));
    for (int i = 0; i < num; i++) {
        int numToWrite = rand();
        fprintf(fp, "%d\n", numToWrite);
    }
    fclose(fp);
}

// ���ļ���ȡ���ݲ�����
void readAndSortData(const char *filename, void (*sortFunc)(int[], int), int num) {
    int *arr = (int *)malloc(num * sizeof(int));
    if (arr == NULL) {
        perror("Memory allocation failed");
        return;
    }
    FILE *fp = fopen(filename, "r");
    if (fp == NULL) {
        perror("Failed to open file");
        free(arr);
        return;
    }
    for (int i = 0; i < num; i++) {
        fscanf(fp, "%d", &arr[i]);
    }
    fclose(fp);

    clock_t start = clock();
    sortFunc(arr, num);
    clock_t end = clock();
    double time_spent = (double)(end - start) / CLOCKS_PER_SEC;
    printf("Sorting time: %f seconds\n", time_spent);

    free(arr);
}

// �����������ڲ�ͬ�������µ���ʱ
void testSortingTimes(void (*sortFunc)(int[], int)) {
    int dataSizes[] = {10000, 50000, 200000};
    for (int i = 0; i < 3; i++) {
        char filename[20];
        sprintf(filename, "data_%d.txt", dataSizes[i]);
        generateAndSaveData(filename, dataSizes[i]);
        readAndSortData(filename, sortFunc, dataSizes[i]);
    }
}

int main() {
    // ���Բ�������
    testSortingTimes(InsertSort);
    // ���Թ鲢����
    testSortingTimes(MergeSort);
    // ���Կ�������
    testSortingTimes(QuickSort);
    // ���Լ�������
    testSortingTimes(CountSort);

    return 0;
}
