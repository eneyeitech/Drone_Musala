# Instructions

## Database
Using MySQL create a local database name drone

## Running the code
Move into the root directory and run the code

#### Linux/MacOS
./mvnw spring-boot:run

#### Windows:
mvnw spring-boot:run

## REST API Paths
#### registering a drone
localhost:8080/api/drone/new
`{
"serial_number":"abu",
"weight_limit":300
}`
#### loading a drone with medication items
api/medication/{drone_serial_number}
`{
"name":"Omeprazole",
"weight": 100,
"code":"ome"
}
`
#### checking loaded medication items for a given drone
api/medications/{drone_serial_number}
#### checking available drones for loading
localhost:8080/api/drones/loading
#### check drone battery level for a given drone
api/battery/{drone_serial_number}


# Thank You