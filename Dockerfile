# cache as most as possible in this multistage dockerfile.
FROM maven:3.6-alpine as DEPS

COPY parent-pom.xml /tmp/parent/pom.xml
RUN mvn -f /tmp/parent/pom.xml clean install

WORKDIR /opt/app
COPY Mensajes_Api/pom.xml Mensajes_Api/pom.xml
COPY Mensajes_Datos/pom.xml Mensajes_Datos/pom.xml
COPY Mensajes_DTO/pom.xml Mensajes_DTO/pom.xml
COPY Mensajes_Negocio/pom.xml Mensajes_Negocio/pom.xml
COPY Mensajes_Transversal/pom.xml Mensajes_Transversal/pom.xml
COPY Transversal/pom.xml Transversal/pom.xml
COPY Pilae_controlador/pom.xml Pilae_controlador/pom.xml
COPY Pilae_dominio/pom.xml Pilae_dominio/pom.xml
COPY Pilae_dto/pom.xml Pilae_dto/pom.xml
COPY Pilae_entidad/pom.xml Pilae_entidad/pom.xml
COPY Pilae_infraestructura/pom.xml Pilae_infraestructura/pom.xml
COPY Pilae_servicio/pom.xml Pilae_servicio/pom.xml
COPY Pilae_transversal/pom.xml Pilae_transversal/pom.xml


# you get the idea:
# COPY moduleN/pom.xml moduleN/pom.xml

COPY pom.xml .
RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline

# if you have modules that depends each other, you may use -DexcludeArtifactIds as follows
# RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=module1

# Copy the dependencies from the DEPS stage with the advantage
# of using docker layer caches. If something goes wrong from this
# line on, all dependencies from DEPS were already downloaded and
# stored in docker's layers.

# use -o (--offline) if you didn't need to exclude artifacts.
# if you have excluded artifacts, then remove -o flag
RUN mvn -B -e -o clean install -DskipTests=true

# At this point, BUILDER stage should have your .jar or whatever in some path
FROM openjdk:8-alpine
WORKDIR /opt/app
COPY --from=builder /opt/app/Pilae_controlador/Pilae_controlador-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD [ "java", "-jar", "/opt/app/Pilae_controlador-0.0.1-SNAPSHOT.jar" ]