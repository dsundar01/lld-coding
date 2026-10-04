## Tic Tac Toe

Flow 
- players play alternatively and use X or O and there are winning patterns.
- winning patterns : horizontal, diagonal, vertical

Entity (Story)
- Game Tic Tac Toe has multiple board and 2 players for each.

Class Design (Start with Facade and working)

Flow
- multiple players can play the game max 6
- board preset by the admin
- player do the dice rolling
- player who reached the start end of board is winner.
- board can have booster and loser and normal box

Entity(Story)
- Game has board with preset boosters and negative and normal box
- Player play one by one

Class Design
- rules should be within the class.
