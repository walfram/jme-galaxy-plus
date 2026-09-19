### Ship group stuff

Ship group has cargo capacity which depends on ship type's cargo bay size and current cargo tech level.
Formula for single ship is `cargoTechLevel * (cargoBaySize + (cargoBaySize * cargoBaySize) / 20.0)`. 
For ship group this is multiplied by group size. 
Ship group transports `total cargo quantity` but all calculations are `per ship`

#### ShipGroup properties
- ShipType
- TechLevels
- owner
- planet

#### ShipGroup "mutable" properties
- destination (Planet)
- coordinates: initial is current planet, while hyperspace then updated using current speed

#### states:
- in orbit - created/default
    - launched, upgrade, transfer
- launched
    - hyperspace    
- in hyperspace
  - in hyperspace, in orbit
- upgrading
    - in orbit
- transferring
    - in orbit

#### actions:
- cargo loading
- cargo unloading
- split
- join

Ship group destruction is special case - ship group is removed from contet
