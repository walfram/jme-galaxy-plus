### Orders

- ChangeDiplomacy – Race foo declares WAR/PEACE to another Race 
- SplitShipGroup – splits existing ship group
- DefineShipType - creates new ship type
- GiftShipGroup - changes ownership of ship group
- DefineScience - creates/updates science
- JoinShipGroups - joins ship groups
- BreakShipGroup - breaks ship group, adds industry and material to planet
- LoadShipGroup - loads ship group with cargo
- NamePlanet - changes planet name
- StrtProduction - starts (or changes current production) of a planet
- SendShipGroup - sends ship group to another planet
- UnloadShipGroup - unloads ship group cargo to planet
- UpgradeShipGroup - upgrades ship group

### Phases

- QuitBySleep - when player does not send orders for N turns, N comes from config
- QuitByNoPlanets - player does not own any planet
- ShipTransfer - transfers gift ships
- JoinShipGroups - joins ship groups (multiple times per turn, see order below)
- CombatPhase - ships shoot each other where possible
- LoadCargo - ship groups with load card order do load cargo
- MovePhase - moves ships in hyperspace
- UpgradePhase - if ships were set to upgrade
- ProductionPhase - executes production (maybe upgrade phase not needed?)
- UnloadPhase - ship groups unload cargo
- VictoryCheck - checks win conditions
