# About Me
My name is Justin Shatwell. This Capstone Project Portfolio completes my Computer Science program at Southern New Hampshire University. I've worked professionally as a historian, writer, editor, and producer. I am now eager to add programmer to that list. My varied experience in the workforce gives me a unique perspective and the ability to wear many hats in any organization.
### Technical Skils
- Fluent in C++, Java, and Python
- Comfortable working with MySQL and Mongo databases
- Experience with Node.js and Angular

### Non-Technical Skills
- Strong writing and editing skills
- Project management experience
- Ability to learn quickly


To learn more about me, [click here](assessment.md)

# About This Project
For my Capstone Project, I was tasked with improving a program I created in a previous course. The revisions had to include improvements to the software engineering, the database, and the algorithms. I chose to upgrade a rudimentary cellphone app I created in my Mobile Architechure course. The first step in this process was to critique the original program and outline a plan of improvement. To see my full code review, please [click here](https://youtube.com).

# Project Improvements
The original app was meant to mimic an app that would display event listings. Because the project was geared towards teaching us the Android Studio platform, the app had to demonstrate an understanding a several core principles. Because of that, the end project was not a very realistic application. For instance, the app would both create the event listings and display them. In a real world scenario, the events would be created by the advertising organizations on some other platform. The app would merely display them. This is the core functionality I set out to build.

### Software Engineering Improvements
I split the original program into two separate ones. The cellphone app continues to display listings while creating listings is handled by a web app. Both the cellphone app and web app follow a Model-View-Controller architecture. They communicate via HTTP.

### Database Improvements
The original program used a single SQLite database housed on the cellphone. The improved program added a second, Mongo database on the web server. The Mongo database houses all of the events created on the web app. When the cellphone app contacts the web app, it downloads the listings to its local database. Additionally, I added functionality that ensures the cellphone only adds new events to its database, avoiding duplication. It is also capable of purging expired event listings automatically.

### Algorithm Improvements
The layout of the original program was created using static XML pages. Given that the app would not know how many listings it would need to display, a more repsonsive layout was necessary. I removed all of the XML layouts and replaced them with layouts built dynamcially using Java. The pages use Constraint Layouts, which allow you to place items relative to the edges of the page or to other objects.

To read the full project narrative, [click here](narrative.md)
