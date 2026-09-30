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

