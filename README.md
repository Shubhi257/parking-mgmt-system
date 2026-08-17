🚗 Parking Management System:

A backend application built using Spring Boot, Spring Data JPA, and MySQL to 
manage parking-related operations through REST APIs.
The system provides a structured backend for managing parking slots, vehicles, 
parking entries, and parking records.

📌 Project Overview:

The Parking Management System is a Spring Boot REST API application designed 
to simplify parking management.
The application uses:
Spring Boot for backend development
Spring Web MVC for REST APIs
Spring Data JPA for database operations
Hibernate/JPA for ORM
MySQL for persistent data storage
Maven for dependency management and build
Java 21 as the programming language

🏗 Architecture:


![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/02adb00a56bdc04049724dbf8ae417a28454bbe2/Screenshot%202026-08-18%20005136.png)
                    

Request Flow:

![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/c1b39ccdb34ec1fc92bee0a111dbbf2ba49f10e3/Screenshot%202026-08-18%20005912.png)  

🛠Tech Stack:

![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/df282f38f84c295f3e8d37125c84daf1c9d69417/Screenshot%202026-08-18%20010729.png)

📂 Project Structure

A recommended structure for the Parking Management System is:

![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/e224be4a8fb4bb3225cdfff5a91d62a91317a0d5/Screenshot%202026-08-18%20011135.png)

🚘 Core Modules

The application can be organized around the following modules.

1. Vehicle Management:

Responsible for managing vehicles entering and leaving the parking facility.

→ Typical operations:
a) Add Vehicle
b) Get Vehicle
c) Update Vehicle
d) Delete Vehicle

2. Parking Slot Management:

Responsible for managing available and occupied parking slots.

→ Typical operations:
a) Create Parking Slot
b) View Parking Slots
c) Check Slot Availability
d) Update Slot Status

→ Example slot states:
a) AVAILABLE
b) OCCUPIED

3. Parking Management:

Responsible for maintaining parking records.

A parking record can contain information such as:
a) Vehicle
b) Parking Slot
c) Entry Time
d) Exit Time
e) Parking Status

🌐 REST API:

The backend exposes REST APIs using Spring Web MVC.

Typical API structure:
Parking slot APIs:
Parking APIs:

🗄 Database:

The application uses MySQL as its database.
Spring Data JPA is used to communicate with the database.
The project includes the MySQL Connector/J runtime dependency in pom.xml.

![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/f97cb92aae3c4b30ba9eaf0386382f43159bd790/Screenshot%202026-08-18%20013116.png)

⚙ Configuration:

Database configuration can be provided in:

src/main/resources/application.properties

Example:

![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/660da0c00a3e0e0c4a606ed8b40458f754c930bd/Screenshot%202026-08-18%20013747.png)

🧪 Testing:

The project includes Spring Boot testing dependencies.

![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/0d6e8f47a94935590abdbbf73dfebb4db41bbb4f/Screenshot%202026-08-18%20014253.png)

🔍 API Testing:

The APIs can be tested using:

Postman


📊 Working Flow:


![image alt](https://github.com/Shubhi257/parking-mgmt-system/blob/c2be4cf41427585f8ffba8c12a225b9799159500/Screenshot%202026-08-18%20014615.png)


