SISTEMA DI PRENOTAZIONE SALE
Progetto di gestione prenotazioni sale con utenti, sale e prenotazioni, integrando uno Scheduler per cancellare automaticamente prenotazioni scadute.
Funzionalità 

* Gestione Sale
* Gestione Utenti
* Gestione Prenotazioni: creazione e chiusura delle prenotazioni.

Architettura 
* Entity: Utente, Sala, Prenotazione
  - Relazioni: ogni prenotazione è collegata a un utente e a una sala (ManytoOne)
* Repository
* Service: logica di business per gestione di prenotazioni e sale.
* Scheduler
* Controller: esposizione API per operazioni CRUD  
