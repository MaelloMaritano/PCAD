#include "my_barrier.h"

/*
typedef struct my_barrier
{
	volatile unsigned int vinit;
	volatile unsigned int val;
	pthread_mutex_t lock;
	pthread_cond_t varcond;
} my_barrier;

Vi ricordiamo che la barriera blocca i thread che chiamano wait, finché vinit threads hanno fatto questa chiamata, a questo
punto la barriera sveglia tutti gli thread in attesa e torna al suo stato iniziale aspettando di nuovo vinit threads. La funzione
pthread_my_barrier_init serve a inizializzare la barriera impostando il valore v dentro vinit (il valore val della barriera
serve a contare il numero di threads che ha fatto wait fino a questo momento). Per questa ultima funzione, se v è uguale a 0, la
funzione deve restituire -1 per segnalare un problema. Ciascuna di queste due funzioni ritorna 0, se tutto è andato a buon fine.
*/

unsigned int pthread_my_barrier_init(my_barrier* mb , unsigned int v)
{
	if(v==0) return -1;
	mb->vinit=v;
	mb->val=0;
	pthread_mutex_init(&(mb->lock),NULL);
	pthread_cond_init(&(mb->varcond),NULL);
	return 0;
}

unsigned int pthread_my_barrier_wait(my_barrier* mb)
{
	pthread_mutex_lock(&(mb->lock));
	mb->val++;
	sleep(0.5);

	if(mb->val>=mb->vinit)
	{
		printf("%d processi sono arrivati!\n", mb->val);
		pthread_cond_broadcast(&(mb->varcond));
	}

	else while(mb->val<mb->vinit)
	{
		printf("Il processo %d aspetta...\n", mb->val);
		pthread_cond_wait(&(mb->varcond), &(mb->lock));
	}
	
	pthread_mutex_unlock(&(mb->lock));
}