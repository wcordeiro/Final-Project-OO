Object Oriented Programming 2015
CypherBreaker Project
Wilson Cordeiro

This project is also available in Github: https://github.com/wcordeiro/Final-Project-OO

This application breaks a Rail Fence Cyphers.
The encrypted text must be in a file on the same directory as the .jar, the program will ask
for the name of the file.
The program creates N threads where N is the number of caracters on the text divided by two.
Each Thread test a key and set the value on a BlockingQueue, a consumer thread evaluates which
key has the best result.
The program print the best result on screen.
In order to execute the program one must execute the .jar file or compile the whole code inside the src 
directory and then execute.
The program only ask for the fileName with the encrypted text the rest is automatically done.

——————————————————————————————————————————————
Files:
docs (javadocs)
build.xml
design.png (UML diagram)
src (java files)

Main() method in the class RailFence.

——————————————————————————————————————————————
Test case:
Filename: 
inputfile.txt
Encrypted text >SATTMTSLSOETAEEPHHCGTTEA
Decrypted text >
Result {
	key: 5
	text: STOPTHEMATTHECASTLEGATES
	score: 2.1931894091917146
}