# Book-library rental
____
*__This project is a test-project for book-library rental application backend. Provided functionality:__*
- *adding/updating/deleting author*
- *adding/updating/deleting book* 
- *adding/updating/deleting customer*
- *taking book if it's available*
- *returning book*
- *sending email notification to customers if their book rent time is exprired*
- *changing book rent time. This option doesn't affect the books already been taken*
## Used technologies
**Spring, Gradle, Hibernate, Quartz, Postgresql, Flywaydb, Swagger, Mapstruct, Lombok**
## How to start application
- Annotation processor should be enabled in Intellij IDEA settings.
- Set up database
    - install Postresql on your PC
    - choose DB port 5432 or leave it as default
    - set up `spring.datasource.username` and `spring.datasource.password` as `postgres` and `coolgame` **OR** change according fields in properties file if you want to set other ones
    - create database `library` in postresql
- Change server port if needed
- Register on mail service
    - for default mail service https://mailtrap.io/ is used. Please note, that outcoming messages from this service will be displayed in mail box, but they **WON'T** reach any email.
    - create an acoount on choosen service
    - change `spring.mail.protocol`, `spring.mail.username`, `spring.mail.password`, `spring.mail.host`, `spring.mail.port` in properties file as described in choosen mail service credentials
    - choose book rent time `library.borrow.days` and email notification's frequency `library.borrow.remainder` in properties file or leave them as default ( 30 days book rent time, notifications are send once per day) 
    
## How to use
- To see exposed endpoints and DTO information you can visit Swagger application page http://localhost:8181/swagger-ui.html after application is started. Port number should be picked from [How to start application](https://github.com/Hydropumpon/readme-test/blob/master/README.md#how-to-start-application) section.
- To use "author" functionality use endpoint **/library/author**
- To use "book" functionality use endpoint **/library/book**. Please note, if you want to add book's author - author must be persisted first
- To use "book rent" functionalty use endpoint **/library/borrow**.
