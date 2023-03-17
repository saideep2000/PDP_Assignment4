Employing the MVC approach, creating a user-interactive photo editor.
The basic functionalities of an editor are implemented.

------------------------------------------------------------------------------------------------------------------>
Design:

------------------------------------------------------------------------------------------------------------------>
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
10. Read and run commands from script-commands.

------------------------------------------------------------------------------------------------------------------>
How to use:
You can run the application by running the main method and typing in the command prompt,
or You can use your terminals/command prompt to run this java file and give the commands in the below fashion.
--------------------------------------->
(** Make sure there are no gaps in the front and back of the file & no extra gaps between words **)
For file type commands (here a sample file called commands.txt is kept in res folder) :
run res/command.txt
--------------------------------------->
For the command prompt interaction use these commands:
(You can use these exact commands and see the output which will be generated in this folder)
(** Make sure there are no gaps in the front and back of the commands & no extra gaps between words **)

(Use this to run the command file)
1. run res/command.txt

(If you have an image named hand in res folder)
2. load res/hand.ppm hand

(Use this loaded image to brighter and save it to hand-brighter)
3. brighten 10 hand hand-brighter

(Use this loaded image to vertically flip it and save it to hand-vertical)
4. vertical-flip hand hand-vertical

(Use this hand-vertical image to horizontally flip it and save it to hand-horizontal)
5. horizontal-flip hand-vertical hand-vertical-horizontal

(Use this loaded image to greyscale it to a red component and save it to hand-red-greyscale)
6. greyscale red-component hand hand-red-greyscale

(Use this loaded image to greyscale it to a red component and save it to hand-green-greyscale)
7. greyscale green-component hand hand-green-greyscale

(Use this loaded image to greyscale it to a red component and save it to hand-blue-greyscale)
8. greyscale blue-component hand hand-blue-greyscale

(Use this loaded image to greyscale it to a red component and save it to hand-value-greyscale)
9. greyscale value-component hand hand-value-greyscale

(Use this loaded image to greyscale it to a red component and save it to hand-luma-greyscale)
10. greyscale luma-component hand hand-luma-greyscale

(Use this loaded image to greyscale it to a red component and save it to hand-intensity-greyscale)
11. greyscale intensity-component hand hand-intensity-greyscale

(You can save the files you generated according to the names you have given to it)
12.save images/hand-brighter.ppm hand-brighter

(You can also overwrite the images)
13.load images/hand-brighter.ppm hand

(You can also split the images to red,green,blue)
14. rgb-split hand hand-red hand-green hand-blue

(You can also combine them back and save it to hand-combine)
15. rgb-combine hand-combine hand-red hand-green hand-blue

------------------------------------------------------------------------------------------------------------------>

