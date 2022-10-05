Hello! So this is a group project I made over the summer with5 other hardworkers! This was in my software design course
where we had to use Clean Architecture, design patterns, and SOLID to construct any project we wanted. We decided on a
knock-off flappy bird! If you have seen my COVID_pygame project, you can see the great improvment since then.

Our program uses external libraries' org.apache.logging.log4j.1.2.api, apache.logging.log4j.core, de.mkammerer.argon2.jvm,
all at the highest level and SDK corretto-11 and language level 11. These libraries helped with logging and enhanced
switch code.

You can run our program from the Main class. When you run this class, you will get a FileNotFoundException. This calls
the userdata.txt file in case someone wants to log in, however, since there is no such file when you first run the
program, the exception will arise. You can still put in you input though. This is what creates the userdata.txt file and
allows the user to log back in after the program ends. The Main class was refactored based on the Phase 1 feedback to
maintain Clean Architecture. When you run the Main class, you will be prompted with a question "NEW or OLD?". This is
asking if you want to create a NEW account or login to an OLD account. Follow the instructions displayed on terminal. If
you enter a piece of information wrong, the program will let you know. If, by mistake, you meant to log in to your
account but typed NEW or meant to create a new account but typed OLD, just exit the program and start again. All user
input goes through several handler classes while staying within boundaries of Clean Architecture.

Once you log in, you will be shown some menu options to our game. It is suggested you view how to play first. Avoid the
black dots (aka "poison apples") and collect the yellow dots (aka "golden apples"). Every time you select a menu option,
the menu will always present itself again. Selecting menu option 4 will only log you out.

Once the game has ended, a popup window will display your score. Click "Ok" and head back to the terminal. If you click
the X on the game, the program will end, and you will have to log back in to play again. You will still have access to
the menu once the game has ended, and you can then view the Leaderboard. Only your highest score will enter the
leaderboard.

Once you play the game, you can go back to the terminal to play it again. Once you enter menu option 1 again, the game
GUI window will display but the cursor will still be set to the terminal (IntelliJ). As quickly as possible, click the
GUI window once you select the play game option, so you don't hit an obstacle once the game starts. Place your cursor on
where the game GUI window will appear based on the first time you played. The game window will always appear in the same
position. Make sure your IntelliJ window is not set to take the maximum size (do not expand the window beyond what it is
set to when you first open it) or else you will not be able to see the game.

Unfortunately, we do not have the menu set as a GUI like it was supposed to in the specification. A group member was
unable to complete this part of our program. We do not have the Admin account menu options set up as well. This is a
feature that could potentially be added in the future. However, overall we have managed to improve our GUI from phase 1
and improved on our code smells and Clean Architecture violations.

If you end the program, you log yourself out, but we have set up the program so that your account information gets
stored in our local database, so you can log back in even if you terminated the program.

Enjoy the program!

Notes for testing:
1. testProcessInfoSetOld only passes when run separately. When all the tests are run at once, it does
not pass.
2. Leaderboard testGetScoreMap and LBFileHandler tests will not pass if entries are added (for example, a new user is
created and plays a game) or removed from the LeaderBoard.txt file. These tests are based on the number of entries
currently in the leaderboard file.