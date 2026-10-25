# Dungeons

Build a simple 2D console game featuring heroes, enemies, treasures, and spells. Your hero explores dungeons, fights enemies, collects powerful items, and finds the exit.

---

## Heroes and Enemies — the `Actor` interface

Both `Hero` and `Enemy` must implement the `Actor` interface.

### `Hero`

| Member | Description |
|---|---|
| `Hero(String name, int health, int mana)` | Constructor |
| `String getName()` | Returns the hero's name |
| `int getHealth()` | Returns the hero's current health |
| `int getMana()` | Returns the hero's current mana |
| `boolean isAlive()` | Returns `true` if health > 0 |
| `takeHealing(int healingPoints)` | Adds `healingPoints` to health (capped at max health). Does nothing if the hero is dead |
| `takeDamage(int damagePoints)` | Reduces health by `damagePoints` (minimum 0) |
| `takeMana(int manaPoints)` | Adds `manaPoints` to mana (capped at max mana) |
| `equip(Weapon weapon)` | Equips the hero with this weapon, replacing the current one only if the new weapon is stronger. Heroes start unarmed |
| `Weapon getWeapon()` | Returns the hero's current weapon |
| `learn(Spell spell)` | The hero learns this spell, replacing the current one only if the new spell is stronger. Heroes start with no spell |
| `Spell getSpell()` | Returns the hero's current spell |
| `int attack()` | Returns the hero's attack power — the stronger of weapon damage and spell damage. To use a spell, the hero must have at least as much mana as its cost; mana is consumed on use. The hero may have only a weapon, only a spell, or both |

### `Enemy`

| Member | Description |
|---|---|
| `Enemy(String name, int health, int mana, Weapon weapon, Spell spell)` | Constructor |
| `String getName()` | Returns the enemy's name |
| `int getHealth()` | Returns the enemy's current health |
| `int getMana()` | Returns the enemy's current mana |
| `boolean isAlive()` | Returns `true` if health > 0 |
| `Weapon getWeapon()` | Returns the enemy's weapon |
| `Spell getSpell()` | Returns the enemy's spell |
| `takeDamage(int damagePoints)` | Reduces health by `damagePoints` (minimum 0) |
| `int attack()` | Returns the enemy's attack power — same logic as `Hero.attack()` |

---

## Treasures — the `Treasure` interface

All artefacts (`Weapon`, `Spell`, `HealthPotion`, `ManaPotion`) must implement `Treasure`.

### `Weapon`

| Member | Description |
|---|---|
| `Weapon(String name, int damage)` | Constructor |
| `String getName()` | Returns the weapon's name |
| `int getDamage()` | Returns the weapon's damage |
| `String collect(Hero hero)` | Equips the hero if this weapon is stronger, returns `"Weapon found! Damage points: X"` |

### `Spell`

| Member | Description |
|---|---|
| `Spell(String name, int damage, int manaCost)` | Constructor |
| `String getName()` | Returns the spell's name |
| `int getDamage()` | Returns the spell's damage |
| `int getManaCost()` | Returns the mana cost |
| `String collect(Hero hero)` | The hero learns the spell if it is stronger, returns `"Spell found! Damage points: X, Mana cost: Y"` |

### `HealthPotion`

| Member | Description |
|---|---|
| `HealthPotion(int healingPoints)` | Constructor |
| `int heal()` | Returns the health points this potion restores |
| `String collect(Hero hero)` | Restores health (capped at max; does nothing if hero is dead), returns `"Health potion found! X health points added to your hero!"` where X is the potion's value |

### `ManaPotion`

| Member | Description |
|---|---|
| `ManaPotion(int manaPoints)` | Constructor |
| `int heal()` | Returns the mana points this potion restores |
| `String collect(Hero hero)` | Restores mana (capped at max), returns `"Mana potion found! X mana points added to your hero!"` where X is the potion's value |

---

## The Dungeon

The dungeon is a 2D map that looks like this:

```
S . ## . . . . . T
#T## . . ### .
# . ###E###E
# . E . . . ### .
###T#####G
```

Cell legend:

| Symbol | Meaning |
|---|---|
| `S` | Starting position |
| `H` | Hero's current position |
| `#` | Obstacle (impassable) |
| `T` | Treasure |
| `.` | Open path |
| `E` | Enemy |
| `G` | Exit |

### `Direction` enum

Create an enum `Direction` with values: `LEFT`, `UP`, `DOWN`, `RIGHT`.

### `GameEngine`

| Member | Description |
|---|---|
| `GameEngine(char[][] map, Hero hero, Enemy[] enemies, Treasure[] treasures)` | Constructor |
| `char[][] getMap()` | Returns the current state of the map |
| `Hero getHero()` | Returns the hero |
| `Position getHeroPosition()` | Returns the hero's current position |
| `String makeMove(Direction direction)` | Moves the hero in the given direction — see move logic below |

#### Move logic

| Situation | Outcome |
|---|---|
| Target cell is open | Hero moves. Previous cell becomes `'.'`. Returns `"You moved successfully to the next position."` |
| Target cell is an obstacle | Hero stays put. Returns `"Wrong move. There is an obstacle and you cannot bypass it."` |
| Target cell is a treasure | Treasure is removed from map. First uncollected treasure from the array is collected by the hero. Hero moves. Returns the treasure's `collect()` message |
| Target cell is an enemy | A battle begins with the first undefeated enemy from the array. Hero attacks first; if the enemy survives, it attacks back. Repeat until one side reaches 0 health. On hero victory: hero takes the enemy's cell, previous cell becomes `'.'`, returns `"Enemy died."`. On hero defeat: returns `"Hero is dead! Game over!"` |
| Target cell is the exit | Game ends. Returns `"You have successfully passed through the dungeon. Congrats!"` |
| Unknown command | Returns `"Unknown command entered."` |

---

## Package Structure

| Class / Interface | Package |
|---|---|
| `Actor`, `Enemy`, `Hero`, `Position` | `bg.sofia.uni.fmi.mjt.dungeon.actor` |
| `Treasure`, `HealthPotion`, `ManaPotion`, `Spell`, `Weapon` | `bg.sofia.uni.fmi.mjt.dungeon.treasure` |
| `GameEngine`, `Direction` | `bg.sofia.uni.fmi.mjt.dungeon` |

Any additional interfaces or classes you introduce may go in the package of your choice.

> **Clean code note:** A good practice is for a method not to mutate objects it received from the outside world.
