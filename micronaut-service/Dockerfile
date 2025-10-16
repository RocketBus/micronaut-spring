FROM ghcr.io/graalvm/native-image-community:21 AS builder

WORKDIR /app

COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
COPY src/ src/

RUN ./mvnw clean native:compile -Pnative -DskipTests -Dfile.encoding=UTF-8

FROM debian:bookworm-slim AS runner

RUN apt-get update && \
    apt-get install -y --no-install-recommends zlib1g=1:1.2.13.dfsg-1 && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /app

COPY --from=builder /app/target/demo ./service

EXPOSE 8080

ENTRYPOINT ["./service"]
