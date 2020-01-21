#include <stdio.h>
#include <string.h>


int startingPos, width, height, strPixelLength;


int printOutCmdArgs(int argc, char *argv[]) {
    printf("\n"); int counter;
    if(argc == 1) {
        printf("No Extra Command Line Argument Passed Other Than: %s \n", argv[0]);
    }
    if(argc >= 2) {
        printf("Number Of Arguments Passed: %d \n\n",argc);
        printf("----  Command Line Arguments Passed  ----\n");
        for(counter = 0 ; counter < argc ; counter++)
            printf("argv[%d] ==> %s \n",counter,argv[counter]);
    }
    printf("\n"); return 0;
}


void getPictureInfo(char *argv[], FILE *file){
    file = fopen(argv[1],"r"); // Open bitmap file (.bmp)
    fseek(file, 10, SEEK_SET); // The offset or starting address of the byte where the bitmap image data (pixel array) can be found is at 10(dec)
    fread(&startingPos, 4, 1, file);
    printf("\nStrt Pos ==> %d \n", startingPos);

    fseek(file, 18, SEEK_SET); // The Width is stored 18 bytes into the file
    fread( &width, 4, 1,file);
    printf("Width    ==> %d \n", width);

    fseek(file, 22, SEEK_SET); // The height is stored 22 bytes into the file
    fread(&height, 4, 1, file);
    if ( height < 0) height *= -1; // Makes height positive
    printf("Height   ==> %d \n", height);

    //fclose(file); // Close bitmap file (.bmp)
}


int main(int argc, char *argv[]) {
    FILE *bmpFile;
    bmpFile = fopen(argv[1],"r");

    char *secretMessage = argv[3];

    printOutCmdArgs(argc, argv);
    getPictureInfo(argv, bmpFile);

    int totalPixels = width * height * 4 ;
    printf("totalPixels ==> %d\n", totalPixels);
    int pixelItorator = startingPos; // 54
    int eachColorValue = 0;
    int lengthOfStr = (int)strlen(secretMessage);
    char eachChar;

    FILE *newBMPFile; // new bmp file
    newBMPFile = fopen(argv[2],"w");

    fseek(bmpFile, 0, SEEK_SET);
    char header[54];
    fread(&header,54,1,bmpFile);
    fwrite(&header,54,1,newBMPFile); // write in the header

    int charIterator = 0;
    int bitCounter = 0;
    int nullCounter = 0;

    for (int i = 0; i < totalPixels; i++) { // Loop that iterates over all of the pixels in the bmp file
        fseek(bmpFile, pixelItorator, SEEK_SET);
        fread(&eachColorValue, 1, 1, bmpFile);
//        printf("This is temp==> %d\n",temp);
//        printf("This is each color Value => %d\n",eachColorValue);

        if( charIterator <= lengthOfStr && (i % 4 != 3)) {
            eachChar = secretMessage[charIterator];
            char lsb = (eachChar >> bitCounter) & 1;
            //printf("This is bin str ==> %x\n",binStr[lastCounter]);
            eachColorValue = eachColorValue >> 1;
            eachColorValue = eachColorValue << 1;
            eachColorValue = eachColorValue | lsb;
            fwrite(&eachColorValue, 1, 1, newBMPFile);
            bitCounter++;
            if (bitCounter > 7){
                charIterator++;
                bitCounter = 0;
            }
        }
        else if(charIterator >= lengthOfStr && nullCounter < 8){
            eachColorValue = eachColorValue >> 1;
            eachColorValue = eachColorValue << 1;
            fwrite(&eachColorValue, 1, 1, newBMPFile);
            nullCounter++;
        }
        else{
            fwrite(&eachColorValue, 1, 1, newBMPFile);
        }
        pixelItorator++;
    }

    fclose(newBMPFile);
    fclose(bmpFile);
    return 0;
}
