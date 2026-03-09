ES1
Bacchette sono mutex
Filosofi thread, tutti numerati, serve dare loro diversi identificatori
Stesso codice per ogni filosofo, lock in array globale + modulo
Se implementato semplicemente rischio deadlock, va fatto in modo da risolvere
NO soluzione probabilistica, no variabili condizionali, solo lock

ES2
Implementare barriera con variabili condizionali (e thread e mutex)