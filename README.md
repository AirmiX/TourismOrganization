## Uputstvo za korišćenje

Otvoriti Terminal (ili Command Prompt na Windows-u) i pozicionirati se na željenom direktorijumu.

### Kloniranje repozitorijuma

Klonirati repozitorijum u trenutni direktorijum.
```
git clone https://takmicenje.cloud.levi9.com/AirmiX/TourismOrganization.git
```

### Kompajliranje programa

Kreirati novi direktorijum za smeštanje kompajliranih klasa.
```
mkdir bin
```
Kompajlirati klase (uz zadavanje classpath-a i izlaznog direktorijuma)
```
javac -cp lib/*:src -d bin src/app/App.java
```

### Pokretanje programa

Pokrenuti program uz prosleđivanje 2, 3 ili 4 parametra.
```
java -cp lib/*:bin app.App input/input.json output.json
```
```
java -cp lib/*:bin app.App input/input.json output.json 3
```
```
java -cp lib/*:bin app.App input/input.json output.json 2 "Sekspirova 9, Novi Sad, Serbia"
```