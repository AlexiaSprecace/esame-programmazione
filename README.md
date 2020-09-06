# esame-programmazione
Progetto per l'esame di programmazione ad oggetti, Università Politecnica delle Marche, Anno 2019/2020 

## Introduzione

Il seguente progetto è un Web Service che permette ad un client (es. Postman) di interagire con Dropbox ed eseguire, mediante REST API di tipo GET e POST, operazioni come:
* Visualizzare tutti i link condivisi in formato JSON
* Ottenere statistiche su tali dati
* Ottenere dati filtrati attarverso vari parametri
* Ottenere statistiche sui dati filtrati

### Struttura dei dati
I dati sono strutturati mediante una serie di campi:
1. Nome del file/cartella
2. Percorso del file/cartella
3. Estensione del file
4. Tipo del file
5. Dimensione del file
6. Un attributo che distingue i file con link condiviso, dai file contenuti all'interno di una cartella con link condiviso (file accessibili ma senza link)

