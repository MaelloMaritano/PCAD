#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <string.h>
#include <unistd.h>

//LOCKS
static pthread_mutex_t ch[]=
{
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER,
    PTHREAD_MUTEX_INITIALIZER
};

void* philo(void* ptr)
{
	int id=*(int*)ptr;
	printf("Il filosofo %d sta pensando\n", id);
	pthread_mutex_t* left_ch=&ch[id];
	pthread_mutex_t* right_ch=&ch[(id+5-1)%5];
	for(int i=1; i<=5; i++)
	{
		if(id==0)
		{
			pthread_mutex_lock(right_ch);
			printf("Il filosofo %d ha preso la bacchetta %d\n", id, (id+5-1)%5);
			sleep(0.5);
			pthread_mutex_lock(left_ch);
			printf("Il filosofo %d ha preso la bacchetta %d\n", id, id);
		}
		else
		{
			pthread_mutex_lock(left_ch);
			printf("Il filosofo %d ha preso la bacchetta %d\n", id, id);
			sleep(0.5);
			pthread_mutex_lock(right_ch);
			printf("Il filosofo %d ha preso la bacchetta %d\n", id, (id+5-1)%5);
		}
		
		printf("Il filosofo %d sta mangiando per la %d volta\n", id, i);
		
		pthread_mutex_unlock(left_ch);
		pthread_mutex_unlock(right_ch);
		
		printf("Il filosofo %d ha posato le bacchette\n", id);
	}
	return NULL;
}

int main()
{
	pthread_t ph[5];
	int id[]={0, 1, 2, 3, 4};

	for(int i=0; i<5; i++) pthread_create (&ph[i], NULL, philo, &id[i]);

	for(int i=0; i<5; i++) pthread_join(ph[i], NULL);

	printf("Tutti i filosofi hanno mangiato 5 volte!\n");
	return 0;
}