#include<stdio.h>

void charu(int *a,int n){
	for(int i=0;i<n;i++){
		int min = a[i];
		int o = i;
		for(int p=0;p<n;p++){
			o =	a[p]<min?p:o;
			min =a[p]<min?a[p]:min;
		}
		a[o] = a[i];
		a[i] = min;
	}
	return;
}

void guibing(int *a,int top,int end){
	if(top<end){
		int mid = top+(end-top)/2;
		guibing(a,top,mid);
		guibing(a,mid+1,top);
		
		guibing(a,a+mid,mid-top,end-mid+1)
	}
}

void guibing1(int *a,int top,int mid,int end){
	
}
