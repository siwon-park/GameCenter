GROUP_0532 PHASE 1

### INSTRUCTIONS:

1. Install Android Studio if you haven't already.
2. Get the URL for the starter code repository from MarkUs and clone it using Android Studio.
        - URL: https://markus.teach.cs.toronto.edu/git/csc207-2018-09-reg/group_0532
        - Answer "Yes" to whether you want to create a new Android Studio project.
        - Select "Import project from external model" and click Next
        - Add the project to the Gradle project path
        - If you get the  message "Unregistered VS root detected",  choose "Add root"
3. The project should now build.
4. Create an Android Virtual Device within Android Studio.
        - Select a Pixel2 smartphone to emulate, specifying the device OS as Android 8.1 API 27.
        - Launch the virtual device and ensure it loads correctly.
        - There may be a possible lag with your emulator. For every button you press, please give
        a few seconds to wait for the phone to respond. For instance, when you see "You Win" at the
        end of a game, wait till the pop-up has appeared and disappeared before clicking anything
        else in order for the game to register properly.
5. Now run it, and it should open to the Log In page (see Functionality below).

### FUNCTIONALITY:

Login/Register Page
    - Users can log in to their accounts (saved in HashMap), or
    - Register if they don't have one
    - Once logged in, directed to Sliding Tiles game page
Game Page
    - Can start a new game/load saved game/load previous game
    - Can check scoreboards(one for Leaderboard, one for User Scoreboard)
    - Can sign out of account, returning to log in page.
    - Can rate the app (if the app is published, it would open directly to the app page in Google
      Play Store, however it is not so it currently only leads to the Play Store homepage).
New Game
    - Once the user starts a new game...
    - Can choose from three different board sizes (3x3/4x4/5x5).
    - Then (AS BONUS) user has option to use an image as the board tiles by choosing from the 
    images provided/ import image from phone.
    - If this option is skipped, starts a generic game with number tiles
During the Game
    - Can undo moves until the initial board setting is reached.
    - Can save game to access later.
    - Moves made are autosaved into current game savefile.