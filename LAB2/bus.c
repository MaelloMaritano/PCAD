#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

#define N 10	//passeggeri
#define C 5	//posti del bus, C<N

volatile unsigned int passengers=0;	//passeggeri attualmente sul bus
volatile unsigned int off_var=0;	//per discesa dei passeggeri
pthread_mutex_t on_lock=PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t off_lock=PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t on_varcond=PTHREAD_COND_INITIALIZER;		//salita dei passeggeri
pthread_cond_t off_varcond=PTHREAD_COND_INITIALIZER;	//partenza del bus e discesa passeggeri

void* bus_func()
{
	while(1)
	{
		pthread_mutex_lock(&off_lock);
		//Il bus è pronto a far salire i passeggeri
		off_var=0;
		pthread_mutex_lock(&on_lock);
		pthread_cond_broadcast(&on_varcond);
		pthread_mutex_unlock(&on_lock);
		//Il bus aspetta che i passeggeri salgano
		while(passengers<C)
		{
			pthread_cond_wait(&off_varcond, &off_lock);
		}
		pthread_mutex_unlock(&off_lock);
		//Il bus parte
		printf("Il bus gira\n");
		sleep(1);
		//Il bus ritorna, ora i passeggeri possono scendere
		pthread_mutex_lock(&off_lock);
		off_var=1;
		pthread_cond_broadcast(&off_varcond);
		//Il bus aspetta che i passeggeri scendano
		while(passengers>0)
		{
			pthread_cond_wait(&off_varcond, &off_lock);
		}
		pthread_mutex_unlock(&off_lock);
	}
}

void* passenger_func()
{
	while(1)
	{
		//Un passeggero non può salire se il bus è già pieno o se stanno scendendo i precedenti passeggeri
		pthread_mutex_lock(&on_lock);
		while(passengers>=C || off_var==1)
		{
			pthread_cond_wait(&on_varcond, &on_lock);
		}
		//Un passeggero sale
		passengers++;
		printf("Passeggero salito\n");
		sleep(1);
		//Se è l'ultimo il bus può partire
		if(passengers==C)
		{
			printf("Sono saliti tutti i passeggeri\n");
			sleep(1);
			pthread_mutex_lock(&off_lock);
			pthread_cond_signal(&off_varcond);
			pthread_mutex_unlock(&off_lock);
		}
		pthread_mutex_unlock(&on_lock);
		//Il passeggero è sul bus
		pthread_mutex_lock(&off_lock);
		while(off_var==0)
		{
			pthread_cond_wait(&off_varcond, &off_lock);
		}
		pthread_mutex_unlock(&off_lock);
		//Il passeggero scende
		pthread_mutex_lock(&on_lock);
		passengers--;
		printf("Passeggero sceso\n");
		sleep(1);
		//Se è l'ultimo il bus può accogliere nuovi passeggeri
		if(passengers==0)
		{
			printf("Sono scesi tutti i passeggeri\n");
			sleep(1);
			pthread_mutex_lock(&off_lock);
			pthread_cond_signal(&off_varcond);
			pthread_mutex_unlock(&off_lock);
		}
		pthread_mutex_unlock(&on_lock);
	}
}

int main()
{
	pthread_t bus;
	pthread_t passenger[N];
	
	pthread_create (&bus, NULL, bus_func, NULL);
	for(int i=0; i<N; i++) pthread_create (&passenger[i], NULL, passenger_func, NULL);

	pthread_join(bus, NULL);
}