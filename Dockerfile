# cache as most as possible in this multistage dockerfile.
FROM maven:3.6-alpine as DEPS


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

WORKDIR /opt/app/Transversal
RUN mvn clean install
WORKDIR /opt/app/Mensajes_DTO
RUN mvn clean install
WORKDIR /opt/app/Mensajes_Transversal
RUN mvn clean install
WORKDIR /opt/app/Mensajes_Datos
RUN mvn clean install
WORKDIR /opt/app/Mensajes_Negocio
RUN mvn clean install
WORKDIR /opt/app/Mensajes_Api
RUN mvn clean install

WORKDIR /opt/app/Pilae_dto
RUN mvn clean install
WORKDIR /opt/app/Pilae_transversal
RUN mvn clean install
WORKDIR /opt/app/Pilae_dominio
RUN mvn clean install
WORKDIR /opt/app/Pilae_entidad
RUN mvn clean install
WORKDIR /opt/app/Pilae_infraestructura
RUN mvn clean install
WORKDIR /opt/app/Pilae_servicio
RUN mvn clean install
WORKDIR /opt/app/Pilae_controlador
RUN mvn clean install





# if you have modules that depends each other, you may use -DexcludeArtifactIds as follows
# RUN mvn -B -e -C org.apache.maven.plugins:maven-dependency-plugin:3.1.2:go-offline -DexcludeArtifactIds=module1

# Copy the dependencies from the DEPS stage with the advantage
# of using docker layer caches. If something goes wrong from this
# line on, all dependencies from DEPS were already downloaded and
# stored in docker's layers.

# use -o (--offline) if you didn't need to exclude artifacts.
# if you have excluded artifacts, then remove -o flag

# At this point, BUILDER stage should have your .jar or whatever in some path

FROM maven:3.6-alpine as BUILDER
WORKDIR /opt/app
COPY --from=deps /root/.m2 /root/.m2
COPY --from=deps /opt/app/ /opt/app
COPY Mensajes_Api/src /opt/app/Mensajes_Api
COPY Mensajes_Datos/src /opt/app/Mensajes_Datos
COPY Mensajes_DTO/src /opt/app/Mensajes_DTO
COPY Mensajes_Negocio/src /opt/app/Mensajes_Negocio
COPY Mensajes_Transversal/src /opt/app/Mensajes_Transversal
COPY Transversal/src /opt/app/Transversal
COPY Pilae_controlador/src /opt/app/Pilae_controlador
COPY Pilae_dominio/src /opt/app/Pilae_dominio
COPY Pilae_dto/src /opt/app/Pilae_dto
COPY Pilae_entidad/src /opt/app/Pilae_entidad
COPY Pilae_infraestructura/src /opt/app/Pilae_infraestructura
COPY Pilae_servicio/src /opt/app/Pilae_servicio
COPY Pilae_transversal/src /opt/app/Pilae_transversal

# use -o (--offline) if you didn't need to exclude artifacts.



FROM openjdk:11.0.11-slim
WORKDIR /opt/app
ADD Mensajes_Api/target/Mensajes_Api-0.0.1-SNAPSHOT.jar /usr/share/Mensajes_Api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/share/Mensajes_Api-0.0.1-SNAPSHOT.jar"]