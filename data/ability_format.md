#Ability Formatting

This can get pretty hard and unreadable, so treat this as your reference guide.

```SingleAbility(animation, name, desc, energyCost, cooldown, xRange, yRange, damage, enemyTarget, friendTarget)``` 
(10 params)

```AOEAbility(animation, name, desc, energyCost, cooldown, xRange, yRange, xAOE, yAOE, damage, enemyTarget, friendTarget)``` 
(12 params)

```BasicMoveAbility(name, desc, energyCost, cooldown, moves)```
(5 params)

```CombinationAbility(animation, name, desc, energyCost, cooldown, xRange, yRange, damage, enemyTarget, friendTarget)```
(10 params)

```StarAbility(animation, name, desc, energyCost, cooldown, xRange, yRange, damage, enemyTarget, friendTarget)```
(10 params)

```StatusAbility(effect, animation, name, desc, energyCost, cooldown, xRange, yRange, enemyTarget, friendTarget)```
(10 params)

```AbilityPair(animation, name, desc, energyCost, cooldown, firstAbility, secondAbility)```
(7 params)  
NOTE: You MUST separate the initial params, the first ability, and the second ability with a triple tilde (~~~)
Due to how nesting works, the first ability of an ability pair can NOT be another ability pair.
```SpearAbility(animation, name, desc, energyCost, cooldown, damage)```
(6 params)
