#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

typedef struct StackNode {
    double num;
    bool pd;        //是否为字符
    struct StackNode* next;
};

struct head {
    struct StackNode* t;
};
#define III sizeof(struct head)
#define TTT sizeof(struct StackNode)

// 初始化为 NULL
struct head *top = NULL;

void in(struct head **q, double num, bool pd) { //入栈
    struct StackNode *p = (struct StackNode*)malloc(sizeof(struct StackNode));
    p->num = num;
    p->pd = pd;

    p->next = (*q)->t;
    (*q)->t = p;
    return;
}

double push(struct head **q, bool *pd) {
    *pd = (*q)->t->pd;
    double ans = (*q)->t->num;
    struct StackNode *temp = (*q)->t;
    (*q)->t = (*q)->t->next;
    free(temp);
    return ans;
}

void toHouZui() {
    struct head *p, *p1; //p中间栈 p1结果栈
    p = (struct head*)malloc(sizeof(struct head));
    p1 = (struct head*)malloc(sizeof(struct head));
    p->t = NULL;
    p1->t = NULL;
    double ans;
    bool op;

    while (top->t != NULL) {
        ans = push(&top, &op);
        if (op) {
            char f = (char)ans;
            if (f == '(') {
                in(&p, ans, op);
            } else if (f == ')') {
                while (p->t->num - (double)'(' > 10e-5) {
                    bool a;
                    double b = push(&p, &a);
                    in(&p1, b, a);
                }
                push(&p, &op);
            } else if ((f == '*' || f == '/') && ((p->t != NULL) && (p->t->num - (double)'+' <= 10e-5 || p->t->num - (double)'-' <= 10e-5))) {
                double o = push(&p, &op);
                in(&p1, o, op);
                in(&p, ans, op);
            } else {
                in(&p, ans, op);
            }
        } else {
            in(&p1, ans, op);
        }
    }

    while (p->t != NULL) {
        double o = push(&p, &op);
        in(&p1, o, op);
    }

    while (p1->t != NULL) {
        double o = push(&p1, &op);
        in(&top, o, op);
    }
    free(p);
    free(p1);
    return;
}

double work() {
    struct head *p = (struct head*)malloc(sizeof(struct head));
    p->t = NULL;
    double ans;
    bool op;
    while (top->t != NULL) {
        double a;
        a = push(&top, &op);
        if (op) {
            double one = push(&p, &op);
            double tow = push(&p, &op);
            double num;

            char f = (char)a;
            switch (f) {
                case '+':
                    num = one + tow;
                    break;
                case '-':
                    num = tow - one;
                    break;
                case '*':
                    num = tow * one;
                    break;
                case '/':
                    num = tow / one;
                    break;
            }

            in(&p, num, op);
        } else {
            in(&p, a, op);
        }
    }

    ans = push(&p, &op);
    free(p);
    return ans;
}


int main() {
    // 在 main 函数里分配内存
    top = (struct head*)malloc(III);
    top->t = NULL;
    char a[500001];
    scanf("%s", a);
    struct head *p = (struct head*)malloc(sizeof(struct head));
    p->t = NULL;
    int num;
    for (num = 0; a[num] != '\0'; num++) {
        if (!((a[num] == '-' || a[num] == '+' || a[num] == '*' || a[num] == '/') || (a[num] >= '0' && a[num] <= '9')||a[num]=='('||a[num]==')')) {
            printf("输入有误哦~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            return 0;
        }

        if (a[num] >= '0' && a[num] <= '9') {
            char b[500001];
            int i = 0;
            while ((a[num + 1] != '\0') && (a[num + 1] == '.' || (a[num + 1] <= '9' && a[num + 1] >= '0'))) {
                b[i++] = a[num++];
            }
            b[i++] = a[num];
            b[i] = '\0';
            char *end;
            in(&p, strtod(b, &end), 0);
        } else {
            in(&p, (double)a[num], 1);
        }
    }
    bool op;
    while (p->t != NULL) {
        double o = push(&p, &op);
        in(&top, o, op);
    }
    free(p);

    toHouZui();
    double ans = work();
    printf("%.2lf", ans);
    free(top);
    return 0;
}
