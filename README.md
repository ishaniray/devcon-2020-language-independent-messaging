# devcon-2020-language-independent-messaging
 A proof-of-concept for the DevCon India 2020 talk titled 'Paleolithic, Neolithic, Monolithic...Microlithic?'.
 
 
 
#For Spring Cloud Dataflow:

Start skipper by cmd=> java -jar spring-cloud-skipper-server-2.3.0.BUILD-SNAPSHOT.jar
Start server by cmd=> java -jar spring-cloud-dataflow-server-2.4.0.BUILD-SNAPSHOT.jar

For shell either use the UI -> http://localhost:9393/dashboard/#  or cmd


#To access h2 database while using SCDF:

In the command line of shell while deploying stream:
stream deploy --name crawler --properties "app.source.spring.h2.console.enabled=true,app.source.spring.h2.console.path=/h2,app.source.server.port=9097"

OR

From UI while deploying the stream add the properties in freetext:
app.source.server.port=9097
app.source.spring.h2.console.enabled=true
app.source.spring.h2.console.path=/h2
