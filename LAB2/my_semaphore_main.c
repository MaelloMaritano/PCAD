#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>
#include "my_semaphore.h"

#define N 5

my_semaphore ch[N];
my_semaphore ticket;

void* philo(void* ptr)
{
	int id=*(int*)ptr;
	printf("Il filosofo %d sta pensando\n", id);
	sleep(0.5);
	
	for(int i=1; i<=5; i++)
	{
		my_sem_wait(&ticket);
		my_sem_wait(&ch[id]);
		printf("Il filosofo %d ha preso la bacchetta %d\n", id, id);
		sleep(0.5);
		my_sem_wait(&ch[(id+N-1)%N]);
		printf("Il filosofo %d ha preso la bacchetta %d\n", id, (id+N-1)%N);
		sleep(0.5);
		printf("Il filosofo %d sta mangiando per la %d volta\n", id, i);
		sleep(0.5);
		my_sem_signal(&ch[id]);
		my_sem_signal(&ch[(id+N-1)%N]);
		my_sem_signal(&ticket);
		printf("Il filosofo %d ha posato le bacchette\n", id);
	}
	return NULL;
}

int main()
{
	pthread_t ph[N];
	int id[N];
	for(int i=0; i<N; i++) id[i]=i;

	for(int i=0; i<N; i++) my_sem_init(&ch[i], 1);
	my_sem_init(&ticket, N-1);

	for(int i=0; i<N; i++) pthread_create (&ph[i], NULL, philo, &id[i]);
	for(int i=0; i<N; i++) pthread_join(ph[i], NULL);

	printf("Tutti i filosofi hanno mangiato\n");

	return 0;
}