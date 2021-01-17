#include <stdio.h>


void reverseRec () {

	char a = getchar();		// Variabel a sparar en char på stacken och kontrollerar mot \n för att sedan åter kalla på funktionen och sparar varje enskild a på sin stackframe.
	
	if (a != '\n')
		reverseRec();
	
	putchar(a);				// När enter trycks poppar putchar stacken och returnerar senaste 'a' på sin stackframe.

}


void reverseIter () {

char array[25];
int counter = 0;
char b;


while (b != '\n') {			// Så länge inte enter trycks så pushar getchar() senaste char i arrayen som sedan ökar indexplats med 1.
	
	b = getchar();
	array [counter++] = b;

	}

for (int i = counter; i >= 0; i--) {		// När enter trycks så poppas arrayen omvänt med hjälp av counter variabeln och printar ut det på sista index.
	putchar(array[i]);
}

}
 




int main(void)
{

	printf("Ange text för rekursiv: \n");
	reverseRec();
	printf("\n\n");

	printf("Ange text för iterativ: \n");
	reverseIter();




}