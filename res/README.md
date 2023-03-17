Employing the MVC approach, creating a user-interactive photo editor.
The basic functionalities of a photo editor are implemented.

---------------------------------------------------------------------------------------------------
Design:
Our design include (controller, model, view)

controller:
ImeController(interface):
This defines the methods which we will use to run the whole function.
ImeControllerImpl:
It takes inputs and process it and give instructions to model and view accordingly.

model: (Image Entity)
ImeModel(interface):
This represents an image entity which include functionalities which we can use it and manipulate 
accordingly.
ImeModelImpl:
This will implement all the functionalities mentioned and returns appropriate changes.

View:
ImeView
ImeViewImpl
This is useful to communicate with the customer. And display appropriate messages and status.

---------------------------------------------------------------------------------------------------
Features:
1. Load images from the local disk.
2. Brighten it.
3. Flip it vertically.
4. Flip it horizontally.
5. greyscale the image into (red,green,blue,value,luma,intensity) components.
6. save the images.
7. Overwrite the image.
8. rgb-split
9. rgb-combine

---------------------------------------------------------------------------------------------------
How to use:
You can run the application by running the main method and typing in the command prompt,
or You can use your terminals/command prompt to run this java file and give the commands in the 
below fashion.

---------------------------------------
(** Make sure there are no gaps in the front and back of the file & no extra gaps between words **)
For file type commands (here a sample file called commands.txt is kept in res folder) :
res/commands.txt

---------------------------------------
For the command prompt interaction use these commands:
(You can use these exact commands and see the output which will be generated in this folder)
(** Make sure there are no gaps in the front and back of the commands & no extra gaps between 
words **)

(If you have an image named book in res folder)
1. load res/book.ppm book

(Use this loaded image to brighter and save it to book-brighter)
2. brighten 10 book book-brighter

(Use this loaded image to vertically flip it and save it to book-vertical)
3. vertical-flip book book-vertical

(Use this book-vertical image to horizontally flip it and save it to book-horizontal)
4. horizontal-flip book-vertical book-vertical-horizontal

(Use this loaded image to greyscale it to a red component and save it to book-red-greyscale)
5. greyscale red-component book book-red-greyscale

(Use this loaded image to greyscale it to a red component and save it to book-green-greyscale)
6. greyscale green-component book book-green-greyscale

(Use this loaded image to greyscale it to a red component and save it to book-blue-greyscale)
7. greyscale blue-component book book-blue-greyscale

(Use this loaded image to greyscale it to a red component and save it to book-value-greyscale)
8. greyscale value-component book book-value-greyscale

(Use this loaded image to greyscale it to a red component and save it to book-luma-greyscale)
9. greyscale luma-component book book-luma-greyscale

(Use this loaded image to greyscale it to a red component and save it to book-intensity-greyscale)
10. greyscale intensity-component book book-intensity-greyscale

(You can save the files you generated according to the names you have given to it)
11.save images/book-brighter.ppm book-brighter

(You can also overwrite the images)
12.load images/book-brighter.ppm book

(You can also split the images to red,green,blue)
13. rgb-split book book-red book-green book-blue

(You can also combine them back and save it to book-combine)
14. rgb-combine book-combine book-red book-green book-blue

---------------------------------------------------------------------------------------------------
To generate the images provided in the submission, run the commands.txt file using the command
"run res/commands.txt".
Some extra test commands are added at the end of the commands.txt file resulting in 2 extra images 
generation.

---------------------------------------------------------------------------------------------------

COPYRIGHT: THE IMAGE USED IN THIS PROJECT IS CREATED BY AJAY AND SAI AND ARE AUTHORIZED TO USE IT.