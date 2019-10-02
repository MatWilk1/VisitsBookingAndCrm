# visits-booking-and-crm
Spring MVC and Hibernate web app. 
Allows customers to book the visit after fill the form with personal data and visit date.
Data are stored in database (SQL script to create database is located in repository). I used MySQL database.

Additionally app contains basic CRM which allows to display all customer and their visits (with delete, update, add visit and search functionality).

To see pages with customers and visits lists user has to login using username and password (stored in database and encrypted by bcrypt). For tests, attached SQL script creates two profiles with username/pasword: admin1/admin1 and admin2/admin2.

I used AOP code for storing logs about acts of deleting users and visits.