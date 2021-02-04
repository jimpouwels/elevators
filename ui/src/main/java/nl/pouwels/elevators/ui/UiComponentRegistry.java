package nl.pouwels.elevators.ui;

import nl.pouwels.gameengine.ClickableUiComponent;
import nl.pouwels.gameengine.UiComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UiComponentRegistry {

    private static final List<UiComponent> uiComponents = new ArrayList<>();

    public static void register(UiComponent uiComponent) {
        uiComponents.add(uiComponent);
    }

    public static List<ClickableUiComponent> getClickableUiComponents() {
        return uiComponents
                .stream()
                .filter(uiComponent -> uiComponent instanceof ClickableUiComponent)
                .map(uiComponent -> (ClickableUiComponent) uiComponent)
                .collect(Collectors.toList());
    }
}
