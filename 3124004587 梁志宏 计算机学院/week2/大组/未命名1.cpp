#include <stdio.h>
#include <malloc.h>
#include <stdlib.h>




typedef struct StackNode {
	double num;
	bool pd;        //是否为字符
	struct StackNode* next;
};

struct head {
	struct StackNode* t;
};
head *top = (head*)malloc(sizeof(head));


void in(head **q,double num,bool pd) { //入栈

	StackNode *p = (StackNode*)malloc(sizeof(StackNode));
	p->num = num;
	p->pd = pd;
	
	p->next = (*q)->t;
	(*q)->t = p;

	
	
	return ;
}

double push(head **q,bool *pd) {
	*pd = (*q)->t->pd;
	double ans = (*q)->t->num;
	(*q)->t = (*q)->t->next;
	return ans;
}

void toHouZui() {
	head *p,*p1; //p中间栈 p1结果栈
	p = (head*)malloc(sizeof(head));
	p1 = (head*)malloc(sizeof(head));
	p->t = NULL;
	p1->t = NULL;
	double ans;
	bool op;

	while(top->t != NULL) {

		ans = push(&top,&op);
		if(op) {
			char f = (char)ans;
			if(f=='(') {
				in(&p,ans,op);
			} else if(f == ')') {
				while(p->t->num - (double)'('>10e-5) {
					bool a;
					double b = push(&p,&a);
					in(&p1,b,a);
				}push(&p,&op);
			} else if((f=='*'||f =='/')&&((p->t!=NULL)&&(p->t->num - (double)'+' <= 10e-5||p->t->num - (double)'-' <= 10e-5))) {
				double o = push(&p,&op);
				in(&p1,o,op);
				in(&p,ans,op);
			} else {
				in(&p,ans,op);
			}
		} else {
			in(&p1,ans,op);
		}

	}

	while(p->t!=NULL){
		double o = push(&p,&op);
		in(&p1,o,op);
	}
	
	while(p1->t!=NULL){
		double o = push(&p1,&op);
		in(&top,o,op);
	}
	
	return ;
}

double work(){
	head *p=(head*)malloc(sizeof(head));
	p->t = NULL;
	double ans;
	bool op;
	while(top->t!=NULL){
		double a;
		a = push(&top,&op);
		if(op){
			double one = push(&p,&op);
			double tow = push(&p,&op);
			double num;
			
			char f = (char)a;
			switch(f){
				case '+':num = one + tow;break;
				case '-':num = tow - one;break;
				case '*':num = tow * one;break;
				case '/':num = tow / one;break;
			}
				
			in(&p,num,op);
		}else{
			in(&p,a,op);
		}
	}
	
	ans = push(&p,&op);
	return ans;
	
	
	
}





int main() {
	top->t = NULL;
	char a[500001];
	scanf("%s",&a);
	head *p = (head*)malloc(sizeof(head));
	p->t = NULL;
	int num;
	for(num = 0;a[num]!='\0';num++){
		if(a[num]>='0'&&a[num]<='9'){
			char b[500001];
			int i = 0;
			while((a[num+1]!='\0')&&(a[num+1]=='.'||(a[num+1]<='9'&&a[num+1]>='0'))){
				
				b[i++] = a[num++];
			}
				b[i++] = a[num];
				b[i] = '\0';
			char *end;
			in(&p,strtod(b,&end),0);
		}else{
			in(&p,(double)a[num],1);
		}
	}
     bool op;
		while(p->t!=NULL){
		double o = push(&p,&op);
		in(&top,o,op);
	}

	toHouZui();
	double ans = work();
	printf("%.2lf",ans);
	return 0;
}
