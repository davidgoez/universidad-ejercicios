#include <stdio.h>
#include <stdlib.h>

typedef struct s_{
	size_t inf;
	size_t sup;
} sstack;

void qs(int *, size_t, size_t);
void quicksort(int *, size_t);

void qs(int *lista, size_t inf, size_t sup){
	sstack pila[20]; /* suficiente para 1millon */
	size_t izq, der;
	int mitad, x;
	size_t p;
	p = 1, pila[p].inf = inf, pila[p].sup = sup;
	do{
		inf = pila[p].inf, sup = pila[p].sup, p--;
		do{
			izq = inf, der = sup;
			mitad = lista[izq + ((der-izq)/2)];
			do{
				while (lista[izq] < mitad && izq < sup) izq++;
				while (mitad < lista[der] && der > inf) der--;
				if (izq <= der){
					x=lista[izq], lista[izq]=lista[der], lista[der]=x;
					izq++, der--;
				}
			}while (izq <= der);
			if (izq < sup){
				p++, pila[p].inf = izq, pila[p].sup = sup;
			}
			sup = der;
		}while (inf < der);
	}while (p);
}

void quicksort(int *lista, size_t n_elementos){
	qs(lista, 0, n_elementos - 1);
}

int main(void){
	int values [] = {4,6,2,1,456,43,2,1};
	size_t i, n;
	n = sizeof(values)/sizeof(int);
	quicksort(values, n);
	for(i=0;i<n;i++)
		printf("val[%lu]=%d\n", (unsigned long)i, values[i]);
}


