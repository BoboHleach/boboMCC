# BoboMCC
Hunger Games Type minigame with abilities inspired by My Hero Academia.

# Important Game Concepts:
- Abilities are used with sticks
- Zone Center is 0,0
- World Border Damage is 2.5 hearts
- World Border Shrink Formula:
  - ```newWorldBorder = (OldworldBorder * 0.35) - 500```
- Game Format:
    - Class and Team Selection
    - Game Start
    - World Border Shrink
    - Grace Period end (5 Minutes after game starts)
        - No PVP in the First 5 Minutes
        - Abilities Enabled
# Abilities:
- Compress
    - Teleports a player away and returns after 15 seconds when hit
- Copy:
    - Copies abilities of user that was hit
- Collector
    - Normal (Right Click): Mines all ores in 5 x 5 x 5 Volume
    - Ultimate (Left Click): Mines all ores 10 x 10 x 10 Volume
- Warp:
    - Normal (Right Click): Teleports to the highest point in a random (20, 20) location
    - Ultimate (Player Hit): Teleports attacked player 25 blocks above ground
- Tank:
    - Right Click: Toggles Damage Storing
    - Player Hit: Releases damage on Player once toggle is off.