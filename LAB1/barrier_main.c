#include "my_barrier.h"

#define N_THREAD 10

my_barrier mb;

void* barrier_call(void*)
{
	pthread_my_barrier_wait(&mb);
}

int main()
{
	pthread_my_barrier_init(&mb, N_THREAD);

	pthread_t ph[N_THREAD];
	for(int i=0; i<N_THREAD; i++) pthread_create (&ph[i], NULL, barrier_call, NULL);
	for(int i=0; i<N_THREAD; i++) pthread_join(ph[i], NULL);

	printf("END\n");
	return 0;
}