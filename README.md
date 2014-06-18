# This is the Playful Coming Soon sample app. It uses the following tech

## The stack:

- Play Framework 2.3.0  
- Scala 2.10.4  
- Twitter Bootstrap 3.1.1  
- JQuery 1.9.1  
- PostgreSQL 9.1-901.jdbc4  
- Heroku  
- sbt 0.13.5   
- webjars (for Bootstrap and JQuery)  

## To date it demonstrates the following aspects of Play:  
- Evolutions:  
	- Create and alter table  
- The CR part of CRUD using ScalaAnorm  
- Scala/Play localization  
- Partials  
- Scala templates / Play forms  

## What does it need to do:  
- Testing via spec2  
- Authorization to allow an admin to view who has registered for launch information and to download a CSV  
- Send an email to the interested party  
- Check for duplicate emails and update the person model rather than creating new (maybe?)  

