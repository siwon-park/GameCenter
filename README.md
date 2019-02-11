# GameCenter
An android app that allows the user to play up to 3 games. Group Project for CSC209 coded in Java.
Instructions to install can be found in folder Phase2.

# Login/Register Page
   - Users can log in to their accounts (saved in HashMap), or
   - Register if they don't have one
   - Once logged in, directed to main game page, where user can choose one of three games
# Each Game Page
   - Can start a new game/load saved game/load previous game
   - Can check scoreboards(one for Leaderboard, one for User Scoreboard)
   - Can sign out of account, returning to log in page.
   - Can rate the app (app is not published so it only leads to the PlayStore).
# Sliding Tiles Game
   - Once the user starts a new game...
   - Can choose from three different board sizes (3x3/4x4/5x5).
   - Then user has option to use an image as the board tiles by choosing from the images provided 
      or import image from phone. If this option is skipped, starts a generic game with number tiles
   - Brought back to main game page when solved
   - Implements Autosave, Save, and Undo
# Matching Cards Game
   - Once the user starts a new game...
   - Can choose from three different board sizes (2x2/4x4/6x6).
   - Then the game begins; can flip two cards at a time until no cards remain
   - Brought back to main game page when solved
   - Implements Autosave, Save
# Sudoku Game
   - Once the user starts a new game...
   - A random Sudoku board is generated; can enter input until board is solved.
   - Brought back to main game page when solved
   - Implements Autosave, Save, and Undo
