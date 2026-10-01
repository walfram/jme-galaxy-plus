### examples

- cap transport: 
  - 62.1 0 0 0 36.9
  - 57 1 2 3.1 36.9

- col transport: 
  - 37 0 0 0 12.5
  - 17.42 0 0 0 7.33

- colonizer:
  - 8.9 0 0 0 1 
  - 8.9 1 1.1 0 1 
  - 14.3 1 1 3.5 1

- cruiser:
  - 49.5 2 20 18.5 1

- unbreakable:
  - 0 1 10 188 0 (stationary) 
  - 99 1 5 93 1

- light perf (many guns with caliber 1)
  - 49.5 58 1 19 1
  - 99 147 1 24 1 

- heavy perf (many guns with caliber 1.5 - 4.0)
  - 49.5 29 2 18.5 1

- terrorist:
  - 1 1 1 0 0
  - 3 1 1.02 1.19 0

- turret; light, caliber 6..8; heavy, caliber 10..12:
  - 49.5 7 7.37 19 1

### ship type stuff

Ship type is defined by
- name
- engine size
- weapons: number of guns and gun caliber
- shield size
- cargo bay size

Ship group = "ship type" + "tech level" + "group size"

### Shared properties

Speed, attack, defense - all ships in group have same value

Cargo capacity is defined by cargo bay size. Cargo is loaded to group by splitting loaded amount
evenly between ships. One group - one cargo type.

### formulas 

speed = 20.0 * tech.engines * type.engines / ship.mass

ship.mass = type.mass + cargo.mass

