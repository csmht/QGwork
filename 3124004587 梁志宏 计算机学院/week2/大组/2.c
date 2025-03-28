#include <stdio.h>
#include <stdlib.h>

// ������нڵ�
typedef struct QueueNode {
    int *data;
    struct QueueNode *next;
} QueueNode;

// ������нṹ
typedef struct {
    QueueNode *front;
    QueueNode *rear;
} Queue;

// ��ʼ������
Queue *initQueue() {
    Queue *queue = (Queue *)malloc(sizeof(Queue));
    queue->front = queue->rear = NULL;
    return queue;
}

// �ж϶����Ƿ�Ϊ��
int isEmpty(Queue *queue) {
    return queue->front == NULL;
}

// ��Ӳ���
void enqueue(Queue *queue, int *data) {
    QueueNode *newNode = (QueueNode *)malloc(sizeof(QueueNode));
    newNode->data = data;
    newNode->next = NULL;

    if (isEmpty(queue)) {
        queue->front = queue->rear = newNode;
    } else {
        queue->rear->next = newNode;
        queue->rear = newNode;
    }
}

// ���Ӳ���
int *dequeue(Queue *queue) {
    if (isEmpty(queue)) {
        return NULL;
    }
    QueueNode *temp = queue->front;
    int *data = temp->data;
    queue->front = queue->front->next;
    if (queue->front == NULL) {
        queue->rear = NULL;
    }
    free(temp);
    return data;
}

// ���ٶ���
void destroyQueue(Queue *queue) {
    while (!isEmpty(queue)) {
        dequeue(queue);
    }
    free(queue);
}

// ��ӡ����Ԫ��
void printQueue(Queue *queue) {
    QueueNode *current = queue->front;
    while (current != NULL) {
        printf("%d ", *(int *)current->data);
        current = current->next;
    }
    printf("\n");
}

int main() {
    Queue *queue = initQueue();
    int choice;
    int *data;

    while (1) {
        printf("��ѡ�����:\n");
        printf("1. ���\n");
        printf("2. ����\n");
        printf("3. �鿴����\n");
        printf("4. �˳�\n");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                data = (int *)malloc(sizeof(int));
                printf("������Ҫ��ӵ���: ");
                scanf("%d", data);
                enqueue(queue, data);
                printf("��ӳɹ���\n");
                break;
            case 2:
                data = (int *)dequeue(queue);
                if (data != NULL) {
                    printf("����Ԫ��: %d\n", *data);
                    free(data);
                } else {
                    printf("����Ϊ��\n");
                }
                break;
            case 3:
                if (isEmpty(queue)) {
                    printf("����Ϊ��\n");
                } else {
                    printf("����Ԫ��: ");
                    printQueue(queue);
                }
                break;
            case 4:
                destroyQueue(queue);
                return 0;
            default:
                printf("���������룡\n");
        }
    }
}
