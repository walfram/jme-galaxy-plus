### Orders

- ChangeDiplomacy – Race declares WAR/PEACE to another Race 
- SplitShipGroup – splits existing ship group
- DefineShipType - creates new ship type
- GiftShipGroup - changes ownership of ship group
- DefineScience - creates/updates science
- JoinShipGroups - joins ship groups
- BreakShipGroup - breaks ship group, adds industry and material to planet
- LoadShipGroup - loads ship group with cargo
- NamePlanet - changes planet name
- StartProduction - starts (or changes current production) of a planet: capital, materials, science research, tech research, build ships, upgrade ships 
- SendShipGroup - sends ship group to another planet
- UnloadShipGroup - unloads ship group cargo to planet

### Phases

- QuitBySleep - when player does not send orders for N turns, N comes from config
- QuitByNoPlanets - player does not own any planet
- ShipTransfer - transfers ships between races
- JoinShipGroups - joins ship groups (multiple times per turn, see order below)
- CombatPhase - ships shoot each other where possible, ship group must be in state "in_orbit"
- BombingPhase - ships shoot at planet
- LoadCargo - ship groups with load card order do load cargo
- MovePhase - moves ships in hyperspace
- UpgradePhase - if ships were set to upgrade (deprecated, upgrade will be performed in production phase)
- ProductionPhase - executes production (maybe upgrade phase not needed?)
- UnloadPhase - ship groups unload cargo
- VictoryCheck - checks win conditions

#### Phase order:

QuitBySleep, QuitByNoPlanets, ShipTransfer, JoinShipGroups, CombatPhase, LoadCargo, MovePhase, JoinShipGroups, 
CombatPhase, BombingPhase, ProductionPhase, UnloadPhase, JoinShipGroups, VictoryCheck 

### Production
- Materials
- Capital
- Science
- Tech
- Ships
- Upgrade
