# oblech-project

Project made for Java course in TCS 22/23 winter semester. Multiplayer card game Oblech. 
Author Mikołaj Kot.

## How to compile/start/play
Project can be compiled using ./build command(latest version of JAVA and maven is needed) and run using ./run command.
## Instructions for playing the game in real live
Game can be played by 3 to 10 players
- Game is played with card deck of size 12 + 4 * numberOfPlayers
- At the start of the turn each player draws cards at the start of the game one
- One of players starts with saying one of possible sets (that would be listed below) witch that this player believes is drawn cards
- Next player needs say higher set or check the player before
- If player is checked and set is not on the table checked player for the rest of the game draws one more card at the start of the turn if set is on the table player that did the checking draws that card
- If player have 5/6 cards and draws one more they loses
- In the next turn starting player is one that drawn or player left of them(if they lost )
- Winner of the whole game is last player standing

### List of possible sets ranked from worst to best
- One card: Player says number of the card
- Pair: Player says number of the cards in onePair
- Two Pairs: Player says numbers of the cards in each onePair
- Straight: Player says the smallest card from Straight
- Three of a Kind : Player says number of the cards in Three of a Kind
- Full House : Player says numbers of the cards in Three of a Kind and in Pair
- Four of a Kind : Player says number of the cards in Four of a Kind
- Straight Flush : Player says number of the smallest card form Straight Flush and suit of cards
