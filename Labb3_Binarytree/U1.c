/* README 
Uppgift 1 Searching lab
Skapad av: Jens Ekenblad
*/



#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>


// Läser in till pekare ptr1 från texten som ska analyseras och sedan skriver ut resultatet till en ny text fil. Med hjälp av isalpha som kollar om den lästa character är a-z/A-Z annars returnerar 0.
void clean() {

	FILE *ptr1;
	FILE *ptr2;
	ptr1 = fopen("thetext.txt", "r");
	ptr2 = fopen("writeText.txt", "w");
	char c;
	char blank = ' ';


	while (1) {

		c = fgetc(ptr1);
		if (isalpha(c) > 0 || c == '\n')
			fputc(c, ptr2);


		if (isalpha(c) == 0)
			fputc(blank, ptr2);

		if(c == EOF)
			break;
			
	}

	fclose(ptr1);
	fclose(ptr2);

}


int main (void) {

	
	clean();
	return 0;

	
}