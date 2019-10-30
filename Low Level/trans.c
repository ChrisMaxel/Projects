// Authors: Nicolas Rose and Chris Maxel

/* 
 * trans.c - Matrix transpose B = A^T
 *
 * Each transpose function must have a prototype of the form:
 * void trans(int M, int N, int A[N][M], int B[M][N]);
 *
 * A transpose function is evaluated by counting the number of misses
 * on a 1KB direct mapped cache with a block size of 32 bytes.
 */ 
#include <stdio.h>
#include "cachelab.h"

int is_transpose(int M, int N, int A[N][M], int B[M][N]);

/* 
 * transpose_submit - This is the solution transpose function that you
 *     will be graded on for Part B of the assignment. Do not change
 *     the description string "Transpose submission", as the driver
 *     searches for that string to identify the transpose function to
 *     be graded. 
 */
char transpose_submit_desc[] = "Transpose submission";
void transpose_submit(int M, int N, int A[N][M], int B[M][N]){

    if (M == 32 && N == 32){
        for(int i = 0; i < 32; i += 8){
            for(int j = 0; j < 32; j += 8){
                for(int jj = 0; jj < 8; jj++){
                    int t1 = A[jj+j][i];
                    int t2 = A[jj+j][i+1];
                    int t3 = A[jj+j][i+2];
                    int t4 = A[jj+j][i+3];
                    int t5 = A[jj+j][i+4];
                    int t6 = A[jj+j][i+5];
                    int t7 = A[jj+j][i+6];
                    int t8 = A[jj+j][i+7];
                    B[i][jj+j] = t1;
                    B[i+1][jj+j] = t2;
                    B[i+2][jj+j] = t3;
                    B[i+3][jj+j] = t4;
                    B[i+4][jj+j] = t5;
                    B[i+5][jj+j] = t6;
                    B[i+6][jj+j] = t7;
                    B[i+7][jj+j] = t8;
                }
            }
        }
    }

    if(M == 64 && N == 64){
        for(int i = 0; i < 64; i += 4){
            for(int j = 0; j < 64; j += 4){
                for(int ii = 0; ii < 4; ii++){
                    for(int jj = 0; jj < 4; jj++){
                        B[ii+i][jj+j] = A[jj+j][ii+i];
                    }
                }
            }
        }
        
    }

    if(M == 61 && N == 67){ 
        int number = 23; 
            for(int j = 0; j < 61; j += number){
        for(int i = 0; i < 67; i += number){
                for(int ii = i; ii < number + i && ii < 67 ;ii++){
                    for(int jj = j; jj < number + j && jj < 61 ;jj++){
                        B[jj][ii] = A[ii][jj];
                    }
                }
            }
        }
    }




}

/* 
 * You can define additional transpose functions below. We've defined
 * a simple one below to help you get started. 
 */ 

/* 
 * trans - A simple baseline transpose function, not optimized for the cache.
 */
char trans_desc[] = "Simple row-wise scan transpose";
void trans(int M, int N, int A[N][M], int B[M][N])
{
    int i, j, tmp;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; j++) {
            tmp = A[i][j];
            B[j][i] = tmp;
        }
    }    

}

/*
 * registerFunctions - This function registers your transpose
 *     functions with the driver.  At runtime, the driver will
 *     evaluate each of the registered functions and summarize their
 *     performance. This is a handy way to experiment with different
 *     transpose strategies.
 */
void registerFunctions()
{
    /* Register your solution function */
    registerTransFunction(transpose_submit, transpose_submit_desc); 

    /* Register any additional transpose functions */
    //registerTransFunction(trans, trans_desc); 

}

/* 
 * is_transpose - This helper function checks if B is the transpose of
 *     A. You can check the correctness of your transpose by calling
 *     it before returning from the transpose function.
 */
int is_transpose(int M, int N, int A[N][M], int B[M][N])
{
    int i, j;

    for (i = 0; i < N; i++) {
        for (j = 0; j < M; ++j) {
            if (A[i][j] != B[j][i]) {
                return 0;
            }
        }
    }
    return 1;
}

