FROM ubuntu:16.04
RUN apt-get -y update && apt-get -y install git && apt-get -y install openjdk-8-jdk && apt-get -y install maven && apt-get -y install junit4
RUN git clone https://github.com/TheSpyGeek/AppJavaPanda.git && cd AppJavaPanda && mvn package
CMD cd AppJavaPanda/ressources && ls && java -jar /AppJavaPanda/target/AppJavaPanda-1.0-jar-with-dependencies.jar
