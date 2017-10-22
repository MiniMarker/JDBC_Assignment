**— Innlevering 1—** 

I denne oppgaven tar jeg i bruk JDBC for å koble til en database, oppretter tabeller og populerer den med innhold fra filene ’subjects.csv’ og ’teachers.csv’ i prosjektmappen.
Dette dokumentet inneholder instrukser som trengs for å få prosjektet til å kjøre på din maskin. 

**— Steg 1**

Åpne dokumentet ‘dbConnectionCredentials.csv’ i textEdit/notepad eller en annen egnet  og endre feltene til det som passer for deg og din maskin.

Format:
dbName,host,username,password

Eksempel:
TimetableWesterdals,localhost,root,pass


**— Steg 2**

Deretter er det bare å åpne prosjektet i ønsket IDE.

Metodekallene:

dbConnection.setupCheck();
dbHandler.dropTablesIfExists();
dbHandler.createTables();

I prosjektets Main-klasse er alle nødvendige for å få programmet til å kommunisere med databasen og opprette de nødvendige tabellene.


inputHandler.addSubjectDataFromFile();
inputhandler.addTeacherDataFromFile();

Populerer tabellene med data fra filene ’subjects.csv’ og ’teachers.csv’ i prosjektmappen.


**Built With:**

	•	IntelliJ - IDE
	•	Maven - Dependency håndtering og prosjekt format.
	•	Java - Språk.
	•	MySQL Workbench - Databasebehandling og språk.
