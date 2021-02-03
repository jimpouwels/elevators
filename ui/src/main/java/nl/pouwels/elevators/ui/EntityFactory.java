package nl.pouwels.elevators.ui;

import nl.pouwels.elevators.Button;
import nl.pouwels.elevators.Door;
import nl.pouwels.gameengine.Engine;

public class EntityFactory {

    public static Door createDoor(Engine engine, int offsetX, int offsetY) {
        DoorUiComponent doorUiComponent = new DoorUiComponent(engine, offsetX, offsetY);
//        FloorButtonUiComponent floorButtonUiComponent = new FloorButtonUiComponent(engine, offsetX, offsetY);
        UiComponentRegistry.register(doorUiComponent);
//        UiComponentRegistry.register(floorButtonUiComponent);
        return new Door(doorUiComponent, new Button(null));
    }
}
