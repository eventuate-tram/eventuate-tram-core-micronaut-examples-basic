. ./_set-env.sh

export DATASOURCE_URL=jdbc:mysql://${DOCKER_HOST_IP}/eventuate
export DATASOURCE_USERNAME=mysqluser
export DATASOURCE_PASSWORD=mysqlpw
export DATASOURCE_DRIVERCLASSNAME=com.mysql.jdbc.Driver

export EVENTUATELOCAL_CDC_DB_USER_NAME=root
export EVENTUATELOCAL_CDC_DB_PASSWORD=rootpassword
