// Authors: Nicolas Rose and Chris Maxel

#define _GNU_SOURCE
#include "cachelab.h"
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <stdlib.h>
#include <unistd.h>

typedef struct line{  // This is our struct
	int timer;
	char valid;
	long tag;
} Line;

/*
Line[E] set;
set[pow(int 2, int s)] cache;
structPointer = (Line *)malloc(E*sizeof(Line));
*/

int readFromCMD(int argc, char * arg[]){ 
/* Reads input from the Command line. (eg. Gets arguments(4,1,4,"traces/yi.trace") 
	from ./csim -v -s 4 -E 1 -b 4 -t traces/yi.trace) */

	
	/*int numSets;  // The commented variables are initalized in cachelab.h
	int numLines;
	int bitSize;
	char* fileName;
	*/
	int i;
	verbose = 0;

	for(i = 1; i < argc; i++){		
		switch(arg[i][1]){
			case 'h':
				printf("usage: ./csim-ref [-hv] -s <num> -E <num> -b <num> -t <file> \n");
				printf("Options: \n" );
				printf("-h         Print this help message. \n");
				printf("-v         Optional verbose flag. \n");
				printf("-s <num>   Number of set index bits. \n");
				printf("-E <num>   Number of lines per set. \n");
				printf("-b <num>   Number of block offset bits. \n");
				printf("-t <file>  Trace file. \n");
				printf(" \n");
				printf("Examples: \n");
				printf("linux>  ./csim-ref -s 4 -E 1 -b 4 -t traces/yi.trace \n");
				printf("linux>  ./csim-ref -v -s 8 -E 2 -b 4 -t traces/yi.trace \n");
				break;

			case 'v':
				verbose = 1;
				break;

			case 's':
				if(i+1 == argc) {return 1;}
				setBits = atoi(arg[i+1]);
				numSets = 1 << atoi(arg[i+1]);
				i++; break;

			case 'E':
				if(i+1 == argc) {return 1;}
				numLines = atoi(arg[i+1]);
				i++;  break;

			case 'b':
				if(i+1 == argc) {return 1;}
				blockOffset = atoi(arg[i+1]);
				bitSize = 1 << atoi(arg[i+1]);
				i++; break;

			case 't':
				if(i+1 == argc) {return 1;}
				fileName = arg[i+1];
				i++; break;

			default:
				printf("invalid flag: %s\n",arg[i]);
				return 1;
		}
	}
	if(verbose == 1){	
		if(numSets == 0) {printf("s is not set :");}
		 else {printf("\nnumber of sets  : %d\n",numSets);} 

		if(numLines == 0) {printf("E is not set:");} 
			else {printf("number of lines : %d\n",numLines);}

		if(bitSize == 0) {printf("b is not set:");} 
			else {printf("Size of block   : %d\n",bitSize);}

		if(fileName == NULL) {printf("file name not set");} 
		else {printf("fileName        : %s\n", fileName);}
	}
	return 0;
}

void print_array(char *array, int length){
	/* This is a helper func that prints arrays */
	printf("\nHere is your array:\n");
    for (int i = 0; i < length; i++){
    	printf("%c ", array[i]);
    }
    printf("\nEnd of your array!\n");
}


Line* setUpCache(){
	// This function sets up the cache, or creates the correct amount of spots for 

	int cacheSize = numLines * numSets;
	printf("Size of theCache: %d\n", cacheSize);

	Line* theCache = (Line*)calloc(cacheSize,sizeof(Line));

	printf("\nHow many stucts are in our cache => %d\n", numLines * numSets);
	return theCache;
}


void readInFileAndPopulateCache(){
	/* In this function we are reading the stuff that we want to input into the cache and array em up */

	Line* theCache = setUpCache();
	theCache[0].valid = '0';
	printf("here is the first vlidbit in theCache: %c\n", theCache[0].valid);

	FILE * fp; // File pointer
	char * line = NULL;
	size_t len = 0;
	ssize_t lengthOfLine; // Length of Each line from the input file.  May not need this but could be helpful
	int chPerLine = 0;
	char * address;

	int CHit = 0; CHit += 0; // fix this trash
	int CMiss = 0; CMiss += 0;
	int CEvict = 0; CEvict += 0;

	fp = fopen(fileName,"r"); // Should maybe put error handling into this if the file does not exist


	while((lengthOfLine = getline(&line, &len, fp)) != -1){ // Grabs each line and puts it in an array
		chPerLine = 0;
		if(line[0] == ' '){
			address = line + 3;
			strtok(address, ",");
			char * size = address + strlen(address) + 1;
			unsigned int addr_l = strtol(address, NULL, 16); addr_l += 0;
			int size_l = strtol(size, NULL, 16); size_l += 0;
			int shiftStuff = (1 << setBits) - 1;
			int setIndex = (addr_l >> blockOffset) & shiftStuff;
			int validTag = addr_l >> (setBits + blockOffset);

			printf("This is setIndex:%d\n", setIndex);
			printf("This is validTag:%d\n\n", validTag);
			
			int isInCache = 0; isInCache += 0;
			int noOpenSpot = 0;
			int evictSpot = 0;
			int postHit = 0;

			if(line[1] == 'S' || line[1] == 'L'){ // If S or L
				for(int i = 0; i < numLines ; i++){ // Checks if not in Cache
					if(theCache[numLines*setIndex+i].valid != '1' || theCache[numLines*setIndex+i].tag != validTag){
						isInCache = 0;
					}
					if(theCache[numLines*setIndex+i].valid == '1' && theCache[numLines*setIndex+i].tag == validTag){
						theCache[numLines*setIndex+i].timer = 0;
						CHit++;
						postHit = 1;
						break;
					}

				}
				if(postHit == 0){
					for(int i = 0; i < numLines ; i++){
						if(theCache[numLines*setIndex+i].valid != '1'){
							theCache[numLines*setIndex+i].valid = '1';
							theCache[numLines*setIndex+i].timer = 0;
							theCache[numLines*setIndex+i].tag = validTag;
							CMiss++;
							noOpenSpot = 0;
							break;
						}
						else{
							noOpenSpot = 1;
						}
					}
					if(noOpenSpot == 1){
						int greatestTimer = 0; greatestTimer += 0;

						for(int i = 0; i < numLines ; i++){
							if(theCache[numLines*setIndex+i].timer > greatestTimer){
								greatestTimer = theCache[numLines*setIndex+i].timer;
								evictSpot = numLines*setIndex+i;
							}
						}
						CEvict++; CMiss++;
						theCache[evictSpot].valid = '1';
						theCache[evictSpot].timer = 0;
						theCache[evictSpot].tag = validTag;
					}
				}
			}

			if(line[1] == 'M'){ // if M
				for(int i = 0; i < 2; i++){
					for(int i = 0; i < numLines ; i++){ // Checks if not in Cache
						if(theCache[numLines*setIndex+i].valid != '1' || theCache[numLines*setIndex+i].tag != validTag){
							isInCache = 0;
						}
						if(theCache[numLines*setIndex+i].valid == '1' && theCache[numLines*setIndex+i].tag == validTag){
							theCache[numLines*setIndex+i].timer = 0;
							CHit++;
							postHit = 1;
							break;
						}

					}
					if(postHit == 0){
						for(int i = 0; i < numLines ; i++){
							if(theCache[numLines*setIndex+i].valid != '1'){
								theCache[numLines*setIndex+i].valid = '1';
								theCache[numLines*setIndex+i].timer = 0;
								theCache[numLines*setIndex+i].tag = validTag;
								CMiss++;
								noOpenSpot = 0;
								break;
							}
							else{
								noOpenSpot = 1;
							}
						}
						if(noOpenSpot == 1){
							int greatestTimer = 0; greatestTimer+=0;

							for(int i = 0; i < numLines ; i++){
								if(theCache[numLines*setIndex+i].timer > 0){
									greatestTimer = theCache[numLines*setIndex+i].timer;
									evictSpot = numLines*setIndex+i;
								}
							}
							CEvict++; CMiss++;
							theCache[evictSpot].valid = '1';
							theCache[evictSpot].timer = 0;
							theCache[evictSpot].tag = validTag;
						}
					}	
				}
			}
			for(int i = 0; i < numLines ; i++){
				if(theCache[numLines*setIndex+i].valid == '1'){
					theCache[numLines*setIndex+i].timer += 1;
				}
			}
		}
		chPerLine++;
		
		// printf("Length of line : %zu \n", lengthOfLine);
	}

	for(int i = 0; i < 7 ; i++){
		// printf("This is the Address==> %x\n", addressList[i]);
	}

	// print_array(opperationList, 7);
	fclose(fp); // Close file
	/*if (line){
		free(line);
	}*/
	printSummary(CHit,CMiss,CEvict);
}






void populateCache(){
	/* This Func will popoulate the cache and count hits, misses, and evicts */
}



int main(int argc, char * arg[]){
	readFromCMD(argc, arg);

	readInFileAndPopulateCache();
	//populateCache();


    //printSummary(0, 0, 0);
    return 0;
}
