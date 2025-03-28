#include <stdio.h>
#include <stdlib.h>

// 定义队列节点
typedef struct QueueNode {
    int *data;
    struct QueueNode *next;
} QueueNode;

// 定义队列结构
typedef struct {
    QueueNode *front;
    QueueNode *rear;
} Queue;

// 初始化队列
Queue *initQueue() {
    Queue *queue = (Queue *)malloc(sizeof(Queue));
    queue->front = queue->rear = NULL;
    return queue;
}

// 判断队列是否为空
int isEmpty(Queue *queue) {
    return queue->front == NULL;
}

// 入队操作
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

// 出队操作
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

// 销毁队列
void destroyQueue(Queue *queue) {
    while (!isEmpty(queue)) {
        dequeue(queue);
    }
    free(queue);
}

// 打印队列元素
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
        printf("请选择操作:\n");
        printf("1. 入队\n");
        printf("2. 出队\n");
        printf("3. 查看队列\n");
        printf("4. 退出\n");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                data = (int *)malloc(sizeof(int));
                printf("请输入要入队的数: ");
                scanf("%d", data);
                enqueue(queue, data);
                printf("入队成功！\n");
                break;
            case 2:
                data = (int *)dequeue(queue);
                if (data != NULL) {
                    printf("出队元素: %d\n", *data);
                    free(data);
                } else {
                    printf("队列为空\n");
                }
                break;
            case 3:
                if (isEmpty(queue)) {
                    printf("队列为空\n");
                } else {
                    printf("队列元素: ");
                    printQueue(queue);
                }
                break;
            case 4:
                destroyQueue(queue);
                return 0;
            default:
                printf("请重新输入！\n");
        }
    }
}
