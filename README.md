# JDBC_Innlevering

I denne oppgaven tar jeg i bruk JDBC for å opprette, fylle inn og hente ut data fra en to datebasetabeller: 
'TimetabeWesterdals.Subject' og 'TimetabeWesterdals.Teacher'.

Jeg bruker også en terminalbasert meny for å visuelt vise brukeren hva som skjer i koden

Dette README doukumentet inneholder instrukser som trengs for å få løsningen til å kjøre på din maskin.

**— Maven**

Når du åpner koden i ditt ønskede IDE er det viktig at du kjører enten kommadoen **mvn package**. Dette er for at filene 
som brukes for å opprette tabellene og fylle dem med data genereres ved bruk av maven.

Disse filene vil bli lagt i mappen target/textfiles.

**— Properties**

I mappen 'resources' en property-fil 'dbConfig'. Denne filen inneholder data som brukes av koden: navn på databasen, navnet på testdatabasen, host, brukernavn og passord for opprettelse og tilkobling til databasen.
Dette er felter som er nødvendig for deg å endre på for å få prosjektet til å kjøre på din maskin.

**— Kjør koden**

For å kjøre koden er alt du trenger å gjøre å kjøre klassen Main

### Enjoy


**Built With:**

+ [IntelliJ - IDE](https://www.jetbrains.com/idea/)
+ [Maven - Dependency håndtering og prosjekt format](https://maven.apache.org)
+ [Java - Språk](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
+ [MySQL Workbench - Databasebehandling og språk](http://mysqlworkbench.org)
+ [GitHub](https://github.com/MiniMarker/Server_Innlevering)