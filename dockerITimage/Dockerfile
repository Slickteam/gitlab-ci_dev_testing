FROM mongo:4.2.6

ENV MONGO_INITDB_ROOT_USERNAME root
ENV MONGO_INITDB_ROOT_PASSWORD password
ENV MONGO_INITDB_DATABASE moviedb

ADD mongo-ci-init.js /docker-entrypoint-initdb.d/