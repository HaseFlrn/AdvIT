# Testfile

## Aufbau des Projektordners

Im Ordner "bin" sind die .class Files zu finden.
Im Ordner "src" sind die .java Files zu finden.
Im Ordner "test" sind zwei md-Dateien, in denen jeweils Ausgabe-Auszüge in den jeweiligen Konfigurationen abgedruckt sind.
In den Ordnern bin und src sind Packages definiert, die das Projekt nach Aufgabenteil a und b strukturieren.
Um den Code auszuführen muss die Main-Methode im jeweiligen Package in der App.java File ausgeführt werden.

## Zur Lösung

Um die Vorgabe zu überprüfen, dass Lok0 und Lok1 trotz unterschiedlicher Geschwindigkeiten abwechselnd durch den kritischen Abschnitt des Mittelgleises, werden zufällige int zwischen 0 und 1000 an Lok0 und Lok1 zu Beginn mitgegeben, die die sleep-Dauer bestimmen.
Zur Überprüfung der Vorgabe, dass Lok0 vor Lok1 in das Mittelgleis einfährt, kann hierbei manuell für Lok0 ein hoher Wert und für Lok1 ein geringer Wert für speed gesetzt werden.
Zur besseren Veranschaulichung ist Lok1 immer eingerückt.

### Zu Aufgabenteil a

Gemäß der Aufgabenstellung wurde der Ansatz des Erzeuger-Verbraucher-Problems verwendet.
Hierbei stellt Lok0 den Erzeuger und Lok1 den Verbraucher dar und werden in ihren jeweiligen Files als Subklasse von Thread implementiert.

### Zu Aufgabenteil b

Gemäß der Aufgabenstellung wurde der Ansatz der privaten Semaphore verwendet.
