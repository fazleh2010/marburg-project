# Marburg Project 

This command-line tool is designed for creating nodes in graph database .
## Compile And Run
<p>The source code can be compiled and run using <em>Java 11</em> and <em>Maven</em>.</p>

```shell script
git clone https://github.com/fazleh2010/marburg-project.git 
```
build the jar file
```shell script
mvn clean package
```
- CREATE: add nodes in neo4j graph database .
- RELATION: add relation between nodes . 
- CREATE_RELATION: add nodes and relation in neo4j graph database .
- DELETE: delete nodes .

Run the system:
````shell script
java -jar <jar file> <parameter> 


Check the output in 

- http://localhost:7474/browser/ (if the program is run in local host)
- http://137.248.186.54:7474/browser/ (if run it from server)

## Developers
* **Mohammad Fazleh Elahi**
