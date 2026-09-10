package galaxy.order;

import java.util.List;

public interface Orders {

	int size();

	List<ChangeDiplomacy> changeDiplomacy();
	List<SplitShipGroup> splitShipGroups();
	List<DefineShipType> defineShipTypes();
	List<GiftShipGroup> giftShipGroups();
	List<DefineScience> defineSciences();
	List<JoinShipGroupsOrder> joinShipGroupOrders();
	List<BreakShipGroup> breakShipGroups();
	List<LoadShipGroup> loadShipGroups();
	List<NamePlanet> namePlanets();
	List<StartProduction> startProductions();
	List<SendShipGroup> sendShipGroups();
	List<UnloadShipGroup> unloadShipGroups();

}
