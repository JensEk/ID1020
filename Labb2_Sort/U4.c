/*README

 * Sorting lab uppgift 4
 * Implementering av algoritm för att placera negativa tal före positiva utan att sortera de i storleksordning. Algoritmens tidskomplexitet är O(N).
 *
 * Skapad av: Jens Ekenblad.
 *
 * */

#include <stdio.h>
#include <stdlib.h>

/* Sort tar emot en array och en int för antal element i arrayen. I loopen används två pekare där båda sätts till att peka på index 0 och där pekare j flyttas om antingen det pekar på ett negativt tal eller ett byte har skett.
 För att ett byte ska ske så måste det valda elementen vara < 0 och platsen där j pekar på måste vara ett positivt tal. */

void sort (int arr[], int size){

	int count = 0;
	int j = 0;
	for(int i = 0; i < size; i++) {

		
		if(arr[i] < 0 && arr[j] >0) {
			int temp = arr[j];
			arr[j] = arr[i];
			arr[i] = temp;
			j++;
			count++;
		}
		else if (arr[j] < 0)
			j++;

	}
	printf("Antal byten: %d\n", count);

}


int main (void) {

	int array[] = {5, -3, 4, -8, 6, -2};
	int size = sizeof(array) / sizeof(array[0]);
	sort(array, size);

	int i = 0;
	while(i < size) {
		printf("%d" , array[i]);
		i++;
	}
	printf("\n");

}