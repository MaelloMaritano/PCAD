#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

#define C 3	//posti del bus
#define N 5	//passeggeri, N>C

static pthread_mutex_t on_lock=PTHREAD_MUTEX_INITIALIZER;	//blocca salita
static pthread_mutex_t go_lock=PTHREAD_MUTEX_INITIALIZER;	//blocca salita
static pthread_cond_t on_varcond=PTHREAD_COND_INITIALIZER;	//si può salire
static pthread_cond_t go_varcond=PTHREAD_COND_INITIALIZER;	//può partire
volatile unsigned int current=0;

void* bus_func()
{
	while(1)
	{
		pthread_mutex_lock(&go_lock);
		printf("Il bus è al capolinea\n");
		sleep(1);
		pthread_cond_broadcast(&on_varcond);

		//aspetta che C passeggeri salgano
		pthread_cond_wait(&varcond, &lock);

		//fai il giro
		printf("GIROO!\n");
		sleep(3);

		//fai scendere i passeggeri
		pthread_cond_broadcast(&varcond);
		pthread_mutex_unlock(&lock);
	}
}

void* passeggero_func()
{
	while(1)
	{
		pthread_mutex_lock(&lock);
		//se c'è posto sul bus sali
		pthread_cond_wait(&varcond, &lock);	//aspetta che il bus sia al capolinea e sia vuoto

		if(current<C)
		{
			current++;
			printf("Un passeggero sale\n");
			sleep(2);
		}
		if(current==C)
		{
			pthread_cond_broadcast(&varcond);
			printf("Il bus è pieno\n");
		}

		//aspetta fine del giro
		pthread_cond_wait(&varcond, &lock);

		//scendi
		printf("Un passeggero scende\n");
		current--;
		if(current==0)
		{
			pthread_cond_broadcast(&varcond);
			printf("Tutti i passeggeri sono scesi\n");
			sleep(3);
		}
		pthread_mutex_unlock(&lock);
	}
}

int main()
{
	pthread_t bus;
	pthread_t passeggero[N];
	
	pthread_create (&bus, NULL, bus_func, NULL);
	for(int i=0; i<N; i++) pthread_create (&passeggero[i], NULL, passeggero_func, NULL);
	
	pthread_join(bus, NULL);
}