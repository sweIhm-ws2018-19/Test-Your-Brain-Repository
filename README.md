# TEST YOUR BRAIN

### Travis-CI Build-Status
[![Build Status](https://travis-ci.org/sweIhm-ws2018-19/skillproject-fr-34.svg?branch=master)](https://travis-ci.org/sweIhm-ws2018-19/skillproject-fr-34)


### Description
Bei "Test your Brain" handelt es sich um einen Allgemeinwissenstest, der Ihnen ermöglicht Ihr Allgemeinwissen substantiell zu verbessern. Die gestellten Fragen können über die Kategorie Ihren Wissenslücken angepasst werden, oder durch zufällige Fragen neue graue Stellen aufzeigen. Auch die Funktion die Schwierigkeitsstufe anzupassen ermöglicht, die Fragen an Ihren Wissensstand anzupassen um dadurch Frustration vorzubeugen. 

Um immer eine genaue Übersicht über ihre Leistungen bzw. Wissensstand zu haben, wird nach jeder Partie die erzielte Punktzahl ausgegeben. Ausserdem gibt es die Funktion sowohl ihre letzten Punktzahlen als auch den besten jemals erzielten Score zu begutachten zu können.

### Deployment

Build with Maven:
```
mvn org.apache.maven.plugins:maven-assembly-plugin:2.6:assembly -DdescriptorId=jar-with-dependencies package
```
go to Frontend:

https://developer.amazon.com/alexa/console/ask

upload jar with dependencies here:

https://aws.amazon.com/de/education/awseducate/

make sure to connect Frontend with aws!
