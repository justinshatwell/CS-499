# Overview:

This artifact I chose to work on for my capstone project is a cellphone app I developed in CS-360 Mobile Architecture and Programming. It is an Android app that was meant to mimic an app that displays event listings. The original artifact was meant to demonstrate core competencies rather than operate as a functioning app. The goal for this capstone project is to transform the app into something that is more practical and usable. This process will involve changing the layout of the app, adjusting the overall architecture of the program, improving the underlying algorithms and code, expanding the database, and developing a program to manage that database.

# Software Design and Engineering Improvements
The original artifact was a standalone program. It both created and displayed the event listings. This is not very realistic. In a real-world setting, the app would only display listings. The listings themselves would be created by the advertising organizations and uploaded to the service somehow. As such, the core goal of this project was to split the original artifact into two programs: a cellphone app that displays listings and a web app that creates them. The two halves communicate via HTTP connection, allowing the app to download new listings. To achieve this, I made several improvements.

### Creating the Web Server
The first step was to set up a web server. I did this using Node.js and Express. The website is very simple, with just a landing page that is not meant to ever be served. Rather, the role of the website is to set up communication with a Mongo database. I created a RESTful API to accept user requests at an API endpoint. The web server then communicates with the database and returns an array of JSON objects.

### Creating the Angular App
Users interact with the website through an Angular app. The app lists all events currently in the database and allows users to create new ones. This is done through a simple form. The app gathers the data from the fields and compiles them into a JSON object that can be added to the Mongo database. 

### Setting Up Communication
In order to allow the cellphone app to contact the website, I learned to use the Volley library. Volley is a Java library that allows an app to make HTTP requests. In order to use this library, I added it as a dependency in my build Gradle and added a permission to the app manifest to allow access to the Internet. I then wrote a function in the app that set a GET request to the API endpoint and received back an array of JSON objects.

# Database Improvements
The original artifact used a single SQLite database that was hosted on the cellphone. For this project, I wanted to use two databases, the one on the phone and one on the web server. The idea was to limit the amount of server traffic by having the app store events locally. It would only contact the web server for updates and download only events it did not already have stored locally. I did not fully realize this goal. Currently, the app downloads all events from the web server every time it makes contact. Improving this will be the next step in this development process. 

### Update Function
The app calls the database update function every time the event listing screen is loaded. The function uses the Volley library to make the GET request and receives the response as an array of JSON objects. The function also makes a list of event titles currently in its database. It then compares the newly received event listings to the list. Any that are not present are added to the local SQLite database. The rest are discarded to avoid duplicating listings.

### Purge Function
The app also has a purge function to delete event listings that are passed. When the function is called, the creates a Date object for each event in the database using the integers stored in their month, day, and year fields. The app then adds a day to each of these Dates, then compares it to the current date. Any that are less than the current date are deleted. This process ensures that each event listing is automatically deleted the day after the event occurs.

# Algorithm Improvements
The layout of the original artifact was constructed using static XML pages. This was not a good fit for this project as the app would not have any way of knowing how many events it might receive from the web server. Therefore, the layout needed to be developed dynamically.
	I entirely removed the XML layouts from the program and constructed the layouts with Java instead. The pages utilize Constraint Layouts, which allow you to place items on the page in relation to the boundaries of the screen or other objects. 
	The event listing page uses two nested Constraint Layouts. Each individual listing is its own layout, consisting of an ImageView for the picture and two TextViews, one for the title and one for the date. The elements can be individually styled and arranged within the listing layout. Each listing is then placed in the out Constraint Layout, which arranges them into a list.

# Biggest Areas of Learning
I feel that my biggest accomplishment in this project was enabling communication between the web server and the cellphone app. I have done projects before that use a Model-View-Controller architecture and feature communication between different layers of the program. However, I've never created a program before that relies on contacting an entirely separate program to operate. This project is effectively two different MVC programs connected by HTTP.
	I also gained a great deal of experience working with Angular and Android Studio. Most of my experience has been in developing business logic. I'm much more at ease working with the Model and the Controller than with the View. Building the layouts for the web app and cellphone app, as rudimentary as they are, was a major step for me.  
