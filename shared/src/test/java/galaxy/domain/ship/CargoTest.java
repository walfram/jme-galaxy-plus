package galaxy.domain.ship;

import galaxy.domain.technology.CargoTechnology;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CargoTest {

	@Test
	void test_effective_cargo_capacity_1() {
		Cargo cargo = new Cargo(new CargoTemplate(1), new CargoTechnology());
		assertEquals(1.05, cargo.cargoCapacity());
	}

	@Test
	void test_effective_cargo_capacity_5() {
		Cargo cargo = new Cargo(new CargoTemplate(5), new CargoTechnology());
		assertEquals(6.25, cargo.cargoCapacity());
	}

	@Test
	void test_effective_cargo_capacity_10() {
		Cargo cargo = new Cargo(new CargoTemplate(10), new CargoTechnology());
		assertEquals(15.0, cargo.cargoCapacity());
	}

	@Test
	void test_effective_cargo_capacity_50() {
		Cargo cargo = new Cargo(new CargoTemplate(50), new CargoTechnology());
		assertEquals(175.0, cargo.cargoCapacity());
	}

	@Test
	void test_effective_cargo_capacity_100() {
		Cargo cargo = new Cargo(new CargoTemplate(100), new CargoTechnology());
		assertEquals(600.0, cargo.cargoCapacity());
	}

	@Test
	void test_effective_cargo_capacity_39_57() {
		Cargo cargo = new Cargo(new CargoTemplate(39.57), new CargoTechnology());
		assertEquals(117.85924500000002, cargo.cargoCapacity());
	}

}
